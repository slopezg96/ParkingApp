package com.example.santiagolopez.parkingapp.dependencyinjection.modulos;

import com.example.santiagolopez.parkingapp.businesslogic.ParqueaderoBusinessLogic;
import com.example.santiagolopez.parkingapp.businesslogic.TipoVehiculoBusinessLogic;
import com.example.santiagolopez.parkingapp.repositories.IParkingRepository;
import com.example.santiagolopez.parkingapp.repositories.ITipoVehiculoRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by santiago.lopez on 1/24/18.
 */

@Module
public class ModuloPresentacion {

    @Provides
    ParqueaderoBusinessLogic provideParqueaderoBusinessLogic(IParkingRepository iParkingRepository) {
        return new ParqueaderoBusinessLogic(iParkingRepository);
    }

    @Provides
    TipoVehiculoBusinessLogic provideTipoVehiculoBusinessLogic(ITipoVehiculoRepository iTipoVehiculoRepository) {
        return new TipoVehiculoBusinessLogic(iTipoVehiculoRepository);
    }
}
