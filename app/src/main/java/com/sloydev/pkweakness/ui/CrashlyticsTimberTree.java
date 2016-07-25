package com.sloydev.pkweakness.ui;

import android.util.Log;

import com.crashlytics.android.Crashlytics;

import timber.log.Timber;

public class CrashlyticsTimberTree extends Timber.Tree {

    @Override
    protected void log(int priority, String tag, String message, Throwable throwable) {
        Crashlytics.log(priority + " | " + tag + " | " + message);
        if (priority >= Log.WARN && throwable != null) {
            Crashlytics.logException(throwable);
        }
    }
}
