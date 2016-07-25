package com.sloydev.pkweakness.core.model;

import java.util.List;

public interface PokemonCollection {
    List<Pokemon> getAll();

    Pokemon getByNumber(int number);
}
