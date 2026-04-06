/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import response.CitaPendienteResponse;
import utilerias.Utils;

/**
 *
 * @author Ramon Valencia
 */
public class DetallesCitaController implements Initializable{
    
    @FXML private Label lblPaciente;
    @FXML private Label lblIdEmpleado;
    @FXML private Label lblFecha;
    @FXML private Label lblHora;
    @FXML private TextArea txtDescripcion;
    
    private CitaPendienteResponse cita;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initializeData(CitaPendienteResponse cita) {
        this.cita = cita;
        lblPaciente.setText(cita.getNombreEmpleado());
        lblIdEmpleado.setText(cita.getIdEmpleado().toString());
        lblFecha.setText(Utils.formatoFecha(cita.getFechaHora().toLocalDate()));
        lblHora.setText(Utils.formatoHora(cita.getFechaHora().toLocalTime()));
        txtDescripcion.setText(cita.getMotivo());
    }
}
