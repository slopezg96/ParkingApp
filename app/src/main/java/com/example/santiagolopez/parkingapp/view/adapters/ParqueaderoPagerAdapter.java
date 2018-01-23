package com.example.santiagolopez.parkingapp.view.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.santiagolopez.parkingapp.model.VehiculoParqueado;
import com.example.santiagolopez.parkingapp.view.fragments.CarrosFragment;
import com.example.santiagolopez.parkingapp.view.fragments.MotosFragment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by santiago.lopez on 1/22/18.
 */

public class ParqueaderoPagerAdapter extends FragmentStatePagerAdapter{

    private static final String TITULO_MOTOS = "MOTOS";
    private static final String TITULO_CARROS = "CARROS";
    private List<Fragment> listaFragmentos = new ArrayList<>();
    private List<String> listaTitulos = new ArrayList<>();
    private List<VehiculoParqueado> vehiculosParqueados;

    public ParqueaderoPagerAdapter(FragmentManager fragmentManager,
                                   List<VehiculoParqueado> vehiculosParqueados) {
        super(fragmentManager);
        this.vehiculosParqueados = vehiculosParqueados;
        crearListaDeFragmentos();
        crearListaDeTitulo();
    }

    @Override
    public Fragment getItem(int position) {
        return listaFragmentos.get(position);
    }

    @Override
    public int getCount() {
        return listaFragmentos.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listaTitulos.get(position);
    }

    private void crearListaDeTitulo() {
        listaTitulos.add(TITULO_MOTOS);
        listaTitulos.add(TITULO_CARROS);
    }

    private void crearListaDeFragmentos() {
        listaFragmentos = new ArrayList<>();
        listaFragmentos.add(crearFragmentoMotos());
        listaFragmentos.add(crearFragmentoCarros());
    }

    private Fragment crearFragmentoCarros() {
        List<VehiculoParqueado> carrosParqueados = new ArrayList<>();
        carrosParqueados.addAll(vehiculosParqueados);
        filtrarSoloCarros(carrosParqueados);
        CarrosFragment carrosFragment = new CarrosFragment();
        carrosFragment.setVehiculosParqueados(carrosParqueados);
        return carrosFragment;
    }

    private void filtrarSoloCarros(List<VehiculoParqueado> carrosParqueados) {
        Iterator<VehiculoParqueado> iter = carrosParqueados.iterator();
        while(iter.hasNext()) {
            if (iter.next().getVehiculo().getTipo().getNombre().equals("Moto")) iter.remove();
        }
    }

    private Fragment crearFragmentoMotos() {
        List<VehiculoParqueado> motosParqueadas = new ArrayList();
        motosParqueadas.addAll(vehiculosParqueados);
        filtrarSoloMotos(motosParqueadas);

        MotosFragment motosFragment = new MotosFragment();
        motosFragment.setVehiculosParqueados(motosParqueadas);
        return motosFragment;
    }

    private void filtrarSoloMotos(List<VehiculoParqueado> motosParqueadas) {
        Iterator<VehiculoParqueado> iter = motosParqueadas.iterator();
        while(iter.hasNext()) {
            if (iter.next().getVehiculo().getTipo().getNombre().equals("Carro")) iter.remove();
        }
    }
}
