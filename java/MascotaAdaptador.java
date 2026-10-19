package com.example.contactoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {

    private final ArrayList<Mascota> mascotas;
    private final BaseDatos baseDatos;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Context context) {
        this.mascotas = mascotas;
        this.baseDatos = new BaseDatos(context);
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MascotaViewHolder holder, int position) {
        final Mascota mascota = mascotas.get(position);
        holder.imgFoto.setImageResource(mascota.getFoto());
        holder.tvNombre.setText(mascota.getNombre());
        holder.tvRating.setText(String.valueOf(mascota.getRating()));

        // Hueso blanco: suma 1 al rating y guarda la mascota completa en la base de datos
        holder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mascota.setRating(mascota.getRating() + 1);
                baseDatos.guardarRating(mascota);
                notifyItemChanged(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgFoto;
        private final TextView tvNombre;
        private final TextView tvRating;
        private final ImageButton btnLike;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto = itemView.findViewById(R.id.imgFoto);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvRating = itemView.findViewById(R.id.tvRating);
            btnLike = itemView.findViewById(R.id.btnLike);
        }
    }
}
