package com.example.santiagolopez.parkingapp;

import android.support.annotation.NonNull;

import com.example.santiagolopez.parkingapp.businesslogic.ParqueaderoBusinessLogic;
import com.example.santiagolopez.parkingapp.model.TipoVehiculo;
import com.example.santiagolopez.parkingapp.model.Vehiculo;
import com.example.santiagolopez.parkingapp.model.VehiculoParqueado;
import com.example.santiagolopez.parkingapp.presenters.SalidaVehiculoPresenter;
import com.example.santiagolopez.parkingapp.repositories.IParkingRepository;
import com.example.santiagolopez.parkingapp.util.DateHelper;
import com.example.santiagolopez.parkingapp.view.interfaces.ISalidaVehiculo;
import com.example.santiagolopez.parkingapp.view.interfaces.IValidateInternet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by santiago.lopez on 2/2/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class SalidaVehiculoPresenterTest {

    @Mock
    ISalidaVehiculo iSalidaVehiculo;


    @Mock
    IValidateInternet iValidateInternet;

    @Mock
    IParkingRepository iParkingRepository;

    SalidaVehiculoPresenter salidaVehiculoPresenter;
    ParqueaderoBusinessLogic parqueaderoBusinessLogic;
    VehiculoParqueado vehiculoParqueado;

    @Before
    public void setUp() throws Exception{
        parqueaderoBusinessLogic = Mockito.spy(new ParqueaderoBusinessLogic(iParkingRepository));
        salidaVehiculoPresenter = Mockito.spy(new SalidaVehiculoPresenter(parqueaderoBusinessLogic));
        salidaVehiculoPresenter.adicionarVista(iSalidaVehiculo);
        salidaVehiculoPresenter.setValidateInternet(iValidateInternet);
        salidaVehiculoPresenter.setVehiculoParqueado(getVehiculoParqueado());
    }

    @NonNull
    private VehiculoParqueado getVehiculoParqueado(){
        VehiculoParqueado vehiculoParqueado = new VehiculoParqueado();
        vehiculoParqueado.setVehiculo(getVehiculo());
        vehiculoParqueado.setFechaIngreso(DateHelper.convertirStringADate("2018-02-05 10:00:00"));
        vehiculoParqueado.setFechaSalida(DateHelper.convertirStringADate("2018-02-05 13:00:00"));
        return vehiculoParqueado;
    }

    @NonNull
    private TipoVehiculo getTipoVehiculoMoto(){
        TipoVehiculo tipoVehiculo = new TipoVehiculo();
        tipoVehiculo.setId(1L);
        tipoVehiculo.setNombre("Moto");
        tipoVehiculo.setValorHora(500);
        tipoVehiculo.setValorDia(4000);
        return tipoVehiculo;
    }

    @NonNull
    private Vehiculo getVehiculo(){
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setTipo(getTipoVehiculoMoto());
        vehiculo.setPlaca("STF94D");
        vehiculo.setCilindraje(150);
        return vehiculo;
    }

    @Test
    public void metodoValidarInternetBuscarVehiculoParqueadoXPlacaSinConexionMuestraUnMensaje(){
        String placa = "STF94D";
        when(iValidateInternet.isConnected()).thenReturn(false);
        salidaVehiculoPresenter.validarInternetBuscarVehiculoParqueadoXPlaca(placa);
        verify(iSalidaVehiculo).mostrarMensajeError(R.string.texto_no_internet);
        verify(salidaVehiculoPresenter, never()).buscarVehiculoParqueadoXPlaca(placa);
    }

    @Test
    public void metodoValidarInternetBuscarVehiculoParqueadoXPlacaConConexionLlamaBuscarVehiculoXPlaca(){
        String placa = "STF94D";
        when(iValidateInternet.isConnected()).thenReturn(true);
        salidaVehiculoPresenter.validarInternetBuscarVehiculoParqueadoXPlaca(placa);
        verify(iSalidaVehiculo, never()).mostrarMensajeError(R.string.texto_no_internet);
        verify(salidaVehiculoPresenter).buscarVehiculoParqueadoXPlaca(placa);
    }

    @Test
    public void metodoValidarInternetCobrarSinConexionMuestraUnMensaje(){
        when(iValidateInternet.isConnected()).thenReturn(false);
        salidaVehiculoPresenter.validarInternetCobrar();
        verify(iSalidaVehiculo).mostrarMensajeError(R.string.texto_no_internet);
        verify(salidaVehiculoPresenter, never()).cobrar();
    }

    @Test
    public void metodoValidarInternetCobrarConConexionLlamaCobrar(){
        when(iValidateInternet.isConnected()).thenReturn(true);
        salidaVehiculoPresenter.validarInternetCobrar();
        verify(iSalidaVehiculo, never()).mostrarMensajeError(R.string.texto_no_internet);
        verify(salidaVehiculoPresenter).cobrar();
    }

}
