/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import clienteApi.ClienteApi;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Ramon Valencia
 */
public class CitasController implements Initializable {

    // --- Elementos del FXML ---
    @FXML
    private DatePicker datePicker;
    @FXML
    private ListView<String> lvCitasDelDia; // Cambia String por tu objeto Cita luego
    @FXML
    private Label lblFechaSeleccionada;
    @FXML
    private Label lblMesAño;
    @FXML
    private Button btnNuevaCita;

    private final ClienteApi apiClient = new ClienteApi();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        datePicker.setOnAction(event -> {
            LocalDate fecha = datePicker.getValue();
            lblFechaSeleccionada.setText(fecha.toString());
            cargarCitasPorFecha(fecha);
        });

        datePicker.setValue(LocalDate.now());
    }

    private void cargarCitasPorFecha(LocalDate fecha) {
        // Mostramos un mensaje de carga o limpiamos la lista
        lvCitasDelDia.getItems().setAll("Cargando citas...");

        // Llamada asíncrona a tu API /edr/citas?fecha=...
//        apiClient.obtenerCitas(fecha).thenAccept(json -> {
//            Platform.runLater(() -> {
//                // Aquí procesarás el JSON. Por ahora simulamos:
//                lvCitasDelDia.getItems().setAll("9:00 AM - Pepe Perez", "7:00 PM - Leonardo Flores");
//            });
//        }).exceptionally(ex -> {
//            Platform.runLater(() -> lvCitasDelDia.getItems().setAll("Error al conectar con la API"));
//            return null;
//        });
    }

    @FXML
    private void cargarCitasExistentes() {
        FXMLLoader loader = new FXMLLoader();
    }

    @FXML
    private void handleNuevaCita() {
        try {
            // 1. Cargar el FXML de la nueva pantalla
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/AgendarCita.fxml"));
            Parent root = loader.load();

            // 2. Crear la escena (puedes pasarle el tamaño o dejar que use el del FXML)
            Scene scene = new Scene(root);

            // Opcional: Cargar tu CSS a la nueva ventana
            scene.getStylesheets().add(getClass().getResource("/styles/AgendarCita.css").toExternalForm());

            // 3. Crear el nuevo "Escenario" (Ventana)
            Stage stage = new Stage();
            stage.setTitle("Agendar Nueva Cita - Distrito de Riego");

            // 4. Hacerla "Modal" (bloquea la ventana de atrás hasta que cierres esta)
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.err.println("Error al abrir la ventana de Nueva Cita: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
