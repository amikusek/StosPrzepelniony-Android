package pl.sggw.stosprzepelniony.viper.reset_password;

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
import pl.sggw.stosprzepelniony.data.entity.ResetPasswordBundle;
import pl.sggw.stosprzepelniony.exception.BaseException;

public class ResetPasswordActivity
        extends ViperButterKnifePassiveActivity
        <ResetPasswordContract.View>
        implements ResetPasswordContract.View {

    @BindView(R.id.field_email)
    EditText emailField;
    @BindView(R.id.btn_reset_password)
    Button resetPasswordButton;
    @BindView(R.id.reset_password_button_container)
    FrameLayout resetPasswordButtonContainer;
    @BindView(R.id.view_loading)
    ProgressBar loadingView;
    @BindView(R.id.btn_close)
    ImageButton closeActivityButton;

    @Override
    public Observable<ResetPasswordBundle> getResetPasswordClicks() {
        return RxView
                .clicks(resetPasswordButton)
                .map(v -> new ResetPasswordBundle(emailField.getText().toString()));
    }

    @Override
    public Observable<Object> getCloseActivityClicks() {
        return RxView.clicks(closeActivityButton);
    }

    @Override
    public void showLoading() {
        TransitionManager.beginDelayedTransition(resetPasswordButtonContainer);
        resetPasswordButton.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        TransitionManager.beginDelayedTransition(resetPasswordButtonContainer);
        loadingView.setVisibility(View.GONE);
        resetPasswordButton.setVisibility(View.VISIBLE);
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
    public void showResetPasswordSuccess() {
        Toasty.success(this, getString(R.string.register_success), Toast.LENGTH_LONG, true).show();
    }


    @NonNull
    @Override
    public ViperPresenter<ResetPasswordContract.View> createPresenter() {
        return new ResetPasswordPresenter();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_reset_password;
    }
}
