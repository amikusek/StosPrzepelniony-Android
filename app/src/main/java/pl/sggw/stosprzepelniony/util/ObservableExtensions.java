package pl.sggw.stosprzepelniony.util;

import io.reactivex.CompletableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Consumer;

public class ObservableExtensions {

    public static <T> ObservableTransformer<T, T> withObservableRetryErrorLogic(Consumer<Throwable> onErrorAction) {
        return stream ->
                stream
                        .doOnError(onErrorAction)
                        .retry();
    }

    public static CompletableTransformer withCompletableRetryErrorLogic(Consumer<Throwable> onErrorAction) {
        return stream ->
                stream
                        .doOnError(onErrorAction)
                        .retry();
    }
}
