package com.example.santiagolopez.parkingapp.presenters;


import com.example.santiagolopez.parkingapp.view.interfaces.BaseView;

/**
 * Created by santiago.lopez on 1/22/18.
 */

public abstract class BasePresenter<T extends BaseView>{

    public abstract void iniciar();

    public abstract void detener();

    public void adicionarVista(T vista) {
        this.vista = vista;
    }

    T vista;

}
