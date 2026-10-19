package com.example.contactoapp;

// POJO de la entidad Mascota (identico a la tabla "mascota" de la base de datos)
public class Mascota {
    private int id;          // identificador unico
    private String nombre;
    private int foto;        // id del recurso drawable
    private int rating;

    public Mascota(int id, String nombre, int foto, int rating) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
        this.rating = rating;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getFoto() { return foto; }
    public void setFoto(int foto) { this.foto = foto; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
}
