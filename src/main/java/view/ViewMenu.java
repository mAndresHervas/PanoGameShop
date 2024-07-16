package view;

import controller.ControllerLogin;
import controller.ControllerMenu;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

import java.awt.*;
import java.sql.SQLException;
import java.util.Objects;

public class ViewMenu extends Application
{
    private static final String GTA = "GRAND THEFT AUTO V";
    private static final String RED = "RED DEAD REDEMPTION 2";
    private static final String SEKIRO = "SEKIRO, SHADOW DIES TWICE";
    private static final String GOW = "GOD OF WAR: RAGNAROK";
    private static final String JC = "JUST CAUSE 4";
    private static final String ACS = "Assasins Creed Syndicate";
    ControllerMenu cm = new ControllerMenu();
    ViewFicha vf = new ViewFicha();

    public void startMenu(Stage stage, String nombreUsu)
    {
        Stage stageMenu = new Stage();
        VBox contenedor = new VBox(10);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setStyle("-fx-background-color: burlywood; -fx-padding: 10px;");

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-border-style: solid; -fx-border-width: 2px; -fx-border-color: black;");
        Menu menuArchivo = new Menu(nombreUsu);
        menuArchivo.setStyle("-fx-border-style: dashed; -fx-border-width: 2px; -fx-border-color: black; -fx-border-radius: 10px;");
        MenuItem cuenta = new MenuItem("Mi cuenta");
        MenuItem guias = new MenuItem("Guias y servidores");
        MenuItem contacto = new MenuItem("Contactanos");
        MenuItem cerrarSesion = new MenuItem("Cerrar sesion");
        menuArchivo.getItems().addAll(cuenta, guias, contacto, cerrarSesion);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menuArchivo);
        borderPane.setTop(menuBar);
        contenedor.getChildren().addAll(borderPane);

        //gta 5
        String imagePath = "/view/GTA5.jpg";
        Image imagenGta = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        ImageView imageViewGta = new ImageView(imagenGta);
        imageViewGta.setStyle("-fx-border-width: 20px; -fx-border-color: black; -fx-border-style: solid");
        imageViewGta.setFitWidth(200);
        imageViewGta.setFitHeight(200);
        Button fichaGTA = new Button("Ver Ficha");
        fichaGTA.setStyle("-fx-background-color: blue; -fx-border-color: black; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
        contenedor.getChildren().addAll(imageViewGta, fichaGTA);

        //rdr 2
        String imagePathRdr = "/view/RDR2.jpg";
        Image imagenRdr2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePathRdr)));
        ImageView imageViewRdr2 = new ImageView(imagenRdr2);
        imageViewRdr2.setStyle("-fx-border-width: 20px; -fx-border-color: black; -fx-border-style: solid");
        imageViewRdr2.setFitHeight(200);
        imageViewRdr2.setFitWidth(200);
        Button fichaRdr = new Button("Ver Ficha");
        fichaRdr.setStyle("-fx-background-color: blue; -fx-border-color: black; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
        contenedor.getChildren().addAll(imageViewRdr2, fichaRdr);

        //sekiro
        String imagePathSek = "/view/sekiro.jpg";
        Image imagenSek = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePathSek)));
        ImageView imageViewSek = new ImageView(imagenSek);
        imageViewSek.setStyle("-fx-border-width: 20px; -fx-border-color: black; -fx-border-style: solid");
        imageViewSek.setFitHeight(200);
        imageViewSek.setFitWidth(200);
        Button fichaSek = new Button("Ver Ficha");
        fichaSek.setStyle("-fx-background-color: blue; -fx-border-color: black; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
        contenedor.getChildren().addAll(imageViewSek, fichaSek);

        //god of war
        String imagePathGow = "/view/GOW.jpg";
        Image imagenGow = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePathGow)));
        ImageView imageViewGow = new ImageView(imagenGow);
        imageViewGow.setStyle("-fx-border-width: 20px; -fx-border-color: black; -fx-border-style: solid");
        imageViewGow.setFitHeight(200);
        imageViewGow.setFitWidth(200);
        Button fichaGow = new Button("Ver Ficha");
        fichaGow.setStyle("-fx-background-color: blue; -fx-border-color: black; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
        contenedor.getChildren().addAll(imageViewGow, fichaGow);

        //just cause 4
        String imagePathjc = "/view/JC4.jpg";
        Image imagenjc = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePathjc)));
        ImageView imageViewjc = new ImageView(imagenjc);
        imageViewGow.setStyle("-fx-border-width: 20px; -fx-border-color: black; -fx-border-style: solid");
        imageViewGow.setFitHeight(200);
        imageViewGow.setFitWidth(200);
        Button fichajc = new Button("Ver Ficha");
        fichajc.setStyle("-fx-background-color: blue; -fx-border-color: black; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
        contenedor.getChildren().addAll(imageViewjc, fichajc);

        //assasins creed syndicate
        String imagePathAcs = "/view/acs.jpg";
        Image imagenAcs = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePathAcs)));
        ImageView imageViewAcs = new ImageView(imagenAcs);
        imageViewGow.setStyle("-fx-border-width: 20px; -fx-border-color: black; -fx-border-style: solid");
        imageViewAcs.setFitHeight(200);
        imageViewAcs.setFitWidth(200);
        Button fichaAcs = new Button("Ver Ficha");
        fichajc.setStyle("-fx-background-color: blue; -fx-border-color: black; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
        contenedor.getChildren().addAll(imageViewAcs, fichaAcs);


        fichaGTA.setOnAction( e -> {
            try {
                vf.fichas(stage, GTA, nombreUsu);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        fichaRdr.setOnAction( e -> {
            try {
                vf.fichas(stage, RED, nombreUsu);
            } catch (SQLException ex){
                throw new RuntimeException();
            }
        });
        fichaSek.setOnAction( e -> {
            try {
                vf.fichas(stage, SEKIRO, nombreUsu);
            } catch (SQLException ex){
                throw new RuntimeException();
            }
        });
        fichaGow.setOnAction( e -> {
            try {
                vf.fichas(stage, GOW, nombreUsu);
            } catch (SQLException ex){
                throw new RuntimeException();
            }
        });
        fichajc.setOnAction( e -> {
            try {
                vf.fichas(stage, JC , nombreUsu);
            } catch (SQLException ex){
                throw new RuntimeException();
            }
        });
        fichaAcs.setOnAction( e -> {
            try {
                vf.fichas(stage, ACS, nombreUsu);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        cuenta.setOnAction( e -> {
            ViewCuenta vc = new ViewCuenta();
            vc.startCuenta(stage, nombreUsu);
        });
        cerrarSesion.setOnAction( e -> {
            ViewLogin vl = new ViewLogin();
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText("Cerrando Sesion");
            alerta.setContentText("¿Es posible que los datos no guardados se pierdan, desea continuar?");
            alerta.show();
            try {
                vl.start(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(contenedor);
        scroll.setFitToWidth(true);

        Scene escena = new Scene(scroll, 800, 800);
        stage.setScene(escena);
        stage.show();
    }

    @Override
    public void start(Stage stage) throws Exception
    {

    }
}
