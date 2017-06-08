package pl.sggw.stosprzepelniony.exception;

import android.content.Context;

public abstract class BaseException extends Exception {

    public abstract String getUserMessage(Context context);
}
