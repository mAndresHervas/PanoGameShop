package model;

import Utils.DataBaseUtilAdmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class AdminModel
{
    DataBaseUtilAdmin da = new DataBaseUtilAdmin();
    public ArrayList<String> accederVentas()
    {
        return (ArrayList<String>) da.obtenerTodasLasVentas();
    }
    public HashMap<String, Integer> obtenerPreciosVentas()
    {
        return da.accederPreciosVentas();
    }
}
