package com.dmiiy.wayapp;

public class ItemObject {
    private String nombre;
    private String grupo;
    private int foto;
    private String edad;

    public ItemObject(String nombre, String grupo, int foto, String edad) {
        this.nombre = nombre;
        this.grupo = grupo;
        this.foto = foto;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }
}
