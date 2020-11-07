package com.osamaelsh3rawy.pokemon.db;

import android.app.Application;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DBMoudule {
    @Provides
    @Singleton
    public static PokemonDB ProvideDB(Application application){
        return Room.databaseBuilder(application,PokemonDB.class,"fav_DB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static PokemonDao proviodeDao(PokemonDB pokemonDB){
        return pokemonDB.pokemonDao();
    }
}
