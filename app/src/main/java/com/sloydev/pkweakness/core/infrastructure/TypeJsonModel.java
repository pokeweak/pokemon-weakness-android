package com.sloydev.pkweakness.core.infrastructure;


public class TypeJsonModel {
    public String id;
    public String name;
    public String color;

    @Override
    public String toString() {
        return "TypeJsonModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", colorHex='" + color + '\'' +
                '}';
    }
}
