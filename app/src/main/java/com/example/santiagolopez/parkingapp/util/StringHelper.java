package com.example.santiagolopez.parkingapp.util;

import java.text.DecimalFormat;

/**
 * Created by santiago.lopez on 1/29/18.
 */

public class StringHelper {
    public static String getFormatoPesos(double valor) {
        DecimalFormat decimalFormat = new DecimalFormat("$ #,###.##");
        return decimalFormat.format(valor);
    }
}
