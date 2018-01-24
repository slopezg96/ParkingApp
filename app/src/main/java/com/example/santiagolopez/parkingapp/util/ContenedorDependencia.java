package com.example.santiagolopez.parkingapp.util;

import android.app.Application;

import com.example.santiagolopez.parkingapp.ParqueaderoApp;
import com.example.santiagolopez.parkingapp.dependencyinjection.componentes.DaggerComponentePresentacion;
import com.example.santiagolopez.parkingapp.dependencyinjection.modulos.ModuloPresentacion;

/**
 * Created by santiago.lopez on 1/24/18.
 */

public class ContenedorDependencia {

    DaggerComponentePresentacion.Builder contenedor;
    ParqueaderoApp app;
    Application application;

    public ContenedorDependencia(Application application) {
        this.application = application;
        inicializarInyeccionDependencias();
    }

    public ParqueaderoApp getApp() {
        return app;
    }

    public DaggerComponentePresentacion.Builder getContenedor() {
        return contenedor;
    }

    private void inicializarInyeccionDependencias() {
        app = (ParqueaderoApp) application;
        contenedor = DaggerComponentePresentacion.builder();
        contenedor.componenteAplicacion(app.getAppComponent());
        contenedor.moduloPresentacion(new ModuloPresentacion());
    }
}