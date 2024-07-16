package model;

import DAO.Usuario;
import Utils.DataBaseUtilLogin;

import java.sql.SQLException;
import java.util.Objects;

/**
 * La clase LoginModel, que sirve de enlace directo entre el controlador y la base de datos
 */
public class LoginModel
{
    DataBaseUtilLogin db = new DataBaseUtilLogin();

    /**
     * @param nombreUsu - El nombre de usuario que proporciona el usuario al iniciar sesion
     * @param password - La contrasenya que proporciona el usuario al iniciar sesion
     * @return - Devuelve un booleano en base a si los datos introducidos coinciden con algunos de los almacenados en la base de datos 8Table Login).
     * @throws SQLException - Para controlar las excepciones lanzadas en la base de datos.
     */
    public boolean compararDatos(String nombreUsu, String password) throws SQLException {
        String passwordBD = db.getPasswordDb(nombreUsu);
        if (Objects.equals(passwordBD, password))
        {
            return true;
        }
        else if (Objects.equals(passwordBD, "a"))
        {
            return false;
        }
        return false;
    }

    /**
     * @param usuario -  Un objeto Usuario (DAO), que servira para registrar sus atributos en la base de datos (Table Usuario).
     * @return - Devuelve un booleano en base a si se ha podido almacenar al nuevo usuario sin problema.
     * @throws SQLException - Para controlar las excepciones lanzadas en la base de datos.
     */
    public boolean registrarUsuario(Usuario usuario) throws SQLException {
        if (db.anyadirUsuarioBD(usuario))
        {
            return true;
        }
        return false;
    }
    public int obtenerId()
    {
        return db.accederId();
    }
    public String[] obtenerDatosCuenta(String nombreUsu)
    {
        return db.accederDatosCuenta(nombreUsu);
    }
    public void enviarPuntos(String nombreUsu, int puntos)
    {
        db.alterarPuntos(nombreUsu, puntos);
    }
}
