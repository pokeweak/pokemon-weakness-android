package com.sloydev.pkweakness.ui.view;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;

import com.google.gson.Gson;
import com.sloydev.pkweakness.BuildConfig;
import com.sloydev.pkweakness.core.infrastructure.JsonPokemonCollection;
import com.sloydev.pkweakness.core.model.Pokemon;
import com.sloydev.pkweakness.core.model.PokemonCollection;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class GeneratePaletteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PokemonCollection pokemonCollection = new JsonPokemonCollection(this, new Gson());

        List<Pokemon> pokemons = pokemonCollection.getAll();
        List<PokemonJson> pokemonJsons = new ArrayList<>(pokemons.size());
        for (Pokemon pokemon : pokemons) {
            Timber.d("Generating colorHex for %s...", pokemon.name());
            int resId = getResources().getIdentifier("pk_" + pokemon.number(), "drawable", BuildConfig.APPLICATION_ID);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
            Palette palette = Palette.from(bitmap).generate();
            bitmap.recycle();

            int color = 0;
            Palette.Swatch swatch = palette.getMutedSwatch();
            if (swatch == null) {
                swatch = palette.getVibrantSwatch();
            }
            if (swatch != null) {
                color = swatch.getRgb();
            }

            String colorText = String.format("#%06X", 0xFFFFFF & color);
            Timber.d("Color selected %s", colorText);

            pokemonJsons.add(new PokemonJson(pokemon.number(), pokemon.name(), color, colorText));
        }

        Timber.d("¡¡¡Color generation done!!!");

        String json = new Gson().toJson(pokemonJsons);

        ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText(json)
                .startChooser();
    }

    private static class PokemonJson {
        final int number;
        final String name;
        final int color;
        final String colorText;

        public PokemonJson(int number, String name, int color, String colorText) {
            this.number = number;
            this.name = name;
            this.color = color;
            this.colorText = colorText;
        }
    }

}
