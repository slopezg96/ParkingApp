package com.example.santiagolopez.parkingapp.view.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.santiagolopez.parkingapp.R;
import com.example.santiagolopez.parkingapp.model.VehiculoParqueado;
import com.example.santiagolopez.parkingapp.presenters.ParqueaderoPresenter;
import com.example.santiagolopez.parkingapp.view.adapters.ParqueaderoPagerAdapter;
import com.example.santiagolopez.parkingapp.view.interfaces.IHomeView;
import com.example.santiagolopez.parkingapp.view.popup.NuevoVehiculoPopUp;
import com.example.santiagolopez.parkingapp.view.popup.SalidaVehiculoPopup;

import java.util.List;


public class HomeActivity extends BaseActivity<ParqueaderoPresenter> implements IHomeView {

    private ViewPager viewPagerTabsParqueadero;
    private TabLayout tabLayout;
    private FragmentManager fragmentManager;
    private ParqueaderoPagerAdapter parqueaderoPagerAdapter;
    private List<VehiculoParqueado> vehiculoParqueados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dependencia.getContenedor().build().inject(this);
        presentador.adicionarVista(this);
        configurarActionBar();
        obtenerControles();
        presentador.iniciar();
        fragmentManager = getSupportFragmentManager();
    }

    private void configurarActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);
    }

    private void obtenerControles() {
        tabLayout = findViewById(R.id.tabs);
        viewPagerTabsParqueadero = findViewById(R.id.viewPagerTabs_Parqueadero);
    }

    private void crearTabs() {
        parqueaderoPagerAdapter = new ParqueaderoPagerAdapter(
                getSupportFragmentManager(), vehiculoParqueados);
        viewPagerTabsParqueadero.setAdapter(parqueaderoPagerAdapter);
        tabLayout.setupWithViewPager(viewPagerTabsParqueadero);
    }

    @Override
    public void mostrarVehiculosParqueados(List<VehiculoParqueado> vehiculosParqueados) {
        this.vehiculoParqueados = vehiculosParqueados;
        crearTabs();
    }

    @Override
    public void refrescarDisponibilidadParqueadero(VehiculoParqueado vehiculoParqueado) {
        vehiculoParqueados.add(vehiculoParqueado);
        crearTabs();
        if (vehiculoParqueado.getVehiculo().getTipo().getNombre().equals("Carro")) {
            viewPagerTabsParqueadero.setCurrentItem(1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_entrada_vehiculo:
                NuevoVehiculoPopUp nuevoVehiculoPopUp = new NuevoVehiculoPopUp();
                nuevoVehiculoPopUp.setiHomeView(this);
                nuevoVehiculoPopUp.show(fragmentManager, "");
                break;
            case R.id.action_salida_vehiculo:
                SalidaVehiculoPopup salidaVehiculoPopup = new SalidaVehiculoPopup();
                salidaVehiculoPopup.show(fragmentManager, "");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
