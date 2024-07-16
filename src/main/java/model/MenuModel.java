package model;

import Utils.DataBaseUtilLogin;
import Utils.DataBaseUtilMenu;

public class MenuModel
{
    DataBaseUtilMenu db = new DataBaseUtilMenu();
    public String getDescripciones(String campo, String nombre)
    {
        return (String) db.accederADescripcion(campo, nombre);
    }
}
