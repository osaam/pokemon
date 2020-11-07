package com.osamaelsh3rawy.pokemon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.osamaelsh3rawy.pokemon.adapter.PokemonAdapter;
import com.osamaelsh3rawy.pokemon.model.PokemonData;
import com.osamaelsh3rawy.pokemon.viewModel.PokemonViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private PokemonViewModel viewModel;
    private RecyclerView recyclerView;
    private PokemonAdapter pokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        pokemonAdapter = new PokemonAdapter(this);
        recyclerView.setAdapter(pokemonAdapter);
        setupSwip();

        Button toFav= findViewById(R.id.btn_fav);
        toFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,FavActivity.class);
                startActivity(intent);
            }
        });


        viewModel =new ViewModelProvider(this).get(PokemonViewModel.class);
        viewModel.getpokemons();
        viewModel.getPokemonList().observe(this, new Observer<ArrayList<PokemonData>>() {
            @Override
            public void onChanged(ArrayList<PokemonData> pokemonData) {
                pokemonAdapter.setList(pokemonData);
            }
        });

    }

    private void setupSwip() {
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position=viewHolder.getAdapterPosition();
                PokemonData pokemonposition=pokemonAdapter.getPokemonAt(position);
                viewModel.insertPokemon(pokemonposition);
                pokemonAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,"Pokemon Added",Toast.LENGTH_SHORT).show();

            }
        };

        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }



}