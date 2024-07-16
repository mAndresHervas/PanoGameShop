package DAO;

public class Juego
{
    private int id_juego;
    private int id_companyia;
    private String nombre;
    private String categoria;
    private int tiempo_Juego;
    private int precio;
    private int stock;
    private int pegi;

    public Juego(int id_juego, int id_companyia, String nombre, String categoria, int tiempo_Juego, int precio, int stock, int pegi)
    {
        this.id_juego = id_juego;
        this.id_companyia = id_companyia;
        this.nombre = nombre;
        this.categoria = categoria;
        this.tiempo_Juego = tiempo_Juego;
        this.precio = precio;
        this.stock = stock;
        this.pegi = pegi;
    }

    public int getId_juego() {
        return id_juego;
    }

    public void setId_juego(int id_juego) {
        this.id_juego = id_juego;
    }

    public int getId_companyia() {
        return id_companyia;
    }

    public void setId_companyia(int id_companyia) {
        this.id_companyia = id_companyia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getTiempo_Juego() {
        return tiempo_Juego;
    }

    public void setTiempo_Juego(int tiempo_Juego) {
        this.tiempo_Juego = tiempo_Juego;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPegi() {
        return pegi;
    }

    public void setPegi(int pegi) {
        this.pegi = pegi;
    }
}
