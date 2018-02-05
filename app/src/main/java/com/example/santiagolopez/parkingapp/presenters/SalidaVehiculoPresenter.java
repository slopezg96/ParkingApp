package com.example.santiagolopez.parkingapp.presenters;

import com.example.santiagolopez.parkingapp.R;
import com.example.santiagolopez.parkingapp.businesslogic.ParqueaderoBusinessLogic;
import com.example.santiagolopez.parkingapp.model.VehiculoParqueado;
import com.example.santiagolopez.parkingapp.services.dto.VehiculoParqueadoDTO;
import com.example.santiagolopez.parkingapp.util.Mapper;
import com.example.santiagolopez.parkingapp.view.interfaces.ISalidaVehiculo;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by santiago.lopez on 1/29/18.
 */

public class SalidaVehiculoPresenter extends BasePresenter<ISalidaVehiculo> {

    private ParqueaderoBusinessLogic parqueaderoBusinessLogic;
    private VehiculoParqueado vehiculoParqueado;

    @Inject
    public SalidaVehiculoPresenter(ParqueaderoBusinessLogic parqueaderoBusinessLogic) {
        this.parqueaderoBusinessLogic = parqueaderoBusinessLogic;
    }

    @Override
    public void iniciar() {

    }

    @Override
    public void detener() {

    }

    public void buscarVehiculoParqueadoXPlaca(String placa) {
        parqueaderoBusinessLogic.buscarVehiculoParqueadoXPlaca(new Callback<VehiculoParqueadoDTO>() {
            @Override
            public void onResponse(Call<VehiculoParqueadoDTO> call, Response<VehiculoParqueadoDTO> response) {
                if (response.isSuccessful()) {
                    vista.mostrarInformacionVehiculoParqueado(
                            Mapper.convertirDTOVehiculoParqueadoAModelo(response.body()));
                }else {
                    vista.mostrarMensajeError(vista.getContext().getString(R.string.texto_vehiculo_con_la_placa)
                            + placa + vista.getContext().getString(R.string.texto_no_se_encentra_parqueado));
                }
            }

            @Override
            public void onFailure(Call<VehiculoParqueadoDTO> call, Throwable t) {
                vista.mostrarMensajeError(t.getMessage());
            }
        }, placa);

    }

    public void validarInternetBuscarVehiculoParqueadoXPlaca(String placa) {
        if (getValidateInternet().isConnected()) {
            buscarVehiculoParqueadoXPlaca(placa);
        } else {
            vista.mostrarMensajeError(R.string.texto_no_internet);
        }
    }

    public void validarInternetCobrar() {
        if (getValidateInternet().isConnected()) {
            cobrar();
        } else {
            vista.mostrarMensajeError(R.string.texto_no_internet);
        }
    }

    public void setVehiculoParqueado(VehiculoParqueado vehiculoParqueado) {
        this.vehiculoParqueado = vehiculoParqueado;
    }

    public void cobrar() {
        vehiculoParqueado.setFechaSalida(new Date());
        parqueaderoBusinessLogic.cobrar(new Callback<VehiculoParqueadoDTO>() {
            @Override
            public void onResponse(Call<VehiculoParqueadoDTO> call, Response<VehiculoParqueadoDTO> response) {
                if (response.isSuccessful()) {
                    vehiculoParqueado = Mapper.convertirDTOVehiculoParqueadoAModelo(response.body());
                    vista.mostrarValortotal(vehiculoParqueado);
                }
            }

            @Override
            public void onFailure(Call<VehiculoParqueadoDTO> call, Throwable t) {
                vista.mostrarMensajeError(t.getMessage());
            }
        }, Mapper.convertirModeloVehiculoParqueadoADTO(vehiculoParqueado));
    }

}
