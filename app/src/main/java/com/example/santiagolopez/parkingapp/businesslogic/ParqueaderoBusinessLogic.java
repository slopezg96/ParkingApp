package com.example.santiagolopez.parkingapp.businesslogic;

import com.example.santiagolopez.parkingapp.model.Vehiculo;
import com.example.santiagolopez.parkingapp.model.VehiculoParqueado;
import com.example.santiagolopez.parkingapp.repositories.IParkingRepository;
import com.example.santiagolopez.parkingapp.services.dto.VehiculoParqueadoDTO;
import com.example.santiagolopez.parkingapp.util.Mapper;
import com.example.santiagolopez.parkingapp.util.RepositoryError;
import com.example.santiagolopez.parkingapp.util.Respuesta;
import com.example.santiagolopez.parkingapp.util.Validaciones;

import java.util.Date;
import java.util.List;

import retrofit2.Callback;


/**
 * Created by santiago.lopez on 1/17/18.
 */

public class ParqueaderoBusinessLogic {

    private IParkingRepository iParkingRepository;

    public ParqueaderoBusinessLogic(IParkingRepository iParkingRepository) {
        this.iParkingRepository = iParkingRepository;
    }

    private VehiculoParqueadoDTO crearEntidadVehiculoParqueadoDTO(Vehiculo vehiculo) {
        VehiculoParqueado vehiculoParqueado = new VehiculoParqueado();
        vehiculoParqueado.getVehiculo().setPlaca(vehiculo.getPlaca());
        vehiculoParqueado.getVehiculo().setCilindraje(vehiculo.getCilindraje());
        vehiculoParqueado.setFechaIngreso(new Date());
        vehiculoParqueado.getVehiculo().setTipo(vehiculo.getTipo());
        return Mapper.convertirModeloVehiculoParqueadoADTO(vehiculoParqueado);
    }

    public void ingresarVehiculo(Callback<VehiculoParqueadoDTO> callback, Vehiculo vehiculo) throws RepositoryError{
        Validaciones.validateNullParameter(callback, vehiculo);
        Validaciones.validateNullParameter(vehiculo.getPlaca(), vehiculo.getTipo());
        Validaciones.validateEmptyParameter(vehiculo.getPlaca());
        iParkingRepository.ingresarVehiculoParqueado(callback,
                crearEntidadVehiculoParqueadoDTO(vehiculo));
    }

    public void getVehiculosParqueados(Callback<List<VehiculoParqueadoDTO>> callback) {
        iParkingRepository.getVehiculosParqueados(callback);
    }

    public void buscarVehiculoParqueadoXPlaca(Callback<List<VehiculoParqueadoDTO>> callback, String placa) {
        iParkingRepository.buscarVehiculoParqueadoXPlaca(callback, placa);
    }

    public void cobrar(Callback<VehiculoParqueadoDTO> callback, VehiculoParqueadoDTO vehiculoParqueadoDTO) {
        iParkingRepository.cobrar(callback, vehiculoParqueadoDTO);
    }
}
