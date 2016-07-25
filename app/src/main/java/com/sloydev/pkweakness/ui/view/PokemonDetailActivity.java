package com.sloydev.pkweakness.ui.view;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sloydev.pkweakness.R;
import com.sloydev.pkweakness.core.action.GetPokemonByNumber;
import com.sloydev.pkweakness.core.infrastructure.ServiceLocator;
import com.sloydev.pkweakness.core.model.Pokemon;
import com.sloydev.pkweakness.core.model.PokemonType;
import com.sloydev.pkweakness.ui.infrastructure.PokemonImageLoader;
import com.sloydev.pkweakness.ui.infrastructure.widget.LabelView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sloydev.pkweakness.ui.infrastructure.RxUtils.genericOnError;

public class PokemonDetailActivity extends AppCompatActivity {

    private static final String EXTRA_NUMBER = "pk_number";

    @BindView(R.id.pokemon_number)
    TextView number;
    @BindView(R.id.pokemon_name)
    TextView name;
    @BindView(R.id.pokemon_image)
    ImageView image;
    @BindView(R.id.pokemon_background)
    View background;
    @BindView(R.id.detail_weaknesses_list)
    ViewGroup weaknessesList;

    private PokemonImageLoader pokemonImageLoader;
    private GetPokemonByNumber getPokemonByNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);
        ButterKnife.bind(this);

        int pokemonNumber = getIntent().getExtras().getInt(EXTRA_NUMBER);
        getPokemonByNumber = ServiceLocator.provideGetPokemonByNumber(this);

        getPokemonByNumber.get(pokemonNumber)
                .subscribe(this::showPokemon, genericOnError());
    }

    private void showPokemon(Pokemon pokemon) {
        number.setText(pokemon.displayNumber());
        name.setText(pokemon.name());

        pokemonImageLoader = ServiceLocator.providePokemonImageLoader();
        pokemonImageLoader.loadPokemonImage(pokemon, image);

        ((GradientDrawable) background.getBackground()).setColor(pokemon.colorArgb());

        for (PokemonType pokemonType : pokemon.weaknesses()) {
            LabelView label = LabelView.create(weaknessesList);
            label.setText(pokemonType.name());
            label.setLabelColor(pokemonType.colorArgb());
            weaknessesList.addView(label);
        }
    }

    @OnClick(R.id.detail_background)
    public void onClickOutside() {
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.detail_activity_fade_in, R.anim.detail_activity_fade_out);
    }

    public static Intent createIntent(Context context, Pokemon pokemon) {
        Intent intent = new Intent(context, PokemonDetailActivity.class);
        intent.putExtra(EXTRA_NUMBER, pokemon.number());
        return intent;
    }
}
