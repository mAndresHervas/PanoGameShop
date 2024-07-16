package model;

import Utils.DataBaseUtilFicha;

import java.sql.SQLException;

public class FichasModel
{
    DataBaseUtilFicha df = new DataBaseUtilFicha();
    public String categoriaJuego(String nombre) throws SQLException
    {
        return df.accederDatosCategoria(nombre);
    }
    public String precioJuego(String nombre) throws SQLException
    {
        return df.accederDatosPrecio(nombre);
    }
    public int idJuego(String nombre) throws SQLException {
        return df.accederDatosId(nombre);
    }
}
