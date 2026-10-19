package com.example.contactoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FotoAdaptador extends RecyclerView.Adapter<FotoAdaptador.FotoViewHolder> {

    private final ArrayList<FotoMascota> fotos;

    public FotoAdaptador(ArrayList<FotoMascota> fotos) {
        this.fotos = fotos;
    }

    @NonNull
    @Override
    public FotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_foto, parent, false);
        return new FotoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FotoViewHolder holder, int position) {
        FotoMascota foto = fotos.get(position);
        holder.imgFoto.setImageResource(foto.getImagen());
        holder.tvRating.setText(String.valueOf(foto.getRating()));
    }

    @Override
    public int getItemCount() {
        return fotos.size();
    }

    public static class FotoViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgFoto;
        private final TextView tvRating;

        public FotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto = itemView.findViewById(R.id.imgFotoGrid);
            tvRating = itemView.findViewById(R.id.tvRatingGrid);
        }
    }
}
