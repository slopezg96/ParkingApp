package com.example.santiagolopez.parkingapp.util;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by santiago.lopez on 1/18/18.
 */

public class DateHelper {

    private Calendar calendarIngreso = Calendar.getInstance();
    private Calendar calendarSalida = Calendar.getInstance();
    private long milisegundosIngreso;
    private long milisegundosSalida;
    private long milisegundoDiferencia;
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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

    public static Date convertirStringADate(String fecha){
        try {
            return format.parse(fecha);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String convertirDateAString(Date fechaAConvertir) throws ParseException {
        Format formato = format;
        return formato.format(fechaAConvertir);
    }
}
