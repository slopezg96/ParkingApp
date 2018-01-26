package com.example.santiagolopez.parkingapp.view.popup;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.santiagolopez.parkingapp.R;
import com.example.santiagolopez.parkingapp.businesslogic.ParqueaderoBusinessLogic;
import com.example.santiagolopez.parkingapp.model.TipoVehiculo;
import com.example.santiagolopez.parkingapp.model.Vehiculo;
import com.example.santiagolopez.parkingapp.model.VehiculoParqueado;
import com.example.santiagolopez.parkingapp.presenters.NuevoVehiculoPresenter;
import com.example.santiagolopez.parkingapp.util.ContenedorDependencia;
import com.example.santiagolopez.parkingapp.view.adapters.TipoVehiculoAdapter;
import com.example.santiagolopez.parkingapp.view.interfaces.IHomeView;
import com.example.santiagolopez.parkingapp.view.interfaces.INuevoVehiculoView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by santiago.lopez on 1/23/18.
 */

public class NuevoVehiculoPopUp extends BasePopup<NuevoVehiculoPresenter> implements INuevoVehiculoView {

    @Inject
    ParqueaderoBusinessLogic parqueaderoBusinessLogic;

    private IHomeView iHomeView;
    private Spinner spinnerTiposVehiculos;
    private EditText editTextPlaca;
    private EditText editTextCilindraje;
    private ImageView imageViewBuscarVehiculo;
    private Button buttonIngresarNuevoVehiculo;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        inicializarInyeccionDependencias();
        presentador.adicionarVista(this);
        return iniciarDialogo();
    }

    @NonNull
    private Dialog iniciarDialogo() {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_nuevo_vehiculo);
        obtenerControles(dialog);
        asignarEventos();presentador.iniciar();
        return dialog;
    }

    public void setiHomeView(IHomeView iHomeView) {
        this.iHomeView = iHomeView;
    }

    private void inicializarInyeccionDependencias() {
        dependencia = new ContenedorDependencia(getActivity().getApplication());
        dependencia.getContenedor().build().inject(this);
    }

    private void asignarEventos() {
        buttonIngresarNuevoVehiculo.setOnClickListener(view -> {
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setTipo((TipoVehiculo) spinnerTiposVehiculos.getSelectedItem());
            vehiculo.setPlaca(editTextPlaca.getText().toString());
            vehiculo.setCilindraje(Integer.parseInt(editTextCilindraje.getText().toString()));
            presentador.ingresoVehiculo(vehiculo);
            dismiss();
        });
    }

    private void obtenerControles(Dialog dialog) {
        spinnerTiposVehiculos = dialog.findViewById(R.id.spinner_tipoVehiculo);
        editTextPlaca = dialog.findViewById(R.id.editText_Placa);
        editTextCilindraje = dialog.findViewById(R.id.editText_Cilindraje);
        imageViewBuscarVehiculo = dialog.findViewById(R.id.imageView_SearchVehiculo);
        buttonIngresarNuevoVehiculo = dialog.findViewById(R.id.button_IngresarNuevoVehiculo);
    }

    @Override
    public void mostrarTiposVehiculos(List<TipoVehiculo> tipoVehiculos) {
        spinnerTiposVehiculos.setAdapter(new TipoVehiculoAdapter(getContext(), tipoVehiculos));
    }

    @Override
    public void cerrarDialog() {
        dismiss();
    }

    @Override
    public void refrescarDisponibilidadParqueadero(VehiculoParqueado vehiculoParqueado) {
        iHomeView.refrescarDisponibilidadParqueadero(vehiculoParqueado);
    }
}
