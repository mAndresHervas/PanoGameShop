package controller;

import DAO.Juego;
import model.JuegoModel;

public class ControllerJuego
{
    JuegoModel jm = new JuegoModel();
    public boolean anyadirJuego(int id_juego, int id_companyia, String nombre, String categoria, int tiempo_juego, int precio, int stock, int pegi)
    {
        Juego juego = new Juego(id_juego, id_companyia, nombre, categoria, tiempo_juego, precio, stock, pegi);
        if (jm.enviarJuego(juego))
        {
            return true;
        }
        return false;
    }
    public int precioJuego(String nombre)
    {
        return jm.averiguarPrecio(nombre);
    }

}
