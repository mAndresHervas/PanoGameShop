package Utils;

import DAO.Juego;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class DataBaseUtilAdmin
{
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "Game";
    private static final String PASSWORD = "admin";

    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public boolean anyadirJuegoBD(Juego juego) {
        Connection connection = null;
        PreparedStatement preparedStatement1 = null;

        try {
            connection = getConnection();

            // Query para insertar en la tabla JUEGO
            String querySqlJuego = "INSERT INTO JUEGO (ID_JUEGO, ID_COMPANYIA, NOMBRE, CATEGORIA, TIEMPO_JUEGO, PRECIO, STOCK, PEGI) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement1 = connection.prepareStatement(querySqlJuego);
            preparedStatement1.setInt(1, juego.getId_juego());
            preparedStatement1.setInt(2, juego.getId_companyia());
            preparedStatement1.setString(3, juego.getNombre());
            preparedStatement1.setString(4, juego.getCategoria());
            preparedStatement1.setInt(5, juego.getTiempo_Juego());
            preparedStatement1.setInt(6, juego.getPrecio());
            preparedStatement1.setInt(7, juego.getStock());
            preparedStatement1.setInt(8, juego.getPegi());

            // Ejecutar la consulta
            preparedStatement1.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Imprimir la traza de la excepción para depuración
            return false;
        } finally {
            // Cerrar recursos en un bloque finally
            try {
                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Imprimir la traza de la excepción en el cierre
            }
        }
        return true;
    }
    public List<String> obtenerTodasLasVentas()
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> ventas = new ArrayList<>();

        try {
            connection = getConnection(); // Obtén la conexión a la base de datos

            String querySql = "SELECT ID_VENTA, ID_USUARIO, NOMBRE_USUARIO, ID_JUEGO, NOMBRE_JUEGO, FECHA, PRECIO FROM VENTAS";
            preparedStatement = connection.prepareStatement(querySql);
            resultSet = preparedStatement.executeQuery();

            // Procesar cada fila de resultados
            while (resultSet.next()) {
                int idVenta = resultSet.getInt("ID_VENTA");
                int idUsu = resultSet.getInt("ID_USUARIO");
                String nombreUsuario = resultSet.getString("NOMBRE_USUARIO");
                int idJuego = resultSet.getInt("ID_JUEGO");
                String nombreJuego = resultSet.getString("NOMBRE_JUEGO");
                Date fecha = resultSet.getDate("FECHA");
                int precio = resultSet.getInt("PRECIO");

                // Construir una cadena con los datos de la venta
                String ventaString = String.format(" %d | %d | %s | %d |  %s |  %s |  %d",
                        idVenta, idUsu, nombreUsuario, idJuego, nombreJuego, fecha.toString(), precio);

                ventas.add(ventaString); // Agregar la venta a la lista
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Manejar la excepción adecuadamente en tu aplicación
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Manejar la excepción adecuadamente en tu aplicación
            }
        }

        return ventas;
    }
    public int accederPrecio(String nombre)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int precio = 0;

        try {
            connection = getConnection(); // Obtener la conexión desde algún método como getConnection()

            String querySql = "SELECT PRECIO FROM JUEGO WHERE NOMBRE = ?";
            preparedStatement = connection.prepareStatement(querySql);
            preparedStatement.setString(1, nombre);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                precio = resultSet.getInt("PRECIO");
            }

        } catch (SQLException e) {
            // Manejar la excepción según tu lógica de la aplicación
            e.printStackTrace(); // Aquí se imprime el stack trace, considera manejarlo de otra manera según tu aplicación
            // Podrías lanzar una excepción específica o retornar un valor particular según el contexto
            throw new RuntimeException("Error al acceder al ID de usuario: " + e.getMessage());
        } finally {
            // Cerrar recursos en el orden inverso de su apertura para evitar problemas de recursos no liberados
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Manejar o lanzar una excepción según tu lógica de la aplicación
            }
        }

        return precio;
    }
    public HashMap<String, Integer> accederPreciosVentas()
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        HashMap<String, Integer> mapa = new HashMap<>();

        String querySQL = "SELECT NOMBRE_JUEGO, COUNT(*) FROM VENTAS GROUP BY NOMBRE_JUEGO";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(querySQL);
            resultSet = preparedStatement.executeQuery(querySQL);
            while(resultSet.next())
            {
                mapa.put(resultSet.getString("NOMBRE_JUEGO"), resultSet.getInt("COUNT(*)"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mapa;
    }
}
