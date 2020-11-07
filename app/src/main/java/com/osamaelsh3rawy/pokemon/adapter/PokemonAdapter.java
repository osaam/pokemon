package com.osamaelsh3rawy.pokemon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.osamaelsh3rawy.pokemon.R;
import com.osamaelsh3rawy.pokemon.model.PokemonData;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {
    private Context context;
    private ArrayList<PokemonData> list = new ArrayList<>();

    public PokemonAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PokemonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.pockemon_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonAdapter.ViewHolder holder, int position) {
        PokemonData pokemonData = list.get(position);
        holder.pokemonName.setText(pokemonData.getName());
        Glide.with(context).load(pokemonData.getUrl()).into(holder.pokemonImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(ArrayList<PokemonData> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public PokemonData getPokemonAt(int position){
        return list.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView pokemonImage;
        private TextView pokemonName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pokemonImage = itemView.findViewById(R.id.image);
            pokemonName = itemView.findViewById(R.id.name);
        }
    }
}
