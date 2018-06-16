package com.babiloneo.puntodeventa.dataBase;

import io.realm.RealmObject;

public class Usuario extends RealmObject {

    private int id;
    private String usuario;
    private String correo;
    private String contraseña;
    private String tipo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    private boolean altas;
    private boolean bajas;
    private boolean modificaciones;
    private boolean consultas;

    public boolean isConsultas() {
        return consultas;
    }

    public void setConsultas(boolean consultas) {
        this.consultas = consultas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isAltas() {
        return altas;
    }

    public void setAltas(boolean altas) {
        this.altas = altas;
    }

    public boolean isBajas() {
        return bajas;
    }

    public void setBajas(boolean bajas) {
        this.bajas = bajas;
    }

    public boolean isModificaciones() {
        return modificaciones;
    }

    public void setModificaciones(boolean modificaciones) {
        this.modificaciones = modificaciones;
    }

}
