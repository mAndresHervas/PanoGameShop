package model;

import DAO.Juego;
import Utils.DataBaseUtilAdmin;

public class JuegoModel
{
    DataBaseUtilAdmin dba = new DataBaseUtilAdmin();
    public boolean enviarJuego(Juego juego)
    {
        if (dba.anyadirJuegoBD(juego))
        {
            return true;
        }
        return false;
    }
    public int averiguarPrecio(String nombre)
    {
        return dba.accederPrecio(nombre);
    }
}
