package com.sloydev.pkweakness.ui.infrastructure;


import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import timber.log.Timber;

public class KeyboardUtils {

    /**
     * Hides the keyboard. BEWARE THE ALIEN! It don't prevent it from popping up.
     */
    public static void hideKeyboard(View view) {
        try {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (RuntimeException e) {
            Timber.w(e, "Error on hideKeyboard()");
        }
    }
}
