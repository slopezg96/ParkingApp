package com.example.santiagolopez.parkingapp.view.popup;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.santiagolopez.parkingapp.R;
import com.example.santiagolopez.parkingapp.model.TipoVehiculo;
import com.example.santiagolopez.parkingapp.model.Vehiculo;
import com.example.santiagolopez.parkingapp.model.VehiculoParqueado;
import com.example.santiagolopez.parkingapp.presenters.NuevoVehiculoPresenter;
import com.example.santiagolopez.parkingapp.util.ContenedorDependencia;
import com.example.santiagolopez.parkingapp.view.adapters.TipoVehiculoAdapter;
import com.example.santiagolopez.parkingapp.view.interfaces.IHomeView;
import com.example.santiagolopez.parkingapp.view.interfaces.INuevoVehiculoView;

import java.util.List;

/**
 * Created by santiago.lopez on 1/23/18.
 */

public class NuevoVehiculoPopUp extends BasePopup<NuevoVehiculoPresenter> implements INuevoVehiculoView {

    private IHomeView iHomeView;
    private Spinner spinnerTiposVehiculos;
    private EditText editTextPlaca;
    private EditText editTextCilindraje;
    private ImageView imageViewBuscarVehiculo;
    private Button buttonIngresarNuevoVehiculo;
    private List<VehiculoParqueado> motosParqueadas;
    private List<VehiculoParqueado> carrosParqueados;


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
        asignarEventos();
        presentador.setCarrosParqueados(carrosParqueados);
        presentador.setMotosParqueadas(motosParqueadas);
        presentador.setValidateInternet(getValidateInternet());
        presentador.iniciar();
        return dialog;
    }

    public void setMotosParqueadas(List<VehiculoParqueado> motosParqueadas) {
        this.motosParqueadas = motosParqueadas;
    }

    public void setCarrosParqueados(List<VehiculoParqueado> carrosParqueados) {
        this.carrosParqueados = carrosParqueados;
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
            presentador.setVehiculo(vehiculo);
            presentador.ingresoVehiculo();
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
        iHomeView.adicionarVehiculo(vehiculoParqueado);
    }

    @Override
    public void mostrarMensajeError(int resourceMensaje) {
        Toast.makeText(getContext(), getString(resourceMensaje), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarMensajeError(String mensaje) {
        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return getActivity();
    }
}
