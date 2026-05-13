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
import javafx.util.StringConverter;
import request.AgregarExpedienteRequest;
import request.AtributosFisicosRequest;
import response.AgregarExpedienteResponse;
import response.ExpedienteConfigResponse;
import utilerias.RegistroAtributo;
import utilerias.SesionExpedientes;
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

    private ClienteApi clienteApi;
    private SesionExpedientes sesion;

    private ExpedienteConfigResponse config;

    private Map<String, Integer> idAtributos;
    private Map<String, AtributosFisicosRequest> atributos;

    private final String KEY_CABEZA = "CABEZA";
    private final String KEY_OJOS = "OJOS";
    private final String KEY_BOCA = "BOCA";
    private final String KEY_OIDOS = "OIDOS";
    private final String KEY_CUELLO = "CUELLO";
    private final String KEY_AREA_PRECORDIAL = "AREA_PRECORDIAL";
    private final String KEY_ABDOMEN = "ABDOMEN";
    private final String KEY_COLUMNA_VERTEBRAL = "COLUMNA_VERTEBRAL";
    private final String KEY_LUMBAR = "LUMBAR";
    private final String KEY_MIEMBROS_TORACICOS = "MIEMBROS_TORACICOS";
    private final String KEY_MIEMBROS_PELVICOS = "MIEMBROS_PELVICOS";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tablaCabeza = new TablaRegistro(KEY_CABEZA, new String[]{"FORMA", "TAMAÑO", "PELO", "CARA"});
        contenedorExploracion.getChildren().add(tablaCabeza);

        tablaOjos = new TablaRegistro(KEY_OJOS, new String[]{"REFLEJOS", "PARPADOS", "PUPILAS", "CONJUNTIVAS", "FONDO OJOS", "NARIZ", "MUCOSAS", "TABIQUE"});
        contenedorExploracion.getChildren().add(tablaOjos);

        tablaBoca = new TablaRegistro(KEY_BOCA, new String[]{"MUCOSAS", "DENTADURA", "LENGUA", "ENCIAS", "FARINGE", "AMIGDALAS"});
        contenedorExploracion.getChildren().add(tablaBoca);

        tablaOidos = new TablaRegistro(KEY_OIDOS, new String[]{"PABELLON", "CONDUCTO", "TIMPANO"});
        contenedorExploracion.getChildren().add(tablaOidos);

        tablaCuello = new TablaRegistro(KEY_CUELLO, new String[]{"GANGLIOS", "MOVILIDAD", "TIROIDES", "PULSOS"});
        contenedorExploracion.getChildren().add(tablaCuello);

        tablaAreaPrecordial = new TablaRegistro(KEY_AREA_PRECORDIAL, new String[]{"FORMA", "RUIDOS CARDIACOS", "CAMPOS PULMONARES"});
        contenedorExploracion.getChildren().add(tablaAreaPrecordial);

        tablaAbdomen = new TablaRegistro(KEY_ABDOMEN, new String[]{"FORMA", "VISCEROMEGALIA", "HERNIAS"});
        contenedorExploracion.getChildren().add(tablaAbdomen);

        tablaColumnaVertebral = new TablaRegistro(KEY_COLUMNA_VERTEBRAL, new String[]{"INTEGRIDAD", "FORMA", "TONO MUSCULAR"});
        contenedorExploracion.getChildren().add(tablaColumnaVertebral);

        tablaLumbar = new TablaRegistro(KEY_LUMBAR, new String[]{"INTEGRIDAD", "FORMA", "TONO MUSCULAR", "SENSIBILIDAD", "FUERZA"});
        contenedorExploracion.getChildren().add(tablaLumbar);

        tablaMiembrosToracicos = new TablaRegistro(KEY_MIEMBROS_TORACICOS, new String[]{"INTEGRIDAD", "FORMA", "ARTICULACIONES", "TONO MUSCULAR", "REFLEJOS", "SENSIBILIDAD"});
        contenedorExploracion.getChildren().add(tablaMiembrosToracicos);

        tablaMiembrosPelvicos = new TablaRegistro(KEY_MIEMBROS_PELVICOS, new String[]{"INTEGRIDAD", "FORMA", "ARTICULACIONES", "TONO MUSCULAR", "REFLEJOS", "SENSIBILIDAD"});
        contenedorExploracion.getChildren().add(tablaMiembrosPelvicos);
        clienteApi = new ClienteApi();
        
        clienteApi.obtenerInfoConfiguracionExpediente().thenAccept(info -> {
            config = info;
            idAtributos = info.getAtributos();
        }).exceptionally(ex -> {
            // Tip: Siempre maneja el error si el API falla para que no se quede la pantalla "congelada"
            ex.printStackTrace();
            return null;
        });
        atributos = new HashMap<>();
        sesion = SesionExpedientes.getInstance();
    }

    @FXML
    private void guardarExpediente() {

        tablaCabeza.crearMapa();
        tablaOjos.crearMapa();
        tablaBoca.crearMapa();
        tablaOidos.crearMapa();
        tablaCuello.crearMapa();
        tablaAreaPrecordial.crearMapa();
        tablaAbdomen.crearMapa();
        tablaColumnaVertebral.crearMapa();
        tablaLumbar.crearMapa();
        tablaMiembrosToracicos.crearMapa();
        tablaMiembrosPelvicos.crearMapa();

        // Ahora llenamos el mapa de atributos usando las constantes
        atributos.put(KEY_CABEZA, new AtributosFisicosRequest(idAtributos.get(KEY_CABEZA), tablaCabeza.getMapa()));
        atributos.put(KEY_OJOS, new AtributosFisicosRequest(idAtributos.get(KEY_OJOS), tablaOjos.getMapa()));
        atributos.put(KEY_BOCA, new AtributosFisicosRequest(idAtributos.get(KEY_BOCA), tablaBoca.getMapa()));
        atributos.put(KEY_OIDOS, new AtributosFisicosRequest(idAtributos.get(KEY_OIDOS), tablaOidos.getMapa()));
        atributos.put(KEY_CUELLO, new AtributosFisicosRequest(idAtributos.get(KEY_CUELLO), tablaCuello.getMapa()));
        atributos.put(KEY_AREA_PRECORDIAL, new AtributosFisicosRequest(idAtributos.get(KEY_AREA_PRECORDIAL), tablaAreaPrecordial.getMapa()));
        atributos.put(KEY_ABDOMEN, new AtributosFisicosRequest(idAtributos.get(KEY_ABDOMEN), tablaAbdomen.getMapa()));
        atributos.put(KEY_COLUMNA_VERTEBRAL, new AtributosFisicosRequest(idAtributos.get(KEY_COLUMNA_VERTEBRAL), tablaColumnaVertebral.getMapa()));
        atributos.put(KEY_LUMBAR, new AtributosFisicosRequest(idAtributos.get(KEY_LUMBAR), tablaLumbar.getMapa()));
        atributos.put(KEY_MIEMBROS_TORACICOS, new AtributosFisicosRequest(idAtributos.get(KEY_MIEMBROS_TORACICOS), tablaMiembrosToracicos.getMapa()));
        atributos.put(KEY_MIEMBROS_PELVICOS, new AtributosFisicosRequest(idAtributos.get(KEY_MIEMBROS_PELVICOS), tablaMiembrosPelvicos.getMapa()));
        
        AgregarExpedienteRequest agregarExpediente = sesion.construirExpediente(atributos);
        clienteApi.agregarExpedienteEmpleado(agregarExpediente);
    
    }

}
