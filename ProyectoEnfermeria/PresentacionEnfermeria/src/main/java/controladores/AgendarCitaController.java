/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import clienteApi.ClienteApi;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import request.CrearCitaRequest;
import response.EmpleadoOptionResponse;

/**
 *
 * @author Ramon Valencia
 */
public class AgendarCitaController implements Initializable {

    @FXML
    private ComboBox<EmpleadoOptionResponse> cbPaciente;
    @FXML
    private TextField txtIdEmpleado;
    @FXML
    private DatePicker dpFechaCita;
    @FXML
    private ComboBox<LocalTime> cbHora;
    @FXML
    private TextArea txtDescripcion;
    ClienteApi cliente = new ClienteApi();
    private List<EmpleadoOptionResponse> empleados = new ArrayList<>();

    private final ObservableList<String> listaPacientes = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("hh:mm a");
        LocalTime horaInicio = LocalTime.of(8, 0); // 8:00 AM
        LocalTime horaFin = LocalTime.of(16, 0);   // 4:00 PM
        crearComboBox();

        while (horaInicio.isBefore(horaFin.plusSeconds(1))) {
            cbHora.getItems().add(horaInicio);
            horaInicio = horaInicio.plusMinutes(15);
        }

    }

    private void crearComboBox() {
        // Configuración del Converter (Igual que ya lo tienes)
        cbPaciente.setConverter(new StringConverter<EmpleadoOptionResponse>() {
            @Override
            public String toString(EmpleadoOptionResponse e) {
                return (e == null) ? "" : e.getNombre();
            }

            @Override
            public EmpleadoOptionResponse fromString(String s) {
                return cbPaciente.getItems().stream().filter(e -> e.getNombre().equals(s)).findFirst().orElse(null);
            }
        });

        // --- 1. ESCUCHAR LA SELECCIÓN PARA EL TEXTFIELD ---
        cbPaciente.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtIdEmpleado.setText(String.valueOf(newSelection.getIdEmpleado()));
            } else {
                Platform.runLater(() -> txtIdEmpleado.clear());
            }
        });

        // --- 2. ESCUCHAR EL EDITOR PARA FILTRAR ---
        cbPaciente.getEditor().textProperty().addListener((obs, oldVal, newVal) -> {
            // Si se borra todo, mandamos cadena vacía para traer todos
            String filtro = (newVal == null || newVal.trim().isEmpty()) ? "" : newVal;
            actualizarListaEmpleados(filtro);
        });

        // Carga inicial
        actualizarListaEmpleados("");
    }

    private void actualizarListaEmpleados(String filtro) {
        cliente.buscarEmpleadosPorFiltro(filtro).thenAccept(lista -> {
            Platform.runLater(() -> {
                String currentText = cbPaciente.getEditor().getText();

                cbPaciente.getItems().setAll(lista);

                cbPaciente.getEditor().setText(currentText);

                if (!lista.isEmpty() && !filtro.isEmpty()) {
                    cbPaciente.show();
                } else if (filtro.isEmpty()) {
                    cbPaciente.hide();
                }
            });
        }).exceptionally(ex -> {
            ex.printStackTrace();
            return null;
        });
    }

    @FXML
    private void guardarCita() {
        EmpleadoOptionResponse seleccionado = cbPaciente.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            Integer idEmpleado = seleccionado.getIdEmpleado();
            LocalDateTime fechaCita = LocalDateTime.of(dpFechaCita.getValue(), cbHora.getValue());
            String motivo = txtDescripcion.getText();
            Integer idEnfermero = 1; //Harcodeado para pruebas
            CrearCitaRequest cita = new CrearCitaRequest(fechaCita, motivo, idEmpleado, idEnfermero);
            cliente.enviarCita(cita).thenAccept(res -> {
                mostrarAlerta("Éxito", "La cita se ha agendado correctamente.", Alert.AlertType.INFORMATION);
                // Opcional: Limpiar el formulario tras el éxito
                Platform.runLater(this::limpiarFormulario);
            })
                    .exceptionally(ex -> {
                        mostrarAlerta("Error de Conexión", "No se pudo guardar la cita: " + ex.getMessage(), Alert.AlertType.ERROR);
                        return null;
                    });
        }
    }

    private void limpiarFormulario() {
        cbPaciente.getSelectionModel().clearSelection();
        cbPaciente.getEditor().clear();
        txtIdEmpleado.clear();
        dpFechaCita.setValue(null);
        cbHora.getSelectionModel().clearSelection();
        txtDescripcion.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Platform.runLater(() -> {
            Alert alerta = new Alert(tipo);
            alerta.setTitle(titulo);
            alerta.setHeaderText(null); // Esto quita el encabezado gris grueso
            alerta.setContentText(mensaje);
            alerta.showAndWait();
        });
    }
}
