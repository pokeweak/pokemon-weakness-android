package com.sloydev.pkweakness.core.infrastructure;


import android.content.Context;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.gson.Gson;
import com.sloydev.pkweakness.core.action.GetAllPokemons;
import com.sloydev.pkweakness.core.action.GetPokemonByNumber;
import com.sloydev.pkweakness.core.action.SearchPokemon;
import com.sloydev.pkweakness.core.model.PokemonCollection;
import com.sloydev.pkweakness.core.model.SearchService;
import com.sloydev.pkweakness.ui.infrastructure.AssetsPicassoPokemonImageLoader;
import com.sloydev.pkweakness.ui.infrastructure.FirebaseRemoteConfiguration;
import com.sloydev.pkweakness.ui.infrastructure.PokemonImageLoader;
import com.sloydev.pkweakness.ui.infrastructure.RemoteConfiguration;
import com.sloydev.pkweakness.ui.presenter.PokemonListPresenter;

public class ServiceLocator {

    public static PokemonImageLoader providePokemonImageLoader() {
//        return new AssetsGlidePokemonImageLoader();
        return new AssetsPicassoPokemonImageLoader();
    }

    private static PokemonCollection providePokemonCollection(Context context) {
        return new JsonPokemonCollection(context, provideGson());
    }

    private static Gson provideGson() {
        return new Gson();
    }

    private static GetAllPokemons provideGetAllPokemons(Context context) {
        return new GetAllPokemons(providePokemonCollection(context));
    }

    public static GetPokemonByNumber provideGetPokemonByNumber(Context context) {
        return new GetPokemonByNumber(providePokemonCollection(context));
    }

    public static PokemonListPresenter providePokemonListPresenter(Context context, PokemonListPresenter.PokemonListView view) {
        return new PokemonListPresenter(view, provideGetAllPokemons(context), provideSearchPokemon(context));
    }

    private static SearchPokemon provideSearchPokemon(Context context) {
        return new SearchPokemon(provideSearchService(context));
    }

    private static SearchService provideSearchService(Context context) {
        return new SearchService(providePokemonCollection(context));
    }

    public static RemoteConfiguration provideRemoteConfiguration() {
        return new FirebaseRemoteConfiguration(FirebaseRemoteConfig.getInstance());
    }
}
