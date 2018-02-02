package com.example.santiagolopez.parkingapp.view.popup;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.santiagolopez.parkingapp.R;
import com.example.santiagolopez.parkingapp.model.VehiculoParqueado;
import com.example.santiagolopez.parkingapp.presenters.SalidaVehiculoPresenter;
import com.example.santiagolopez.parkingapp.util.ContenedorDependencia;
import com.example.santiagolopez.parkingapp.util.DateHelper;
import com.example.santiagolopez.parkingapp.util.StringHelper;
import com.example.santiagolopez.parkingapp.view.interfaces.IHomeView;
import com.example.santiagolopez.parkingapp.view.interfaces.ISalidaVehiculo;

import java.text.ParseException;

/**
 * Created by santiago.lopez on 1/23/18.
 */

public class SalidaVehiculoPopup extends BasePopup<SalidaVehiculoPresenter> implements ISalidaVehiculo {


    private EditText editTextPlaca;
    private ImageView imageViewSearch;
    private TextView textViewPlaca;
    private TextView textViewCilindraje;
    private TextView textViewFechaIngreso;
    private TextView textViewTipoVehiculo;
    private TextView textViewValorTotal;
    private LinearLayout linearLayoutCilindraje;
    private LinearLayout linearLayoutFechaIngreso;
    private LinearLayout linearLayoutTipoVehiculo;
    private LinearLayout linearLayoutValorTotal;
    private LinearLayout linearLayoutPlaca;
    private Button buttonCobrar;
    private Button buttonSalir;
    private IHomeView iHomeView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        inicializarInyeccionDependencias();
        presentador.adicionarVista(this);
        presentador.setValidateInternet(getValidateInternet());
        return iniciarDialogo();
    }

    @NonNull
    private Dialog iniciarDialogo() {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_salida_vehiculo);
        obtenerControles(dialog);
        asignarEventos();
        return dialog;
    }

    public void setiHomeView(IHomeView iHomeView) {
        this.iHomeView = iHomeView;
    }

    private void inicializarInyeccionDependencias() {
        dependencia = new ContenedorDependencia(getActivity().getApplication());
        dependencia.getContenedor().build().inject(this);
    }

    private void obtenerControles(Dialog dialog) {
        editTextPlaca = dialog.findViewById(R.id.editText_Placa);
        imageViewSearch = dialog.findViewById(R.id.imageView_SearchVehiculo);
        textViewPlaca = dialog.findViewById(R.id.textView_PlacaVehiculo);
        textViewCilindraje = dialog.findViewById(R.id.textView_Cilindraje);
        textViewFechaIngreso = dialog.findViewById(R.id.textView_FechaIngreso);
        textViewTipoVehiculo = dialog.findViewById(R.id.textView_TipoVehiculo);
        textViewValorTotal = dialog.findViewById(R.id.textView_ValorTotal);
        linearLayoutCilindraje = dialog.findViewById(R.id.linearLayout_Cilindraje);
        linearLayoutFechaIngreso = dialog.findViewById(R.id.linearLayout_FechaIngreso);
        linearLayoutTipoVehiculo = dialog.findViewById(R.id.linearLayout_TipoVehiculo);
        linearLayoutValorTotal = dialog.findViewById(R.id.linearLayout_Total);
        linearLayoutPlaca = dialog.findViewById(R.id.linearLayout_Placa);
        buttonCobrar = dialog.findViewById(R.id.button_Cobrar);
        buttonSalir = dialog.findViewById(R.id.button_Salir);
    }

    private void asignarEventos() {
        imageViewSearch.setOnClickListener(view -> {
            if (!editTextPlaca.getText().toString().isEmpty()) {
                presentador.validarInternetBuscarVehiculoParqueadoXPlaca(editTextPlaca.getText().toString());
            } else {
                mostrarMensaje(getString(R.string.texto_debe_ingresar_placa));
            }
        });

        buttonCobrar.setOnClickListener(view -> {
            presentador.validarInternetCobrar();
        });

        buttonSalir.setOnClickListener(view -> {
            refrescarLista();
            dismiss();
        });
    }

    @Override
    public void mostrarInformacionVehiculoParqueado(VehiculoParqueado vehiculoParqueado) {
        presentador.setVehiculoParqueado(vehiculoParqueado);
        mostrarControlesInformacion();
        textViewTipoVehiculo.setText(vehiculoParqueado.getVehiculo().getTipo().getNombre());
        textViewPlaca.setText(vehiculoParqueado.getVehiculo().getPlaca());
        textViewCilindraje.setText(String.valueOf(vehiculoParqueado.getVehiculo().getCilindraje()));
        try {
            textViewFechaIngreso.setText(DateHelper.convertirDateAString(vehiculoParqueado.getFechaIngreso()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mostrarValortotal(VehiculoParqueado vehiculoParqueado) {
        buttonCobrar.setVisibility(View.GONE);
        linearLayoutPlaca.setVisibility(View.GONE);
        buttonSalir.setVisibility(View.VISIBLE);
        linearLayoutValorTotal.setVisibility(View.VISIBLE);
        textViewValorTotal.setText(StringHelper.getFormatoPesos(vehiculoParqueado.getValor()));
    }

    public void refrescarLista() {
        iHomeView.refrescarLista();
    }

    private void mostrarControlesInformacion() {
        linearLayoutCilindraje.setVisibility(View.VISIBLE);
        linearLayoutTipoVehiculo.setVisibility(View.VISIBLE);
        linearLayoutFechaIngreso.setVisibility(View.VISIBLE);
        buttonCobrar.setVisibility(View.VISIBLE);
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
