package com.sloydev.pkweakness.ui.infrastructure;

import android.widget.ImageView;

import com.sloydev.pkweakness.BuildConfig;
import com.sloydev.pkweakness.core.model.Pokemon;
import com.squareup.picasso.Picasso;

public class AssetsPicassoPokemonImageLoader implements PokemonImageLoader {

    @Override
    public void loadPokemonImage(Pokemon pokemon, final ImageView imageView) {
        Picasso.with(imageView.getContext())
                .load("file:///android_asset/pk_images/pk_"+pokemon.number()+".png")
                .into(imageView);
    }
}
