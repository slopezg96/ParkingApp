package com.example.santiagolopez.parkingapp.presenters;


import com.example.santiagolopez.parkingapp.businesslogic.ParqueaderoBusinessLogic;
import com.example.santiagolopez.parkingapp.businesslogic.TipoVehiculoBusinessLogic;
import com.example.santiagolopez.parkingapp.model.TipoVehiculo;
import com.example.santiagolopez.parkingapp.model.Vehiculo;
import com.example.santiagolopez.parkingapp.services.dto.TipoVehiculoDTO;
import com.example.santiagolopez.parkingapp.services.dto.VehiculoParqueadoDTO;
import com.example.santiagolopez.parkingapp.util.Mapper;
import com.example.santiagolopez.parkingapp.util.Respuesta;
import com.example.santiagolopez.parkingapp.view.interfaces.INuevoVehiculoView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by santiago.lopez on 1/24/18.
 */

public class NuevoVehiculoPresenter extends BasePresenter<INuevoVehiculoView> {

    private ParqueaderoBusinessLogic parqueaderoBusinessLogic;
    private TipoVehiculoBusinessLogic tipoVehiculoBusinessLogic;

    @Inject
    public NuevoVehiculoPresenter(ParqueaderoBusinessLogic parqueaderoBusinessLogic,
                                  TipoVehiculoBusinessLogic tipoVehiculoBusinessLogic) {
        this.parqueaderoBusinessLogic = parqueaderoBusinessLogic;
        this.tipoVehiculoBusinessLogic = tipoVehiculoBusinessLogic;
    }

    @Override
    public void iniciar() {
        tipoVehiculoBusinessLogic.getTiposVehiculos(new Callback<List<TipoVehiculoDTO>>() {
            @Override
            public void onResponse(Call<List<TipoVehiculoDTO>> call, Response<List<TipoVehiculoDTO>> response) {
                if (response.isSuccessful()) {
                    if (response.isSuccessful()) {
                        List<TipoVehiculo> tipoVehiculos = new ArrayList<>();
                        for (TipoVehiculoDTO tipoVehiculoDTO : response.body()) {
                            tipoVehiculos.add(Mapper.convertirDTOTipoVehiculoAModelo(tipoVehiculoDTO));
                        }
                        vista.mostrarTiposVehiculos(tipoVehiculos);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<TipoVehiculoDTO>> call, Throwable t) {

            }
        });
    }

    @Override
    public void detener() {

    }

    public void ingresoVehiculo(Vehiculo vehiculo) {

        Respuesta respuesta = parqueaderoBusinessLogic.validarDisponibilidadParqueadero(vehiculo);
        if (respuesta.respuesta) {
            parqueaderoBusinessLogic.ingresarVehiculo(new Callback<VehiculoParqueadoDTO>() {
                @Override
                public void onResponse(Call<VehiculoParqueadoDTO> call, Response<VehiculoParqueadoDTO> response) {
                    vista.refrescarDisponibilidadParqueadero(Mapper.convertirDTOVehiculoParqueadoAModelo(response.body()));
                    vista.cerrarDialog();
                }

                @Override
                public void onFailure(Call<VehiculoParqueadoDTO> call, Throwable t) {

                }
            }, vehiculo);
        }
    }
}
