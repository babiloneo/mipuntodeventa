package com.babiloneo.puntodeventa.dataBase;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class service {

    private Realm realm;
    public static Usuario user;
    public static boolean estado;
    public service(Realm realm){
        this.realm=realm;
    }

    public void NewUser(String usuario, String correo,String contraseña, String tipo, boolean altas, boolean bajas, boolean modifi, boolean consultas) {

        realm.beginTransaction();

        Number currentIdNum = realm.where(Usuario.class).max("id");
        int nextId;
        if(currentIdNum == null) {
            nextId = 1;
        } else {
            nextId = currentIdNum.intValue() + 1;
        }

        Usuario nuevo=realm.createObject(Usuario.class);
        nuevo.setId(nextId);
        nuevo.setUsuario(usuario);
        nuevo.setCorreo(correo);
        nuevo.setContraseña(contraseña);
        nuevo.setTipo(tipo);
        nuevo.setAltas(altas);
        nuevo.setBajas(bajas);
        nuevo.setModificaciones(modifi);
        nuevo.setConsultas(consultas);

        realm.commitTransaction();
    }

    public List<Usuario> obtener(){
        RealmResults<Usuario> r=realm.where(Usuario.class).findAll();
        return r;
    }

    public Usuario user(String usuario){

        Usuario miuser = realm.where(Usuario.class).equalTo("usuario",usuario).findFirst();
        return miuser;
    }

    public void deleteAll(){
        realm.beginTransaction();

        RealmResults<Usuario> r=realm.where(Usuario.class).findAll();
        r.deleteAllFromRealm();
        realm.commitTransaction();

    }

    //MEtodo para gregar un nuevo producto a mi BD
    public void newProducto(String nombre, double precio,int stock) {

        realm.beginTransaction();//Iniciar transaccion en mi db

        Number currentIdNum = realm.where(Producto.class).max("id");
        int nextId;
        if(currentIdNum == null) {
            nextId = 1;
        } else {
            nextId = currentIdNum.intValue() + 1;
        }

        Producto nuevo=realm.createObject(Producto.class);
        nuevo.setId(nextId);
        nuevo.setNombre(nombre);
        nuevo.setPrecio(precio);
        nuevo.setStock(stock);

        realm.commitTransaction(); //Terminar transaccion en mi db
    }

    //metodo para consultar lista de productos
    public List<Producto> obtenerProductos(){
        RealmResults<Producto> r=realm.where(Producto.class).findAll();
        return r;
    }

    //metodo para eliminar producto
    public void eliminarPrpoducto(int id){
        realm.beginTransaction();
        RealmResults<Producto> result = realm.where(Producto.class).equalTo("id",id).findAll();
        result.deleteAllFromRealm();
        realm.commitTransaction();
    }

    //buscar un producto po r id y devolverlo si se encuentra
    public Producto getProducto(int id){
        Producto producto = realm.where(Producto.class).equalTo("id",id).findFirst();
        return producto;
    }

    //meotodo para actualizar un producto en mi BD
    public void actualizarProducto(int id,String nombre,double precio,int stock){
        realm.beginTransaction();
        Producto p = realm.where(Producto.class).equalTo("id",id).findFirst();
        p.setNombre(nombre);
        p.setPrecio(precio);
        p.setStock(stock);
        realm.copyToRealm(p); //instrucción para asegurar inserción
        realm.commitTransaction();
    }
}
