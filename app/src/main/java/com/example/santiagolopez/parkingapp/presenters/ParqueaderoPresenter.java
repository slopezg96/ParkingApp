package com.example.santiagolopez.parkingapp.presenters;

import com.example.santiagolopez.parkingapp.R;
import com.example.santiagolopez.parkingapp.businesslogic.ParqueaderoBusinessLogic;
import com.example.santiagolopez.parkingapp.model.VehiculoParqueado;
import com.example.santiagolopez.parkingapp.services.dto.VehiculoParqueadoDTO;
import com.example.santiagolopez.parkingapp.util.Mapper;
import com.example.santiagolopez.parkingapp.view.interfaces.IHomeView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by santiago.lopez on 1/22/18.
 */

public class ParqueaderoPresenter extends BasePresenter<IHomeView> {

    private ParqueaderoBusinessLogic parqueaderoBusinessLogic;
    private List<VehiculoParqueado> motosParqueadas;
    private List<VehiculoParqueado> carrosParqueados;

    @Inject
    public ParqueaderoPresenter(ParqueaderoBusinessLogic parqueaderoBusinessLogic) {
        this.parqueaderoBusinessLogic = parqueaderoBusinessLogic;
    }

    public void setMotosParqueadas(List<VehiculoParqueado> vehiculoParqueados) {
        motosParqueadas = new ArrayList<>();
        motosParqueadas.addAll(vehiculoParqueados);
        filtrarSoloMotos(motosParqueadas);
    }

    private void filtrarSoloMotos(List<VehiculoParqueado> motosParqueadas) {
        Iterator<VehiculoParqueado> iter = motosParqueadas.iterator();
        while (iter.hasNext()) {
            if (iter.next().getVehiculo().getTipo().getNombre().equals("Carro")) iter.remove();
        }
    }

    public void setCarrosParqueados(List<VehiculoParqueado> vehiculoParqueados) {
        carrosParqueados = new ArrayList<>();
        carrosParqueados.addAll(vehiculoParqueados);
        filtrarSoloCarros(carrosParqueados);
    }

    private void filtrarSoloCarros(List<VehiculoParqueado> carrosParqueados) {
        Iterator<VehiculoParqueado> iter = carrosParqueados.iterator();
        while (iter.hasNext()) {
            if (iter.next().getVehiculo().getTipo().getNombre().equals("Moto")) iter.remove();
        }
    }

    @Override
    public void iniciar() {
        validarInternetGetVehiculosParqueados();
    }

    public void getVehiculosParqueados() {
        parqueaderoBusinessLogic.getVehiculosParqueados(new Callback<List<VehiculoParqueadoDTO>>() {
            @Override
            public void onResponse(Call<List<VehiculoParqueadoDTO>> call,
                                   Response<List<VehiculoParqueadoDTO>> response) {

                if (response.isSuccessful()) {
                    List<VehiculoParqueado> vehiculoParqueados = new ArrayList<>();
                    for (VehiculoParqueadoDTO vehiculoParqueadoDTO : response.body()) {
                        vehiculoParqueados.add(Mapper.convertirDTOVehiculoParqueadoAModelo(vehiculoParqueadoDTO));
                    }
                    vista.mostrarVehiculosParqueados(vehiculoParqueados);
                }
            }

            @Override
            public void onFailure(Call<List<VehiculoParqueadoDTO>> call, Throwable t) {
                vista.mostrarMensajeError(t.getMessage());
            }
        });
    }

    public void validarInternetGetVehiculosParqueados() {
        if (getValidateInternet().isConnected()) {
            getVehiculosParqueados();
        } else {
            vista.mostrarMensajeError(R.string.texto_no_internet);
        }
    }

    @Override
    public void detener() {

    }

    public List<VehiculoParqueado> getCarrosParqueados() {
        return carrosParqueados;
    }

    public List<VehiculoParqueado> getMotosParqueadas() {
        return motosParqueadas;
    }
}
