/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import clienteApi.ClienteApi;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import request.CrearCitaRequest;

/**
 *
 * @author Ramon Valencia
 */
public class AgendarCitaController implements Initializable {

    @FXML
    private ComboBox<String> cbPaciente;
    @FXML
    private TextField txtIdEmpleado;
    @FXML
    private DatePicker dpFechaCita;
    @FXML
    private ComboBox<LocalTime> cbHora;
    @FXML 
    private TextArea txtDescripcion;

    private final ObservableList<String> listaPacientes = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 1. Llenar horas (ejemplo rápido)
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("hh:mm a");
        LocalTime horaInicio = LocalTime.of(8, 0); // 8:00 AM
        LocalTime horaFin = LocalTime.of(16, 0);   // 4:00 PM

        while (horaInicio.isBefore(horaFin.plusSeconds(1))) {
            cbHora.getItems().add(horaInicio);
            horaInicio = horaInicio.plusMinutes(15); 
        }
        // 2. Lógica de búsqueda en el ComboBox
        cbPaciente.getEditor().textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null || newVal.isEmpty()) {
                cbPaciente.hide();
            } else {
                // Aquí filtrarías la lista según lo que escribes
                // Y podrías llamar a la API si quieres búsqueda en tiempo real
                cbPaciente.show();
            }
        });
    }

    @FXML
    private void guardarCita() {
        
        Integer idEmpleado = 2;
        LocalDateTime fechaCita = LocalDateTime.of(dpFechaCita.getValue(), cbHora.getValue());
        String motivo = txtDescripcion.getText();
        Integer idEnfermero = 1; //Harcodeado para pruebas
        CrearCitaRequest cita = new CrearCitaRequest(fechaCita, motivo, idEmpleado, idEnfermero);
        ClienteApi cliente = new ClienteApi();
        cliente.enviarCita(cita);
    }
}
