package com.sloydev.pkweakness.core.action;


import com.sloydev.pkweakness.core.model.Pokemon;
import com.sloydev.pkweakness.core.model.PokemonCollection;

import java.util.List;

import rx.Observable;

public class GetAllPokemons {

    private final PokemonCollection pokemonCollection;

    public GetAllPokemons(PokemonCollection pokemonCollection) {
        this.pokemonCollection = pokemonCollection;
    }

    public Observable<List<Pokemon>> getAll() {
        return Observable.fromCallable(pokemonCollection::getAll);
    }
}
