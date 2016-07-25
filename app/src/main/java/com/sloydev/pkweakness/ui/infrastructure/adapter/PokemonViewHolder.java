package com.sloydev.pkweakness.ui.infrastructure.adapter;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sloydev.pkweakness.R;
import com.sloydev.pkweakness.core.model.Pokemon;
import com.sloydev.pkweakness.ui.infrastructure.PokemonImageLoader;

public class PokemonViewHolder extends RecyclerView.ViewHolder {

    private final PokemonImageLoader pokemonImageLoader;

    private TextView number;
    private TextView name;
    private ImageView image;
    private View background;

    public static PokemonViewHolder create(LayoutInflater inflater, ViewGroup parent, PokemonImageLoader pokemonImageLoader) {
        return new PokemonViewHolder(inflater.inflate(R.layout.item_pokemon, parent, false), pokemonImageLoader);
    }

    private PokemonViewHolder(View itemView, PokemonImageLoader pokemonImageLoader) {
        super(itemView);
        number = (TextView) itemView.findViewById(R.id.pokemon_number);
        name = (TextView) itemView.findViewById(R.id.pokemon_name);
        image = (ImageView) itemView.findViewById(R.id.pokemon_image);
        background = itemView.findViewById(R.id.pokemon_background);
        this.pokemonImageLoader = pokemonImageLoader;
    }

    public void bind(final Pokemon pokemon, final PokemonAdapter.OnPokemonClickListener onPokemonClickListener) {
        number.setText(pokemon.displayNumber());
        name.setText(pokemon.name());
        ((GradientDrawable) background.getBackground()).setColor(pokemon.colorArgb());
        pokemonImageLoader.loadPokemonImage(pokemon, image);
        itemView.setOnClickListener(v -> onPokemonClickListener.onPokemonClick(pokemon, itemView));
    }
}
