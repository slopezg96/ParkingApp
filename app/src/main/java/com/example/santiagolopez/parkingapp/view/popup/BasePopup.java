package com.example.santiagolopez.parkingapp.view.popup;

import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import com.example.santiagolopez.parkingapp.presenters.BasePresenter;
import com.example.santiagolopez.parkingapp.util.ContenedorDependencia;
import com.example.santiagolopez.parkingapp.util.ValidateInternet;
import com.example.santiagolopez.parkingapp.view.interfaces.IValidateInternet;

import javax.inject.Inject;

/**
 * Created by santiago.lopez on 1/26/18.
 */

public class BasePopup<T extends BasePresenter> extends DialogFragment {

    protected ContenedorDependencia dependencia;
    @Inject
    T presentador;
    private IValidateInternet validateInternet;

    public void mostrarMensaje(final String mensaje) {
        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
    }
    public IValidateInternet getValidateInternet() {
        if (validateInternet == null) {
            validateInternet = new ValidateInternet(getContext());
        }
        return validateInternet;
    }

    public void setValidateInternet(IValidateInternet validateInternet) {
        this.validateInternet = validateInternet;
    }

}
