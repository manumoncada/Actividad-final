package com.example.contactoapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// Fragment 2: perfil de la mascota (foto redonda, nombre y grid de fotos)
public class PerfilFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        RecyclerView rvFotos = v.findViewById(R.id.rvFotos);
        rvFotos.setLayoutManager(new GridLayoutManager(getContext(), 3)); // 3 columnas
        rvFotos.setAdapter(new FotoAdaptador(crearDataSet()));
        return v;
    }

    // Datos dummy
    private ArrayList<FotoMascota> crearDataSet() {
        ArrayList<FotoMascota> fotos = new ArrayList<>();
        int[] ratings = {5, 3, 8, 1, 4, 7, 2, 6, 9, 0, 3, 5};
        for (int r : ratings) {
            fotos.add(new FotoMascota(R.drawable.ic_pet, r));
        }
        return fotos;
    }
}
