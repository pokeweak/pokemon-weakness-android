package com.sloydev.pkweakness.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;

import com.sloydev.pkweakness.R;
import com.sloydev.pkweakness.core.infrastructure.ServiceLocator;
import com.sloydev.pkweakness.core.model.Pokemon;
import com.sloydev.pkweakness.ui.infrastructure.KeyboardUtils;
import com.sloydev.pkweakness.ui.infrastructure.adapter.PokemonAdapter;
import com.sloydev.pkweakness.ui.presenter.PokemonListPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnTextChanged;
import butterknife.OnTouch;

public class PokemonListActivity extends AppCompatActivity implements PokemonListPresenter.PokemonListView {

    private static final int POKEMONS_PER_ROW = 3;

    private PokemonListPresenter pokemonListPresenter;

    @BindView(R.id.pokemon_list_list)
    RecyclerView pokemonList;
    @BindView(R.id.pokemon_list_search_input)
    EditText searchInput;
    @BindView(R.id.pokemon_list_empty_state)
    View emptyState;
    @BindView(R.id.pokemon_list_appbar)
    AppBarLayout appBar;
    @BindView(R.id.pokemon_list_search_clear)
    View clearSearchIcon;
    @BindView(R.id.pokemon_list_search_more)
    View moreOptionsIcon;

    private PokemonAdapter pokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list_toolbar);
        ButterKnife.bind(this);

        pokemonListPresenter = ServiceLocator.providePokemonListPresenter(this, this);

        pokemonAdapter = new PokemonAdapter(getLayoutInflater(), ServiceLocator.providePokemonImageLoader(), this::openPokemonDetail);
        pokemonList.setAdapter(pokemonAdapter);
        pokemonList.setLayoutManager(new GridLayoutManager(this, POKEMONS_PER_ROW));


        pokemonListPresenter.initialize();
    }

    @OnTouch(R.id.pokemon_list_search_input)
    boolean onSearchClick() {
        prepareSearchUi();
        return false;
    }

    @OnTextChanged(R.id.pokemon_list_search_input)
    void onSearchInputChanged(CharSequence query) {
        pokemonListPresenter.onSearchInputChanged(query.toString());
        if (query.toString().isEmpty()) {
            clearSearchIcon.setVisibility(View.GONE);
            moreOptionsIcon.setVisibility(View.VISIBLE);
        } else {
            clearSearchIcon.setVisibility(View.VISIBLE);
            moreOptionsIcon.setVisibility(View.GONE);
        }
    }

    @OnEditorAction(R.id.pokemon_list_search_input)
    boolean onSearchInputImeAction() {
        KeyboardUtils.hideKeyboard(searchInput);
        return false;
    }


    @OnClick(R.id.pokemon_list_search_clear)
    void onClearText() {
        searchInput.setText(null);
        appBar.setExpanded(true);
        KeyboardUtils.hideKeyboard(searchInput);
    }

    @OnClick(R.id.pokemon_list_search_more)
    void onMoreOptionsClick() {
        PopupMenu popupMenu = new PopupMenu(this, moreOptionsIcon, Gravity.RIGHT);
        popupMenu.getMenu().add("About");
        popupMenu.setOnMenuItemClickListener(item -> {
            openAboutScreen();
            return true;
        });
        popupMenu.show();
    }

    private void openAboutScreen() {
        Intent intent = AboutActivity.createIntent(this);
        startActivity(intent);
    }

    private void prepareSearchUi() {
        appBar.setExpanded(false);
    }

    private void openPokemonDetail(Pokemon pokemon, View itemView) {
        Intent intent = PokemonDetailActivity.createIntent(PokemonListActivity.this, pokemon);
        startActivity(intent);
        overridePendingTransition(R.anim.detail_activity_fade_in, R.anim.detail_activity_fade_out);
    }


    @Override
    public void show(List<Pokemon> pokemons) {
        pokemonList.setVisibility(View.VISIBLE);
        emptyState.setVisibility(View.INVISIBLE);
        pokemonAdapter.setItems(pokemons);
    }

    @Override
    public void showEmpty() {
        pokemonList.setVisibility(View.INVISIBLE);
        emptyState.setVisibility(View.VISIBLE);
    }
}
