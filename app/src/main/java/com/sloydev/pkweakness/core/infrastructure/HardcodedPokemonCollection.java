package com.sloydev.pkweakness.core.infrastructure;


import android.graphics.Color;
import android.support.annotation.NonNull;

import com.sloydev.pkweakness.core.model.Pokemon;
import com.sloydev.pkweakness.core.model.PokemonCollection;
import com.sloydev.pkweakness.core.model.PokemonType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HardcodedPokemonCollection implements PokemonCollection {

    @Override
    public List<Pokemon> getAll() {
        List<Pokemon> list = new ArrayList<>();
        list.addAll(getHardcodedList());
        list.addAll(getHardcodedList());
        return list;
    }

    @Override
    public Pokemon getByNumber(int number) {
        for (Pokemon pokemon : getHardcodedList()) {
            if (pokemon.number() == number) {
                return pokemon;
            }
        }
        throw new IllegalArgumentException("Pokemon not found");
    }

    @NonNull
    private List<Pokemon> getHardcodedList() {
        return Arrays.asList(
                Pokemon.create()
                        .number(1)
                        .name("Bulbasaur")
                        .colorArgb(Color.parseColor("#a6d5ba"))
                        .build(),
                Pokemon.create()
                        .number(2)
                        .name("Ivysaur")
                        .colorArgb(Color.parseColor("#a1cacc"))
                        .build(),
                Pokemon.create()
                        .number(3)
                        .name("Venasaur")
                        .colorArgb(Color.parseColor("#5a9498"))
                        .build(),

                Pokemon.create()
                        .number(4)
                        .name("Charmander")
                        .colorArgb(Color.parseColor("#ecbb92"))
                        .build(),
                Pokemon.create()
                        .number(5)
                        .name("Charmaleon")
                        .colorArgb(Color.parseColor("#d47466"))
                        .build(),
                Pokemon.create()
                        .number(6)
                        .name("Charizard")
                        .colorArgb(Color.parseColor("#f2b678"))
                        .weaknesses(Arrays.asList(
                                new PokemonType("Rock", Color.parseColor("#a97746")),
                                new PokemonType("Electric", Color.parseColor("#edd247")),
                                new PokemonType("Water", Color.parseColor("#6fabf0"))
                        ))
                        .build(),

                Pokemon.create()
                        .number(7)
                        .name("Squirtle")
                        .colorArgb(Color.parseColor("#77abc0"))
                        .build(),
                Pokemon.create()
                        .number(8)
                        .name("Wartortle")
                        .colorArgb(Color.parseColor("#bac7e7"))
                        .build(),
                Pokemon.create()
                        .number(9)
                        .name("Blastoise")
                        .colorArgb(Color.parseColor("#7994c3"))
                        .build(),

                Pokemon.create()
                        .number(10)
                        .name("Caterpie")
                        .colorArgb(Color.parseColor("#85ad6f"))
                        .build(),
                Pokemon.create()
                        .number(11)
                        .name("Methapod")
                        .colorArgb(Color.parseColor("#90bb61"))
                        .build(),
                Pokemon.create()
                        .number(12)
                        .name("Buterflee")
                        .colorArgb(Color.parseColor("#6e6987"))
                        .build()

        );
    }
}
