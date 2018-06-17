package com.babiloneo.puntodeventa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ListView;

import com.babiloneo.puntodeventa.adaptador.historialAdapter;
import com.babiloneo.puntodeventa.adaptador.productosAdapter;
import com.babiloneo.puntodeventa.dataBase.Ventas;
import com.babiloneo.puntodeventa.dataBase.service;

import java.util.List;

import io.realm.Realm;

public class HistorialActivity extends AppCompatActivity {

    historialAdapter adapter;
    ListView vista;
    List<Ventas> ventas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        vista=(ListView) findViewById(R.id.listview_historial);
        listar();
    }

    private void listar() {
        service s = new service(Realm.getDefaultInstance());

        ventas=s.obtenerVentas();
        adapter=new historialAdapter(getApplicationContext(),R.layout.lista_historial,ventas);
        vista.setAdapter(adapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == event.KEYCODE_BACK) {
            Intent view = new Intent(HistorialActivity.this,VentasActivity.class);
            startActivity(view);
            this.finish();
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }
}
