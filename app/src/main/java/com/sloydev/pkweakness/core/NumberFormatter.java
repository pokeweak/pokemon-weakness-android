package com.sloydev.pkweakness.core;

public class NumberFormatter {


    public static String getDisplayNumber(int number) {
        if (number < 10) {
            return "00" + number;
        } else if (number < 100) {
            return "0" + number;
        } else {
            return String.valueOf(number);
        }
    }
}
