package Utils;

import java.sql.*;

public class DataBaseUtilMenu
{
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "Game";
    private static final String PASSWORD = "admin";

    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public String accederADescripcion(String campo, String nombre)
    {
        Connection connection = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;

        try {

            String valor = " ";
            String querySql = "SELECT ? FROM JUEGO WHERE NOMBRE = ?";

            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(querySql);
            preparedStatement.setString(1, campo);
            preparedStatement.setString(2, nombre);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                valor = resultSet.getString("CONTRASENYA");
            }
            else
            {
                return "error";
            }
            connection.close();
            return (String) valor;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
