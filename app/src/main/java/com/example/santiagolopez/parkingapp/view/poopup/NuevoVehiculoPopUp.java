package com.example.santiagolopez.parkingapp.view.poopup;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.LinearLayout;

import com.example.santiagolopez.parkingapp.R;

/**
 * Created by santiago.lopez on 1/23/18.
 */

public class NuevoVehiculoPopUp extends BasePopup {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return iniciarDialogo();
    }

    @NonNull
    private Dialog iniciarDialogo() {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_nuevo_vehiculo);
//        asignarEventos();
        return dialog;
    }
}
