package com.babiloneo.puntodeventa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.babiloneo.puntodeventa.dataBase.service;

import io.realm.Realm;

public class BajasActivity extends AppCompatActivity {

    Button eliminar;
    EditText id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bajas);
        id=(EditText)findViewById(R.id.baj_id);
        eliminar=(Button)findViewById(R.id.btn_eliminar);
        funcion();
    }

    private void funcion() {
         eliminar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(id.getText().toString().isEmpty()){
                     Toast.makeText(BajasActivity.this, "Ingrese la ID", Toast.LENGTH_SHORT).show();

                 }else{
                     service s = new service(Realm.getDefaultInstance());
                     s.eliminarPrpoducto(Integer.parseInt(id.getText().toString()));
                     Toast.makeText(BajasActivity.this, "Producto Eliminado", Toast.LENGTH_SHORT).show();
                     id.setText("");
                 }


             }
         });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == event.KEYCODE_BACK) {
            Intent view = new Intent(BajasActivity.this,ProductosActivity.class);
            startActivity(view);
            this.finish();
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }
}
