package com.example.santiagolopez.parkingapp.view.popup;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.santiagolopez.parkingapp.R;
import com.example.santiagolopez.parkingapp.presenters.NuevoVehiculoPresenter;
import com.example.santiagolopez.parkingapp.util.ContenedorDependencia;
import com.example.santiagolopez.parkingapp.view.interfaces.INuevoVehiculoView;

/**
 * Created by santiago.lopez on 1/23/18.
 */

public class NuevoVehiculoPopUp extends BasePopup implements INuevoVehiculoView {

    private Spinner spinnerTiposVehiculos;
    private EditText editTextPlaca;
    private EditText editTextCilindraje;
    private ImageView imageViewBuscarVehiculo;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        inicializarInyeccionDependencias();
        return iniciarDialogo();
    }

    @NonNull
    private Dialog iniciarDialogo() {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_nuevo_vehiculo);

        obtenerControles(dialog);
        asignarEventos();
        return dialog;
    }

    private void inicializarInyeccionDependencias() {
        dependencia = new ContenedorDependencia(getActivity().getApplication());
        dependencia.getContenedor().build().inject(this);
    }

    private void asignarEventos() {

    }

    private void obtenerControles(Dialog dialog) {
        spinnerTiposVehiculos = dialog.findViewById(R.id.spinner_tipoVehiculo);
        editTextPlaca = dialog.findViewById(R.id.editText_Placa);
        editTextCilindraje = dialog.findViewById(R.id.editText_Cilindraje);
        imageViewBuscarVehiculo = dialog.findViewById(R.id.imageView_SearchVehiculo);
    }
}
