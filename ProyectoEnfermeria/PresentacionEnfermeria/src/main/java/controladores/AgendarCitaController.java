/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 *
 * @author Ramon Valencia
 */
public class AgendarCitaController implements Initializable{
    @FXML private ComboBox<String> cbPaciente;
    @FXML private ComboBox<String> cbHora;
    
    // Lista original que traerás de tu API /edr/pacientes
    private final ObservableList<String> listaPacientes = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 1. Llenar horas (ejemplo rápido)
        cbHora.getItems().addAll("09:00 AM", "10:00 AM", "11:00 AM", "07:00 PM");

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
        // Aquí usas tu ClienteApi para mandar el POST a /edr/citas
        String nombre = cbPaciente.getEditor().getText();
        
    }
}
