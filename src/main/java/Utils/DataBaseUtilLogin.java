package Utils;

import DAO.Usuario;

import java.sql.*;

public class DataBaseUtilLogin {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "Game";
    private static final String PASSWORD = "admin";

    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public String getPasswordDb(String nombreUsuario) throws SQLException {
        String password = null;
        String querySql = "SELECT CONTRASENYA FROM LOGIN WHERE NOMBRE_USU = ?";

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(querySql);
        preparedStatement.setString(1, nombreUsuario);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            password = resultSet.getString("CONTRASENYA");
        } else {
            return "a";
        }
        connection.close();
        return password;
    }

    public boolean anyadirUsuarioBD(Usuario usuario) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false); // Desactivar el autocommit para la transacción

            // Query para insertar en la tabla USUARIO
            String querySqlUsu = "INSERT INTO USUARIO (ID_USUARIO, NOMBRE, NOMBRE_USU, EDAD, PUNTOS, DIRECCION) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement1 = connection.prepareStatement(querySqlUsu);
            preparedStatement1.setInt(1, usuario.getId_usuario());
            preparedStatement1.setString(2, usuario.getNombre());
            preparedStatement1.setString(3, usuario.getNombreUsu());
            preparedStatement1.setInt(4, usuario.getEdad());
            preparedStatement1.setInt(5, usuario.getPuntos());
            preparedStatement1.setString(6, usuario.getDireccion());
            preparedStatement1.executeUpdate(); // Ejecutar la primera consulta

            // Query para insertar en la tabla LOGIN
            String querySqlLog = "INSERT INTO LOGIN (ID_USUARIO, NOMBRE_USU, CONTRASENYA) VALUES (?, ?, ?)";
            preparedStatement2 = connection.prepareStatement(querySqlLog);
            preparedStatement2.setInt(1, usuario.getId_usuario());
            preparedStatement2.setString(2, usuario.getNombreUsu());
            preparedStatement2.setString(3, usuario.getContrasenya());
            preparedStatement2.executeUpdate();

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
                if (preparedStatement2 != null) {
                    preparedStatement2.close();
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

    public int accederId() {
        Connection connection = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        int contador = 0;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            String querySql = "SELECT ID_USUARIO FROM USUARIO";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(querySql);
            while (true) {
                if (!resultSet.next()) {
                    break;
                }
                contador++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contador;
    }

    public String[] accederDatosCuenta(String nombreUsu) {
        Connection connection = null;
        PreparedStatement preparedStatement1 = null;
        String[] datos = new String[6];
        try {
            connection = getConnection();

            String querySql = "SELECT ID_USUARIO, NOMBRE, NOMBRE_USU, EDAD, DIRECCION, PUNTOS FROM USUARIO WHERE NOMBRE_USU = ?";
            preparedStatement1 = connection.prepareStatement(querySql);
            preparedStatement1.setString(1, nombreUsu);

            try (ResultSet resultSet = preparedStatement1.executeQuery()) {
                if (resultSet.next()) {
                    datos[0] = resultSet.getString("ID_USUARIO");
                    datos[1] = resultSet.getString("NOMBRE");
                    datos[2] = resultSet.getString("NOMBRE_USU");
                    datos[3] = resultSet.getString("EDAD");
                    datos[4] = resultSet.getString("DIRECCION");
                    datos[5] = resultSet.getString("PUNTOS");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return datos;
    }

    public void alterarPuntos(String nombreUsu, int puntos) {
        int puntosMax = accederPuntos(nombreUsu) + puntos;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String querySQL = "UPDATE USUARIO SET PUNTOS = ? WHERE NOMBRE_USU = ?";

        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(querySQL);
            preparedStatement.setInt(1, puntosMax);
            preparedStatement.setString(2, nombreUsu);

            int filasActualizadas = preparedStatement.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Actualización exitosa para el usuario: " + nombreUsu);
                connection.commit();
            } else {
                System.out.println("No se encontró el usuario: " + nombreUsu);
                connection.rollback();
            }

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private int accederPuntos(String nombreUsu) {
        int puntos = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String querySql = "SELECT PUNTOS FROM USUARIO WHERE NOMBRE_USU = ?";

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(querySql);
            preparedStatement.setString(1, nombreUsu);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                puntos = resultSet.getInt("PUNTOS");
            } else {
                System.out.println("No se encontraron puntos para el usuario: " + nombreUsu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return puntos;
    }
}
