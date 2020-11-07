package com.osamaelsh3rawy.pokemon.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.osamaelsh3rawy.pokemon.model.PokemonData;

import java.util.List;

import javax.inject.Inject;

@Dao
public interface PokemonDao {
    @Insert
    public void insert(PokemonData pokemonData);

    @Query("delete from fav_table where name=:PokemonName")
    public void delete(String PokemonName);

    @Query("select * from fav_table")
    public LiveData<List<PokemonData>> getAllPokemon();
}
