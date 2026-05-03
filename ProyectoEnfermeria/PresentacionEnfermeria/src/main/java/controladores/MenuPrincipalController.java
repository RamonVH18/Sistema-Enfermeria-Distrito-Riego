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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cargarPantalla("MenuPrincipal", "/vistas/panelPrincipal.fxml");
            cargarPantalla("PantallaCitas", "/vistas/pantallaCitas.fxml");
            cargarPantalla("HistorialPacientes", "/vistas/HistorialPacientes.fxml");
            
            mostrarPantalla("MenuPrincipal");
        } catch (IOException e) {
            System.err.println("Error al cargar el menú principal: " + e.getMessage());
        }
    }
    
    private void cargarPantalla(String nombre, String ruta) throws IOException {
        Node pantalla = FXMLLoader.load(getClass().getResource(ruta));
        pantalla.setVisible(false);
        pantallas.put(nombre, pantalla);
        mainLayout.getChildren().add(pantalla);
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
    private void mostrarPanelPrincipal(){
        mostrarPantalla("MenuPrincipal");
    }
    
    @FXML
    private void mostrarCitas(){
        mostrarPantalla("PantallaCitas");
    }
    
    @FXML
    private void mostrarHistorial() {
        mostrarPantalla("HistorialPacientes");
    }
}
