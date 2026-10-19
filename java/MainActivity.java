package com.example.contactoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager = findViewById(R.id.viewPager);

        viewPager.setAdapter(new ViewPagerAdapter(this));

        // Une las pestañas con el ViewPager y les pone icono
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position == 0) {
                tab.setIcon(R.drawable.ic_tab_home);
            } else {
                tab.setIcon(R.drawable.ic_tab_pet);
            }
        }).attach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_favoritas) {
            startActivity(new Intent(this, MascotasFavoritas.class));
            return true;
        } else if (id == R.id.action_contacto) {
            startActivity(new Intent(this, ContactoActivity.class));
            return true;
        } else if (id == R.id.action_acerca_de) {
            startActivity(new Intent(this, AcercaDeActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
