/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import clienteApi.ClienteApi;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import response.EmpleadoHistoricoResponse;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import response.DatosEmpleadoResponse;
import response.EmpleadoHistoricoResponse;
import response.ExpedienteResponse;

/**
 *
 * @author isaac
 */
public class ExpedientePacienteController implements Initializable {

    @FXML // Labels de la pantalla principal
    private Label lblNombre, lblTelefono, lblEdad, lblDepartamento, lblTipoSangre;
    @FXML // Labels de la pantalla de signos vitales
    private Label lblPresion, lblFrecCard, lblTemp, lblPeso, lblAltura, lblSaturacion, lblIMC;
    @FXML
    private TextArea txtNotas;
    @FXML
    private Button btnGuardar, btnEditar;
    @FXML
    private StackPane contentArea;

    private DatosEmpleadoResponse empleadoActivo;
    private ExpedienteResponse expedienteActual;
    private final ClienteApi clienteApi = new ClienteApi();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void initializeData(DatosEmpleadoResponse empleado) {
        this.empleadoActivo = empleado;

        // Datos fijos del empleado
        cargarDatosExpediente(empleado);
        cargarStackPane("SignosVitales.fxml");
    }

    private void cargarDatosExpediente(DatosEmpleadoResponse e) {
        lblNombre.setText(e.getNombreEmpleado());
        lblTelefono.setText("Telefono: " + e.getTelefono());
        lblDepartamento.setText("Departamento: " + e.getNombreDepartamento());
        lblEdad.setText("Edad: " + e.getEdad());
        lblTipoSangre.setText("Tipo de Sangre: " + e.getTipoSangre());
    }

    private void cargarStackPane(String url) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/" + url));
        Parent contenido;
        try {
            
            loader.setController(this);
            contenido = loader.load();
            cargarContenido(url);
            contentArea.getChildren().setAll(contenido);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    private void cargarContenido(String url) {
        switch (url) {
            case "SignosVitales.fxml" -> {
                cargarSignosVitales();
            }
            default -> throw new AssertionError();
        }
    }
    
    private void cargarSignosVitales() {
        clienteApi.obtenerSignosVitalesEmpleado(empleadoActivo.getIdEmpleado()).thenAccept(signos -> {
            Platform.runLater(() -> {
                lblPresion.setText(signos.getPresionSistolica() + "/" + signos.getPresionDiatolica());
                lblFrecCard.setText(signos.getFrecuencia_cardiaca().toString());
                lblTemp.setText(signos.getTemperatura() + "°");
                lblPeso.setText(signos.getPeso().toString());
                lblAltura.setText(signos.getAltura().toString());
                lblSaturacion.setText(signos.getOxigenacion().toString());
                lblIMC.setText(signos.getImc().toString());
            });
        }).exceptionally(ex -> {
            Platform.runLater(() -> System.err.println("Error: " + ex.getMessage()));
            return null;
        });
    }

}
