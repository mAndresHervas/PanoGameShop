package Utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;

public class DataBaseUtilFicha
{
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "Game";
    private static final String PASSWORD = "admin";

    public static Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public String accederDatosCategoria(String nombre) throws SQLException
    {
        String categoria = null;
        String querySql = "SELECT CATEGORIA FROM JUEGO WHERE NOMBRE = ?";

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(querySql);
        preparedStatement.setString(1, nombre);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next())
        {
            categoria = resultSet.getString("CATEGORIA");
        }
        else
        {
            return "Error";
        }
        connection.close();
        return categoria;
    }
    public String accederDatosPrecio(String nombre) throws SQLException
    {
        String precio = null;
        String querySql = "SELECT PRECIO FROM JUEGO WHERE NOMBRE = ?";

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(querySql);
        preparedStatement.setString(1, nombre);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next())
        {
            precio = resultSet.getString("PRECIO");
        }
        else
        {
            return "Error";
        }
        connection.close();
        return precio;
    }
    public int accederDatosId(String nombre) throws SQLException {
        String id = null;
        String querySql = "SELECT ID_JUEGO FROM JUEGO WHERE NOMBRE = ?";

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(querySql);
        preparedStatement.setString(1, nombre);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next())
        {
            id = resultSet.getString("ID_JUEGO");
        }
        else
        {
            return 0;
        }
        connection.close();
        return Integer.parseInt(id);
    }
    public void anyadirImagenes(String path, int idImg, int idJue, String nombreImg)
    {
        String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "yourusername";
        String password = "yourpassword";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            String sql = "INSERT INTO IMAGEN (id_imagen, id_juego, imagenes) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idImg);// id
            statement.setInt(2, idJue);
            statement.setString(3, nombreImg); // name

            InputStream inputStream = new FileInputStream(path);
            statement.setBlob(3, inputStream);

            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("A contact was inserted with photo image.");
            }

            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
