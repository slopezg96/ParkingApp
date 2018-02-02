package com.example.santiagolopez.parkingapp.presenters;


import com.example.santiagolopez.parkingapp.R;
import com.example.santiagolopez.parkingapp.businesslogic.ParqueaderoBusinessLogic;
import com.example.santiagolopez.parkingapp.businesslogic.TipoVehiculoBusinessLogic;
import com.example.santiagolopez.parkingapp.model.TipoVehiculo;
import com.example.santiagolopez.parkingapp.model.Vehiculo;
import com.example.santiagolopez.parkingapp.model.VehiculoParqueado;
import com.example.santiagolopez.parkingapp.services.dto.TipoVehiculoDTO;
import com.example.santiagolopez.parkingapp.services.dto.VehiculoParqueadoDTO;
import com.example.santiagolopez.parkingapp.util.Mapper;
import com.example.santiagolopez.parkingapp.util.RepositoryError;
import com.example.santiagolopez.parkingapp.util.Respuesta;
import com.example.santiagolopez.parkingapp.view.interfaces.INuevoVehiculoView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by santiago.lopez on 1/24/18.
 */

public class NuevoVehiculoPresenter extends BasePresenter<INuevoVehiculoView> {

    private ParqueaderoBusinessLogic parqueaderoBusinessLogic;
    private TipoVehiculoBusinessLogic tipoVehiculoBusinessLogic;
    private Vehiculo vehiculo;
    private List<VehiculoParqueado> motosParqueadas;
    private List<VehiculoParqueado> carrosParqueados;
    private static final String MOTO = "Moto";
    private static final String CARRO = "Carro";

    @Inject
    public NuevoVehiculoPresenter(ParqueaderoBusinessLogic parqueaderoBusinessLogic,
                                  TipoVehiculoBusinessLogic tipoVehiculoBusinessLogic) {
        this.parqueaderoBusinessLogic = parqueaderoBusinessLogic;
        this.tipoVehiculoBusinessLogic = tipoVehiculoBusinessLogic;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public void iniciar() {
        if (getValidateInternet().isConnected()) {
            tipoVehiculoBusinessLogic.getTiposVehiculos(new Callback<List<TipoVehiculoDTO>>() {
                @Override
                public void onResponse(Call<List<TipoVehiculoDTO>> call, Response<List<TipoVehiculoDTO>> response) {
                    if (response.isSuccessful()) {
                        if (response.isSuccessful()) {
                            List<TipoVehiculo> tipoVehiculos = new ArrayList<>();
                            for (TipoVehiculoDTO tipoVehiculoDTO : response.body()) {
                                tipoVehiculos.add(Mapper.convertirDTOTipoVehiculoAModelo(tipoVehiculoDTO));
                            }
                            vista.mostrarTiposVehiculos(tipoVehiculos);
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<TipoVehiculoDTO>> call, Throwable t) {
                    vista.mostrarMensajeError(t.getMessage());
                }
            });
        } else {
            vista.mostrarMensajeError(R.string.texto_no_internet);
        }
    }

    @Override
    public void detener() {

    }

    public Respuesta validarDisponibilidadXFechaYPlaca() {
        Respuesta respuesta = new Respuesta();
        if (estaDisponibleXPlacaYFecha(vehiculo.getPlaca(), new Date())) {
            respuesta.respuesta = true;
        } else {
            respuesta.mensaje = R.string.texto_placa_no_habilitada;
        }
        return respuesta;
    }


    public boolean estaDisponibleXPlacaYFecha(String placa, Date fechaIngreso) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaIngreso);

        return !(placa.substring(0, 1).equals("A") && !(calendar.get(Calendar.DAY_OF_WEEK) == 1
                || calendar.get(Calendar.DAY_OF_WEEK) == 2));
    }

    public void ingresoVehiculo() {
        if (validarDisponibilidad().respuesta) {
            Respuesta respuesta = validarDisponibilidadXFechaYPlaca();
            if (respuesta.respuesta) {
                try {
                    parqueaderoBusinessLogic.ingresarVehiculo(new Callback<VehiculoParqueadoDTO>() {
                        @Override
                        public void onResponse(Call<VehiculoParqueadoDTO> call, Response<VehiculoParqueadoDTO> response) {
                            vista.refrescarDisponibilidadParqueadero(Mapper.convertirDTOVehiculoParqueadoAModelo(response.body()));
                            vista.cerrarDialog();
                        }

                        @Override
                        public void onFailure(Call<VehiculoParqueadoDTO> call, Throwable t) {
                            vista.mostrarMensajeError(t.getMessage());
                        }
                    }, vehiculo);
                } catch (RepositoryError repositoryError) {
                    repositoryError.printStackTrace();
                }
            } else {
                vista.mostrarMensajeError(respuesta.mensaje);
            }
        } else {
            vista.mostrarMensajeError(R.string.texto_no_hay_parqueadero_disponible);
        }

    }

    public Respuesta validarDisponibilidad() {
        Respuesta respuesta = new Respuesta();
        switch (vehiculo.getTipo().getNombre()) {
            case MOTO:
                respuesta.respuesta = motosParqueadas.size() < 10;
                break;
            case CARRO:
                respuesta.respuesta = carrosParqueados.size() < 20;
                break;
        }
        return respuesta;
    }

    public void setMotosParqueadas(List<VehiculoParqueado> motosParqueadas) {
        this.motosParqueadas = motosParqueadas;
    }

    public void setCarrosParqueados(List<VehiculoParqueado> carrosParqueados) {
        this.carrosParqueados = carrosParqueados;
    }

    public void validarInternetGetTiposVehiculos() {
        if (getValidateInternet().isConnected()) {
            ingresoVehiculo();
        } else {
            vista.mostrarMensajeError(R.string.texto_no_internet);

        }
    }
}
