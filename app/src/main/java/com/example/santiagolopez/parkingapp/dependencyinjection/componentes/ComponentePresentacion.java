package com.example.santiagolopez.parkingapp.dependencyinjection.componentes;

import com.example.santiagolopez.parkingapp.dependencyinjection.modulos.ModuloPresentacion;
import com.example.santiagolopez.parkingapp.dependencyinjection.scopes.ActivityScoped;
import com.example.santiagolopez.parkingapp.view.activities.HomeActivity;
import com.example.santiagolopez.parkingapp.view.popup.NuevoVehiculoPopUp;

import dagger.Component;

/**
 * Created by santiago.lopez on 1/24/18.
 */

@ActivityScoped
@Component(dependencies = ComponenteAplicacion.class, modules = ModuloPresentacion.class)
public interface ComponentePresentacion {

    void inject(HomeActivity homeActivity);

    void inject(NuevoVehiculoPopUp nuevoVehiculoPopUp);
}
