package com.example.santiagolopez.parkingapp.dependencyinjection.modulos;

import android.content.Context;

import com.example.santiagolopez.parkingapp.ParqueaderoApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by santiago.lopez on 1/24/18.
 */

@Module
public class ModuloAplicacion {

    private final ParqueaderoApp application;

    public ModuloAplicacion(ParqueaderoApp application) {

        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext ()
    {
        return application;
    }
}