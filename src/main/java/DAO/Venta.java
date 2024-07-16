package DAO;

import controller.ControllerCompra;

import java.time.LocalDate;

public class Venta
{
    ControllerCompra cc = new ControllerCompra();
    private int id_venta;
    private int id_usuario;
    private String nombre_usuario;
    private int id_juego;
    private String nombre_juego;
    private int precio;

    public Venta(String nombre_usuario, String nombre_juego, int precio)
    {
        this.id_venta = cc.id_generator() + 10;
        this.id_usuario = cc.idUsu(nombre_usuario);
        this.nombre_usuario = nombre_usuario;
        this.id_juego = cc.idJue(nombre_juego);
        this.nombre_juego = nombre_juego;
        this.precio = precio;
    }

    public ControllerCompra getCc() {
        return cc;
    }

    public void setCc(ControllerCompra cc) {
        this.cc = cc;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public int getId_juego() {
        return id_juego;
    }

    public void setId_juego(int id_juego) {
        this.id_juego = id_juego;
    }

    public String getNombre_juego() {
        return nombre_juego;
    }

    public void setNombre_juego(String nombre_juego) {
        this.nombre_juego = nombre_juego;
    }


    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
