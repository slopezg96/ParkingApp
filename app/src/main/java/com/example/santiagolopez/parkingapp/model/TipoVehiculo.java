package com.example.santiagolopez.parkingapp.model;

import java.io.Serializable;

/**
 * Created by santiago.lopez on 1/22/18.
 */

public class TipoVehiculo implements Serializable {

    private Long id;
    private String nombre;
    private double valorDia;
    private double valorHora;

    public TipoVehiculo() {
        nombre = "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getValorDia() {
        return valorDia;
    }

    public void setValorDia(double valorDia) {
        this.valorDia = valorDia;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }
}
