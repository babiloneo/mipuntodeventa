package com.babiloneo.puntodeventa.adaptador;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.babiloneo.puntodeventa.R;
import com.babiloneo.puntodeventa.dataBase.Producto;
import com.babiloneo.puntodeventa.dataBase.Ventas;

import java.util.List;

public class historialAdapter extends ArrayAdapter<Ventas> {
    private List<Ventas> lista;
    private Context context;
    private int resource;

    public historialAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Ventas> objects) {
        super(context, resource,objects);

        this.lista=objects;
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View v=null;

        final Ventas item=lista.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v=inflater.inflate(resource,parent,false);


        TextView id =(TextView)v.findViewById(R.id.his_id);
        TextView cantidad = (TextView)v.findViewById(R.id.his_cantidad);
        TextView total = (TextView)v.findViewById(R.id.his_total);
        TextView usuario = (TextView)v.findViewById(R.id.his_usuario);

        id.setText(Integer.toString(item.getId()));
        usuario.setText(item.getUsuario());
        total.setText(Double.toString(item.getTotal()));
        cantidad.setText(Integer.toString(item.getCantidad()));

        return v;
    }
}
