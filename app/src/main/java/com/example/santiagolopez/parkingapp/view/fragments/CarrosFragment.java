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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by santiago.lopez on 1/22/18.
 */

public class CarrosFragment extends Fragment {

    private RecyclerView recyclerViewCarros;
    private List<VehiculoParqueado> carrosParqueados = new ArrayList<>();


    public void setVehiculosParqueados(List<VehiculoParqueado> carrosParqueados) {
        this.carrosParqueados = carrosParqueados;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_carros, container, false);
        obtenerControles(rootView);
        llenarAdapterCarros();
        return rootView;
    }


    private void llenarAdapterCarros() {
        recyclerViewCarros.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recyclerViewCarros.setHasFixedSize(true);
        recyclerViewCarros.setAdapter(new VehiculosParqueadosAdapter(
                getContext(), carrosParqueados, false));
    }

    private void obtenerControles(View rootView) {
        recyclerViewCarros = rootView.findViewById(R.id.recyclerView_Carros);
    }
}
