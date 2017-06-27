package pl.sggw.stosprzepelniony.viper.login;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.mateuszkoslacz.moviper.base.view.activity.autoinject.passive.butterknife.ViperButterKnifePassiveActivity;
import com.mateuszkoslacz.moviper.iface.presenter.ViperPresenter;

import pl.sggw.stosprzepelniony.R;
import pl.sggw.stosprzepelniony.data.entity.LoginBundle;
import pl.sggw.stosprzepelniony.exception.BaseException;
import pl.sggw.stosprzepelniony.viper.main.MainActivity;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;

public class LoginActivity
        extends ViperButterKnifePassiveActivity
        <LoginContract.View>
        implements LoginContract.View {

    @BindView(R.id.btn_login)
    Button loginButton;
    @BindView(R.id.txt_forgot_password)
    TextView forgotPasswordTextView;
    @BindView(R.id.txt_sign_up)
    TextView signUpTextView;
    @BindView(R.id.login_button_container)
    FrameLayout loginButtonContainer;
    @BindView(R.id.view_loading)
    ProgressBar loadingView;
    @BindView(R.id.field_email)
    EditText emailField;
    @BindView(R.id.field_password)
    EditText passwordField;

    public static void start(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    @Override
    public Observable<LoginBundle> getLoginClicks() {
        return RxView
                .clicks(loginButton)
                .map(v ->
                        new LoginBundle(
                                emailField.getText().toString(),
                                passwordField.getText().toString()));
    }

    @Override
    public Observable<Object> getForgottenPasswordClicks() {
        return RxView.clicks(forgotPasswordTextView);
    }

    @Override
    public Observable<Object> getSignUpClicks() {
        return RxView.clicks(signUpTextView);
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
    public void showLoading() {
        TransitionManager.beginDelayedTransition(loginButtonContainer);
        loginButton.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        TransitionManager.beginDelayedTransition(loginButtonContainer);
        loadingView.setVisibility(View.GONE);
        loginButton.setVisibility(View.VISIBLE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @NonNull
    @Override
    public ViperPresenter<LoginContract.View> createPresenter() {
        return new LoginPresenter();
    }
}
