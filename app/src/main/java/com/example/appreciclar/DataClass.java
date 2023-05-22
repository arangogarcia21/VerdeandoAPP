package com.example.appreciclar;

public class DataClass {

    private String dataNombre;
    private String dataDesc;
    private String dataCalif;
    private String dataImage;

    public String getDataNombre() {
        return dataNombre;
    }

    public String getDataDesc() {
        return dataDesc;
    }

    public String getDataCalif() {
        return dataCalif;
    }

    public String getDataImage() {
        return dataImage;
    }

    public DataClass(String dataNombre, String dataDesc, String dataCalif, String dataImage) {
        this.dataNombre = dataNombre;
        this.dataDesc = dataDesc;
        this.dataCalif = dataCalif;
        this.dataImage = dataImage;
    }
}
