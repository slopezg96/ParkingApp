package com.example.santiagolopez.parkingapp.services;

import com.example.santiagolopez.parkingapp.model.TipoVehiculo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by santiago.lopez on 1/22/18.
 */

public interface IServices {

    @GET("tiposVehiculo/")
    Call<List<TipoVehiculo>> getTodosTiposVehiculo();


}
