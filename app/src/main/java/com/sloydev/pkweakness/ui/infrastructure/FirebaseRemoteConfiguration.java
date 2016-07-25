package com.sloydev.pkweakness.ui.infrastructure;

import android.os.Handler;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.sloydev.pkweakness.BuildConfig;
import com.sloydev.pkweakness.R;

import java.util.concurrent.TimeUnit;

import timber.log.Timber;

public class FirebaseRemoteConfiguration implements RemoteConfiguration {

    private static final long FIREBASE_CACHE_TTL_SECONDS = BuildConfig.DEBUG ? 0 : TimeUnit.DAYS.toSeconds(1);
    public static final String DEFAULT_INDICATOR = "default";

    private final FirebaseRemoteConfig firebaseRemoteConfig;

    public FirebaseRemoteConfiguration(FirebaseRemoteConfig firebaseRemoteConfig) {
        this.firebaseRemoteConfig = firebaseRemoteConfig;
    }

    @Override
    public void init() {
        firebaseRemoteConfig.setDefaults(R.xml.firebase_remote_config_defaults);
        if (BuildConfig.DEBUG) {
            enableDeveloperMode();
        }
    }

    private void enableDeveloperMode() {
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(true)
                .build();
        firebaseRemoteConfig.setConfigSettings(configSettings);
    }

    @Override
    public void update() {
        new Handler().postDelayed(() ->
                firebaseRemoteConfig.fetch(FIREBASE_CACHE_TTL_SECONDS)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Timber.i("Firebase fetch succeeded");
                                firebaseRemoteConfig.activateFetched();
                            } else {
                                Timber.w(task.getException(), "Firebase fetch failed");
                            }
                        }), 0);
    }

    @Override
    public String getString(String key, String defaultValue) {
        String value = firebaseRemoteConfig.getString(key);
        Timber.d("Firebase value: %s", value);
        if (DEFAULT_INDICATOR.equals(value)) {
            return defaultValue;
        } else {
            return value;
        }
    }
}
