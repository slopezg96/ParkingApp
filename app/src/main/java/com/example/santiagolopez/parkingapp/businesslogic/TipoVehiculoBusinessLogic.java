package com.example.santiagolopez.parkingapp.businesslogic;

import com.example.santiagolopez.parkingapp.repositories.ITipoVehiculoRepository;
import com.example.santiagolopez.parkingapp.services.dto.TipoVehiculoDTO;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by santiago.lopez on 1/25/18.
 */

public class TipoVehiculoBusinessLogic{

    private ITipoVehiculoRepository iTipoVehiculoRepository;

    public TipoVehiculoBusinessLogic(ITipoVehiculoRepository iTipoVehiculoRepository) {
        this.iTipoVehiculoRepository = iTipoVehiculoRepository;
    }


    public void getTiposVehiculos(Callback<List<TipoVehiculoDTO>> callback){
        iTipoVehiculoRepository.getTiposVehiculo(callback);
    }

}
