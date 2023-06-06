package com.example.appreciclar;

public class DataPublicaciones {
    String Calificacion, Titulo, Descripcion, Creador;

    public DataPublicaciones(){}

    public DataPublicaciones(String calificacion, String titulo, String descripcion, String creador) {
        Calificacion = calificacion;
        Titulo = titulo;
        Descripcion = descripcion;
        Creador = creador;
    }

    public String getCalificacion() {
        return Calificacion;
    }

    public void setCalificacion(String calificacion) {
        Calificacion = calificacion;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getCreador() {
        return Creador;
    }

    public void setCreador(String creador) {
        Creador = creador;
    }
}
