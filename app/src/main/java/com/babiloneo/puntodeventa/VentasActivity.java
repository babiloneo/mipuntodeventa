package com.babiloneo.puntodeventa;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.babiloneo.puntodeventa.adaptador.productosAdapter;
import com.babiloneo.puntodeventa.adaptador.ventasAdapter;
import com.babiloneo.puntodeventa.dataBase.LocalStorage;
import com.babiloneo.puntodeventa.dataBase.Producto;
import com.babiloneo.puntodeventa.dataBase.Usuario;
import com.babiloneo.puntodeventa.dataBase.Ventas;
import com.babiloneo.puntodeventa.dataBase.service;
import com.babiloneo.puntodeventa.models.usuario;

import java.util.List;

import io.realm.Realm;

public class VentasActivity extends AppCompatActivity {

    List<Producto> lista;
    ventasAdapter adapter;
    ListView vista;
    Button pagar,historial;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);
        vista=(ListView)findViewById(R.id.listview_ventas);
        pagar=(Button)findViewById(R.id.btn_pagar);
        historial=(Button)findViewById(R.id.btn_hitorial);
        listar();
        mContext = this;
        funciones();
    }

    private void funciones() {
        pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(LocalStorage.cantidad>0){

                    final LocalStorage storage=new LocalStorage();
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setMessage("El total de " + LocalStorage.cantidad + " productos es de $"+LocalStorage.total);
                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            service s = new service(Realm.getDefaultInstance());
                            usuario u =storage.consultar(getApplicationContext());
                            s.newVenta(LocalStorage.cantidad,LocalStorage.total,u.getUsuario());
                            Toast.makeText(VentasActivity.this, "Venta pagada", Toast.LENGTH_SHORT).show();
                            LocalStorage.cantidad=0;
                            LocalStorage.total=0;
                            Intent view = new Intent(VentasActivity.this,MainActivity.class);
                            startActivity(view);
                            VentasActivity.this.finish();
                            onBackPressed();
                        }
                    });
                    builder.setNegativeButton("Cancelar",null);
                    builder.show();
                }else{
                    Toast.makeText(VentasActivity.this, "Seleccione un producto", Toast.LENGTH_SHORT).show();
                }
            }
        });

        historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(VentasActivity.this,HistorialActivity.class);
                startActivity(view);
            }
        });
    }

    private void listar() {
        service s = new service(Realm.getDefaultInstance());

        lista=s.obtenerProductos();
        adapter=new ventasAdapter(getApplicationContext(),R.layout.list_compras,lista);
        vista.setAdapter(adapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == event.KEYCODE_BACK) {
            Intent view = new Intent(VentasActivity.this,MainActivity.class);
            startActivity(view);
            this.finish();
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }
}
