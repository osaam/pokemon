package com.osamaelsh3rawy.pokemon.viewModel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.osamaelsh3rawy.pokemon.model.PokemonData;
import com.osamaelsh3rawy.pokemon.model.PokemonResponse;
import com.osamaelsh3rawy.pokemon.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PokemonViewModel extends ViewModel {
    private Repository repository;
    MutableLiveData<ArrayList<PokemonData>> PokemonList = new MutableLiveData<>();
    LiveData<List<PokemonData>> FevPokemonList = new MutableLiveData<>();

    public LiveData<List<PokemonData>> getFevPokemonList() {
        return FevPokemonList;
    }

    @ViewModelInject
    public PokemonViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<PokemonData>> getPokemonList() {
        return PokemonList;
    }


    public void getpokemons() {

        repository.getpockemon().subscribeOn(Schedulers.io())
                .map(new Function<PokemonResponse, ArrayList<PokemonData>>() {
                    @Override
                    public ArrayList<PokemonData> apply(PokemonResponse pokemonResponse) throws Throwable {
                        ArrayList<PokemonData> list = pokemonResponse.getResults();
                        for (PokemonData pokemonData : list) {
                            String url = pokemonData.getUrl();
                            String[] pokemonIndex = url.split("/");
                            pokemonData.setUrl("https://pokeres.bastionbot.org/images/pokemon/"+pokemonIndex[pokemonIndex.length - 1]+".png");
                        }
                        return list;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(    result -> PokemonList.setValue(result),
                        error -> Log.e("viewModel", error.getMessage()));

    }


    public void insertPokemon(PokemonData pokemonData) {
        repository.insertPokemon(pokemonData);
    }

    public void deletePokemon(String pokemonNmae) {
        repository.deletePokemon(pokemonNmae);
    }

    public void getFav() {
        FevPokemonList = repository.getAll();
    }
}
