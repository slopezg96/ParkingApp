package com.example.santiagolopez.parkingapp.model;


import java.util.Date;

/**
 * Created by santiago.lopez on 1/17/18.
 */

public class Vehiculo {

    private TipoVehiculo tipo;
    private String placa;
    private int cilindraje;
    private Date fechaIngreso;
    private Date fechaSalida;
    private double totalPagar;

    public Vehiculo() {
        placa = "";
        tipo = new TipoVehiculo();
        cilindraje = 0;
        totalPagar = 0;
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

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public int getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(int cilindraje) {
        this.cilindraje = cilindraje;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }
}
