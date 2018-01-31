package com.example.santiagolopez.parkingapp;

import com.example.santiagolopez.parkingapp.businesslogic.ParqueaderoBusinessLogic;
import com.example.santiagolopez.parkingapp.model.TipoVehiculo;
import com.example.santiagolopez.parkingapp.model.Vehiculo;
import com.example.santiagolopez.parkingapp.repositories.IParkingRepository;
import com.example.santiagolopez.parkingapp.services.dto.VehiculoParqueadoDTO;
import com.example.santiagolopez.parkingapp.util.Constants;
import com.example.santiagolopez.parkingapp.util.RepositoryError;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(MockitoJUnitRunner.class)
public class ParqueaderoBusinessLogicTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private com.example.santiagolopez.parkingapp.businesslogic.ParqueaderoBusinessLogic parqueaderoBusinessLogic;

    @Mock
    IParkingRepository iParkingRepository;

    @Before
    public void setUp() {
        parqueaderoBusinessLogic = Mockito.spy(new com.example.santiagolopez.parkingapp.businesslogic.ParqueaderoBusinessLogic(iParkingRepository));
    }

    public TipoVehiculo getTipoMoto(){
        TipoVehiculo tipoVehiculo = new TipoVehiculo();
        tipoVehiculo.setId(1L);
        tipoVehiculo.setNombre("Moto");
        tipoVehiculo.setValorDia(4000);
        tipoVehiculo.setValorHora(500);
        return tipoVehiculo;
    }

    public TipoVehiculo getTipoCarro() {
        TipoVehiculo tipoVehiculo = new TipoVehiculo();
        tipoVehiculo.setId(2L);
        tipoVehiculo.setNombre("Carro");
        tipoVehiculo.setValorDia(8000);
        tipoVehiculo.setValorHora(1000);
        return tipoVehiculo;
    }

    @Test
    public void metodoIngresarVehiculoConVehiculoParqueaderoNulosRetornaUnaExcepcion() throws RepositoryError{
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(Constants.NULL_PARAMETERS);
        parqueaderoBusinessLogic.ingresarVehiculo(null, null);
    }

    @Test
    public void metodoIngresarVehiculoConPlacaNulaRetornaUnaExcepcion() throws RepositoryError{
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(Constants.NULL_PARAMETERS);
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPlaca(null);
        parqueaderoBusinessLogic.ingresarVehiculo(null, vehiculo);
    }

    @Test
    public void metodoIngresarVehiculoConPlacaVaciaRetornaUnaExcepcion() throws RepositoryError{
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(Constants.EMPTY_PARAMETERS);
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPlaca("");
        parqueaderoBusinessLogic.ingresarVehiculo(new Callback<VehiculoParqueadoDTO>() {
            @Override
            public void onResponse(Call<VehiculoParqueadoDTO> call, Response<VehiculoParqueadoDTO> response) {

            }

            @Override
            public void onFailure(Call<VehiculoParqueadoDTO> call, Throwable t) {

            }
        }, vehiculo);
    }

}