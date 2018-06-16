package com.babiloneo.puntodeventa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.babiloneo.puntodeventa.dataBase.LocalStorage;
import com.babiloneo.puntodeventa.dataBase.Usuario;
import com.babiloneo.puntodeventa.dataBase.service;
import com.babiloneo.puntodeventa.models.usuario;

import java.util.List;

import io.realm.Realm;

public class LoginActivity extends AppCompatActivity {

    Realm realm;
    EditText user,contraseña;
    Button login;
    Switch recordar;
    LocalStorage storage = new LocalStorage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user=(EditText)findViewById(R.id.log_usuario);
        contraseña=(EditText)findViewById(R.id.log_comntraseña);
        recordar=(Switch)findViewById(R.id.recordar);
        login=(Button)findViewById(R.id.ingresar);
        usuario us=storage.consultar(getApplicationContext());
        //boorarUsuarios();
        crearUserAdmin();
        if (us!=null){
            if(us.isEstado()){
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        }
        loguear();
    }

    private void loguear() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service service =new service(Realm.getDefaultInstance());
                String miuser=user.getText().toString();
                String miconraseña=contraseña.getText().toString();
                Usuario user= service.user(miuser);
                if(user!=null){
                    if(user.getContraseña().equals(miconraseña)){
                        usuario nuser=new usuario();
                        nuser.setId(user.getId());
                        nuser.setUsuario(user.getUsuario());
                        nuser.setEstado(recordar.isChecked());
                        nuser.setTipo(user.getTipo());
                        nuser.setAltas(user.isAltas());
                        nuser.setBajas(user.isBajas());
                        nuser.setModificaciones(user.isModificaciones());
                        nuser.setConsultas(user.isConsultas());
                        storage.setUser(nuser,getApplicationContext());
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        LoginActivity.this.startActivity(i);
                        LoginActivity.this.finish();
                    }else{
                        Toast.makeText(getApplicationContext(),"La contraseña es incorrecta", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Este usuario no esta registrado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //metodo para crear usuario administrador por defecto
    public void crearUserAdmin(){
        service service =new service(Realm.getDefaultInstance());
        List<Usuario> user=service.obtener();
        if(user.size()<1){
            service.NewUser("iris","jiris_estrada@hotmail.com","12345"
                    ,"administrador",true,true,true,true);
        }
    }

    public void boorarUsuarios(){
        service service =new service(Realm.getDefaultInstance());
        service.deleteAll();
    }
}