package com.example.appreciclar;

public class DataSitiosVerdes {
    String Titulo, Imagen, Descripcion ;
    Double Latitud, Longitud;
    public DataSitiosVerdes(){

    }

    public DataSitiosVerdes(String titulo, String imagen, String descripcion, Double latitud, Double longitud) {
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

    public Double getLatitud() {
        return Latitud;
    }

    public void setLatitud(Double latitud) {
        Latitud = latitud;
    }

    public Double getLongitud() {
        return Longitud;
    }

    public void setLongitud(Double longitud) {
        Longitud = longitud;
    }
}
