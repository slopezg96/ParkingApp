package com.example.santiagolopez.parkingapp.view.interfaces;

import com.example.santiagolopez.parkingapp.model.TipoVehiculo;
import com.example.santiagolopez.parkingapp.model.VehiculoParqueado;

import java.util.List;

/**
 * Created by santiago.lopez on 1/24/18.
 */

public interface INuevoVehiculoView extends BaseView{
    void mostrarTiposVehiculos(List<TipoVehiculo> tipoVehiculos);

    void cerrarDialog();

    void refrescarDisponibilidadParqueadero(VehiculoParqueado vehiculoParqueado);
}
