package com.sloydev.pkweakness.core.action;


import com.sloydev.pkweakness.core.model.Pokemon;
import com.sloydev.pkweakness.core.model.SearchService;

import java.util.List;

import rx.Observable;

public class SearchPokemon {

    private final SearchService searchService;

    public SearchPokemon(SearchService searchService) {
        this.searchService = searchService;
    }

    public Observable<List<Pokemon>> search(String query) {
        return searchService.search(query);
    }
}
