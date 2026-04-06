/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import clienteApi.ClienteApi;
import java.net.URL;
import java.security.PublicKey;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import request.CrearCitaRequest;
import response.EmpleadoOptionResponse;

/**
 *
 * @author Ramon Valencia
 */
public class AgendarCitaController implements Initializable {

    //OBJETOS DEL STAGE
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

    //CONSTANTES
    private LocalTime HORA_INICIO = LocalTime.of(8, 0); // 8:00 AM
    private LocalTime HORA_FIN = LocalTime.of(16, 0);   // 4:00 PM

    private ClienteApi cliente;
    private PantallaCitasController controller;

    /**
     * Inicializador del controlador
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cliente = new ClienteApi();
        crearComboBox();

        while (HORA_INICIO.isBefore(HORA_FIN.plusSeconds(1))) {
            cbHora.getItems().add(HORA_INICIO);
            HORA_INICIO = HORA_INICIO.plusMinutes(15);
        }

    }

    /**
     * Metodo para crear el combo box con los pacientes
     */
    private void crearComboBox() {
        // Configuración del Converter para poder guardar el objeto en el comboBox y que solo se muestre el nombre.
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

        // Se agrega un listener en el cbPaciente en caso de seleccionar un paciente el textField del Id debe cambiar.
        cbPaciente.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtIdEmpleado.setText(String.valueOf(newSelection.getIdEmpleado()));
            } else {
                Platform.runLater(() -> txtIdEmpleado.clear());
            }
        });

        // Se agrega un listener que permitira que si escribes un nombre el combobox cambiara y solo mostrara los valores que coincidan con el regex
        cbPaciente.getEditor().textProperty().addListener((obs, oldVal, newVal) -> {
            String filtro = (newVal == null || newVal.trim().isEmpty()) ? "" : newVal;
            actualizarListaEmpleados(filtro);
        });

        // Se llama al metodo sin filtro para mostrar todos los pacientes.
        actualizarListaEmpleados("");
    }

    /**
     * Metodo para actualizar la lista de empleados y meter dentro del combobos
     * a los pacientes
     *
     * @param filtro
     */
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
            cerrarVentana();
            mostrarAlerta("Error", "Error al cargar la lista de empleados. Contactar con servicio tecnico.", Alert.AlertType.ERROR);
            return null;
        });
    }

    /**
     * Metodo que se activa por un boton. Este metodo es el que se encarga de
     * agendar las citas
     */
    @FXML
    private void guardarCita() {
        if (validarCita()) {
            CrearCitaRequest cita = crearCita();
            cliente.enviarCita(cita).thenAccept(res -> {
                mostrarAlerta("Éxito", "La cita se ha agendado correctamente.", Alert.AlertType.INFORMATION);
                cerrarVentana();
                controller.generarPaginador();
            })
                    .exceptionally(ex -> {
                        mostrarAlerta("Error de Conexión", "No se pudo guardar la cita: " + ex.getMessage(), Alert.AlertType.ERROR);
                        return null;
                    });
        }
    }

    /**
     * Metodo para validar los campos de la cita y asegurarse de que no sean
     * nulos.
     *
     * @return
     */
    private boolean validarCita() {
        if (cbPaciente.getSelectionModel().getSelectedItem() == null) {
            mostrarAlerta("Error", "Debe seleccionar un empleado para agendar una cita.", Alert.AlertType.ERROR);
            return false;
        }
        if (dpFechaCita.getValue() == null || cbHora.getValue() == null) {
            mostrarAlerta("Error", "Debe agregar fecha y hora para agendar una cita.", Alert.AlertType.ERROR);
            return false;
        }
        if (txtDescripcion.getText().isEmpty()) {
            mostrarAlerta("Error", "Debe agregar un motivo para agendar una cita.", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    /**
     * Metodo auxiliar que crea una cita con la informacion de los campos.
     *
     * @return
     */
    private CrearCitaRequest crearCita() {
        EmpleadoOptionResponse seleccionado = cbPaciente.getSelectionModel().getSelectedItem();
        Integer idEmpleado = seleccionado.getIdEmpleado();
        LocalDateTime fechaCita = LocalDateTime.of(dpFechaCita.getValue(), cbHora.getValue());
        String motivo = txtDescripcion.getText();
        // Harcodeado por lo mientras JEJE
        Integer idEnfermero = 1;
        return new CrearCitaRequest(fechaCita, motivo, idEmpleado, idEnfermero);
    }

    /**
     * Metodo Auxiliar para cerrar una ventana
     */
    private void cerrarVentana() {
        Platform.runLater(() -> {
            if (cbPaciente.getScene() != null && cbPaciente.getScene().getWindow() != null) {
                Stage stage = (Stage) cbPaciente.getScene().getWindow();
                stage.close();
            }
        });
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

    public void setObserver(PantallaCitasController controller) {
        this.controller = controller;
    }
}
