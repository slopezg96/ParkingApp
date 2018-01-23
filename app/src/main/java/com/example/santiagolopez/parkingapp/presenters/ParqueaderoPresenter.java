package com.example.santiagolopez.parkingapp.presenters;

import com.example.santiagolopez.parkingapp.businesslogic.IParqueaderoBusinessLogic;
import com.example.santiagolopez.parkingapp.businesslogic.ParqueaderoBusinessLogic;
import com.example.santiagolopez.parkingapp.view.interfaces.IHomeView;

/**
 * Created by santiago.lopez on 1/22/18.
 */

public class ParqueaderoPresenter extends BasePresenter<IHomeView>{

    private IParqueaderoBusinessLogic iParqueaderoBusinessLogic;

    public ParqueaderoPresenter() {
        iParqueaderoBusinessLogic = new ParqueaderoBusinessLogic();
    }

    @Override
    public void iniciar() {
        vista.mostrarVehiculosParqueados(iParqueaderoBusinessLogic.traerVehiculosParqueados());
    }

    @Override
    public void detener() {

    }
}
