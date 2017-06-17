package pl.sggw.stosprzepelniony.viper.register;

import android.support.annotation.NonNull;
import android.support.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.mateuszkoslacz.moviper.base.view.activity.autoinject.passive.butterknife.ViperButterKnifePassiveActivity;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import pl.sggw.stosprzepelniony.R;
import pl.sggw.stosprzepelniony.data.entity.RegisterBundle;
import pl.sggw.stosprzepelniony.exception.BaseException;


public class RegisterActivity
        extends ViperButterKnifePassiveActivity<RegisterContract.View>
        implements RegisterContract.View {

    @BindView(R.id.field_email)
    EditText emailField;
    @BindView(R.id.field_first_name)
    EditText firstNameField;
    @BindView(R.id.field_last_name)
    EditText lastNameField;
    @BindView(R.id.field_password)
    EditText passwordField;
    @BindView(R.id.field_password_confirmation)
    EditText passwordConfirmationField;
    @BindView(R.id.btn_register)
    Button registerButton;
    @BindView(R.id.register_button_container)
    FrameLayout registerButtonContainer;
    @BindView(R.id.view_loading)
    ProgressBar loadingView;
    @BindView(R.id.btn_close)
    ImageButton closeActivityButton;

    @Override
    public Observable<RegisterBundle> getRegisterClicks() {
        return RxView
                .clicks(registerButton)
                .map(v ->
                        new RegisterBundle(
                                emailField.getText().toString(),
                                firstNameField.getText().toString(),
                                lastNameField.getText().toString(),
                                passwordField.getText().toString(),
                                passwordConfirmationField.getText().toString()
                        ));
    }

    @Override
    public Observable<Object> getCloseActivityClicks() {
        return RxView.clicks(closeActivityButton);
    }

    @Override
    public void showLoading() {
        TransitionManager.beginDelayedTransition(registerButtonContainer);
        registerButton.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        TransitionManager.beginDelayedTransition(registerButtonContainer);
        loadingView.setVisibility(View.GONE);
        registerButton.setVisibility(View.VISIBLE);
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
    public void showRegistrationSuccess() {
        Toasty.success(this, getString(R.string.register_success), Toast.LENGTH_LONG, true).show();
    }

    @NonNull
    @Override
    public ViperPresenter<RegisterContract.View> createPresenter() {
        return new RegisterPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }
}
