package com.sloydev.pkweakness.ui.infrastructure;

import rx.functions.Action1;
import timber.log.Timber;

public class RxUtils {
    public static Action1<Throwable> genericOnError() {
        return throwable -> Timber.e(throwable, "Generic Rx error");
    }
}
