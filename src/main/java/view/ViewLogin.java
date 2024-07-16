package view;

import controller.ControllerLogin;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.awt.*;
import java.sql.SQLException;
import java.util.Objects;

/**
 * ViewLogin - Menu incial del usuario para iniciar sesion
 */
public class ViewLogin extends Application
{
    ControllerLogin cl = new ControllerLogin();
    ViewMenu vm = new ViewMenu();

    /**
     *
     * @param stage - El stage, el objeto que los metodos de javaFX deben contener
     * @throws Exception - Por si salta cualquier excepcion no controlada en los try/catch
     */
    @Override
    public void start(Stage stage) throws Exception
    {
        //Creación y configuracion de los contenedores. Estilo y medidas
        VBox contenedor = new VBox(10);
        VBox contLogo = new VBox(20);
        VBox regist = new VBox(10);
        contenedor.setStyle("-fx-background-color: burlywood; -fx-padding: 10px;");
        //Alineamos los elementos de los contenedores adaptando a nuestras necesidades.
        contLogo.setAlignment(Pos.CENTER);
        regist.setAlignment(Pos.CENTER);
        contenedor.setAlignment(Pos.CENTER);

        //Creacion y configuracion de los label. Estilo y texto.
        Label tienesCuenta = new Label("¿Ya tienes una cuenta?");
        Label registrar = new Label("¿No tienes una cuenta?");
        registrar.setStyle("-fx-font-weight: bold");
        tienesCuenta.setStyle("-fx-font-weight: bold");

        //Creacion y configuracion de los Button. Estilo y texto.
        Button logear = new Button("Iniciar sesion");
        Button registrarse = new Button("Crear una cuenta ahora");
        logear.setStyle("-fx-background-color: blue; -fx-border-color: black; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");
        registrarse.setStyle("-fx-background-color: blue; -fx-border-color: black; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");

        //Creacion y configuracion de los separadores. Estilo.
        Separator separador = new Separator();
        Separator separador2 = new Separator();
        separador.setStyle("-fx-border-color: black; -fx-background-color: aqua");
        separador2.setStyle("-fx-border-color: black; -fx-background-color: aqua");

        //Creacion del imageView donde alojaremos el logo de la app (Generado con IA).
        String imagePath = "/view/logo.png";
        Image imagenLogo = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        ImageView imageViewLogo = new ImageView(imagenLogo);
        imageViewLogo.setFitWidth(150);
        imageViewLogo.setFitHeight(150);
        stage.getIcons().add(imagenLogo);

        //Añadimos todos los elementos creados previamente a los contenedores.
        contLogo.getChildren().add(imageViewLogo);
        regist.getChildren().addAll(registrar, registrarse);
        contenedor.getChildren().addAll(tienesCuenta, logear, separador, regist, separador2, contLogo);


        //Eventos de los Button de la vista inicial del login, llamamos a metodos publicos de la misma clase.
        logear.setOnAction( e -> {
            loginV(stage);
        });
        registrarse.setOnAction( e -> {
            registerV(stage);
        });

        //Configuracion de la escena y muestra del stage.
        Scene escena = new Scene(contenedor, 400, 400);
        stage.setScene(escena);
        stage.show();
    }

    /**
     * @param stage - El mismo Stage que vamos a utilizar.
     */
    public void loginV(Stage stage)
    {
        //Creación y configuracion de los contenedores. Estilo y medidas
        VBox contenedor = new VBox(10);
        HBox subCont1 = new HBox(10);
        HBox subCont2 = new HBox(10);
        contenedor.setStyle("-fx-background-color: burlywood; -fx-padding: 10px;");
        //Alineamos los elementos de los contenedores adaptando a nuestras necesidades.
        contenedor.setAlignment(Pos.CENTER);
        subCont1.setAlignment(Pos.CENTER);
        subCont2.setAlignment(Pos.CENTER);

        //Creacion y configuracion de los separadores. Estilo.
        Separator separador = new Separator();
        separador.setStyle("-fx-border-color: black; -fx-background-color: aqua");

        //Creacion y configuracion de los label. Estilo y texto.
        Label introNombreUsu = new Label("Nombre de usuario -> ");
        Label introPassword = new Label("Contraseña -> ");
        introNombreUsu.setStyle("-fx-font-weight: bold");
        introPassword.setStyle("-fx-font-weight: bold");

        //Creacion y contenido de los campos de introduccion de texto.
        TextField nombreUsu = new TextField();
        PasswordField password = new PasswordField();
        nombreUsu.setStyle("-fx-border-color: black; -fx-background-color: #D3D3D3");
        password.setStyle("-fx-border-color: black; -fx-background-color: #D3D3D3");

        //Creacion y configuracion de los Button. Estilo y texto.
        Button boton = new Button("Iniciar sesion ahora");
        boton.setStyle("-fx-background-color: blue; -fx-border-color: black; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");

        //Añadimos todos los elementos a los contenedores.
        subCont1.getChildren().addAll(introNombreUsu, nombreUsu);
        subCont2.getChildren().addAll(introPassword, password);
        contenedor.getChildren().addAll(subCont1, separador, subCont2, boton);

        //Evento del boton de inicio de sesion, que llamará a un método de la clase LoginController
        boton.setOnAction( e -> {
            //Los String que recojeran los datos de los campos de introduccion de texto.
            String nombreUsuStr = nombreUsu.getText();
            String passwordStr = password.getText();
            try {
                //Si el metodo devuelve true, se llamara al metodo startMenu de la clase ViewMenu, donde se visualiza el menu inicial de la app.
                if (cl.loginController(nombreUsuStr, passwordStr))
                {
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setHeaderText("¡Hola de nuevo, " + nombreUsuStr + "!");
                    alerta.setContentText("¡Disfruta de tu estancia por aqui!");
                    alerta.show();
                    vm.startMenu(stage, nombreUsuStr);
                }
                //Si el metodo devuelve false, se vaciaran los campos de introduccion de texto para que el usuario vuelva a intentar iniciar sesion
                else
                {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setHeaderText("¡Usuario o contrasenya invalidos!");
                    alerta.setContentText("Parece que no has usado bien las credenciales, ¿olvidaste tu contrasenya?");
                    alerta.show();
                }
                //Un catch para controlar cualquier excepcion lanzada por los metodos involucrados en la operacion
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        //Configuracion de la escena y muestra del stage.
        Scene escena = new Scene(contenedor, 400, 400);
        stage.setScene(escena);
        stage.show();
    }

    /**
     * @param stage - El mismo Stage que vamos a utilizar.
     */
    public void registerV(Stage stage)
    {
        VBox contenedor = new VBox();
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setStyle("-fx-background-color: burlywood; -fx-padding: 10px;");

        // Crear el contenedor para el formulario
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setStyle("-fx-background-color: burlywood; -fx-padding: 10px;");

        // Crear y añadir los campos de texto y etiquetas al GridPane
        Label nombreLabel = new Label("Nombre:");
        TextField nombreField = new TextField();
        nombreLabel.setStyle("-fx-font-weight: bold");
        nombreField.setStyle("-fx-border-color: black; -fx-background-color: #D3D3D3");

        Label nombreUsuLabel = new Label("Nombre de Usuario:");
        TextField nombreUsuField = new TextField();
        nombreUsuLabel.setStyle("-fx-font-weight: bold");
        nombreUsuField.setStyle("-fx-border-color: black; -fx-background-color: #D3D3D3");

        Label edadLabel = new Label("Edad:");
        TextField edadField = new TextField();
        edadLabel.setStyle("-fx-font-weight: bold");
        edadField.setStyle("-fx-border-color: black; -fx-background-color: #D3D3D3");

        Label direccionLabel = new Label("Dirección:");
        TextField direccionField = new TextField();
        direccionLabel.setStyle("-fx-font-weight: bold");
        direccionField.setStyle("-fx-border-color: black; -fx-background-color: #D3D3D3");

        Label contrasenyaLabel = new Label("Contraseña:");
        PasswordField contrasenyaField = new PasswordField();
        contrasenyaLabel.setStyle("-fx-font-weight: bold");
        contrasenyaField.setStyle("-fx-border-color: black; -fx-background-color: #D3D3D3");

        // Añadir los elementos al GridPane
        gridPane.add(nombreLabel, 0, 0);
        gridPane.add(nombreField, 1, 0);

        gridPane.add(nombreUsuLabel, 0, 1);
        gridPane.add(nombreUsuField, 1, 1);

        gridPane.add(edadLabel, 0, 2);
        gridPane.add(edadField, 1, 2);

        gridPane.add(direccionLabel, 0, 3);
        gridPane.add(direccionField, 1, 3);


        gridPane.add(contrasenyaLabel, 0, 5);
        gridPane.add(contrasenyaField, 1, 5);

        Button boton = new Button("Registrarme ahora");
        boton.setStyle("-fx-background-color: blue; -fx-border-color: black; -fx-text-fill: #FFFFFF; -fx-font-weight: bold;");

        // Añadir el GridPane al contenedor principal
        contenedor.getChildren().addAll(gridPane, boton);
        boton.setOnAction( e -> {
            try {
                if (cl.registerController(nombreField.getText(), nombreUsuField.getText(), Integer.parseInt(edadField.getText()), direccionField.getText(), contrasenyaField.getText()))
                {
                    Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                    alerta.setHeaderText("Ya esta, tu cuenta ha sido registrada con exito");
                    alerta.setContentText("Prueba a iniciar sesion con tu nombre de usuario y tu contraseña");
                    alerta.show();
                    start(stage);
                }
                else
                {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setHeaderText("¡Vaya! Parece que algo ha ido mal...");
                    alerta.setContentText("No hemos podido registrar su usuario, porfavor, pongase en contacto con nosotros si asi lo desea.");
                    alerta.show();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });


        Scene escena = new Scene(contenedor, 400, 400);
        stage.setScene(escena);
        stage.show();
    }
}
