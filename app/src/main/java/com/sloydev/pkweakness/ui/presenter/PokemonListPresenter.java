package com.sloydev.pkweakness.ui.presenter;


import com.sloydev.pkweakness.core.action.GetAllPokemons;
import com.sloydev.pkweakness.core.action.SearchPokemon;
import com.sloydev.pkweakness.core.model.Pokemon;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.sloydev.pkweakness.ui.infrastructure.RxUtils.genericOnError;

public class PokemonListPresenter {

    private final PokemonListView view;
    private final GetAllPokemons getAllPokemons;
    private final SearchPokemon searchPokemon;

    public PokemonListPresenter(PokemonListView view,
                                GetAllPokemons getAllPokemons,
                                SearchPokemon searchPokemon) {
        this.view = view;
        this.getAllPokemons = getAllPokemons;
        this.searchPokemon = searchPokemon;
    }

    public void initialize() {
        obtainAllPokemons();
    }

    public void onSearchInputChanged(String query) {
        if (query.isEmpty()) {
            obtainAllPokemons();
        } else {
            search(query);
        }
    }

    private void obtainAllPokemons() {
        getAllPokemons.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::show, genericOnError());
    }

    private void search(String query) {
        searchPokemon.search(query)
                .subscribe(this::showInView, genericOnError());
    }

    private void showInView(List<Pokemon> pokemons) {
        if (pokemons.isEmpty()) {
            view.showEmpty();
        } else {
            view.show(pokemons);
        }
    }

    public interface PokemonListView {
        void show(List<Pokemon> pokemons);

        void showEmpty();
    }

}
