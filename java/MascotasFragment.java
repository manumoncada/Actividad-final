package com.example.contactoapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// Fragment 1: lista de mascotas
public class MascotasFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mascotas, container, false);

        RecyclerView rvMascotas = v.findViewById(R.id.rvMascotas);
        rvMascotas.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMascotas.setAdapter(new MascotaAdaptador(crearDataSet(requireContext()), requireContext()));
        return v;
    }

    // DataSet: cada mascota tiene un id unico. Si la mascota ya tiene rating guardado
    // en la base de datos, se muestra ese rating.
    private ArrayList<Mascota> crearDataSet(Context context) {
        BaseDatos bd = new BaseDatos(context);
        ArrayList<Mascota> mascotas = new ArrayList<>();
        mascotas.add(crear(bd, 1, "Firulais", 3));
        mascotas.add(crear(bd, 2, "Luna", 5));
        mascotas.add(crear(bd, 3, "Max", 2));
        mascotas.add(crear(bd, 4, "Rocky", 4));
        mascotas.add(crear(bd, 5, "Mia", 1));
        mascotas.add(crear(bd, 6, "Toby", 0));
        mascotas.add(crear(bd, 7, "Nala", 6));
        return mascotas;
    }

    private Mascota crear(BaseDatos bd, int id, String nombre, int ratingInicial) {
        return new Mascota(id, nombre, R.drawable.ic_pet, bd.obtenerRating(id, ratingInicial));
    }
}
