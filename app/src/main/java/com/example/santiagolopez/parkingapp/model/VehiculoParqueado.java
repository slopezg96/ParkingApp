package com.example.santiagolopez.parkingapp.model;

import java.util.Date;

/**
 * Created by santiago.lopez on 1/17/18.
 */

public class VehiculoParqueado {

    private Vehiculo vehiculo;
    private Date fechaIngreso;
    private Date fechaSalida;
    private double valor;

    public VehiculoParqueado() {
        vehiculo = new Vehiculo();
        valor = 0;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
