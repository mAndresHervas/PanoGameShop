package controller;

import model.AdminModel;

import java.util.ArrayList;
import java.util.HashMap;

public class ControllerAdmin
{
    AdminModel am = new AdminModel();
    public ArrayList<String> rellenarLista()
    {
        return am.accederVentas();
    }
    public HashMap<String, Integer> contarJuegos()
    {
        return am.obtenerPreciosVentas();
    }
}
