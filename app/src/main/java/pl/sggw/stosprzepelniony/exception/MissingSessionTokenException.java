package pl.sggw.stosprzepelniony.exception;

import android.content.Context;

public class MissingSessionTokenException extends  BaseException {

    @Override
    public String getUserMessage(Context context) {
        return "Missing session token";
    }
}
