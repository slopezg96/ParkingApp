package com.example.santiagolopez.parkingapp.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by santiago.lopez on 1/18/18.
 */

public class DateHelper {

    private Calendar calendarIngreso = Calendar.getInstance();
    private Calendar calendarSalida = Calendar.getInstance();
    private long milisegundosIngreso;
    private long milisegundosSalida;
    private long milisegundoDiferencia;

    public DateHelper(Date fechaIngreso, Date fechaSalida) {
        calendarIngreso.setTime(fechaIngreso);
        calendarSalida.setTime(fechaSalida);
        milisegundosIngreso = calendarIngreso.getTimeInMillis();
        milisegundosSalida = calendarSalida.getTimeInMillis();
        milisegundoDiferencia = milisegundosSalida - milisegundosIngreso;
    }

    public long diferenciaDias(){
        return milisegundoDiferencia / (24 * 60 * 60 * 1000);
    }

    public long diferenciaHoras(){
        return milisegundoDiferencia / (60 * 60 * 1000);
    }

}
