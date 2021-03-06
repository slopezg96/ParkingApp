package com.example.santiagolopez.parkingapp.repositories;

import com.example.santiagolopez.parkingapp.services.IServices;
import com.example.santiagolopez.parkingapp.services.ServicesFactory;
import com.example.santiagolopez.parkingapp.services.dto.TipoVehiculoDTO;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by santiago.lopez on 1/25/18.
 */

public class TipoVehiculoRepository implements ITipoVehiculoRepository {

    private IServices iServices;

    public TipoVehiculoRepository() {
        iServices = ServicesFactory.getInstance();
    }



    @Override
    public void getTiposVehiculo(Callback<List<TipoVehiculoDTO>> callback) {
        iServices.getTodosTiposVehiculo().enqueue(callback);
    }
}
