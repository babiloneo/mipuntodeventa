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

public class ModificActivity extends AppCompatActivity {

    Button buscar,actualizar;
    EditText id,nombre,precio,stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modific);
        id=(EditText)findViewById(R.id.mod_id);
        nombre=(EditText)findViewById(R.id.mod_nombre);
        precio=(EditText)findViewById(R.id.mod_precio);
        stock=(EditText)findViewById(R.id.mod_stock);

        buscar=(Button)findViewById(R.id.btn_buscar);
        actualizar=(Button)findViewById(R.id.btn_actualizar);

        function();
    }

    private void function() {
            buscar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(id.getText().toString().isEmpty()){
                        Toast.makeText(ModificActivity.this, "Llene el campo ID", Toast.LENGTH_SHORT).show();
                    }else{
                        service s = new service(Realm.getDefaultInstance());
                        Producto producto=s.getProducto(Integer.parseInt(id.getText().toString()));
                        if(producto!=null){
                            nombre.setText(producto.getNombre());
                            nombre.setEnabled(true);
                            precio.setText(Double.toString(producto.getPrecio()));
                            precio.setEnabled(true);
                            stock.setText(Integer.toString(producto.getStock()));
                            stock.setEnabled(true);

                            id.setEnabled(false);
                            buscar.setEnabled(false);
                            actualizar.setEnabled(true);
                        }else{
                            Toast.makeText(ModificActivity.this, "Producto no encontrado", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
            actualizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    service s = new service(Realm.getDefaultInstance());
                    s.actualizarProducto(Integer.parseInt(id.getText().toString()),nombre.getText().toString(), Double.valueOf(precio.getText().toString()).doubleValue(), Integer.parseInt(stock.getText().toString()));
                    Toast.makeText(ModificActivity.this, "Producto Actualizado", Toast.LENGTH_SHORT).show();
                    nombre.setEnabled(false);
                    nombre.setText("");
                    precio.setEnabled(false);
                    precio.setText("");
                    stock.setEnabled(false);
                    stock.setText("");

                    id.setEnabled(true);
                    id.setText("");
                    buscar.setEnabled(true);
                    actualizar.setEnabled(false);
                }
            });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == event.KEYCODE_BACK) {
            Intent view = new Intent(ModificActivity.this,ProductosActivity.class);
            startActivity(view);
            this.finish();
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }
}
