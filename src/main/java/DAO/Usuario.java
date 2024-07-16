package DAO;

import controller.ControllerLogin;

public class Usuario
{
    ControllerLogin cl = new ControllerLogin();
    private int id_usuario;
    private String nombre;
    private String nombreUsu;
    private int edad;
    private String direccion;
    private int puntos;
    private String contrasenya;
    private static int indexId;

    public Usuario(String nombre, String nombreUsu, int edad, String direccion, String contrasenya)
    {
        this.id_usuario = cl.idUsuGenerator() + 1;
        this.nombre = nombre;
        this.nombreUsu = nombreUsu;
        this.edad = edad;
        this.direccion = direccion;
        this.puntos = 1000;
        this.contrasenya = contrasenya;
        indexId++;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsu() {
        return nombreUsu;
    }

    public void setNombreUsu(String nombreUsu) {
        this.nombreUsu = nombreUsu;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }
}
