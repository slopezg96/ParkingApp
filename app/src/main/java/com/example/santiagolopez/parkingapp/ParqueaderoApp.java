package com.example.santiagolopez.parkingapp;

import android.app.Application;

import com.example.santiagolopez.parkingapp.dependencyinjection.componentes.ComponenteAplicacion;
import com.example.santiagolopez.parkingapp.dependencyinjection.componentes.DaggerComponenteAplicacion;
import com.example.santiagolopez.parkingapp.dependencyinjection.modulos.ModuloAplicacion;
import com.example.santiagolopez.parkingapp.dependencyinjection.modulos.ModuloDominio;

/**
 * Created by santiago.lopez on 1/17/18.
 */

public class ParqueaderoApp extends Application {

    private ComponenteAplicacion appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        iniciarInyeccionDependencias();
    }

    private void iniciarInyeccionDependencias() {
        appComponent = DaggerComponenteAplicacion.builder()
                .moduloAplicacion(new ModuloAplicacion(this))
                .moduloDominio(new ModuloDominio(this))
                .build();
    }

    public ComponenteAplicacion getAppComponent() {

        return appComponent;
    }
}
