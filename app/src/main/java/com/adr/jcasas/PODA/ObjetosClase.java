package com.adr.jcasas.PODA;

/**
 * Created by jcasas on 23/04/2016.
 */
public class ObjetosClase {

    int id;
    String nombre;
    //Constructor
    public ObjetosClase(int id, String nombre) {
        super();
        this.id = id;
        this.nombre = nombre;
    }
    @Override
    public String toString() {
        return nombre;
    }
    public int getId() {
        return id;
    }
}
