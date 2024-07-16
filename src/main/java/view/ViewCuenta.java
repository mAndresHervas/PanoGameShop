package view;

import controller.ControllerLogin;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.LoginModel;

public class ViewCuenta extends Application
{
    ControllerLogin cl = new ControllerLogin();
    public void startCuenta(Stage stage, String nombreUsu)
    {
        String[] datos = cl.conseguirDatosCuenta(nombreUsu);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
        gridPane.setStyle("-fx-background-color: burlywood");

        Label idUsu = new Label("Id de usuario: " + datos[0]);
        idUsu.setStyle("-fx-background-color: white; -fx-border-style: solid; -fx-border-width: 2px; -fx-font-weight: bold");
        Label nombre = new Label("Nombre: " + datos[1]);
        nombre.setStyle("-fx-background-color: white; -fx-border-style: solid; -fx-border-width: 2px; -fx-font-weight: bold");
        Label nombreUsuario = new Label("Nombre de usuario: " + datos[2]);
        nombreUsuario.setStyle("-fx-background-color: white; -fx-border-style: solid; -fx-border-width: 2px; -fx-font-weight: bold");
        Label edad = new Label("Edad: " + datos[3]);
        edad.setStyle("-fx-background-color: white; -fx-border-style: solid; -fx-border-width: 2px; -fx-font-weight: bold");
        Label direccion = new Label("Direccion asociada: " + datos[4]);
        direccion.setStyle("-fx-background-color: white; -fx-border-style: solid; -fx-border-width: 2px; -fx-font-weight: bold");
        Label puntos = new Label("Puntos canjeables: " + datos[5]);
        puntos.setStyle("-fx-background-color: white; -fx-border-style: solid; -fx-border-width: 2px; -fx-font-weight: bold");
        Button cambiarNombre = new Button("Cambiar mi nombre de usuario");
        cambiarNombre.setStyle("-fx-background-color: blue; -fx-border-color: black; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
        Button retro = new Button("Volver");
        retro.setStyle("-fx-background-color: blue; -fx-border-color: black; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
        retro.setOnAction( e -> {
            ViewMenu vm = new ViewMenu();
            try {
                vm.startMenu(stage, nombreUsu);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        gridPane.add(idUsu, 0, 0, 2, 1);
        gridPane.add(nombre, 0, 1, 2, 1);
        gridPane.add(nombreUsuario, 0, 2);
        gridPane.add(cambiarNombre, 1, 2);
        gridPane.add(edad, 0, 3, 2, 1);
        gridPane.add(direccion, 0, 4, 2, 1);
        gridPane.add(puntos, 0, 5, 2, 1);
        gridPane.add(retro, 0, 6, 4, 4);

        Scene escena = new Scene(gridPane, 600, 400);
        stage.setScene(escena);
        stage.show();

    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}
