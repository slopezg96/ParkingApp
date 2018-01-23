package com.example.santiagolopez.parkingapp.view.activities;

import android.support.v7.app.AppCompatActivity;

import com.example.santiagolopez.parkingapp.presenters.BasePresenter;

/**
 * Created by santiago.lopez on 1/22/18.
 */

class BaseActivity<T extends BasePresenter> extends AppCompatActivity{

    T presentador;

}
