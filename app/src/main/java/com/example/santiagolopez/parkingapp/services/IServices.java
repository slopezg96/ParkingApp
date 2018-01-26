package com.example.santiagolopez.parkingapp.services;

import com.example.santiagolopez.parkingapp.services.dto.TipoVehiculoDTO;
import com.example.santiagolopez.parkingapp.services.dto.VehiculoParqueadoDTO;
import com.example.santiagolopez.parkingapp.model.TipoVehiculo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by santiago.lopez on 1/22/18.
 */

public interface IServices {

    @GET("tiposVehiculo/")
    Call<List<TipoVehiculoDTO>> getTodosTiposVehiculo();


    @GET("vehiculosParqueados/")
    Call<List<VehiculoParqueadoDTO>> getVehiculosParqueados();

    @POST("vehiculosParqueados/")
    Call<VehiculoParqueadoDTO> ingresarVehiculoParqueado(@Body VehiculoParqueadoDTO vehiculoParqueadoDTO);
}
