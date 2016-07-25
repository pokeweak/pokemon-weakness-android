package com.sloydev.pkweakness.core.model;


import com.sloydev.pkweakness.core.NumberFormatter;

import java.util.Collections;
import java.util.List;

public class Pokemon {
    private final int number;
    private final String displayNumber;
    private final String name;
    private final int colorArgb;
    private final List<PokemonType> weaknesses;

    public static Builder create() {
        return new Builder();
    }

    private Pokemon(Builder builder) {
        number = builder.number;
        name = builder.name;
        colorArgb = builder.colorArgb;
        displayNumber = NumberFormatter.getDisplayNumber(number);
        weaknesses = builder.weaknesses;
    }

    public int number() {
        return number;
    }

    public String displayNumber() {
        return displayNumber;
    }

    public String name() {
        return name;
    }

    public int colorArgb() {
        return colorArgb;
    }

    public List<PokemonType> weaknesses() {
        return weaknesses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pokemon pokemon = (Pokemon) o;

        if (number != pokemon.number) return false;
        if (colorArgb != pokemon.colorArgb) return false;
        if (!displayNumber.equals(pokemon.displayNumber)) return false;
        if (!name.equals(pokemon.name)) return false;
        return weaknesses.equals(pokemon.weaknesses);
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + (displayNumber != null ? displayNumber.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + colorArgb;
        result = 31 * result + (weaknesses != null ? weaknesses.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "number=" + number +
                ", displayNumber='" + displayNumber + '\'' +
                ", name='" + name + '\'' +
                ", colorArgb=" + colorArgb +
                ", weaknesses=" + weaknesses +
                '}';
    }

    public static class Builder {
        private int number;
        private String name;
        private int colorArgb;
        private List<PokemonType> weaknesses;

        private Builder() {
        }

        public Builder number(int number) {
            this.number = number;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder colorArgb(int colorArgb) {
            this.colorArgb = colorArgb;
            return this;
        }

        public Builder weaknesses(List<PokemonType> weaknesses) {
            this.weaknesses = weaknesses;
            return this;
        }

        public Pokemon build() {
            return new Pokemon(this);

        }
    }

}
