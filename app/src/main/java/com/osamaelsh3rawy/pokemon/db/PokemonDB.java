package com.osamaelsh3rawy.pokemon.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.osamaelsh3rawy.pokemon.model.PokemonData;

@Database(entities = PokemonData.class, version = 1, exportSchema = false)
public abstract class PokemonDB extends RoomDatabase {
    public abstract PokemonDao pokemonDao();
}
