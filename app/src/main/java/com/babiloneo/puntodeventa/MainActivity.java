package com.babiloneo.puntodeventa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.babiloneo.puntodeventa.dataBase.LocalStorage;
import com.babiloneo.puntodeventa.models.usuario;

public class MainActivity extends AppCompatActivity {

    Button ventas,productos,registrar,cerrar;
    TextView miuser;
    LocalStorage storage = new LocalStorage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registrar=(Button)findViewById(R.id.registro);
        LocalStorage storage =new LocalStorage();
        miuser=(TextView)findViewById(R.id.main_user);
        usuario user =storage.consultar(getApplicationContext());
        miuser.setText(user.getUsuario());
        ventas=(Button)findViewById(R.id.ventas);
        productos=(Button)findViewById(R.id.productos);
        cerrar=(Button)findViewById(R.id.cerrar_secion);
        funciones();

    }

    private void funciones() {

        usuario user=storage.consultar(getApplicationContext());
        if(user.getTipo().equals("administrador")){
            registrar.setVisibility(View.VISIBLE);
        }else{
            registrar.setVisibility(View.INVISIBLE);
        }

        //botom para ir a la venta de registro
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextViewIndex = new Intent(MainActivity.this,RegistroActivity.class);
                startActivity(nextViewIndex);
            }
        });
        //boton que cierra la secion
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario vacio=new usuario();
                storage.setUser(vacio,getApplicationContext());
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        //boton ir ventana productos
        productos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductosActivity.class);
                startActivity(intent);
            }
        });

        ventas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,VentasActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == event.KEYCODE_BACK) {
            Intent view = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(view);
            this.finish();
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }
}
