package com.example.santiagolopez.parkingapp.util;

import com.example.santiagolopez.parkingapp.model.TipoVehiculo;
import com.example.santiagolopez.parkingapp.model.Vehiculo;
import com.example.santiagolopez.parkingapp.services.dto.TipoVehiculoDTO;
import com.example.santiagolopez.parkingapp.services.dto.VehiculoDTO;
import com.example.santiagolopez.parkingapp.services.dto.VehiculoParqueadoDTO;
import com.example.santiagolopez.parkingapp.model.VehiculoParqueado;

import java.text.ParseException;


/**
 * Created by santiago.lopez on 1/25/18.
 */

public class Mapper {

    public static VehiculoParqueado convertirDTOVehiculoParqueadoAModelo(VehiculoParqueadoDTO vehiculoParqueadoDTO) {
        VehiculoParqueado vehiculoParqueado = new VehiculoParqueado();
        vehiculoParqueado.getVehiculo().setPlaca(vehiculoParqueadoDTO.placa);
        vehiculoParqueado.getVehiculo().getTipo().setNombre(vehiculoParqueadoDTO.tipoVehiculo);
        vehiculoParqueado.getVehiculo().getTipo().setId(vehiculoParqueadoDTO.idTipoVehiculo);
        vehiculoParqueado.getVehiculo().getTipo().setValorDia(vehiculoParqueadoDTO.valorDia);
        vehiculoParqueado.getVehiculo().getTipo().setValorHora(vehiculoParqueadoDTO.valorHora);
        vehiculoParqueado.getVehiculo().setCilindraje(vehiculoParqueadoDTO.cilindraje);
        vehiculoParqueado.setFechaIngreso(DateHelper.convertirStringADate(vehiculoParqueadoDTO.fechaIngreso));
        vehiculoParqueado.setValor(vehiculoParqueadoDTO.valor != null && !vehiculoParqueadoDTO.valor.isEmpty() ?
                Double.parseDouble(vehiculoParqueadoDTO.valor) : 0);
        return vehiculoParqueado;
    }

    public static TipoVehiculo convertirDTOTipoVehiculoAModelo(TipoVehiculoDTO tipoVehiculoDTO) {
        TipoVehiculo tipoVehiculo = new TipoVehiculo();
        tipoVehiculo.setId(tipoVehiculoDTO.id);
        tipoVehiculo.setNombre(tipoVehiculoDTO.nombre);
        tipoVehiculo.setValorHora(tipoVehiculoDTO.valorHora);
        tipoVehiculo.setValorDia(tipoVehiculoDTO.valorDia);
        return tipoVehiculo;
    }

    public static VehiculoParqueadoDTO convertirModeloVehiculoParqueadoADTO(VehiculoParqueado vehiculoParqueado) {
        VehiculoParqueadoDTO vehiculoParqueadoDTO = new VehiculoParqueadoDTO();
        vehiculoParqueadoDTO.placa = vehiculoParqueado.getVehiculo().getPlaca();
        vehiculoParqueadoDTO.cilindraje = vehiculoParqueado.getVehiculo().getCilindraje();
        vehiculoParqueadoDTO.tipoVehiculo = vehiculoParqueado.getVehiculo().getTipo().getNombre();
        vehiculoParqueadoDTO.idTipoVehiculo = vehiculoParqueado.getVehiculo().getTipo().getId();
        vehiculoParqueadoDTO.valorDia = vehiculoParqueado.getVehiculo().getTipo().getValorDia();
        vehiculoParqueadoDTO.valorHora = vehiculoParqueado.getVehiculo().getTipo().getValorHora();
        try {
            vehiculoParqueadoDTO.fechaIngreso = DateHelper.convertirDateAString(vehiculoParqueado.getFechaIngreso());
            if (vehiculoParqueado.getFechaSalida() != null)
                vehiculoParqueadoDTO.fechaSalida = DateHelper.convertirDateAString(vehiculoParqueado.getFechaSalida());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return vehiculoParqueadoDTO;
    }

    public static VehiculoDTO convertirModeloVehiculoADTO(Vehiculo vehiculo) {
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.cilindraje = vehiculo.getCilindraje();
        vehiculoDTO.placa = vehiculo.getPlaca();
        return vehiculoDTO;
    }
}
