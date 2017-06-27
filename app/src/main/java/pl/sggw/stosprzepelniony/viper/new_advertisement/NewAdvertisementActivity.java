package pl.sggw.stosprzepelniony.viper.new_advertisement;


import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;
import com.mateuszkoslacz.moviper.base.view.activity.autoinject.passive.ViperAiPassiveActivity;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import pl.sggw.stosprzepelniony.R;
import pl.sggw.stosprzepelniony.data.entity.NewAdvertisementBundle;
import pl.sggw.stosprzepelniony.exception.BaseException;
import pl.sggw.stosprzepelniony.util.constant.Irrelevant;

public class NewAdvertisementActivity
        extends ViperAiPassiveActivity
        <NewAdvertisementContract.View>
        implements NewAdvertisementContract.View {

    @BindView(R.id.subject_field)
    EditText subjectField;
    @BindView(R.id.salary_field)
    EditText salaryField;
    @BindView(R.id.salary_type)
    RadioGroup salaryType;
    @BindView(R.id.content_field)
    EditText contentField;
    @BindView(R.id.btn_add_categories)
    ImageButton addCategoriesButton;
    @BindView(R.id.categories_flexbox)
    FlexboxLayout categoriesFlexboxLayout;

    private ProgressDialog progressDialog;
    private PublishSubject<Object> dismissButtonClicks = PublishSubject.create();
    private PublishSubject<NewAdvertisementBundle> addButtonClicks = PublishSubject.create();

    @Override
    public Observable<Object> getDismissButtonClicks() {
        return dismissButtonClicks;
    }

    @Override
    public Observable<NewAdvertisementBundle> getAddButtonClicks() {
        return addButtonClicks;
    }

    private void setUpToolbar() {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(getString(R.string.new_advertisement));
    }

    @Override
    protected void injectViews() {
        ButterKnife.bind(this);
        setUpToolbar();
    }

    @Override
    public void showLoading() {
        progressDialog = ProgressDialog.show(this, getString(R.string.please_wait), getString(R.string.adding_ad));
    }

    @Override
    public void showError(Throwable throwable) {
        hideLoading();
        if (throwable instanceof BaseException) {
            Toasty.error(this, ((BaseException) throwable).getUserMessage(this), Toast.LENGTH_LONG, true).show();
        } else {
            Toasty.error(this, getString(R.string.error_default), Toast.LENGTH_LONG, true).show();
        }
    }

    @Override
    public void showConfirmationInfo() {
        Toasty.success(this, getString(R.string.add_advertisement_confirmation), Toast.LENGTH_LONG, true).show();
    }

    private void hideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.new_advertisement_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            dismissButtonClicks.onNext(Irrelevant.EVENT);
        else if (item.getItemId() == R.id.action_add)
            addButtonClicks.onNext(new NewAdvertisementBundle(subjectField.getText().toString(),
                    Double.parseDouble(salaryField.getText().toString().isEmpty() ? "0" : salaryField.getText().toString()),
                    salaryType.getCheckedRadioButtonId(),
                    contentField.getText().toString(),
                    1));
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public ViperPresenter<NewAdvertisementContract.View> createPresenter() {
        return new NewAdvertisementPresenter();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_advertisement;
    }
}
