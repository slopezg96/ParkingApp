package com.example.santiagolopez.parkingapp.repositories;

import com.example.santiagolopez.parkingapp.model.TipoVehiculo;
import com.example.santiagolopez.parkingapp.model.VehiculoParqueado;

import java.util.List;

/**
 * Created by santiago.lopez on 1/22/18.
 */

public interface IParkingRepository {

    List<TipoVehiculo> getTodosTiposVehiculo();

    List<VehiculoParqueado> getVehiculosParqueados();
}
