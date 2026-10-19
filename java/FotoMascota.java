package com.example.contactoapp;

// Foto subida por la mascota (para el grid del perfil)
public class FotoMascota {
    private int imagen;
    private int rating;

    public FotoMascota(int imagen, int rating) {
        this.imagen = imagen;
        this.rating = rating;
    }

    public int getImagen() { return imagen; }
    public int getRating() { return rating; }
}
