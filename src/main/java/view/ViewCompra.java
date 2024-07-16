package view;

import controller.ControllerCompra;
import controller.ControllerJuego;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;


public class ViewCompra extends Application
{

    public void startCompra(Stage stage, String nombreUsu, String nombreJuego) {
        // Crear el GridPane y establecer propiedades
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setStyle("-fx-background-color: burlywood");

        // Etiquetas y VBox para información del usuario y juego
        Label nombreUsuario = new Label("Usuario: " + nombreUsu);
        nombreUsuario.setStyle("-fx-font-weight: bold");
        Label nombreJuegoLab = new Label("Artículo: " + nombreJuego);
        nombreJuegoLab.setStyle("-fx-font-weight: bold");
        VBox datosUsuario = new VBox(5);
        datosUsuario.setStyle("-fx-background-color: burlywood");
        datosUsuario.getChildren().addAll(nombreUsuario, nombreJuegoLab);

        // Campos y etiquetas para la información de la tarjeta
        Label labelNumeroTarjeta = new Label("Número de Tarjeta:");
        labelNumeroTarjeta.setStyle("-fx-font-weight: bold");
        TextField textFieldNumeroTarjeta = new TextField();
        textFieldNumeroTarjeta.setStyle("-fx-border-color: black; -fx-background-color: #D3D3D3");

        Label labelFechaCaducidad = new Label("Fecha de Caducidad (MM/YY):");
        labelFechaCaducidad.setStyle("-fx-font-weight: bold");
        TextField textFieldFechaCaducidad = new TextField();
        textFieldFechaCaducidad.setStyle("-fx-border-color: black; -fx-background-color: #D3D3D3");

        Label labelCVC = new Label("CVC:");
        labelCVC.setStyle("-fx-font-weight: bold");
        TextField textFieldCVC = new TextField();
        textFieldCVC.setStyle("-fx-border-color: black; -fx-background-color: #D3D3D3");

        // Botón de compra
        Button buttonComprar = new Button("Realizar Compra");
        buttonComprar.setStyle("-fx-background-color: blue; -fx-border-color: black; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");

        // Añadir elementos al GridPane
        grid.add(datosUsuario, 0, 0, 4, 1); // Columna 0, fila 0, 4 columnas de ancho, 1 fila de alto
        grid.add(labelNumeroTarjeta, 0, 1); // Columna 0, fila 1
        grid.add(textFieldNumeroTarjeta, 1, 1, 3, 1); // Columna 1, fila 1, 3 columnas de ancho, 1 fila de alto
        grid.add(labelFechaCaducidad, 0, 2); // Columna 0, fila 2
        grid.add(textFieldFechaCaducidad, 1, 2, 3, 1); // Columna 1, fila 2, 3 columnas de ancho, 1 fila de alto
        grid.add(labelCVC, 0, 3); // Columna 0, fila 3
        grid.add(textFieldCVC, 1, 3); // Columna 1, fila 3
        grid.add(buttonComprar, 0, 4, 4, 1); // Columna 0, fila 4, 4 columnas de ancho, 1 fila de alto

        buttonComprar.setOnAction( e -> {
            ControllerCompra cm = new ControllerCompra();
            ControllerJuego cj = new ControllerJuego();
            if (cm.contruirVenta(nombreUsu, nombreJuego, cj.precioJuego(nombreJuego)))
            {
                if (cm.prepararTicket(nombreUsu, nombreJuego, 100))
                {
                    ViewMenu vm = new ViewMenu();
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setHeaderText("Enhorabuena " + nombreUsu + "Acabas de comprar: " + nombreJuego);
                    alerta.setContentText("Tienes el recibo de compra en /desktop/panogs/compras");
                    alerta.show();
                    try {
                        vm.startMenu(stage, nombreUsu);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }
            else
            {
                ViewMenu vm = new ViewMenu();
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText("Algo ha ido mal...");
                alerta.setContentText("No se ha podido ejecutar la compra.");
                alerta.show();
                try {
                    vm.startMenu(stage, nombreUsu);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Configurar la escena y mostrarla en el stage
        Scene escena = new Scene(grid, 400, 400);
        stage.setScene(escena);
        stage.show();
    }
    @Override
    public void start(Stage stage) throws Exception
    {

    }
}
