package com.sloydev.pkweakness.core.infrastructure;


import android.content.Context;
import android.graphics.Color;

import com.google.gson.Gson;
import com.sloydev.pkweakness.core.model.Pokemon;
import com.sloydev.pkweakness.core.model.PokemonCollection;
import com.sloydev.pkweakness.core.model.PokemonType;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import timber.log.Timber;

public class JsonPokemonCollection implements PokemonCollection {

    private final Context context;
    private final Gson gson;

    private List<Pokemon> pokemons;
    private Map<String, PokemonType> types;

    public JsonPokemonCollection(Context context, Gson gson) {
        this.context = context;
        this.gson = gson;
    }

    @Override
    public List<Pokemon> getAll() {
        initPokemons();
        return pokemons;
    }

    private void initPokemons() {
        if (pokemons == null) {
            initTypes();
            try {
                InputStream inputStream = context.getAssets().open("pokemons.json");
                Reader reader = new InputStreamReader(inputStream, "UTF-8");
                PokemonJsonModel[] parsedList = gson.fromJson(reader, PokemonJsonModel[].class);
                pokemons = PokemonJsonModelMapper.map(Arrays.asList(parsedList), types);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Timber.d("Parsed %d pokemons", pokemons.size());
        }
    }

    private void initTypes() {
        if (types == null) {
            try {
                InputStream inputStream = context.getAssets().open("types.json");
                Reader reader = new InputStreamReader(inputStream, "UTF-8");
                TypeJsonModel[] parsedList = gson.fromJson(reader, TypeJsonModel[].class);
                types = new HashMap<>(parsedList.length);
                for (TypeJsonModel type : parsedList) {
                    Timber.d("type: %s", type.toString());
                    types.put(type.id, new PokemonType(type.name, Color.parseColor(type.color)));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Pokemon getByNumber(int number) {
        initPokemons();
        return pokemons.get(number - 1);
    }
}
