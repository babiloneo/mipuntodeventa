package com.babiloneo.puntodeventa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.babiloneo.puntodeventa.dataBase.Usuario;
import com.babiloneo.puntodeventa.dataBase.service;

import org.w3c.dom.Text;

import java.util.List;

import io.realm.Realm;

public class RegistroActivity extends AppCompatActivity {

    Button registrar;
    EditText usuario,correo,contraseña;

    private CheckBox altas, bajas, modifi, consultas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        registrar=(Button)findViewById(R.id.btn_registrar);
        usuario=(EditText)findViewById(R.id.txt_usuario);
        correo=(EditText)findViewById(R.id.txt_correo);
        contraseña=(EditText)findViewById(R.id.txt_contra);
        altas=(CheckBox)findViewById(R.id.altas);
        bajas=(CheckBox)findViewById(R.id.bajas);
        consultas=(CheckBox)findViewById(R.id.consulta);
        modifi=(CheckBox)findViewById(R.id.modificaciones);
        funciones();
    }

    private void funciones() {
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usuario.getText().toString().isEmpty() || correo.getText().toString().isEmpty() || contraseña.getText().toString().isEmpty() ){
                    Toast.makeText(RegistroActivity.this,"Llene todos los campos",Toast.LENGTH_SHORT).show();
                }else{
                    service s=new service(Realm.getDefaultInstance());
                    s.NewUser(usuario.getText().toString(),correo.getText().toString(),contraseña.getText().toString()
                    ,"cliente",altas.isChecked(),bajas.isChecked(),modifi.isChecked(),consultas.isChecked());

                    Intent i = new Intent(RegistroActivity.this, LoginActivity.class);
                    RegistroActivity.this.startActivity(i);
                    RegistroActivity.this.finish();
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == event.KEYCODE_BACK) {
            Intent view = new Intent(RegistroActivity.this,MainActivity.class);
            startActivity(view);
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }
}
