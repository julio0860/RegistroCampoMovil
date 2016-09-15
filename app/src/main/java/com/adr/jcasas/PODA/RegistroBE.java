package com.adr.jcasas.PODA;

/**
 * Created by jcasas on 27/04/2016.
 */
public class RegistroBE {

    private String Lote,Fecha,Registrador;
    private int Contador,Yemas,Pitones;

    public String getLote() {
        return Lote;
    }

    public void setLote(String lote) {
        Lote = lote;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getRegistrador() {
        return Registrador;
    }

    public void setRegistrador(String registrador) {
        Registrador = registrador;
    }

    public int getContador() {
        return Contador;
    }

    public void setContador(int contador) {
        Contador = contador;
    }

    public int getYemas() {
        return Yemas;
    }

    public void setYemas(int yemas) {
        Yemas = yemas;
    }

    public int getPitones() {
        return Pitones;
    }

    public void setPitones(int pitones) {
        Pitones = pitones;
    }

    public RegistroBE(String lote, String fecha, String registrador, int contador, int yemas, int pitones) {
        super();
        this.Lote = lote;
       this.Fecha = fecha;
        this.Registrador = registrador;
        this.Contador = contador;
        this.Yemas = yemas;
        this.Pitones = pitones;
    }

    @Override
    public String toString() {
        return Lote+"  "+Fecha+" "+Registrador+" "+Contador+" "+Yemas+" "+Pitones ;

    }





}
