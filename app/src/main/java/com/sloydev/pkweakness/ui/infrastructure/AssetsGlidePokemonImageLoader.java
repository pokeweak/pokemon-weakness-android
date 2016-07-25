package com.sloydev.pkweakness.ui.infrastructure;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sloydev.pkweakness.BuildConfig;
import com.sloydev.pkweakness.core.model.Pokemon;

public class AssetsGlidePokemonImageLoader implements PokemonImageLoader {

    @Override
    public void loadPokemonImage(Pokemon pokemon, final ImageView imageView) {
        int resId = imageView.getContext().getResources().getIdentifier("pk_" + pokemon.number(), "drawable", BuildConfig.APPLICATION_ID);
        Glide.with(imageView.getContext())
                .load(resId)
                .crossFade(100)
                .into(imageView);
    }
}
