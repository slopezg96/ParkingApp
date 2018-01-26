package com.example.santiagolopez.parkingapp.businesslogic;

import com.example.santiagolopez.parkingapp.model.Vehiculo;
import com.example.santiagolopez.parkingapp.model.VehiculoParqueado;
import com.example.santiagolopez.parkingapp.repositories.IParkingRepository;
import com.example.santiagolopez.parkingapp.services.dto.VehiculoParqueadoDTO;
import com.example.santiagolopez.parkingapp.util.DateHelper;
import com.example.santiagolopez.parkingapp.util.Mapper;
import com.example.santiagolopez.parkingapp.util.Respuesta;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Callback;


/**
 * Created by santiago.lopez on 1/17/18.
 */

public class ParqueaderoBusinessLogic {

    private IParkingRepository iParkingRepository;
    private static final double VALOR_ADICIONAL_X_CILINDRAJE = 2000;
    public static final String MOTO = "Moto";
    public static final String CARRO = "Carro";

    public ParqueaderoBusinessLogic(IParkingRepository iParkingRepository) {
        this.iParkingRepository = iParkingRepository;
    }

    public Respuesta validarDisponibilidadParqueadero(Vehiculo vehiculo) {
        Respuesta respuesta = new Respuesta();

        if (hayCeldasDisponibles(vehiculo)) {
            if (estaDisponibleXPlacaYFecha(vehiculo.getPlaca(), new Date())) {
                respuesta.respuesta = true;
            } else {
                respuesta.mensaje = "La placa de su vehículo no está habilitada para ingresar el día de hoy";
            }
        } else {
            respuesta.mensaje = "No hay celdas disponibles en este momento";
        }

        return respuesta;
    }

    public boolean hayCeldasDisponibles(Vehiculo vehiculo) {
        switch (vehiculo.getTipo().getNombre()) {
            case MOTO:
                return true;
            case CARRO:
                return true;
            default:
                return false;
        }
    }

    public boolean estaDisponibleXPlacaYFecha(String placa, Date fechaIngreso) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaIngreso);

        return !(placa.substring(0, 1).equals("A") && !(calendar.get(Calendar.DAY_OF_WEEK) == 1
                || calendar.get(Calendar.DAY_OF_WEEK) == 2));
    }

    private VehiculoParqueadoDTO crearEntidadVehiculoParqueadoDTO(Vehiculo vehiculo) {
        VehiculoParqueado vehiculoParqueado = new VehiculoParqueado();
        vehiculoParqueado.getVehiculo().setPlaca(vehiculo.getPlaca());
        vehiculoParqueado.getVehiculo().setCilindraje(vehiculo.getCilindraje());
        vehiculoParqueado.setFechaIngreso(new Date());
        vehiculoParqueado.getVehiculo().getTipo().setNombre(vehiculo.getTipo().getNombre());
        return Mapper.convertirModeloVehiculoParqueadoADTO(vehiculoParqueado);
    }

    public void ingresarVehiculo(Callback<VehiculoParqueadoDTO> callback, Vehiculo vehiculo) {
        iParkingRepository.ingresarVehiculoParqueado(callback,
                crearEntidadVehiculoParqueadoDTO(vehiculo));
    }

    public double calcularTotalAPagar(VehiculoParqueado vehiculoParqueado) {
        DateHelper dateHelper = new DateHelper(vehiculoParqueado.getFechaIngreso(), vehiculoParqueado.getFechaSalida());
        long dias = dateHelper.diferenciaDias();
        long horas = dateHelper.diferenciaHoras();
        double valorTotal;
        switch (vehiculoParqueado.getVehiculo().getTipo().getNombre()) {
            case MOTO:
                if (dias > 0) {
                    valorTotal = (dias * vehiculoParqueado.getVehiculo().getTipo().getValorDia()) + ((horas % 24) * vehiculoParqueado.getVehiculo().getTipo().getValorHora());
                } else if (horas >= 9) {
                    valorTotal = vehiculoParqueado.getVehiculo().getTipo().getValorDia();
                } else {
                    valorTotal = horas * vehiculoParqueado.getVehiculo().getTipo().getValorHora();
                }
                return vehiculoParqueado.getVehiculo().getCilindraje() > 500 ? valorTotal + VALOR_ADICIONAL_X_CILINDRAJE : valorTotal;

            case CARRO:
                if (dias > 0) {
                    valorTotal = (dias * vehiculoParqueado.getVehiculo().getTipo().getValorDia()) + ((horas % 24) * vehiculoParqueado.getVehiculo().getTipo().getValorHora());
                } else if (horas >= 9) {
                    valorTotal = vehiculoParqueado.getVehiculo().getTipo().getValorDia();
                } else {
                    valorTotal = horas * vehiculoParqueado.getVehiculo().getTipo().getValorHora();
                }
                return valorTotal;
        }
        return 0;
    }

    public void salidaVehiculo(VehiculoParqueado vehiculoParqueado) {
        calcularTotalAPagar(vehiculoParqueado);
        liberarPuestoParqueadero(vehiculoParqueado);
    }

    public void liberarPuestoParqueadero(VehiculoParqueado vehiculoParqueado) {

    }


    public void getVehiculosParqueados(Callback<List<VehiculoParqueadoDTO>> callback) {
        iParkingRepository.getVehiculosParqueados(callback);
    }

}
