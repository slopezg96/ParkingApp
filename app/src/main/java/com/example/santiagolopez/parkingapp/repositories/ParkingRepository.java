package com.example.santiagolopez.parkingapp.repositories;

import com.example.santiagolopez.parkingapp.VehiculoParqueadoDTO;
import com.example.santiagolopez.parkingapp.model.TipoVehiculo;
import com.example.santiagolopez.parkingapp.model.VehiculoParqueado;
import com.example.santiagolopez.parkingapp.services.IServices;
import com.example.santiagolopez.parkingapp.services.ServicesFactory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by santiago.lopez on 1/22/18.
 */

public class ParkingRepository implements IParkingRepository{

    private IServices iServices;

    public ParkingRepository() {
        iServices = ServicesFactory.getInstance();
    }


    @Override
    public List<TipoVehiculo> getTodosTiposVehiculo() {
        final List<TipoVehiculo> listaTiposVehiculo = new ArrayList<>();
        iServices.getTodosTiposVehiculo().enqueue(new Callback<List<TipoVehiculo>>() {
            @Override
            public void onResponse(Call<List<TipoVehiculo>> call, Response<List<TipoVehiculo>> response) {
                if(response.isSuccessful()) {
                    listaTiposVehiculo.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<TipoVehiculo>> call, Throwable t) {
                t.getMessage();
            }
        });
        return listaTiposVehiculo;
    }

    @Override
    public List<VehiculoParqueado> getVehiculosParqueados() {
        final List<VehiculoParqueado> vehiculosParqueados = new ArrayList<>();
        iServices.getVehiculosParqueados().enqueue(new Callback<List<VehiculoParqueadoDTO>>() {
            @Override
            public void onResponse(Call<List<VehiculoParqueadoDTO>> call, Response<List<VehiculoParqueadoDTO>> response) {
                if(response.isSuccessful()) {
                    response.body();
                }
            }

            @Override
            public void onFailure(Call<List<VehiculoParqueadoDTO>> call, Throwable t) {
                t.getMessage();
            }
        });
        return vehiculosParqueados;
    }
}
