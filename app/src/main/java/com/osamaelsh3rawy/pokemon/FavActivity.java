package com.osamaelsh3rawy.pokemon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.osamaelsh3rawy.pokemon.adapter.PokemonAdapter;
import com.osamaelsh3rawy.pokemon.model.PokemonData;
import com.osamaelsh3rawy.pokemon.viewModel.PokemonViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FavActivity extends AppCompatActivity {
    private PokemonViewModel viewModel;
    private RecyclerView recyclerView;
    private PokemonAdapter pokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourit_pokemon);

        recyclerView = findViewById(R.id.recycler_fav);
        pokemonAdapter = new PokemonAdapter(this);
        recyclerView.setAdapter(pokemonAdapter);
        setupSwip();

        Button toHome = findViewById(R.id.btn_home);
        toHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FavActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        viewModel = new ViewModelProvider(this).get(PokemonViewModel.class);
        viewModel.getFav();
        viewModel.getFevPokemonList().observe(this, new Observer<List<PokemonData>>() {
            @Override
            public void onChanged(List<PokemonData> pokemonData) {
                ArrayList<PokemonData> list = new ArrayList<>();
                list.addAll(pokemonData);
                pokemonAdapter.setList(list);
            }
        });

    }

    private void setupSwip() {
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                PokemonData pokemonposition = pokemonAdapter.getPokemonAt(position);
                viewModel.deletePokemon(pokemonposition.getName());
                pokemonAdapter.notifyDataSetChanged();
                Toast.makeText(FavActivity.this, "Pokemon deleted", Toast.LENGTH_SHORT).show();

            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }
}
