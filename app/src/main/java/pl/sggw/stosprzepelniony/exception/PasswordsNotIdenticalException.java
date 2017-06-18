package pl.sggw.stosprzepelniony.exception;

import android.content.Context;

import pl.sggw.stosprzepelniony.R;

public class PasswordsNotIdenticalException extends BaseException {
    @Override
    public String getUserMessage(Context context) {
        return context.getResources().getString(R.string.error_not_identical_passwords);
    }
}
