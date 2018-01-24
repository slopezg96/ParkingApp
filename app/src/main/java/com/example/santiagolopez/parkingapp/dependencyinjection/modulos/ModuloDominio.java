package com.example.santiagolopez.parkingapp.dependencyinjection.modulos;

import com.example.santiagolopez.parkingapp.ParqueaderoApp;
import com.example.santiagolopez.parkingapp.repositories.IParkingRepository;
import com.example.santiagolopez.parkingapp.repositories.ParkingRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by santiago.lopez on 1/24/18.
 */

@Module
public class ModuloDominio {

    private final ParqueaderoApp parqueaderoApp;

    public ModuloDominio(ParqueaderoApp parqueaderoApp) {
        this.parqueaderoApp = parqueaderoApp;
    }

    @Provides
    IParkingRepository provideIParkingRepository() {
        return new ParkingRepository();
    }
}
