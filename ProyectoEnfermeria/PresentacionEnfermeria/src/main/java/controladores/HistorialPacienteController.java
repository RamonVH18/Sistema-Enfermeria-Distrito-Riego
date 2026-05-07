package controladores;

import clienteApi.ClienteApi;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import response.EmpleadoHistoricoResponse;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import response.DatosEmpleadoResponse;
import response.ExpedienteResponse;

public class HistorialPacienteController implements Initializable {

    @FXML
    private TextField txtBusqueda;
    @FXML
    private TableView<DatosEmpleadoResponse> tablaPacientes;
    @FXML
    private TableColumn<DatosEmpleadoResponse, String> colNombre, colArea, colSangre;
    @FXML
    private TableColumn<DatosEmpleadoResponse, Long> colTelefono;
    @FXML
    private TableColumn<DatosEmpleadoResponse, Integer> colEdad;

    private final ClienteApi clienteApi = new ClienteApi(); // O como lo instancies usualmente
    private ObservableList<DatosEmpleadoResponse> masterData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarColumnas();
        cargarDatosAsincronos();
    }

    private void configurarColumnas() {
        // Nombre completo consolidado desde el modelo
        colNombre.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getNombreEmpleado()));

        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colArea.setCellValueFactory(new PropertyValueFactory<>("nombreDepartamento"));
        colSangre.setCellValueFactory(new PropertyValueFactory<>("tipoSangre"));

        colEdad.setCellValueFactory(cellData
                -> new SimpleObjectProperty<>(cellData.getValue().getEdad())
        );
    }

    private void cargarDatosAsincronos() {
        // Llamada asíncrona 
        clienteApi.obtenerDatosHistorialEmpleado().thenAccept(lista -> {
            Platform.runLater(() -> {
                if (lista != null) {
                    masterData.setAll(lista);
                    configurarFiltro();
                }
            });
        }).exceptionally(ex -> {
            Platform.runLater(() -> System.err.println("Error: " + ex.getMessage()));
            return null;
        });
    }

    @FXML
    private void cargarPacientes() {
        cargarDatosAsincronos();
    }

    @FXML
    private void btnVerExpedienteAction() {
        seleccionarPaciente();
    }

    @FXML
    private void registrarExpediente() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/RegistrarExpediente.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Registrar Expediente Médico");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            System.err.println("Error al abrir la ventana: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void configurarFiltro() {
        FilteredList<DatosEmpleadoResponse> filteredData = new FilteredList<>(masterData, p -> true);
        txtBusqueda.textProperty().addListener((obs, oldVal, newVal) -> {
            filteredData.setPredicate(empleado -> {
                if (newVal == null || newVal.isEmpty()) {
                    return true;
                }
                String filter = newVal.toLowerCase();
                return empleado.getNombreEmpleado().toLowerCase().contains(filter);
//                        || empleado.getIdEmpleado().toString().contains(filter);
            });
        });
        tablaPacientes.setItems(filteredData);
    }

    private void abrirVentanaExpediente(DatosEmpleadoResponse paciente) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/ExpedientePaciente.fxml"));
            Parent root = loader.load();

            ExpedientePacienteController controller = loader.getController();
            // Pasamos los datos y el modo (CREAR o DETALLE)
            controller.initializeData(paciente);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Expediente Médico - " + paciente.getNombreEmpleado());
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/styles/ExpedientePaciente.css").toExternalForm());
            stage.setScene(scene);
            stage.showAndWait(); // Espera a que se cierre para refrescar si es necesario

        } catch (IOException e) {
            System.err.println("Error al abrir la ventana: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void seleccionarPaciente() {
        DatosEmpleadoResponse seleccionado = tablaPacientes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            return;
        }

        abrirVentanaExpediente(seleccionado);
    }

}
