package com.sloydev.pkweakness.core.model;


public class PokemonType {

    private final String name;
    private final int colorArgb;


    public PokemonType(String name, int colorArgb) {
        this.name = name;
        this.colorArgb = colorArgb;
    }

    public String name() {
        return name;
    }

    public int colorArgb() {
        return colorArgb;
    }
}
