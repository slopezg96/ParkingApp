package com.example.santiagolopez.parkingapp.repositories;

import com.example.santiagolopez.parkingapp.services.dto.TipoVehiculoDTO;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by santiago.lopez on 1/25/18.
 */

public interface ITipoVehiculoRepository {
    void getTiposVehiculo(Callback<List<TipoVehiculoDTO>> callback);
}
