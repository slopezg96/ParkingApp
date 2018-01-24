package com.example.santiagolopez.parkingapp.businesslogic;

import com.example.santiagolopez.parkingapp.model.TipoVehiculo;
import com.example.santiagolopez.parkingapp.model.Vehiculo;
import com.example.santiagolopez.parkingapp.model.VehiculoParqueado;
import com.example.santiagolopez.parkingapp.repositories.IParkingRepository;
import com.example.santiagolopez.parkingapp.repositories.ParkingRepository;
import com.example.santiagolopez.parkingapp.util.DateHelper;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by santiago.lopez on 1/17/18.
 */

public class ParqueaderoBusinessLogic implements IParqueaderoBusinessLogic{

    private IParkingRepository iParkingRepository;
    private static final double VALOR_ADICIONAL_X_CILINDRAJE = 2000;
    public static final String MOTO = "Moto";
    public static final String CARRO = "Carro";

    public ParqueaderoBusinessLogic(IParkingRepository iParkingRepository) {
        this.iParkingRepository = iParkingRepository;
    }

    public void ingresoVehiculo(Vehiculo vehiculo) {
        if (hayCeldasDisponibles(vehiculo)) {
            if (estaDisponibleXPlacaYFecha(vehiculo.getPlaca(), new Date())) {
                addVehiculo(vehiculo);
            }
        }
    }

    public boolean hayCeldasDisponibles(Vehiculo vehiculo) {
        switch (vehiculo.getTipo().getNombre()) {
            case MOTO:
                return false;
            case CARRO:
                return false;
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

    public void addVehiculo(Vehiculo vehiculo) {
        VehiculoParqueado vehiculoParqueado = new VehiculoParqueado();
        vehiculoParqueado.getVehiculo().setPlaca(vehiculo.getPlaca());
        vehiculoParqueado.setFechaIngreso(new Date());
    }

    public double calcularTotalAPagar(Vehiculo vehiculo) {
        DateHelper dateHelper = new DateHelper(vehiculo.getFechaIngreso(), vehiculo.getFechaSalida());
        long dias = dateHelper.diferenciaDias();
        long horas = dateHelper.diferenciaHoras();
        double valorTotal;
        switch (vehiculo.getTipo().getNombre()) {
            case MOTO:
                if (dias > 0) {
                    valorTotal = (dias * vehiculo.getTipo().getValorDia()) + ((horas % 24) * vehiculo.getTipo().getValorHora());
                } else if (horas >= 9) {
                    valorTotal = vehiculo.getTipo().getValorDia();
                } else {
                    valorTotal = horas * vehiculo.getTipo().getValorHora();
                }
                return vehiculo.getCilindraje() > 500 ? valorTotal + VALOR_ADICIONAL_X_CILINDRAJE : valorTotal;

            case CARRO:
                if (dias > 0) {
                    valorTotal = (dias * vehiculo.getTipo().getValorDia()) + ((horas % 24) * vehiculo.getTipo().getValorHora());
                } else if (horas >= 9) {
                    valorTotal = vehiculo.getTipo().getValorDia();
                } else {
                    valorTotal = horas * vehiculo.getTipo().getValorHora();
                }
                return valorTotal;
        }
        return 0;
    }

    public void salidaVehiculo(Vehiculo vehiculo) {
        calcularTotalAPagar(vehiculo);
        liberarPuestoParqueadero(vehiculo);
    }

    public void liberarPuestoParqueadero(Vehiculo vehiculo) {

    }

    @Override
    public List<VehiculoParqueado> traerVehiculosParqueados() {
        return iParkingRepository.getVehiculosParqueados();
    }
}
