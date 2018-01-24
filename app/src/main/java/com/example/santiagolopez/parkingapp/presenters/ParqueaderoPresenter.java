package com.example.santiagolopez.parkingapp.presenters;

import com.example.santiagolopez.parkingapp.businesslogic.IParqueaderoBusinessLogic;
import com.example.santiagolopez.parkingapp.businesslogic.ParqueaderoBusinessLogic;
import com.example.santiagolopez.parkingapp.view.interfaces.IHomeView;

import javax.inject.Inject;

/**
 * Created by santiago.lopez on 1/22/18.
 */

public class ParqueaderoPresenter extends BasePresenter<IHomeView>{

    private ParqueaderoBusinessLogic parqueaderoBusinessLogic;

    @Inject
    public ParqueaderoPresenter(ParqueaderoBusinessLogic parqueaderoBusinessLogic) {
        this.parqueaderoBusinessLogic = parqueaderoBusinessLogic;
    }

    @Override
    public void iniciar() {
        parqueaderoBusinessLogic.traerVehiculosParqueados();
    }

    @Override
    public void detener() {

    }
}
