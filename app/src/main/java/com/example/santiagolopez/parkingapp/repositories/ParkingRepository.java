package com.example.santiagolopez.parkingapp.repositories;

import com.example.santiagolopez.parkingapp.model.Vehiculo;
import com.example.santiagolopez.parkingapp.model.VehiculoParqueado;
import com.example.santiagolopez.parkingapp.services.dto.VehiculoDTO;
import com.example.santiagolopez.parkingapp.services.dto.VehiculoParqueadoDTO;
import com.example.santiagolopez.parkingapp.services.IServices;
import com.example.santiagolopez.parkingapp.services.ServicesFactory;
import com.example.santiagolopez.parkingapp.util.RepositoryError;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by santiago.lopez on 1/22/18.
 */

public class ParkingRepository implements IParkingRepository {

    private IServices iServices;

    public ParkingRepository() {
        iServices = ServicesFactory.getInstance();
    }


    @Override
    public void getVehiculosParqueados(Callback<List<VehiculoParqueadoDTO>> callback) {
        iServices.getVehiculosParqueados().enqueue(callback);
    }

    @Override
    public void ingresarVehiculoParqueado(Callback<VehiculoParqueadoDTO> callback,
                                          VehiculoParqueadoDTO vehiculoParqueadoDTO) {
        try {
            iServices.ingresarVehiculoParqueado(vehiculoParqueadoDTO).enqueue(callback);
        } catch (RepositoryError repositoryError) {
            repositoryError.printStackTrace();
        }
    }

    @Override
    public void buscarVehiculoParqueadoXPlaca(Callback<VehiculoParqueadoDTO> callback, String placa) {
        iServices.getVehiculoParqueadoXPlaca(placa).enqueue(callback);
    }

    @Override
    public void cobrar(Callback<VehiculoParqueadoDTO> callback, VehiculoParqueadoDTO vehiculoParqueadoDTO) {
        iServices.cobrar(vehiculoParqueadoDTO).enqueue(callback);
    }

}
