package com.example.appreciclar;

public class DataSitiosVerdes {
    String Titulo, Imagen, Descripcion, Latitud, Longitud;

    public DataSitiosVerdes(){

    }

    public DataSitiosVerdes(String titulo, String imagen, String descripcion, String latitud, String longitud) {
        Titulo = titulo;
        Imagen = imagen;
        Descripcion = descripcion;
        Latitud = latitud;
        Longitud = longitud;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getLatitud() {
        return Latitud;
    }

    public void setLatitud(String latitud) {
        Latitud = latitud;
    }

    public String getLongitud() {
        return Longitud;
    }

    public void setLongitud(String longitud) {
        Longitud = longitud;
    }
}
