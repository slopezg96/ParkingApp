package com.example.santiagolopez.parkingapp;

import com.example.santiagolopez.parkingapp.businesslogic.ParqueaderoBusinessLogic;
import com.example.santiagolopez.parkingapp.model.TipoVehiculo;
import com.example.santiagolopez.parkingapp.model.Vehiculo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Calendar;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {

    private ParqueaderoBusinessLogic parqueaderoBusinessLogic;

    @Before
    public void setUp() {
        parqueaderoBusinessLogic = Mockito.spy(new ParqueaderoBusinessLogic());
    }

    public TipoVehiculo getTipoMoto(){
        TipoVehiculo tipoVehiculo = new TipoVehiculo();
        tipoVehiculo.setId("1");
        tipoVehiculo.setNombre("Moto");
        tipoVehiculo.setValorDia(4000);
        tipoVehiculo.setValorHora(500);
        return tipoVehiculo;
    }

    public TipoVehiculo getTipoCarro() {
        TipoVehiculo tipoVehiculo = new TipoVehiculo();
        tipoVehiculo.setId("2");
        tipoVehiculo.setNombre("Carro");
        tipoVehiculo.setValorDia(8000);
        tipoVehiculo.setValorHora(1000);
        return tipoVehiculo;
    }

    @Test
    public void verificarQueNoIngreseUnaMotoCuandoNoHayParqueaderosDisponiblesPorPuestos() {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setTipo(getTipoMoto());
        when(parqueaderoBusinessLogic.hayCeldasDisponibles(vehiculo)).thenReturn(false);
        parqueaderoBusinessLogic.validarDisponibilidadParqueadero(vehiculo);
        verify(parqueaderoBusinessLogic, never()).addVehiculo(vehiculo);
    }


    @Test
    public void verificarQueIngreseUnaMotoCuandoHayParqueaderosDisponiblesPorPuestosPlacaYFecha() {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setTipo(getTipoMoto());
        vehiculo.setPlaca("STF94D");
        when(parqueaderoBusinessLogic.hayCeldasDisponibles(vehiculo)).thenReturn(true);
        parqueaderoBusinessLogic.validarDisponibilidadParqueadero(vehiculo);
        verify(parqueaderoBusinessLogic).addVehiculo(vehiculo);
    }

    @Test
    public void verificarQueNoIngreseUnaCarroCuandoNoHayParqueaderosDisponiblesPorPuestos() {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setTipo(getTipoCarro());
        when(parqueaderoBusinessLogic.hayCeldasDisponibles(vehiculo)).thenReturn(false);
        parqueaderoBusinessLogic.validarDisponibilidadParqueadero(vehiculo);
        verify(parqueaderoBusinessLogic, never()).addVehiculo(vehiculo);
    }



    @Test
    public void verificarQueIngreseUnaCarroCuandoHayParqueaderosDisponiblesPorPuestosPlacaYFecha() {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setTipo(getTipoCarro());
        vehiculo.setPlaca("STF94D");
        when(parqueaderoBusinessLogic.hayCeldasDisponibles(vehiculo)).thenReturn(true);
        parqueaderoBusinessLogic.validarDisponibilidadParqueadero(vehiculo);
        verify(parqueaderoBusinessLogic).addVehiculo(vehiculo);
    }

    @Test
    public void verificarQueNoIngreseUnCarroCuandoNoHayParqueaderosDisponiblesPorSerLunesYPlacaComienzaEnA() {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setTipo(getTipoCarro());
        vehiculo.setPlaca("ASD123");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        parqueaderoBusinessLogic.validarDisponibilidadParqueadero(vehiculo);
        verify(parqueaderoBusinessLogic, never()).addVehiculo(vehiculo);
    }

    @Test
    public void cobrarExcedenteAMotoConMasDe500CilindrajePorPeriodoDe10Horas(){
        Calendar calendarIngreso = Calendar.getInstance();
        calendarIngreso.set(2018, 1, 19, 9, 0);
        Calendar calendarSalida = Calendar.getInstance();
        calendarSalida.set(2018, 1, 19, 19, 0);

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setCilindraje(650);
        vehiculo.setTipo(getTipoMoto());
        vehiculo.setFechaIngreso(calendarIngreso.getTime());
        vehiculo.setFechaSalida(calendarSalida.getTime());

        double valorTotal = parqueaderoBusinessLogic.calcularTotalAPagar(vehiculo);
        Assert.assertEquals(valorTotal, 6000, 1e-8 );
    }

    @Test
    public void cobrarAMotoConMenosDe500CilindrajePorPeriodoDe10Horas(){
        Calendar calendarIngreso = Calendar.getInstance();
        calendarIngreso.set(2018, 1, 19, 9, 0);
        Calendar calendarSalida = Calendar.getInstance();
        calendarSalida.set(2018, 1, 19, 19, 0);

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setCilindraje(450);
        vehiculo.setTipo(getTipoMoto());
        vehiculo.setFechaIngreso(calendarIngreso.getTime());
        vehiculo.setFechaSalida(calendarSalida.getTime());

        double valorTotal = parqueaderoBusinessLogic.calcularTotalAPagar(vehiculo);
        Assert.assertEquals(valorTotal, 4000, 1e-8 );
    }

    @Test
    public void cobrarAMotoConMenosDe500CilindrajePorPeriodoDe28Horas(){
        Calendar calendarIngreso = Calendar.getInstance();
        calendarIngreso.set(2018, 1, 19, 9, 0);
        Calendar calendarSalida = Calendar.getInstance();
        calendarSalida.set(2018, 1, 20, 13, 0);

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setCilindraje(450);
        vehiculo.setTipo(getTipoMoto());
        vehiculo.setFechaIngreso(calendarIngreso.getTime());
        vehiculo.setFechaSalida(calendarSalida.getTime());

        double valorTotal = parqueaderoBusinessLogic.calcularTotalAPagar(vehiculo);
        Assert.assertEquals(valorTotal, 6000, 1e-8 );
    }

    @Test
    public void cobrarAMotoConMenosDe500CilindrajePorPeriodoDe5Horas(){
        Calendar calendarIngreso = Calendar.getInstance();
        calendarIngreso.set(2018, 1, 19, 9, 0);
        Calendar calendarSalida = Calendar.getInstance();
        calendarSalida.set(2018, 1, 19, 14, 0);

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setCilindraje(450);
        vehiculo.setTipo(getTipoMoto());
        vehiculo.setFechaIngreso(calendarIngreso.getTime());
        vehiculo.setFechaSalida(calendarSalida.getTime());

        double valorTotal = parqueaderoBusinessLogic.calcularTotalAPagar(vehiculo);
        Assert.assertEquals(valorTotal, 2500, 1e-8 );
    }


    @Test
    public void cobrarACarroPorPeriodoDe10Horas(){
        Calendar calendarIngreso = Calendar.getInstance();
        calendarIngreso.set(2018, 1, 19, 9, 0);
        Calendar calendarSalida = Calendar.getInstance();
        calendarSalida.set(2018, 1, 19, 19, 0);

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setTipo(getTipoCarro());
        vehiculo.setFechaIngreso(calendarIngreso.getTime());
        vehiculo.setFechaSalida(calendarSalida.getTime());

        double valorTotal = parqueaderoBusinessLogic.calcularTotalAPagar(vehiculo);
        Assert.assertEquals(valorTotal, 8000, 1e-8 );
    }

    @Test
    public void cobrarACarroPorPeriodoDe28Horas(){
        Calendar calendarIngreso = Calendar.getInstance();
        calendarIngreso.set(2018, 1, 19, 9, 0);
        Calendar calendarSalida = Calendar.getInstance();
        calendarSalida.set(2018, 1, 20, 13, 0);

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setTipo(getTipoCarro());
        vehiculo.setFechaIngreso(calendarIngreso.getTime());
        vehiculo.setFechaSalida(calendarSalida.getTime());

        double valorTotal = parqueaderoBusinessLogic.calcularTotalAPagar(vehiculo);
        Assert.assertEquals(valorTotal, 12000, 1e-8 );
    }

    @Test
    public void cobrarACarroPorPeriodoDe5Horas(){
        Calendar calendarIngreso = Calendar.getInstance();
        calendarIngreso.set(2018, 1, 19, 9, 0);
        Calendar calendarSalida = Calendar.getInstance();
        calendarSalida.set(2018, 1, 19, 14, 0);

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setTipo(getTipoCarro());
        vehiculo.setFechaIngreso(calendarIngreso.getTime());
        vehiculo.setFechaSalida(calendarSalida.getTime());

        double valorTotal = parqueaderoBusinessLogic.calcularTotalAPagar(vehiculo);
        Assert.assertEquals(valorTotal, 5000, 1e-8 );
    }

    @Test
    public void verificarAlSalirUnaMotoSeLiberaElPuesto(){
        Calendar calendarIngreso = Calendar.getInstance();
        calendarIngreso.set(2018, 1, 19, 9, 0);
        Calendar calendarSalida = Calendar.getInstance();
        calendarSalida.set(2018, 1, 19, 14, 0);

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setTipo(getTipoMoto());
        vehiculo.setPlaca("YSD234");
        vehiculo.setFechaIngreso(calendarIngreso.getTime());
        vehiculo.setFechaSalida(calendarSalida.getTime());

        parqueaderoBusinessLogic.validarDisponibilidadParqueadero(vehiculo);
        parqueaderoBusinessLogic.salidaVehiculo(vehiculo);
        verify(parqueaderoBusinessLogic).liberarPuestoParqueadero(vehiculo);
    }

    @Test
    public void verificarAlSalirUnCarroSeLiberaElPuesto(){
        Calendar calendarIngreso = Calendar.getInstance();
        calendarIngreso.set(2018, 1, 19, 9, 0);
        Calendar calendarSalida = Calendar.getInstance();
        calendarSalida.set(2018, 1, 19, 14, 0);

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setTipo(getTipoMoto());
        vehiculo.setPlaca("YSD234");
        vehiculo.setFechaIngreso(calendarIngreso.getTime());
        vehiculo.setFechaSalida(calendarSalida.getTime());

        parqueaderoBusinessLogic.validarDisponibilidadParqueadero(vehiculo);
        parqueaderoBusinessLogic.salidaVehiculo(vehiculo);
        verify(parqueaderoBusinessLogic).liberarPuestoParqueadero(vehiculo);
    }

}