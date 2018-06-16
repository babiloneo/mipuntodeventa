package com.babiloneo.puntodeventa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.babiloneo.puntodeventa.dataBase.Producto;
import com.babiloneo.puntodeventa.dataBase.service;

import io.realm.Realm;

public class AltasActivity extends AppCompatActivity {

    EditText nombre,precio,stock;
    Button guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altas);

        nombre=(EditText)findViewById(R.id.alt_nombre);
        precio=(EditText)findViewById(R.id.alt_precio);
        guardar=(Button)findViewById(R.id.btn_guardar);
        stock=(EditText)findViewById(R.id.alt_stock);

        Guardar();
    }

    private void Guardar() {
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stock.getText().toString().isEmpty() || nombre.getText().toString().isEmpty() || precio.getText().toString().isEmpty()) {
                    Toast.makeText(AltasActivity.this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    //Almacen almacen = new Almacen();
                    service s = new service(Realm.getDefaultInstance());
                    s.newProducto(nombre.getText().toString(), Double.valueOf(precio.getText().toString()).doubleValue(), Integer.parseInt(stock.getText().toString()));

                    Toast.makeText(AltasActivity.this, "Producto Guardado", Toast.LENGTH_SHORT).show();
                    limpiarCajas();
                }
            }
        });
    }
    public void limpiarCajas(){
        nombre.setText("");
        precio.setText("");
        stock.setText("");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == event.KEYCODE_BACK) {
            Intent view = new Intent(AltasActivity.this,ProductosActivity.class);
            startActivity(view);
            this.finish();
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }
}
