package com.example.santiagolopez.parkingapp.services;

import com.example.santiagolopez.parkingapp.VehiculoParqueadoDTO;
import com.example.santiagolopez.parkingapp.model.TipoVehiculo;
import com.example.santiagolopez.parkingapp.model.VehiculoParqueado;

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
    Call<List<TipoVehiculo>> getTodosTiposVehiculo();


    @GET("vehiculosParqueados/")
    Call<List<VehiculoParqueadoDTO>> getVehiculosParqueados();
}
