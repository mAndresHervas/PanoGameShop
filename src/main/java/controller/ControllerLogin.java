package controller;

import DAO.Usuario;
import model.LoginModel;

import java.sql.SQLException;

/**
 * La clase ControllerLogin, que sirve de enlace con el modelo del Login para controlar las operaciones y acceder a los datos desde el view.
 */
public class ControllerLogin
{
    LoginModel lm = new LoginModel();

    /**
     *
     * @param nombreUsu - El nombre de usuario proporcionado por el usuario en los campos de introduccion de texto.
     * @param password - La contrasenya proporcionada por el usuario en los campos de introduccion de texto.
     * @return - Devuelve un booleano segun los datos introducidos coinciden con algunos de los almacenados en la base de datos
     * @throws SQLException - Para controlar las excepciones lanzadas desde el util de la base de datos.
     */
    public boolean loginController(String nombreUsu, String password) throws SQLException {
        if (lm.compararDatos(nombreUsu, password))
        {
            return true;
        }
        return false;
    }

    /**
     *
     * @param nombre - El nombre proporcionado por el usuario en los campos de introduccion de texto.
     * @param nombreUsu - El nombre de usuario por el usuario en los campos de introduccion de texto.
     * @param edad - La edad proporcionada por el usuario en los campos de introduccion de texto.
     * @param direccion - La direccion proporcionada por el usuario en los campos de introduccion de texto.
     * @param contrasenya - La contrasenya proporcionada por el usuario en los campos de introduccion de texto.
     * @return - Devuelve un booleano segun se han podido guardar los datos en la Base de datos.
     * @throws SQLException - Para controlar las excepciones lanzadas desde el util de la base de datos.
     */
    public boolean registerController(String nombre, String nombreUsu, int edad, String direccion, String contrasenya) throws SQLException {
        //Creacion de un objeto Usuario (DAO).
        Usuario usuario = new Usuario(nombre, nombreUsu, edad, direccion, contrasenya);
        if (lm.registrarUsuario(usuario))
        {
            return true;
        }
        return false;
    }

    /**
     * @return - Devuelve un numero generado por algoritmo para asignarlo al usuario que se acaba de registrar.
     */
    public int idUsuGenerator()
    {
        return lm.obtenerId();
    }

    /**
     * @param nombreUsu - El nombre de usuario para buscar los datos del mismo.
     * @return - Devuelve un array de String con todos los datos del usuario.
     */
    public String[] conseguirDatosCuenta(String nombreUsu)
    {
        return lm.obtenerDatosCuenta(nombreUsu);
    }

    /**
     * @param nombreUsu - El nombre de usuario para buscar los datos del mismo.
     * @param puntos - La cantidad de puntos en los que se veran incrementados los puntos del usuario
     */
    public void incrementarPuntos(String nombreUsu, int puntos)
    {
        lm.enviarPuntos(nombreUsu, puntos);
    }
}
