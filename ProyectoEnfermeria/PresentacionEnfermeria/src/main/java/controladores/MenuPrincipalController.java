/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controladores;

import com.mycompany.presentacionenfermeria.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            panelPrincipal = FXMLLoader.load(getClass().getResource("/vistas/panelPrincipal.fxml"));
            panelCitas = FXMLLoader.load(getClass().getResource("/vistas/panelCitas.fxml"));
            
            mainLayout.getChildren().addAll(panelCitas, panelPrincipal);
        } catch (IOException e) {
            System.err.println("Error al cargar el menú principal: " + e.getMessage());
        }
    }
    
//    @FXML
//    private void switchToCitas() throws IOException {
//        App.setRoot("/vistas/citas.fxml"); // ruta relativa al FXML
//    }
    
    @FXML
    private void mostrarPanelPrincipal(){
        panelPrincipal.toFront();
    }
    
    @FXML
    private void mostrarCitas(){
        panelCitas.toFront();
    }    
}
