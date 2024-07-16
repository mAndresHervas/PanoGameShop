package view;

import controller.ControllerFichas;
import controller.ControllerLogin;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.FichasModel;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Insets;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class ViewFicha extends Application
{
    ControllerFichas cf = new ControllerFichas();

    @Override
    public void start(Stage stage) throws Exception
    {

    }


    public void fichas(Stage stage, String nombre, String nombreUsu) throws SQLException {
        int idJuego = cf.obtenerId(nombre);
        ArrayList<ImageView> listaImagenes = cf.cargarImagenes(idJuego);
        String categoriaJuego = cf.obtenerCategoria(nombre);
        String precioJuego = cf.obtenerPrecio(nombre);

        // Crear contenedor GridPane
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(10, 10,  10, 10));
        gridPane.setStyle("-fx-background-color: burlywood");

        // Crear componentes
        Button retroceder = new Button("Volver a ver juegos");
        Button carrito = new Button("Añadir a la compra");
        retroceder.setStyle("-fx-background-color: blue; -fx-border-color: black; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
        carrito.setStyle("-fx-background-color: blue; -fx-border-color: black; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
        Label nombreJuego = new Label(nombre + " ");
        Label categoria = new Label(" Categoria: " + categoriaJuego + " ");
        Label precio = new Label(" Precio: " + precioJuego + " ");
        nombreJuego.setStyle("-fx-background-color: white; -fx-border-style: solid; -fx-border-width: 2px; -fx-font-weight: bold");
        categoria.setStyle("-fx-background-color: white; -fx-border-style: solid; -fx-border-width: 2px;");
        precio.setStyle("-fx-background-color: white; -fx-border-style: solid; -fx-border-width: 2px;");

        Separator separador = new Separator();
        separador.setStyle("-fx-border-color: black; -fx-background-color: aqua");

        // Añadir componentes al GridPane
        if (listaImagenes.size() >= 4) {
            gridPane.add(listaImagenes.get(0), 0, 0); // Primera imagen
            gridPane.add(listaImagenes.get(1), 1, 0); // Segunda imagen
            gridPane.add(listaImagenes.get(2), 0, 1); // Tercera imagen
            gridPane.add(listaImagenes.get(3), 1, 1); // Cuarta imagen
        }

        gridPane.add(nombreJuego, 0, 2, 2, 1); // Nombre del juego abarcando dos columnas
        gridPane.add(categoria, 0, 3, 2, 1); // Categoría abarcando dos columnas
        gridPane.add(precio, 0, 4, 2, 1); // Precio abarcando dos columnas
        gridPane.add(separador, 0, 5, 2, 1); // Separador abarcando dos columnas
        gridPane.add(retroceder, 0, 6); // Botón de retroceder
        gridPane.add(carrito, 1, 6); // Botón de carrito

        // Manejar acciones de los botones
        retroceder.setOnAction(e -> {
            ViewMenu vm = new ViewMenu();
            try {
                vm.startMenu(stage, nombreUsu);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        carrito.setOnAction(e -> {
            ViewCompra vc = new ViewCompra();
            vc.startCompra(stage, nombreUsu, nombre);
        });

        // Configurar la escena y el stage
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(gridPane);
        scroll.setFitToWidth(true);

        Scene escena = new Scene(scroll, 800, 600);
        stage.setScene(escena);
        stage.show();

    }
}
