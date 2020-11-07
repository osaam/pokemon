package com.osamaelsh3rawy.pokemon.repository;

import androidx.lifecycle.LiveData;

import com.osamaelsh3rawy.pokemon.db.PokemonDao;
import com.osamaelsh3rawy.pokemon.model.PokemonData;
import com.osamaelsh3rawy.pokemon.model.PokemonResponse;
import com.osamaelsh3rawy.pokemon.network.Apiservies;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;


public class Repository {
    private Apiservies apiservies;
    private PokemonDao pokemonDao;

    @Inject
    public Repository(Apiservies apiservies,PokemonDao pokemonDao) {
        this.pokemonDao=pokemonDao;
        this.apiservies = apiservies;
    }

    public Observable<PokemonResponse> getpockemon() {
        return apiservies.getPokemon();
    }


    public void insertPokemon(PokemonData pokemonData){
        pokemonDao.insert(pokemonData);
    }
    public void deletePokemon(String pokemonNmae){
        pokemonDao.delete(pokemonNmae);
    }
    public LiveData<List<PokemonData>> getAll(){
        return pokemonDao.getAllPokemon();
    }
}
