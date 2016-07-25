package com.sloydev.pkweakness.ui.infrastructure;


import android.widget.ImageView;

import com.sloydev.pkweakness.core.model.Pokemon;

public interface PokemonImageLoader {
    void loadPokemonImage(Pokemon pokemon, ImageView imageView);
}
