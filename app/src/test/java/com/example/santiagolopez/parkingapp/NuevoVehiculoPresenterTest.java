package com.example.santiagolopez.parkingapp;

import android.support.annotation.NonNull;

import com.example.santiagolopez.parkingapp.businesslogic.ParqueaderoBusinessLogic;
import com.example.santiagolopez.parkingapp.businesslogic.TipoVehiculoBusinessLogic;
import com.example.santiagolopez.parkingapp.model.Vehiculo;
import com.example.santiagolopez.parkingapp.presenters.NuevoVehiculoPresenter;
import com.example.santiagolopez.parkingapp.repositories.IParkingRepository;
import com.example.santiagolopez.parkingapp.util.Respuesta;
import com.example.santiagolopez.parkingapp.view.interfaces.INuevoVehiculoView;
import com.example.santiagolopez.parkingapp.view.interfaces.IValidateInternet;

import org.junit.Assert;
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
 * Created by santiago.lopez on 2/1/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class NuevoVehiculoPresenterTest {


    @Mock
    INuevoVehiculoView iNuevoVehiculoView;


    @Mock
    IValidateInternet iValidateInternet;

    @Mock
    IParkingRepository iParkingRepository;

    NuevoVehiculoPresenter nuevoVehiculoPresenter;

    ParqueaderoBusinessLogic parqueaderoBusinessLogic;

    TipoVehiculoBusinessLogic tipoVehiculoBusinessLogic;

    Vehiculo vehiculo;

    @Before
    public void setUp() throws Exception {
        vehiculo = getVehiculo();
        parqueaderoBusinessLogic = Mockito.spy(new ParqueaderoBusinessLogic(iParkingRepository));
        nuevoVehiculoPresenter = Mockito.spy(new NuevoVehiculoPresenter(
                parqueaderoBusinessLogic, tipoVehiculoBusinessLogic));
        nuevoVehiculoPresenter.adicionarVista(iNuevoVehiculoView);
        nuevoVehiculoPresenter.setVehiculo(vehiculo);
        nuevoVehiculoPresenter.setValidateInternet(iValidateInternet);
    }

    @NonNull
    private Vehiculo getVehiculo() {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPlaca("STG567");
        return vehiculo;
    }

    @Test
    public void metodoValidarInternetGetTiposVehiculosSinConexionDeberiaMostrarUnMensaje() {
        when(iValidateInternet.isConnected()).thenReturn(false);
        nuevoVehiculoPresenter.validarInternetGetTiposVehiculos();
        verify(iNuevoVehiculoView).mostrarMensajeError(R.string.texto_no_internet);
        verify(nuevoVehiculoPresenter, never()).ingresoVehiculo();
    }

    @Test
    public void metodoValidarInternetGetTiposVehiculosConConexionLlamaIngresoVehiculo() {
        when(iValidateInternet.isConnected()).thenReturn(true);
        nuevoVehiculoPresenter.validarInternetGetTiposVehiculos();
        verify(iNuevoVehiculoView, never()).mostrarMensajeError(R.string.texto_no_internet);
        verify(nuevoVehiculoPresenter).ingresoVehiculo();
    }

    @Test
    public void cuandoNoHayaDisponibilidadDeParqueaderoDebeMostrarUnMensajeAlUsuario() {
        Respuesta respuesta = new Respuesta();
        respuesta.mensaje = R.string.texto_no_hay_parqueadero_disponible;
        when(nuevoVehiculoPresenter.validarDisponibilidad()).thenReturn(respuesta);
        nuevoVehiculoPresenter.ingresoVehiculo();
        verify(iNuevoVehiculoView).mostrarMensajeError(R.string.texto_no_hay_parqueadero_disponible);
        verify(nuevoVehiculoPresenter, never()).validarDisponibilidadXFechaYPlaca();
    }

    @Test
    public void cuandoHayaDisponibilidadDeParqueaderoValidarQueLaPlacaEstaHabilitadaParaEseDia() {
        Respuesta respuesta = new Respuesta();
        respuesta.respuesta = true;
        when(nuevoVehiculoPresenter.validarDisponibilidad()).thenReturn(respuesta);
        nuevoVehiculoPresenter.ingresoVehiculo();
        verify(nuevoVehiculoPresenter).validarDisponibilidadXFechaYPlaca();
        verify(iNuevoVehiculoView, never()).mostrarMensajeError(R.string.texto_no_hay_parqueadero_disponible);
    }

    @Test
    public void cuandoLaPlacaDelVehiculoNoEsteHabilitadaParaParquearMostrarUnMensajeAlUsuario() {
        Respuesta respuesta = new Respuesta();
        respuesta.mensaje = R.string.texto_placa_no_habilitada;
        vehiculo.setPlaca("ART456");
        when(nuevoVehiculoPresenter.validarDisponibilidadXFechaYPlaca()).thenReturn(respuesta);
        Assert.assertEquals(nuevoVehiculoPresenter.validarDisponibilidadXFechaYPlaca(), respuesta);
    }

}
