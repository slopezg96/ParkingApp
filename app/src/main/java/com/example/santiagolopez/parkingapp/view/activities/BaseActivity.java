package com.example.santiagolopez.parkingapp.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.santiagolopez.parkingapp.ParqueaderoApp;
import com.example.santiagolopez.parkingapp.presenters.BasePresenter;
import com.example.santiagolopez.parkingapp.util.ContenedorDependencia;
import com.example.santiagolopez.parkingapp.util.ValidateInternet;
import com.example.santiagolopez.parkingapp.view.interfaces.IValidateInternet;

import javax.inject.Inject;

/**
 * Created by santiago.lopez on 1/22/18.
 */

class BaseActivity<T extends BasePresenter> extends AppCompatActivity{

    protected ContenedorDependencia dependencia;
    protected ParqueaderoApp app;
    private IValidateInternet validateInternet;

    @Inject
    T presentador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.validateInternet = new ValidateInternet(this);
        dependencia = new ContenedorDependencia(getApplication());
        app = dependencia.getApp();
    }

    public IValidateInternet getValidateInternet() {
        if (validateInternet == null) {
            validateInternet = new ValidateInternet(this);
        }
        return validateInternet;
    }

    public void setValidateInternet(IValidateInternet validateInternet) {
        this.validateInternet = validateInternet;
    }

    @Override
    protected void onResume() {
        super.onResume();
        dependencia = new ContenedorDependencia(getApplication());
        app = dependencia.getApp();
    }
}
