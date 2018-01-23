package com.example.santiagolopez.parkingapp.view.poopup;

import android.support.v4.app.DialogFragment;
import android.widget.Toast;

/**
 * Created by santiago.lopez on 1/23/18.
 */

public class BasePopup extends DialogFragment {

    public void mostrarMensaje(final String mensaje) {
        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
    }
}