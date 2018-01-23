package com.example.santiagolopez.parkingapp.businesslogic;

import com.example.santiagolopez.parkingapp.model.VehiculoParqueado;

import java.util.List;

/**
 * Created by santiago.lopez on 1/22/18.
 */

public interface IParqueaderoBusinessLogic {

    List<VehiculoParqueado> traerVehiculosParqueados();
}
