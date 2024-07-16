package model;

import DAO.Venta;
import Utils.DataBaseUtilCompra;

public class CompraModel
{
    DataBaseUtilCompra dc = new DataBaseUtilCompra();
    public int asignarId()
    {
        return dc.accederId();
    }
    public int comprobarId(String nombre)
    {
        return dc.accederIdUsu(nombre);
    }
    public int comprobarIdJue(String nombre)
    {
        return dc.accederIdJue(nombre);
    }
    public boolean guardarVenta(Venta venta)
    {
        if (dc.escribirVenta(venta))
        {
            return true;
        }
        return false;
    }
}
