package com.example.santiagolopez.parkingapp.model;


/**
 * Created by santiago.lopez on 1/17/18.
 */

public class Vehiculo {

    private TipoVehiculo tipo;
    private String placa;
    private int cilindraje;

    public Vehiculo() {
        placa = "";
        tipo = new TipoVehiculo();
        cilindraje = 0;
    }

    public TipoVehiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVehiculo tipo) {
        this.tipo = tipo;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getPlaca() {
        return placa;
    }

    public int getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(int cilindraje) {
        this.cilindraje = cilindraje;
    }

}
