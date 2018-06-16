package com.babiloneo.puntodeventa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.babiloneo.puntodeventa.dataBase.LocalStorage;
import com.babiloneo.puntodeventa.dataBase.Producto;
import com.babiloneo.puntodeventa.models.usuario;

public class ProductosActivity extends AppCompatActivity {

    LocalStorage storage = new LocalStorage();
    Button altas,bajas,modifi,consultas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        altas=(Button)findViewById(R.id.btn_altas);
        bajas=(Button)findViewById(R.id.btn_bajas);
        modifi=(Button)findViewById(R.id.btn_modificar);
        consultas=(Button)findViewById(R.id.btn_consultar);

        funciones();
    }

    private void funciones() {
        comprobarfPremisos();

        //ir a ventana de alta de productos
        altas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProductosActivity.this, AltasActivity.class);
                ProductosActivity.this.startActivity(i);
            }
        });

        //ir a ventana de consulta de productos
        consultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProductosActivity.this, ConsultarActivity.class);
                ProductosActivity.this.startActivity(i);
            }
        });

        bajas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProductosActivity.this, BajasActivity.class);
                ProductosActivity.this.startActivity(i);
            }
        });

        modifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProductosActivity.this, ModificActivity.class);
                ProductosActivity.this.startActivity(i);
            }
        });
    }

    //Checamos si el usuario logueado tiene permisos para que coaas en esta ventana
    private void comprobarfPremisos() {
        usuario user=storage.consultar(getApplicationContext());

        if(user.isAltas()){
            altas.setVisibility(View.VISIBLE);
        }else{
            altas.setVisibility(View.INVISIBLE);
        }

        if(user.isBajas()){
            bajas.setVisibility(View.VISIBLE);
        }else{
            bajas.setVisibility(View.INVISIBLE);
        }

        if(user.isModificaciones()){
            modifi.setVisibility(View.VISIBLE);
        }else{
            modifi.setVisibility(View.INVISIBLE);
        }

        if(user.isConsultas()){
            consultas.setVisibility(View.VISIBLE);
        }else{
            consultas.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == event.KEYCODE_BACK) {
            Intent view = new Intent(ProductosActivity.this,MainActivity.class);
            startActivity(view);
            this.finish();
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }


}
