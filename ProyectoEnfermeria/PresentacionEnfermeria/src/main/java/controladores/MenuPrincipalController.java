/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import com.mycompany.presentacionenfermeria.App;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import response.EmpleadoHistoricoResponse;
import utilerias.ParentAware;

/**
 * FXML Controller class
 *
 * @author isaac
 */
public class MenuPrincipalController implements Initializable {

    @FXML
    private StackPane mainLayout;

    private Parent panelPrincipal;
    private Parent panelCitas;
    private Map<String, Node> pantallas = new HashMap<>();
    private Map<String, Object> controladores = new HashMap<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cargarPantalla("MenuPrincipal", "/vistas/panelPrincipal.fxml");
            cargarPantalla("PantallaCitas", "/vistas/pantallaCitas.fxml");
            cargarPantalla("HistorialPacientes", "/vistas/HistorialPacientes.fxml");
            cargarPantalla("OpcionesPacienteHistorial", "/vistas/OpcionesPacienteHistorial.fxml");

            mostrarPantalla("MenuPrincipal");
        } catch (IOException e) {
            System.err.println("Error al cargar el menú principal: " + e.getMessage());
        }
    }

    private void cargarPantalla(String nombre, String ruta) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
        Node pantalla = loader.load();
        pantalla.setVisible(false);
        pantallas.put(nombre, pantalla);
        mainLayout.getChildren().add(pantalla);

        // para los controladores
        Object controller = loader.getController();
        controladores.put(nombre, controller);

        if (controller instanceof ParentAware) {
            ((ParentAware) controller).setMenuPrincipal(this);
        }

    }

    private void mostrarPantalla(String nombre) {
        pantallas.forEach((n, nodo) -> {
            if (n.equals(nombre)) {
                nodo.setVisible(true);
                nodo.toFront();
            } else {
                nodo.setVisible(false);
            }
        });
    }
//    @FXML
//    private void switchToCitas() throws IOException {
//        App.setRoot("/vistas/citas.fxml"); // ruta relativa al FXML
//    }

    @FXML
    private void mostrarPanelPrincipal() {
        mostrarPantalla("MenuPrincipal");
    }

    @FXML
    private void mostrarCitas() {
        mostrarPantalla("PantallaCitas");
    }

    @FXML
    public void mostrarHistorial() {
        mostrarPantalla("HistorialPacientes");
    }

    public void mostrarOpcionesHistorialPaciente(EmpleadoHistoricoResponse seleccionado) {
        OpcionesPacienteHistorialController controller
                = (OpcionesPacienteHistorialController) controladores.get("OpcionesPacienteHistorial");

        controller.cargarDatosPaciente(seleccionado);
        mostrarPantalla("OpcionesPacienteHistorial");
    }
}
