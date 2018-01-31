package com.example.santiagolopez.parkingapp.presenters;


import com.example.santiagolopez.parkingapp.view.interfaces.BaseView;
import com.example.santiagolopez.parkingapp.view.interfaces.IValidateInternet;

/**
 * Created by santiago.lopez on 1/22/18.
 */

public abstract class BasePresenter<T extends BaseView>{

    T vista;

    public IValidateInternet getValidateInternet() {
        return validateInternet;
    }

    public void setValidateInternet(IValidateInternet validateInternet) {
        this.validateInternet = validateInternet;
    }

    private IValidateInternet validateInternet;

    public abstract void iniciar();

    public abstract void detener();

    public void adicionarVista(T vista) {
        this.vista = vista;
    }
}
