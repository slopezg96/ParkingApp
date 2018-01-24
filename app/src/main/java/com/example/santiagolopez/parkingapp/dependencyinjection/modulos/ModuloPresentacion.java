package com.example.santiagolopez.parkingapp.dependencyinjection.modulos;

import com.example.santiagolopez.parkingapp.businesslogic.ParqueaderoBusinessLogic;
import com.example.santiagolopez.parkingapp.repositories.IParkingRepository;

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
}
