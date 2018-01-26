package com.example.santiagolopez.parkingapp.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.santiagolopez.parkingapp.R;
import com.example.santiagolopez.parkingapp.model.TipoVehiculo;

import java.util.List;

/**
 * Created by santiago.lopez on 1/25/18.
 */

public class TipoVehiculoAdapter extends ArrayAdapter<TipoVehiculo> {

    Context context;

    public TipoVehiculoAdapter(Context context, List<TipoVehiculo> tipoVehiculos) {
        super(context, 0, tipoVehiculos);
        this.context = context;
        adicionarValorPorDefecto(tipoVehiculos);
    }

    private void adicionarValorPorDefecto(List<TipoVehiculo> tipoVehiculos) {
        TipoVehiculo tipoVehiculo = new TipoVehiculo();
        tipoVehiculo.setNombre("Tipo Vehiculo");
        tipoVehiculos.add(0, tipoVehiculo);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        convertView = getViewItem(position, convertView, parent);
        return convertView;
    }

    @NonNull
    private View getViewItem(int position, View convertView, ViewGroup parent) {
        TipoVehiculoViewHolder tipoVehiculoViewHolder;
        final TipoVehiculo tipoVehiculo = getItem(position);
        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_spinner_tipo_vehiculo, parent, false);
            tipoVehiculoViewHolder = new TipoVehiculoViewHolder();
            tipoVehiculoViewHolder.tvNombre = convertView.findViewById(R.id.textView_nombre);
            convertView.setTag(tipoVehiculoViewHolder);
        } else {
            tipoVehiculoViewHolder = (TipoVehiculoViewHolder) convertView.getTag();
        }
        tipoVehiculoViewHolder.tvNombre.setText(tipoVehiculo.getNombre());
        if (position == 0) {
            tipoVehiculoViewHolder.tvNombre.setTextColor(this.context.getResources().getColor(R.color.grisclaro));
        } else {
            tipoVehiculoViewHolder.tvNombre.setTextColor(context.getResources().getColor(R.color.negro));
        }
        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = getViewItem(position, convertView, parent);
        return convertView;
    }

    static class TipoVehiculoViewHolder {
        TextView tvNombre;
    }
}
