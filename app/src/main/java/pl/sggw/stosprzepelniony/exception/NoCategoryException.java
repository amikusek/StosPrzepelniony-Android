package pl.sggw.stosprzepelniony.exception;

import android.content.Context;

import pl.sggw.stosprzepelniony.R;

public class NoCategoryException extends BaseException {
    @Override
    public String getUserMessage(Context context) {
        return context.getResources().getString(R.string.error_no_category);
    }
}
