package Utils;

import DAO.Venta;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataBaseUtilCompra
{
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "Game";
    private static final String PASSWORD = "admin";

    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public int accederId()
    {
        Connection connection = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        int contador = 0;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            String querySql = "SELECT ID_VENTA FROM VENTAS";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(querySql);
            while(true)
            {
                if (!resultSet.next())
                {
                    break;
                }
                contador++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contador;
    }
    public int accederIdUsu(String nombre) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int idUsuario = 999; // Valor por defecto si no se encuentra el usuario

        try {
            connection = getConnection(); // Obtener la conexión desde algún método como getConnection()

            String querySql = "SELECT ID_USUARIO FROM USUARIO WHERE NOMBRE_USU = ?";
            preparedStatement = connection.prepareStatement(querySql);
            preparedStatement.setString(1, nombre);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                idUsuario = resultSet.getInt("ID_USUARIO");
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

        return idUsuario;
    }
    public int accederIdJue(String nombre)
    {
        Connection connection = null;
        int contador = 0;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            String querySql = "SELECT ID_JUEGO FROM JUEGO WHERE NOMBRE = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(querySql);
            preparedStatement1.setString(1, nombre);
            ResultSet resultSet = preparedStatement1.executeQuery();
            if (resultSet.next())
            {
                return resultSet.getInt("ID_JUEGO");
            }
            else {
                return 999;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean escribirVenta(Venta venta)
    {
        Connection connection = null;
        PreparedStatement preparedStatement1 = null;
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Formato deseado
        String fechaHoraFormateada = fechaHoraActual.format(formatter);

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            String querySqlUsu = "INSERT INTO VENTAS (ID_VENTA, ID_USUARIO, NOMBRE_USUARIO, ID_JUEGO, NOMBRE_JUEGO, FECHA, PRECIO) VALUES (?, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS'), ?)";
            preparedStatement1 = connection.prepareStatement(querySqlUsu);
            preparedStatement1.setInt(1, venta.getId_venta());
            preparedStatement1.setInt(2, venta.getId_usuario());
            preparedStatement1.setString(3, venta.getNombre_usuario());
            preparedStatement1.setInt(4, venta.getId_juego());
            preparedStatement1.setString(5, venta.getNombre_juego());
            preparedStatement1.setString(6, fechaHoraFormateada);
            preparedStatement1.setInt(7, venta.getPrecio());
            preparedStatement1.executeUpdate(); // Ejecutar la primera consulta
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            try {
                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                }
                if (connection != null) {
                    connection.setAutoCommit(true); // Restaurar el autocommit
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
