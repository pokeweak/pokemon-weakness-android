package com.sloydev.pkweakness.core.action;


import com.sloydev.pkweakness.core.model.Pokemon;
import com.sloydev.pkweakness.core.model.PokemonCollection;

import rx.Observable;

public class GetPokemonByNumber {

    private final PokemonCollection pokemonCollection;

    public GetPokemonByNumber(PokemonCollection pokemonCollection) {
        this.pokemonCollection = pokemonCollection;
    }

    public Observable<Pokemon> get(int number) {
        return Observable.fromCallable(() -> pokemonCollection.getByNumber(number));
    }
}
