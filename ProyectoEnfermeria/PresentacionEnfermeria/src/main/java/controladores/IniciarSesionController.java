/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import clienteApi.ClienteApi;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import request.IniciarSesionRequest;

/**
 * FXML Controller class
 *
 * @author isaac
 */
public class IniciarSesionController {

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnIniciarSesion;

    ClienteApi cliente = new ClienteApi();

    @FXML
    private void handleLogin(ActionEvent event) {
        String email = txtEmail.getText();
        String password = txtPassword.getText();

        if (email.isEmpty() || password.isEmpty()) {
            mostrarAlerta("Error", "Por favor, rellena todos los campos.");
            return;
        }

        IniciarSesionRequest request = new IniciarSesionRequest(email, password);

        // Llamamos al método y nos suscribimos al resultado
        cliente.inicioSesionRequest(request).thenAccept(usuario -> {
            // Platform.runLater es OBLIGATORIO para tocar la UI desde un hilo async
            Platform.runLater(() -> {
                mostrarAlerta("Éxito", "Bienvenido " + usuario.getNombre());
                sesionIniciada();
                // Aquí pones el código para cambiar de ventana
            });
        }).exceptionally(ex -> {
            Platform.runLater(() -> {
                mostrarAlerta("Error", "No se pudo iniciar sesión: " + ex.getMessage());
            });
            return null;
        });
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void sesionIniciada(){
        try {
            // 1. Cargar el FXML de la nueva pantalla
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/MenuPrincipal.fxml"));
            Parent root = loader.load();

            // 2. Crear la escena (puedes pasarle el tamaño o dejar que use el del FXML)
            Scene scene = new Scene(root);
            // Cargar tu CSS a la nueva ventana
            scene.getStylesheets().add(getClass().getResource("/styles/menuprincipal.css").toExternalForm());

            // 3. Crear el nuevo "Escenario" (Ventana)
            Stage stage = new Stage();
            stage.setTitle("Menu Principal - Distrito de Riego");

            stage.setScene(scene);
            stage.show();

            // 4. CERRAR la ventana de Login
            Stage stageLogin = (Stage) btnIniciarSesion.getScene().getWindow();
            stageLogin.close();

        } catch (IOException e) {
            System.err.println("Error al abrir la ventana de Nueva Cita: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
