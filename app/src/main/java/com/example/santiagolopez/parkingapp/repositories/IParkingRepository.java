package com.example.santiagolopez.parkingapp.repositories;

import com.example.santiagolopez.parkingapp.services.dto.VehiculoParqueadoDTO;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by santiago.lopez on 1/22/18.
 */

public interface IParkingRepository {

    void getVehiculosParqueados(Callback<List<VehiculoParqueadoDTO>> callback);

    void ingresarVehiculoParqueado(Callback<VehiculoParqueadoDTO> callback,
                                   VehiculoParqueadoDTO vehiculoParqueadoDTO);

    void buscarVehiculoParqueadoXPlaca(Callback<VehiculoParqueadoDTO> callback, String placa);

    void cobrar(Callback<VehiculoParqueadoDTO> callback, VehiculoParqueadoDTO vehiculoParqueadoDTO);
}
