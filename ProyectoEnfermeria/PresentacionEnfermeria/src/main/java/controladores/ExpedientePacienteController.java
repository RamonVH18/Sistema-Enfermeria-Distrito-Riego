/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import response.EmpleadoHistoricoResponse;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import response.EmpleadoHistoricoResponse;
import response.ExpedienteResponse;

/**
 *
 * @author isaac
 */

public class ExpedientePacienteController {

    @FXML
    private TextField lblNombre, txtAlergias, txtPeso, txtTalla, txtPA;
    @FXML
    private TextArea txtNotas;
    @FXML
    private Button btnGuardar, btnEditar;
    

    private EmpleadoHistoricoResponse empleadoActivo;
    private ExpedienteResponse expedienteActual;

    public void initData(EmpleadoHistoricoResponse empleado, ExpedienteResponse expediente, String modo) {
        this.empleadoActivo = empleado;
        this.expedienteActual = expediente;

        // Datos fijos del empleado
        lblNombre.setText(empleado.getNombreCompleto());

        if (modo.equals("CREAR")) {
            prepararNuevoExpediente();
        } else {
            cargarDatosExpediente(expediente);
            bloquearCampos(true);
        }
    }

    private void cargarDatosExpediente(ExpedienteResponse exp) {
        txtAlergias.setText(exp.getAlergias());
        txtPeso.setText(String.valueOf(exp.getPeso()));
        txtPA.setText(exp.getTensionArterial());
        txtNotas.setText(exp.getNotasEvolucion());
    }

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
