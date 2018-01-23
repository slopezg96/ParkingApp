package com.example.santiagolopez.parkingapp.view.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.santiagolopez.parkingapp.R;
import com.example.santiagolopez.parkingapp.model.VehiculoParqueado;

import java.util.List;

/**
 * Created by santiago.lopez on 1/22/18.
 */

public class VehiculosParqueadosAdapter extends RecyclerView.Adapter<VehiculosParqueadosAdapter.VehiculoParqueadoViewHolder> {

    private List<VehiculoParqueado> vehiculosParqueados;
    private Context context;
    private boolean esVistaMotos;
    private int contadorPintados;

    public VehiculosParqueadosAdapter(Context context, List<VehiculoParqueado> vehiculoParqueados,
                                      boolean esVistaMotos) {
        this.context = context;
        this.vehiculosParqueados = vehiculoParqueados;
        this.esVistaMotos = esVistaMotos;
    }

    @Override
    public VehiculoParqueadoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_celda, parent, false);
        return new VehiculoParqueadoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VehiculoParqueadoViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return esVistaMotos ? 10 : 20;
    }

    public class VehiculoParqueadoViewHolder extends RecyclerView.ViewHolder {

        private CardView cardViewCelda;

        public VehiculoParqueadoViewHolder(View itemView) {
            super(itemView);
            cardViewCelda = itemView.findViewById(R.id.cardViewCelda);
        }

        public void bind() {
            if (contadorPintados < vehiculosParqueados.size()) {
                cardViewCelda.setCardBackgroundColor(context.getResources().getColor(R.color.celdaOcupada));
                contadorPintados++;
            }
        }
    }
}
