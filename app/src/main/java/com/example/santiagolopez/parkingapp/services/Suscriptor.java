package com.example.santiagolopez.parkingapp.services;

import java.io.IOException;

/**
 * Created by santiago.lopez on 1/25/18.
 */

public interface Suscriptor {

    void onError(Throwable e);

    void onCompletetado();

    <T> void onResultado(T datos);
}
