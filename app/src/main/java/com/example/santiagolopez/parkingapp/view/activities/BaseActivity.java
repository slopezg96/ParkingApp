package com.example.santiagolopez.parkingapp.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.santiagolopez.parkingapp.ParqueaderoApp;
import com.example.santiagolopez.parkingapp.presenters.BasePresenter;
import com.example.santiagolopez.parkingapp.util.ContenedorDependencia;

import javax.inject.Inject;

/**
 * Created by santiago.lopez on 1/22/18.
 */

class BaseActivity<T extends BasePresenter> extends AppCompatActivity{

    protected ContenedorDependencia dependencia;
    protected ParqueaderoApp app;

    @Inject
    T presentador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dependencia = new ContenedorDependencia(getApplication());
        app = dependencia.getApp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        dependencia = new ContenedorDependencia(getApplication());
        app = dependencia.getApp();
    }
}
