package com.osamaelsh3rawy.pokemon.model;

import java.util.ArrayList;

public class PokemonResponse {
    private int count;
    private String next,pre;
    private ArrayList<PokemonData> results;

    public PokemonResponse(int count, String next, String pre, ArrayList<PokemonData> results) {
        this.count = count;
        this.next = next;
        this.pre = pre;
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPre() {
        return pre;
    }

    public void setPre(String pre) {
        this.pre = pre;
    }

    public ArrayList<PokemonData> getResults() {
        return results;
    }

    public void setResults(ArrayList<PokemonData> results) {
        this.results = results;
    }
}
