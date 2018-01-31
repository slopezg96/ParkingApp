package com.example.santiagolopez.parkingapp.services;

import com.example.santiagolopez.parkingapp.model.Vehiculo;
import com.example.santiagolopez.parkingapp.services.dto.TipoVehiculoDTO;
import com.example.santiagolopez.parkingapp.services.dto.VehiculoDTO;
import com.example.santiagolopez.parkingapp.services.dto.VehiculoParqueadoDTO;
import com.example.santiagolopez.parkingapp.model.TipoVehiculo;
import com.example.santiagolopez.parkingapp.util.RepositoryError;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by santiago.lopez on 1/22/18.
 */

public interface IServices {

    @GET("tiposVehiculo/")
    Call<List<TipoVehiculoDTO>> getTodosTiposVehiculo();


    @GET("vehiculosParqueados/")
    Call<List<VehiculoParqueadoDTO>> getVehiculosParqueados();

    @GET("vehiculosParqueados")
    Call<List<VehiculoParqueadoDTO>> getVehiculoParqueadoXPlaca(@Query("placa") String placa);

    @POST("vehiculosParqueados/")
    Call<VehiculoParqueadoDTO> ingresarVehiculoParqueado(@Body VehiculoParqueadoDTO vehiculoParqueadoDTO) throws RepositoryError;

    @POST("vehiculosParqueados/cobrar")
    Call<VehiculoParqueadoDTO> cobrar(@Body VehiculoParqueadoDTO vehiculoParqueadoDTO);
}
