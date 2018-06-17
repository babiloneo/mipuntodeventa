package com.babiloneo.puntodeventa.dataBase;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.babiloneo.puntodeventa.models.usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;

public class LocalStorage {

    private static final String PREFS_NAME = "ALUMNOSDB";
    public static int cantidad;
    public static double total;
    public void setUser(usuario user, Context context) {

        boolean estado=false;
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        SharedPreferences settings =
                context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);

            ArrayList<usuario> ListaUsuario = new ArrayList<>();
            ListaUsuario.add(user);
            String us = gson.toJson(ListaUsuario);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("usuarios", us);
            editor.commit();

    }
    public usuario consultar(Context context) {
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        SharedPreferences settings =
                context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();
        String jsonusuario = settings.getString("usuarios", null);
        ArrayList<usuario> user = null;
        if (jsonusuario != null) {
            usuario[] arrayProductos = gson.fromJson(jsonusuario, usuario[].class);
            user = new ArrayList<>(Arrays.asList(arrayProductos));
        }
        usuario miUser=new usuario();
        if(user!=null){
            miUser = user.get(0);
        }


        return miUser;
    }
}
