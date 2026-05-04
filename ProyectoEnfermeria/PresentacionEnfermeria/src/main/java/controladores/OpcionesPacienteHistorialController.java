/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import clienteApi.ClienteApi;
import java.time.LocalDate;
import java.time.Period;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import response.EmpleadoHistoricoResponse;
import utilerias.ParentAware;

/**
 *
 * @author isaac
 */
public class OpcionesPacienteHistorialController implements ParentAware {

    @FXML
    private Label lblNombre, lblID, lblArea, lblEdad, lblSangre;
    @FXML
    private Button btnVer, btnEditar, btnAgregar;

    private final ClienteApi clienteApi = new ClienteApi();
    private EmpleadoHistoricoResponse paciente;
    private MenuPrincipalController menuPrincipal;

    @Override
    public void setMenuPrincipal(MenuPrincipalController main) {
        this.menuPrincipal = main;
    }

    /**
     * Llena el encabezado y decide qué botones mostrar según la API.
     */
    public void cargarDatosPaciente(EmpleadoHistoricoResponse seleccionado) {
        this.paciente = seleccionado;

        lblNombre.setText(paciente.getNombreCompleto());
        lblID.setText("ID: EMP-" + paciente.getIdEmpleado());
        lblArea.setText(paciente.getArea());
        lblSangre.setText(paciente.getTipoSangre());

        // Cálculo dinámico de edad
        if (paciente.getFechaNacimiento() != null) {
            int edad = Period.between(paciente.getFechaNacimiento(), LocalDate.now()).getYears();
            lblEdad.setText(String.valueOf(edad));
        }

        verificarExistenciaExpediente();
    }

    private void verificarExistenciaExpediente() {
        clienteApi.obtenerExpedientePorEmpleado(paciente.getIdEmpleado())
                .thenAccept(expediente -> {
                    Platform.runLater(() -> {
                        // Si el expediente no es nulo, el paciente ya tiene historial 
                        boolean existe = (expediente != null);

                        btnVer.setVisible(existe);
                        btnVer.setManaged(existe);
                        btnEditar.setVisible(existe);
                        btnEditar.setManaged(existe);

                        // Solo mostramos el botón de "Agregar" si NO existe registro 
                        btnAgregar.setVisible(!existe);
                        btnAgregar.setManaged(!existe);
                    });
                })
                .exceptionally(ex -> {
                    // Si la API falla (ej. 404), habilitamos solo la creación
                    Platform.runLater(() -> {
                        btnVer.setVisible(false);
                        btnAgregar.setVisible(true);
                        btnAgregar.setManaged(true);
                    });
                    return null;
                });
    }

    @FXML
    private void volverALista() {
        menuPrincipal.mostrarHistorial(); // Regresa a la tabla
    }

    @FXML
    private void agregarHistorial() {
        // podria usar menuPrincipal para ir a la pantalla del formulario
        System.out.println("Iniciando creación de historial para: " + paciente.getNombreCompleto());
    }

    @FXML
    private void verHistorial() {
    }

    @FXML
    private void editarHistorial() {
    }
}
