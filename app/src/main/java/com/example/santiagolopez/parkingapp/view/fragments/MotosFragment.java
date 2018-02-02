package com.example.santiagolopez.parkingapp.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.santiagolopez.parkingapp.R;
import com.example.santiagolopez.parkingapp.model.VehiculoParqueado;
import com.example.santiagolopez.parkingapp.view.adapters.VehiculosParqueadosAdapter;

import java.util.List;

/**
 * Created by santiago.lopez on 1/22/18.
 */

public class MotosFragment extends Fragment {

    private RecyclerView recyclerViewMotos;
    private List<VehiculoParqueado> motosParqueadas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_motos, container, false);
        obtenerControles(rootView);
        llenarAdapterMotos();
        return rootView;
    }

    private void llenarAdapterMotos() {
        recyclerViewMotos.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recyclerViewMotos.setHasFixedSize(true);
        recyclerViewMotos.setAdapter(new VehiculosParqueadosAdapter(
                getContext(), motosParqueadas, true));
    }

    private void obtenerControles(View rootView) {
        recyclerViewMotos = rootView.findViewById(R.id.recyclerView_Motos);
    }

    public void setVehiculosParqueados(List<VehiculoParqueado> motosParqueadas) {
        this.motosParqueadas = motosParqueadas;
    }
}
