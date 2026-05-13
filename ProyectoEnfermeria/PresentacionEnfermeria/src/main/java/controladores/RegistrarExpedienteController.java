/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import clienteApi.ClienteApi;
import dtos.EmpleadoSinExpedienteDTO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import utilerias.RegistroAtributo;
import utilerias.TablaRegistro;

/**
 *
 * @author Ramon Valencia
 */
public class RegistrarExpedienteController implements Initializable {

    @FXML
    private VBox contenedorExploracion;
    
    private TablaRegistro tablaCabeza;
    private TablaRegistro tablaOjos;
    private TablaRegistro tablaBoca;
    private TablaRegistro tablaOidos;
    private TablaRegistro tablaCuello;
    private TablaRegistro tablaAreaPrecordial;
    private TablaRegistro tablaAbdomen;
    private TablaRegistro tablaColumnaVertebral;
    private TablaRegistro tablaLumbar;
    private TablaRegistro tablaMiembrosToracicos;
    private TablaRegistro tablaMiembrosPelvicos;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tablaCabeza = new TablaRegistro("CABEZA", new String[]{"FORMA", "TAMAÑO", "PELO", "CARA"});
        contenedorExploracion.getChildren().add(tablaCabeza);

        tablaOjos = new TablaRegistro("OJOS", new String[]{"REFLEJOS", "PARPADOS", "PUPILAS", "CONJUNTIVAS", "FONDO OJOS", "NARIZ", "MUCOSAS", "TABIQUE"});
        contenedorExploracion.getChildren().add(tablaOjos);

        tablaBoca = new TablaRegistro("BOCA", new String[]{"MUCOSAS", "DENTADURA", "LENGUA", "ENCIAS", "FARINGE", "AMIGDALAS"});
        contenedorExploracion.getChildren().add(tablaBoca);

        tablaOidos = new TablaRegistro("OIDOS", new String[]{"PABELLON", "CONDUCTO", "TIMPANO"});
        contenedorExploracion.getChildren().add(tablaOidos);

        tablaCuello = new TablaRegistro("CUELLO", new String[]{"GANGLIOS", "MOVILIDAD", "TIROIDES", "PULSOS"});
        contenedorExploracion.getChildren().add(tablaCuello);

        tablaAreaPrecordial = new TablaRegistro("AREA_PRECORDIAL", new String[]{"FORMA", "RUIDOS CARDIACOS", "CAMPOS PULMONARES"});
        contenedorExploracion.getChildren().add(tablaAreaPrecordial);

        tablaAbdomen = new TablaRegistro("ABDOMEN", new String[]{"FORMA", "VISCEROMEGALIA", "HERNIAS"});
        contenedorExploracion.getChildren().add(tablaAbdomen);

        tablaColumnaVertebral = new TablaRegistro("COLUMNA_VERTEBRAL", new String[]{"INTEGRIDAD", "FORMA", "TONO MUSCULAR"});
        contenedorExploracion.getChildren().add(tablaColumnaVertebral);

        tablaLumbar = new TablaRegistro("LUMBAR", new String[]{"INTEGRIDAD", "FORMA", "TONO MUSCULAR", "SENSIBILIDAD", "FUERZA"});
        contenedorExploracion.getChildren().add(tablaLumbar);

        tablaMiembrosToracicos = new TablaRegistro("MIEMBROS_TORACICOS", new String[]{"INTEGRIDAD", "FORMA", "ARTICULACIONES", "TONO MUSCULAR", "REFLEJOS", "SENSIBILIDAD"});
        contenedorExploracion.getChildren().add(tablaMiembrosToracicos);

        tablaMiembrosPelvicos = new TablaRegistro("MIEMBROS_PELVICOS", new String[]{"INTEGRIDAD", "FORMA", "ARTICULACIONES", "TONO MUSCULAR", "REFLEJOS", "SENSIBILIDAD"});
        contenedorExploracion.getChildren().add(tablaMiembrosPelvicos);
    }

    public Map<String, String> obtenerResultados(TableView<RegistroAtributo> tabla) {
        Map<String, String> mapaResultados = new HashMap<>();

        for (RegistroAtributo item : tabla.getItems()) {
            String valor = "";
            if (item.normalProperty().get()) {
                valor = "NORMAL: " + item.notaProperty().get();
            } else if (item.anormalProperty().get()) {
                valor = "ANORMAL: " + item.notaProperty().get();
            } else {
                valor = "SIN EVALUAR: " + item.notaProperty().get();
            }
            mapaResultados.put(item.componenteProperty().get(), valor);
        }

        return mapaResultados;
    }
    
    @FXML
    private void guardarExpediente() {
        
    }

}
