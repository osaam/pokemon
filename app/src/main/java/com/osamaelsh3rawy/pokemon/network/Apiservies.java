package com.osamaelsh3rawy.pokemon.network;

import com.osamaelsh3rawy.pokemon.model.PokemonResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface Apiservies {

    @GET("pokemon")
    Observable<PokemonResponse> getPokemon();
}
