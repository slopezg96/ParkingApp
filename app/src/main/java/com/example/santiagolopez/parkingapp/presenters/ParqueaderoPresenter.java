package com.example.santiagolopez.parkingapp.presenters;

import com.example.santiagolopez.parkingapp.businesslogic.ParqueaderoBusinessLogic;
import com.example.santiagolopez.parkingapp.model.VehiculoParqueado;
import com.example.santiagolopez.parkingapp.services.dto.VehiculoParqueadoDTO;
import com.example.santiagolopez.parkingapp.util.Mapper;
import com.example.santiagolopez.parkingapp.view.interfaces.IHomeView;

import java.util.ArrayList;
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

    @Inject
    public ParqueaderoPresenter(ParqueaderoBusinessLogic parqueaderoBusinessLogic) {
        this.parqueaderoBusinessLogic = parqueaderoBusinessLogic;
    }

    @Override
    public void iniciar() {
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

            }
        });
    }

    @Override
    public void detener() {

    }
}
