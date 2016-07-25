package com.sloydev.pkweakness.core.model;


import java.util.List;

import rx.Observable;

public class SearchService {
    private final PokemonCollection pokemonCollection;

    public SearchService(PokemonCollection pokemonCollection) {
        this.pokemonCollection = pokemonCollection;
    }

    public Observable<List<Pokemon>> search(String query) {
        return Observable.fromCallable(pokemonCollection::getAll)
                .flatMap(Observable::from)
                .filter(pokemon -> pokemon.name().toLowerCase().contains(query.toLowerCase()) || pokemon.displayNumber().contains(query))
                .toList();
    }

}
