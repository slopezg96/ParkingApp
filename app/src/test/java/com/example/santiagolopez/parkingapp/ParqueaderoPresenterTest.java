package com.example.santiagolopez.parkingapp;

import com.example.santiagolopez.parkingapp.businesslogic.ParqueaderoBusinessLogic;
import com.example.santiagolopez.parkingapp.model.VehiculoParqueado;
import com.example.santiagolopez.parkingapp.presenters.ParqueaderoPresenter;
import com.example.santiagolopez.parkingapp.repositories.IParkingRepository;
import com.example.santiagolopez.parkingapp.services.dto.VehiculoParqueadoDTO;
import com.example.santiagolopez.parkingapp.util.RepositoryError;
import com.example.santiagolopez.parkingapp.view.interfaces.IHomeView;
import com.example.santiagolopez.parkingapp.view.interfaces.IValidateInternet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by santiago.lopez on 1/31/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class ParqueaderoPresenterTest {

    @Mock
    IHomeView iHomeView;

    @Mock
    IValidateInternet iValidateInternet;

    @Mock
    IParkingRepository iParkingRepository;

    ParqueaderoPresenter parqueaderoPresenter;

    ParqueaderoBusinessLogic parqueaderoBusinessLogic;

    @Before
    public void setUp() throws Exception{
        parqueaderoBusinessLogic = Mockito.spy(new ParqueaderoBusinessLogic(iParkingRepository));
        parqueaderoPresenter = Mockito.spy(new ParqueaderoPresenter(parqueaderoBusinessLogic));
        parqueaderoPresenter.adicionarVista(iHomeView);
        parqueaderoPresenter.setValidateInternet(iValidateInternet);
    }

    @Test
    public void metodoValidarInternetSinConexionDeberiaMostrarUnMensaje(){
        when(iValidateInternet.isConnected()).thenReturn(false);
        parqueaderoPresenter.validarInternetGetVehiculosParqueados();
        verify(iHomeView).mostrarMensajeError(R.string.texto_no_internet);
        verify(parqueaderoPresenter, never()).getVehiculosParqueados();
    }

    @Test
    public void metodoValidarInternetConConexionLlamaGetVehiculosParqueados(){
        when(iValidateInternet.isConnected()).thenReturn(true);
        parqueaderoPresenter.validarInternetGetVehiculosParqueados();
        verify(iHomeView, never()).mostrarMensajeError(R.string.texto_no_internet);
        verify(parqueaderoPresenter).getVehiculosParqueados();
    }


}
