package controladores;

import clienteApi.ClienteApi;
import java.io.IOException;
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
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import response.ExpedienteResponse;

public class PantallaHistorialMedicoController implements Initializable {

    @FXML
    private TextField txtBusqueda;
    @FXML
    private TableView<EmpleadoHistoricoResponse> tablaPacientes;
    @FXML
    private TableColumn<EmpleadoHistoricoResponse, String> colNombre, colArea, colSangre;
    @FXML
    private TableColumn<EmpleadoHistoricoResponse, Long> colID;
    @FXML
    private TableColumn<EmpleadoHistoricoResponse, Integer> colEdad;

    private final ClienteApi clienteApi = new ClienteApi(); // O como lo instancies usualmente
    private ObservableList<EmpleadoHistoricoResponse> masterData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarColumnas();
        cargarDatosAsincronos();
    }

    private void configurarColumnas() {
        // Nombre completo consolidado desde el modelo
        colNombre.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getNombreCompleto()));

        colID.setCellValueFactory(new PropertyValueFactory<>("idEmpleado"));
        colArea.setCellValueFactory(new PropertyValueFactory<>("area"));
        colSangre.setCellValueFactory(new PropertyValueFactory<>("tipoSangre"));

        // CÁLCULO DE EDAD DINÁMICO
        colEdad.setCellValueFactory(cellData -> {
            LocalDate fechaNac = cellData.getValue().getFechaNacimiento();
            if (fechaNac != null) {
                int edadCalculada = Period.between(fechaNac, LocalDate.now()).getYears();

                return new SimpleObjectProperty<>(edadCalculada);
            }
            return new SimpleObjectProperty<>(0);
        });
    }

    private void cargarDatosAsincronos() {
        // Llamada asíncrona 
        clienteApi.obtenerPacientesHistorial().thenAccept(lista -> {
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

    private void configurarFiltro() {
        FilteredList<EmpleadoHistoricoResponse> filteredData = new FilteredList<>(masterData, p -> true);
        txtBusqueda.textProperty().addListener((obs, oldVal, newVal) -> {
            filteredData.setPredicate(empleado -> {
                if (newVal == null || newVal.isEmpty()) {
                    return true;
                }
                String filter = newVal.toLowerCase();
                return empleado.getNombreCompleto().toLowerCase().contains(filter)
                        || empleado.getIdEmpleado().toString().contains(filter);
            });
        });
        tablaPacientes.setItems(filteredData);
    }

    private void abrirVentanaExpediente(EmpleadoHistoricoResponse paciente, ExpedienteResponse expediente, String modo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/ExpedientePaciente.fxml"));
            Parent root = loader.load();

            ExpedientePacienteController controller = loader.getController();
            // Pasamos los datos y el modo (CREAR o DETALLE)
            controller.initData(paciente, expediente, modo);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Expediente Médico - " + paciente.getNombreCompleto());
            stage.setScene(new Scene(root));
            stage.showAndWait(); // Espera a que se cierre para refrescar si es necesario

        } catch (IOException e) {
            System.err.println("Error al abrir la ventana: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void seleccionarPaciente() {
        EmpleadoHistoricoResponse seleccionado = tablaPacientes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            return;
        }

        // Consultamos a la API si ya tiene expediente
        clienteApi.obtenerExpedientePorEmpleado(seleccionado.getIdEmpleado())
                .thenAccept(expediente -> {
                    Platform.runLater(() -> {
                        // Si la API devuelve un objeto (200 OK), es DETALLE. 
                        // Si devuelve null (porque manejaste el 404), es CREAR.
                        if (expediente == null) {
                            abrirVentanaExpediente(seleccionado, null, "CREAR");
                        } else {
                            abrirVentanaExpediente(seleccionado, expediente, "DETALLE");
                        }
                    });
                });
    }

}
