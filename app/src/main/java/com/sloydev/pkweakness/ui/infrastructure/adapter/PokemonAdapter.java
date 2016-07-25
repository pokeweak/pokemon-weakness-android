package com.sloydev.pkweakness.ui.infrastructure.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sloydev.pkweakness.core.model.Pokemon;
import com.sloydev.pkweakness.ui.infrastructure.PokemonImageLoader;

import java.util.Collections;
import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

    private final LayoutInflater layoutInflater;
    private final PokemonImageLoader pokemonImageLoader;
    private final OnPokemonClickListener onPokemonClickListener;

    private List<Pokemon> pokemons = Collections.emptyList();

    public PokemonAdapter(LayoutInflater layoutInflater, PokemonImageLoader pokemonImageLoader, OnPokemonClickListener onPokemonClickListener) {
        this.layoutInflater = layoutInflater;
        this.pokemonImageLoader = pokemonImageLoader;
        this.onPokemonClickListener = onPokemonClickListener;
    }

    public void setItems(List<Pokemon> items) {
        this.pokemons = items;
        notifyDataSetChanged();
    }

    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return PokemonViewHolder.create(layoutInflater, parent, pokemonImageLoader);
    }

    @Override
    public void onBindViewHolder(PokemonViewHolder holder, int position) {
        holder.bind(pokemons.get(position), onPokemonClickListener);
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public interface OnPokemonClickListener {
        void onPokemonClick(Pokemon pokemon, View itemView);
    }

}
