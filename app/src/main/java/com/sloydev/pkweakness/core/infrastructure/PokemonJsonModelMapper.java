package com.sloydev.pkweakness.core.infrastructure;


import android.graphics.Color;

import com.sloydev.pkweakness.core.model.Pokemon;
import com.sloydev.pkweakness.core.model.PokemonType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PokemonJsonModelMapper {


    public static List<Pokemon> map(Collection<PokemonJsonModel> inputList, Map<String, PokemonType> types) {
        List<Pokemon> outputList = new ArrayList<>(inputList.size());
        for (PokemonJsonModel inputItem : inputList) {
            outputList.add(map(inputItem, types));
        }
        return outputList;

    }

    public static Pokemon map(PokemonJsonModel input, Map<String, PokemonType> types) {
        return Pokemon.create()
                .number(input.number)
                .name(capitalize(input.name))
                .colorArgb(colorWithAlpha(Color.parseColor(input.colorHex)))
                .weaknesses(mapWeaknesses(input.weaknesses, types))
                .build();
    }

    private static List<PokemonType> mapWeaknesses(String[] input, Map<String, PokemonType> types) {
        List<PokemonType> output = new ArrayList<>(input.length);
        for (String weakness : input) {
            output.add(types.get(weakness));
        }
        return output;
    }

    private static String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

    private static int colorWithAlpha(int color) {
        return (color & 0x00FFFFFF) | 0xCC000000; // 80% alpha = #CC
    }
}
