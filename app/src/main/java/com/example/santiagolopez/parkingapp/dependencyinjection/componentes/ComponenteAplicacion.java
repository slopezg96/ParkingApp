package com.example.santiagolopez.parkingapp.dependencyinjection.componentes;

import com.example.santiagolopez.parkingapp.dependencyinjection.modulos.ModuloAplicacion;
import com.example.santiagolopez.parkingapp.dependencyinjection.modulos.ModuloDominio;
import com.example.santiagolopez.parkingapp.repositories.IParkingRepository;
import com.example.santiagolopez.parkingapp.repositories.ITipoVehiculoRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by santiago.lopez on 1/24/18.
 */

@Singleton
@Component(modules = {ModuloAplicacion.class, ModuloDominio.class,})
public interface ComponenteAplicacion {

    IParkingRepository iParkingRepository();

    ITipoVehiculoRepository iTipoVehiculoRepository();
}