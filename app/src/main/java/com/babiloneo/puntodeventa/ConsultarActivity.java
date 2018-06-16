package com.babiloneo.puntodeventa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ListView;

import com.babiloneo.puntodeventa.adaptador.productosAdapter;
import com.babiloneo.puntodeventa.dataBase.Producto;
import com.babiloneo.puntodeventa.dataBase.service;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class ConsultarActivity extends AppCompatActivity {

    productosAdapter adapter;
    List<Producto> listaProductos;
    ListView vistaLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);
        vistaLista=(ListView)findViewById(R.id.listview_prpoductos);
        listar();
    }

    private void listar() {
        service s = new service(Realm.getDefaultInstance());

        listaProductos=s.obtenerProductos();
        adapter=new productosAdapter(getApplicationContext(),R.layout.lista_productos,listaProductos);
        vistaLista.setAdapter(adapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == event.KEYCODE_BACK) {
            Intent view = new Intent(ConsultarActivity.this,ProductosActivity.class);
            startActivity(view);
            this.finish();
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }
}
