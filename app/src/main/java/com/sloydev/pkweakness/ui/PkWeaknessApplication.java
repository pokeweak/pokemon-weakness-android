package com.sloydev.pkweakness.ui;


import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.sloydev.pkweakness.BuildConfig;
import com.sloydev.pkweakness.core.infrastructure.ServiceLocator;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

public class PkWeaknessApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setupFabric();
        setupTimber();
        setupRemoteConfiguration();
    }

    private void setupRemoteConfiguration() {
        ServiceLocator.provideRemoteConfiguration().init();
    }

    private void setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashlyticsTimberTree());
        }
    }

    private void setupFabric() {
        if (!BuildConfig.DEBUG) {
            Fabric.with(this, new Crashlytics());
        }
    }
}
