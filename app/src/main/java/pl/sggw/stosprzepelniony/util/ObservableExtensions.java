package pl.sggw.stosprzepelniony.util;

import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Consumer;

public class ObservableExtensions {

    public static <T> ObservableTransformer<T, T> withRetryErrorLogic(Consumer<Throwable> onErrorAction) {
        return stream ->
                stream
                        .doOnError(onErrorAction)
                        .retry();
    }
}
