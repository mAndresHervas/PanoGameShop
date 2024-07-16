package controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.FichasModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class ControllerFichas
{
    FichasModel fm = new FichasModel();
    public String obtenerCategoria(String nombre) throws SQLException
    {
        return fm.categoriaJuego(nombre);
    }
    public String obtenerPrecio(String nombre) throws SQLException
    {
        return fm.precioJuego(nombre);
    }
    public int obtenerId(String nombre) throws SQLException {
        return fm.idJuego(nombre);
    }
    public ArrayList<ImageView> cargarImagenes(int idJuego) {
        ArrayList<ImageView> lista = new ArrayList<>();
        String path1 = null;
        String path2 = null;
        String path3 = null;
        String path4 = null;

        switch (idJuego) {
            case 1:
                path1 = "/View/gtaFichaUno.jpg";
                path2 = "/View/gtaFichaDos.jpg";
                path3 = "/View/gtaFichaTres.jpg";
                path4 = "/View/gtaFichaQuatro.jpg";
                break;
            case 2:
                path1 = "/View/rdrFichaUno.jpg";
                path2 = "/View/rdrFichaDos.jpg";
                path3 = "/View/rdrFichaTres.jpg";
                path4 = "/View/rdrFichaQuatre.jpg";
                break;
            case 3:
                path1 = "/View/sekiroFichaUno.jpg";
                path2 = "/View/sekiroFichaDos.jpg";
                path3 = "/View/sekiroFichaTres.jpg";
                path4 = "/View/sekiroFichaQuatre.jpg";
                break;
            case 4:
                path1 = "/View/gowFichaUno.jpg";
                path2 = "/View/gowFichaDos.jpg";
                path3 = "/View/gowFichaTres.jpg";
                path4 = "/View/gowFichaQuatre.jpg";
                break;
            case 5:
                path1 = "/View/jcFichaUno.jpg";
                path2 = "/View/jcFichaDos.jpg";
                path3 = "/View/jcFichaTres.jpg";
                path4 = "/View/jcFichaQuatre.jpg";
                break;

            case 6:
                path1 = "/View/acsFichaUno.jpg";
                path2 = "/View/acsFichaDos.jpg";
                path3 = "/View/acsFichaTres.jpg";
                path4 = "/View/acsFichaQuatre.jpg";
                break;

            default:
                // Opcional: manejar casos en los que el idJuego no coincide con ningún caso conocido
                throw new IllegalArgumentException("ID de juego no válido: " + idJuego);
        }

        try {
            String imagePath1 = path1;
            Image imagen1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath1)));
            ImageView imageView1 = new ImageView(imagen1);

            String imagePath2 = path2;
            Image imagen2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath2)));
            ImageView imageView2 = new ImageView(imagen2);

            String imagePath3 = path3;
            Image imagen3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath3)));
            ImageView imageView3 = new ImageView(imagen3);

            String imagePath4 = path4;
            Image imagen4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath4)));
            ImageView imageView4 = new ImageView(imagen4);

            lista.add(imageView1);
            lista.add(imageView2);
            lista.add(imageView3);
            lista.add(imageView4);
        } catch (NullPointerException e) {
            System.err.println("No se pudo cargar una o más imágenes para el juego con ID " + idJuego);
            e.printStackTrace();
        }
        return lista;
    }

}
