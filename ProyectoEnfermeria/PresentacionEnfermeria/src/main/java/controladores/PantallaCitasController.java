/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import clienteApi.ClienteApi;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import response.CitaPendienteResponse;

/**
 *
 * @author Ramon Valencia
 */
public class PantallaCitasController implements Initializable {

    //OBJETOS DEL STAGE
    @FXML
    private DatePicker datePicker;
    @FXML
    private Pagination paginadorCitas;

    // CONSTANTES
    private final int CITAS_POR_PAGINA = 5;

    private List<CitaPendienteResponse> citasPendientes = new ArrayList<>();
    private final ClienteApi cliente = new ClienteApi();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCitasPendientes();
        generarDatePicker();

    }

    private void generarDatePicker() {
        datePicker.valueProperty().addListener((observable, oldDate, newDate) -> {
            if (newDate != null) {
                cliente.obtenerCitasPorFecha(newDate)
                        .thenAccept(citas -> {
                            if (citas == null) {
                                citas = new ArrayList<>();
                            } else {
                                this.citasPendientes = citas;
                            }
                            Platform.runLater(() -> {
                                generarPaginador();
                            });
                        });
            }
        });

    }

    private void cargarCitasPendientes() {

        cliente.obtenerCitasPendientes().thenAccept(citas -> {
            Platform.runLater(() -> {
                this.citasPendientes = citas;
                generarPaginador();
            });
        });

    }

    private void generarPaginador() {
        paginadorCitas.setPageCount(0);
        paginadorCitas.setPageFactory(null); // Limpia la fábrica anterior

        if (citasPendientes == null || citasPendientes.isEmpty()) {
            paginadorCitas.setPageCount(1);
            paginadorCitas.setCurrentPageIndex(0);
            paginadorCitas.setPageFactory(n -> new Label("No hay citas programadas para ese dia."));
            return;
        }

        int totalPaginas = (int) Math.ceil((double) citasPendientes.size() / CITAS_POR_PAGINA);
        paginadorCitas.setPageCount(totalPaginas);
        paginadorCitas.setPageFactory(this::crearPaginaCitas);
    }

    private VBox crearPaginaCitas(int pageIndex) {
        VBox contenedorDePagina = new VBox(10);
        contenedorDePagina.setPadding(new Insets(10));

        int inicio = pageIndex * CITAS_POR_PAGINA;
        int fin = Math.min(inicio + CITAS_POR_PAGINA, citasPendientes.size());

        for (int i = inicio; i < fin; i++) {
            // Pasamos el objeto real al creador de VBox
            VBox cardCita = crearCardIndividual(citasPendientes.get(i));
            contenedorDePagina.getChildren().add(cardCita);
        }

        return contenedorDePagina;
    }

    private VBox crearCardIndividual(CitaPendienteResponse cita) {
        // 1. Creamos el contenedor visual
        VBox card = new VBox(5);
        card.getStyleClass().add("cita-item-card");
        card.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-radius: 8; -fx-cursor: hand;");
        card.setPadding(new Insets(15));

        // 2. Extraemos los datos del objeto para las etiquetas
        Label lblMotivo = new Label(cita.getMotivo());
        lblMotivo.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        String fechaFormateada = cita.getFechaHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        Label lblInfo = new Label("📅 " + fechaFormateada + " | 👤 " + cita.getNombreEmpleado());
        lblInfo.setStyle("-fx-text-fill: #666;");

        card.getChildren().addAll(lblMotivo, lblInfo);

        // --- CLAVE: GUARDAR LA REFERENCIA ---
        // Usamos setUserData para "esconder" el objeto dentro del VBox
        card.setUserData(cita);

        // 3. Añadir evento de clic para recuperar el objeto
        card.setOnMouseClicked(event -> {
            // Recuperamos el objeto guardado
            CitaPendienteResponse citaSeleccionada = (CitaPendienteResponse) card.getUserData();
            System.out.println("Has seleccionado la cita ID: " + citaSeleccionada.getIdCita());

        });

        return card;
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

    @FXML
    private void handleReset() {
        datePicker.setValue(null);
        cargarCitasPendientes();
    }

    /**
     * Metodo Auxiliar para mostrar una ventana
     *
     * @param titulo
     * @param mensaje
     * @param tipo
     */
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Platform.runLater(() -> {
            Alert alerta = new Alert(tipo);
            alerta.setTitle(titulo);
            alerta.setHeaderText(null);
            alerta.setContentText(mensaje);
            alerta.showAndWait();
        });
    }
}
