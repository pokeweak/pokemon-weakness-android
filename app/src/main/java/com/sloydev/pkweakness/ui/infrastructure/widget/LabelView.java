package com.sloydev.pkweakness.ui.infrastructure.widget;


import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sloydev.pkweakness.R;

public class LabelView extends TextView {


    public static LabelView create(ViewGroup parent) {
        return (LabelView) LayoutInflater.from(parent.getContext()).inflate(R.layout.label_pokemon_type, parent, false);
    }

    public LabelView(Context context) {
        super(context);
    }

    public LabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void setLabelColor(@ColorInt int color) {
        ((GradientDrawable) getBackground()).setColor(color);
    }

}
