package com.example.santiagolopez.parkingapp.view.interfaces;

import android.content.Context;

/**
 * Created by santiago.lopez on 1/22/18.
 */

public interface BaseView {

    Context getContext();

    void mostrarMensajeError(int resourceMensaje);

    void mostrarMensajeError(String mensaje);

}
