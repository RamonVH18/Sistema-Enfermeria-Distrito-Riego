/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import response.EmpleadoHistoricoResponse;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import response.DatosEmpleadoResponse;
import response.EmpleadoHistoricoResponse;
import response.ExpedienteResponse;

/**
 *
 * @author isaac
 */

public class ExpedientePacienteController implements Initializable {

    @FXML
    private TextField lblNombre, txtAlergias, txtPeso, txtTalla, txtPA;
    @FXML
    private TextArea txtNotas;
    @FXML
    private Button btnGuardar, btnEditar;
    

    private DatosEmpleadoResponse empleadoActivo;
    private ExpedienteResponse expedienteActual;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public void initializeData(DatosEmpleadoResponse empleado) {
        this.empleadoActivo = empleado;

        // Datos fijos del empleado
        lblNombre.setText(empleado.getNombreEmpleado());
//
//        if (modo.equals("CREAR")) {
//            prepararNuevoExpediente();
//        } else {
//            bloquearCampos(true);
//        }
    }

//    private void cargarDatosExpediente(ExpedienteResponse exp) {
//        txtAlergias.setText(exp.getAlergias());
//        txtPeso.setText(String.valueOf(exp.getPeso()));
//        txtPA.setText(exp.getTensionArterial());
//        txtNotas.setText(exp.getNotasEvolucion());
//    }

    private void prepararNuevoExpediente() {
        btnEditar.setVisible(false);
        btnGuardar.setText("Crear Expediente");
    }

    private void bloquearCampos(boolean bloquear) {
        txtAlergias.setEditable(!bloquear);
        txtPeso.setEditable(!bloquear);
        txtPA.setEditable(!bloquear);
        txtNotas.setEditable(!bloquear);
        btnGuardar.setDisable(bloquear);
    }

    @FXML
    private void habilitarEdicion() {
        bloquearCampos(false);
        btnEditar.setVisible(false);
    }

    
}
