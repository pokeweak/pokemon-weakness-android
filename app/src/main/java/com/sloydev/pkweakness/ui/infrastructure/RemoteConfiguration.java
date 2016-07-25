package com.sloydev.pkweakness.ui.infrastructure;


public interface RemoteConfiguration {
    void init();

    void update();

    String getString(String key, String defaultValue);

}
