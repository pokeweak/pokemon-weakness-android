package com.sloydev.pkweakness.core.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class SearchServiceTest {

    @Mock
    PokemonCollection pokemonCollection;

    private SearchService searchService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        searchService = new SearchService(pokemonCollection);
    }

    @Test
    public void returns_Pikachu_when_search_is_pi() throws Exception {
        when(pokemonCollection.getAll()).thenReturn(asList(stubPikachu(), stubRaichu()));

        List<Pokemon> result = searchService.search("pi")
                .toBlocking().single();

        assertThat(result).containsExactly(stubPikachu());
    }

    @Test
    public void returns_Pikachu_and_Raichu_when_search_is_chu() throws Exception {
        when(pokemonCollection.getAll()).thenReturn(asList(stubPikachu(), stubRaichu()));

        List<Pokemon> result = searchService.search("chu")
                .toBlocking().single();

        assertThat(result).containsExactly(stubPikachu(), stubRaichu());
    }


    @Test
    public void returns_Pikachu_when_search_is_25() throws Exception {
        when(pokemonCollection.getAll()).thenReturn(asList(stubPikachu(), stubRaichu()));

        List<Pokemon> result = searchService.search("25")
                .toBlocking().single();

        assertThat(result).containsExactly(stubPikachu());
    }


    @Test
    public void returns_Pikachu_and_Raichu_when_search_is_2() throws Exception {
        when(pokemonCollection.getAll()).thenReturn(asList(stubPikachu(), stubRaichu()));

        List<Pokemon> result = searchService.search("2")
                .toBlocking().single();

        assertThat(result).containsExactly(stubPikachu(), stubRaichu());
    }


    private Pokemon stubPikachu() {
        return Pokemon.create()
                .name("Pikachu")
                .number(25)
                .weaknesses(Collections.emptyList())
                .build();
    }

    private Pokemon stubRaichu() {
        return Pokemon.create()
                .name("Raichu")
                .number(26)
                .weaknesses(Collections.emptyList())
                .build();
    }
}