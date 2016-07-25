package com.sloydev.pkweakness;

import com.sloydev.pkweakness.core.NumberFormatter;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class NumberFormatterTest {

    @Test
    public void returns_000_when_input_is_0() throws Exception {
        int input = 0;

        String output = NumberFormatter.getDisplayNumber(input);

        assertThat(output, is("000"));
    }

    @Test
    public void returns_001_when_input_is_1() throws Exception {
        int input = 1;

        String output = NumberFormatter.getDisplayNumber(input);

        assertThat(output, is("001"));
    }

    @Test
    public void returns_010_when_input_is_10() throws Exception {
        int input = 10;

        String output = NumberFormatter.getDisplayNumber(input);

        assertThat(output, is("010"));
    }

    @Test
    public void returns_100_when_input_is_100() throws Exception {
        int input = 100;

        String output = NumberFormatter.getDisplayNumber(input);

        assertThat(output, is("100"));
    }
}