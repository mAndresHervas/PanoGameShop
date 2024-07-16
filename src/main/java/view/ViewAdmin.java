package view;

import controller.ControllerAdmin;
import controller.ControllerCompra;
import controller.ControllerJuego;
import javafx.application.Application;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewAdmin extends Application
{
    Button botonVolver = new Button("Volver");
    static ViewAdmin va = new ViewAdmin();
    static ControllerJuego cj = new ControllerJuego();
    ControllerAdmin ca = new ControllerAdmin();
    @Override
    public void start(Stage stage) throws Exception
    {
        Label titleLabel = new Label("Menú de Gestión");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Crear botones
        Button addButton = new Button("Añadir juego");
        Button deleteButton = new Button("Eliminar juego");
        Button salesButton = new Button("Consultar ventas");
        Button balanceButton = new Button("Ver balance");

        // Crear separadores
        Separator separator1 = new Separator();
        Separator separator2 = new Separator();
        Separator separator3 = new Separator();

        // Crear GridPane para organizar botones y etiquetas
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        // Añadir componentes al GridPane
        gridPane.add(addButton, 0, 0);
        gridPane.add(deleteButton, 0, 1);
        gridPane.add(separator1, 0, 2);
        gridPane.add(salesButton, 0, 3);
        gridPane.add(separator2, 0, 4);
        gridPane.add(balanceButton, 0, 5);
        gridPane.add(separator3, 0, 6);

        // Crear VBox para el título y el GridPane
        VBox vBox = new VBox(10, titleLabel, gridPane);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20));

        // Crear HBox para centrar el VBox en la ventana
        HBox root = new HBox(vBox);
        root.setAlignment(Pos.CENTER);

        addButton.setOnAction( e -> {
            va.pedirDatosJuego(stage);
        });
        salesButton.setOnAction( e -> {
           va.mostrarVentas(stage);
        });
        balanceButton.setOnAction( e -> {
           va.mostrarBalanceView(stage);
        });

        // Crear la escena
        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Menú de Gestión");
        stage.show();
    }
    public void pedirDatosJuego(Stage stage)
    {
         TextField idJuegoField = new TextField();
         TextField idCompanyiaField = new TextField();
         TextField nombreField = new TextField();
         TextField categoriaField = new TextField();
         TextField tiempoJuegoField = new TextField();
         TextField precioField = new TextField();
         TextField stockField = new TextField();
         TextField pegiField = new TextField();
         Button enviar = new Button("Anyadir");
         Button volver = new Button("Volver al menu");

        Label idJuegoLabel = new Label("ID Juego:");
        Label idCompanyiaLabel = new Label("ID Compañía:");
        Label nombreLabel = new Label("Nombre:");
        Label categoriaLabel = new Label("Categoría:");
        Label tiempoJuegoLabel = new Label("Tiempo de Juego:");
        Label precioLabel = new Label("Precio:");
        Label stockLabel = new Label("Stock:");
        Label pegiLabel = new Label("PEGI:");

        // Crear separadores
        Separator separator1 = new Separator();
        Separator separator2 = new Separator();

        // Crear GridPane para organizar etiquetas y campos de texto
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        // Añadir etiquetas y campos de texto al GridPane
        gridPane.add(idJuegoLabel, 0, 0);
        gridPane.add(idJuegoField, 1, 0);
        gridPane.add(idCompanyiaLabel, 0, 1);
        gridPane.add(idCompanyiaField, 1, 1);
        gridPane.add(nombreLabel, 0, 2);
        gridPane.add(nombreField, 1, 2);
        gridPane.add(categoriaLabel, 0, 3);
        gridPane.add(categoriaField, 1, 3);
        gridPane.add(tiempoJuegoLabel, 0, 4);
        gridPane.add(tiempoJuegoField, 1, 4);
        gridPane.add(precioLabel, 0, 5);
        gridPane.add(precioField, 1, 5);
        gridPane.add(stockLabel, 0, 6);
        gridPane.add(stockField, 1, 6);
        gridPane.add(pegiLabel, 0, 7);
        gridPane.add(pegiField, 1, 7);
        gridPane.add(enviar, 0, 8);
        gridPane.add(botonVolver, 0, 9);

        // Crear VBox para el título y el GridPane
        VBox vBox = new VBox(10, new Label("Formulario de Juego"), separator1, gridPane, separator2);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20));

        // Crear HBox para centrar el VBox en la ventana
        HBox root = new HBox(vBox);
        root.setAlignment(Pos.CENTER);

        enviar.setOnAction( e -> {

            if (cj.anyadirJuego(Integer.parseInt(idJuegoField.getText()) , Integer.parseInt(idCompanyiaField.getText()), nombreField.getText(), categoriaField.getText(), Integer.parseInt(tiempoJuegoField.getText()), Integer.parseInt(precioField.getText()) , Integer.parseInt(stockField.getText()) , Integer.parseInt(pegiField.getText())))
            {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setHeaderText("Juego anyadido correctamente");
                alerta.setContentText("El juego ja se encuentra en la base de datos");
                alerta.show();
                try {
                    va.start(stage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                try {
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
            else
            {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText("Algo ha ido mal");
                alerta.setContentText("El juego no se ha podido anyadir...");
                alerta.show();
            }
        });
        botonVolver.setOnAction( e -> {
            try {
                start(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });


        // Crear la escena
        Scene scene = new Scene(root, 400, 500);
        stage.setScene(scene);
        stage.setTitle("Formulario de Juego");
        stage.show();
    }
    public void mostrarVentas(Stage stage)
    {
        VBox contenedor = new VBox(5);
        contenedor.setStyle("-fx-background-color: white");
        Label labelInd = new Label("ID VENTA | ID USUARIO | NOMBRE USUARIO | ID JUEGO | NOMBRE JUEGO | FECHA | PRECIO");
        labelInd.setStyle("-fx-background-color: white; -fx-font-weight: bold");
        ScrollBar scroll = new ScrollBar();
        contenedor.getChildren().add(labelInd);
        contenedor.getChildren().add(scroll);


        ArrayList<String> ventas = ca.rellenarLista();
        int contador = 0;
        for (String value : ventas)
        {
            Label venta = new Label("VENTA " + value);
            venta.setStyle("-fx-background-color: lightgray;");
            Separator separador = new Separator();
            contenedor.getChildren().addAll(separador, venta);
            contador++;
        }

        contenedor.getChildren().add(botonVolver);
        botonVolver.setOnAction( e -> {
            try {
                start(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        Scene escena = new Scene(contenedor, 400, 400);
        stage.setScene(escena);
        stage.show();
    }

    public void mostrarBalanceView(Stage stage)
    {
        HashMap<String, Integer> ingresos;
        ingresos = ca.contarJuegos();
        int total = 0;

        VBox cont = new VBox();
        ScrollBar scroll = new ScrollBar();

        for (Map.Entry<String, Integer> value : ingresos.entrySet())
        {
            int totalIngresoJuego = (value.getValue() * cj.precioJuego(value.getKey()));
            Label muestra = new Label(value.getKey() + ": " + value.getValue() + " Copias vendidas, un total de: " + totalIngresoJuego + "€\n");
            cont.getChildren().add(muestra);
            total += totalIngresoJuego;
        }
        Label totalStr = new Label("El ingreso total es: " + total + "€");
        cont.getChildren().add(totalStr);
        cont.getChildren().add(botonVolver);
        cont.getChildren().add(scroll);
        botonVolver.setOnAction( e -> {
            try {
                start(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        Scene escena = new Scene(cont, 500, 500);
        stage.setScene(escena);
        stage.show();
    }
}
