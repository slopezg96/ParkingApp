package com.example.santiagolopez.parkingapp.view.interfaces;

import com.example.santiagolopez.parkingapp.model.VehiculoParqueado;

import java.util.List;

/**
 * Created by santiago.lopez on 1/22/18.
 */

public interface IHomeView extends BaseView {

    void mostrarVehiculosParqueados(List<VehiculoParqueado> vehiculosParqueados);

    void refrescarDisponibilidadParqueadero(VehiculoParqueado vehiculoParqueado);
}
