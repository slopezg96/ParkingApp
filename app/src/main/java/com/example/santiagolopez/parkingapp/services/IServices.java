package com.example.santiagolopez.parkingapp.services;

import com.example.santiagolopez.parkingapp.services.dto.TipoVehiculoDTO;
import com.example.santiagolopez.parkingapp.services.dto.VehiculoParqueadoDTO;
import com.example.santiagolopez.parkingapp.util.RepositoryError;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by santiago.lopez on 1/22/18.
 */

public interface IServices {

    @GET("tiposVehiculo/")
    Call<List<TipoVehiculoDTO>> getTodosTiposVehiculo();


    @GET("vehiculosParqueados/")
    Call<List<VehiculoParqueadoDTO>> getVehiculosParqueados();

    @GET("vehiculosParqueados/{placa}")
    Call<VehiculoParqueadoDTO> getVehiculoParqueadoXPlaca(@Path("placa") String placa);

    @POST("vehiculosParqueados/")
    Call<VehiculoParqueadoDTO> ingresarVehiculoParqueado(@Body VehiculoParqueadoDTO vehiculoParqueadoDTO) throws RepositoryError;

    @POST("vehiculosParqueados/cobrar")
    Call<VehiculoParqueadoDTO> cobrar(@Body VehiculoParqueadoDTO vehiculoParqueadoDTO);
}
