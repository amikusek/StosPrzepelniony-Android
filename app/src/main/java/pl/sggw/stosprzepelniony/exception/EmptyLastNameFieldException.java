package pl.sggw.stosprzepelniony.exception;

import android.content.Context;

import pl.sggw.stosprzepelniony.R;

public class EmptyLastNameFieldException extends BaseException {
    @Override
    public String getUserMessage(Context context) {
        return context.getResources().getString(R.string.error_empty_last_name);
    }
}
