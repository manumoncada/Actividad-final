package com.example.contactoapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

// Adapter del ViewPager: posicion 0 = lista, posicion 1 = perfil
public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity activity) {
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new MascotasFragment();
        }
        return new PerfilFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
