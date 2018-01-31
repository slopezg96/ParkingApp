package com.example.santiagolopez.parkingapp.view.interfaces;

import com.example.santiagolopez.parkingapp.model.VehiculoParqueado;

/**
 * Created by santiago.lopez on 1/29/18.
 */

public interface ISalidaVehiculo extends BaseView{
    void mostrarInformacionVehiculoParqueado(VehiculoParqueado vehiculoParqueado);

    void mostrarValortotal(VehiculoParqueado vehiculoParqueado);

}
