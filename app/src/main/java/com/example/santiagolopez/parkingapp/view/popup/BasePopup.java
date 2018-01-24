package com.example.santiagolopez.parkingapp.view.popup;

import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import com.example.santiagolopez.parkingapp.util.ContenedorDependencia;



/**
 * Created by santiago.lopez on 1/23/18.
 */

public class BasePopup extends DialogFragment {

    protected ContenedorDependencia dependencia;

    public void mostrarMensaje(final String mensaje) {
        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
    }
}