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

import java.util.ArrayList;
import java.util.List;

public class productosAdapter extends ArrayAdapter<Producto> {
    private List<Producto> lista;
    private Context context;
    private int resource;
    private static final String TAG = "PRUEBA";

    public productosAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Producto> objects) {
        super(context, resource,objects);

        this.lista=objects;
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View v=null;

        final Producto item=lista.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v=inflater.inflate(resource,parent,false);


        TextView id =(TextView)v.findViewById(R.id.con_id);
        TextView nombre =(TextView)v.findViewById(R.id.con_nombre);
        TextView precio = (TextView)v.findViewById(R.id.con_precio);
        TextView stock = (TextView)v.findViewById(R.id.con_stock);

        id.setText(Integer.toString(item.getId()));
        nombre.setText(item.getNombre());
        precio.setText(Double.toString(item.getPrecio()));
        stock.setText(Integer.toString(item.getStock()));

        return v;
    }
}
