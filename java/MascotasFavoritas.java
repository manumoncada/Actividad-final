package com.example.contactoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// Muestra las ultimas 5 mascotas con rating, leidas desde la base de datos
public class MascotasFavoritas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritas);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        RecyclerView rvFavoritas = findViewById(R.id.rvFavoritas);
        TextView tvVacio = findViewById(R.id.tvVacio);
        rvFavoritas.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Mascota> favoritas = new BaseDatos(this).obtenerUltimas();

        if (favoritas.isEmpty()) {
            tvVacio.setVisibility(View.VISIBLE);
        } else {
            tvVacio.setVisibility(View.GONE);
        }
        rvFavoritas.setAdapter(new MascotaAdaptador(favoritas, this));
    }
}
