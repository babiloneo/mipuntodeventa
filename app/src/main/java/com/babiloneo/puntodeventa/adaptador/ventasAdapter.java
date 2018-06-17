package com.babiloneo.puntodeventa.adaptador;

import android.arch.core.executor.TaskExecutor;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.babiloneo.puntodeventa.R;
import com.babiloneo.puntodeventa.VentasActivity;
import com.babiloneo.puntodeventa.dataBase.LocalStorage;
import com.babiloneo.puntodeventa.dataBase.Producto;

import java.util.List;

public class ventasAdapter extends ArrayAdapter<Producto> {
    private List<Producto> lista;
    private Context context;
    private int resource;
    public static int cantidad;
    public static double tot;
    public ventasAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Producto> objects) {
        super(context, resource,objects);

        this.lista=objects;
        this.context=context;
        this.resource=resource;
        cantidad=0;
        tot=0;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View v=null;

        final Producto item=lista.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v=inflater.inflate(resource,parent,false);


        TextView id =(TextView)v.findViewById(R.id.ven_id);
        TextView nombre =(TextView)v.findViewById(R.id.ven_nombre);
        TextView precio = (TextView)v.findViewById(R.id.ven_precio);
        ImageView agregar = (ImageView)v.findViewById(R.id.ven_agregar);

        id.setText(Integer.toString(item.getId()));
        nombre.setText(item.getNombre());
        precio.setText(Double.toString(item.getPrecio()));

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cantidad=cantidad+1;
                LocalStorage.cantidad=cantidad;
                tot=tot+item.getPrecio();
                LocalStorage.total=tot;
                Toast.makeText(context,item.getNombre()+ " Agregado", Toast.LENGTH_SHORT).show();

            }
        });

        return v;
    }

}
