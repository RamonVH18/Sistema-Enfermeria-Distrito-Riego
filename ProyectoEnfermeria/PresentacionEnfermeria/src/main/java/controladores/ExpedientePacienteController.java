/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import clienteApi.ClienteApi;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
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
import response.AtributoFisicoResponse;
import response.DatosEmpleadoResponse;
import response.DetalleResponse;
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
    private Label lblLaboral, lblHeredofamiliar, lblPatologico, lblNoPatologico, lblAmputacion, lblGineco, lblIncapacidad;
    @FXML
    private Label lblAgudezaVisual, lblCabeza, lblOjos, lblBoca, lblOidos, lblCuello, lblAreaPrecordial, lblAbdomen, lblColumnaVertebral, lblLumbar, lblMiembrosToracicos, lblMiembrosPelvicos;
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
        abrirSignosVitales();
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
            case "Antecedentes.fxml" -> {
                cargarAntecedentes();
            }
            case "Atributos.fxml" -> {
                cargarAtributos();
            }
            default ->
                throw new AssertionError();
        }
    }

    @FXML
    private void abrirSignosVitales() {
        cargarStackPane("SignosVitales.fxml");
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

    @FXML
    private void abrirAntecedentes() {
        cargarStackPane("Antecedentes.fxml");
    }

    private void cargarAntecedentes() {
        clienteApi.obtenerAntecedentesEmpleado(empleadoActivo.getIdEmpleado()).thenAccept(antecedente -> {
            Platform.runLater(() -> {
                lblLaboral.setText(procesarListaAntecedentes(antecedente.get("LABORAL")));
                lblHeredofamiliar.setText(procesarListaAntecedentes(antecedente.get("HEREDOFAMILIAR")));
                lblPatologico.setText(procesarListaAntecedentes(antecedente.get("PATOLOGICO")));
                lblNoPatologico.setText(procesarListaAntecedentes(antecedente.get("NO_PATOLOGICO")));
                lblAmputacion.setText(procesarListaAntecedentes(antecedente.get("AMPUTACION")));
                lblGineco.setText(procesarListaAntecedentes(antecedente.get("GINECO_OBSTERICO")));
                lblIncapacidad.setText(procesarListaAntecedentes(antecedente.get("INCAPACIDAD")));
            });
        }).exceptionally(ex -> {
            Platform.runLater(() -> System.err.println("Error: " + ex.getMessage()));
            return null;
        });
    }

    @FXML
    private void abrirAtributos() {
        cargarStackPane("Atributos.fxml");
    }

    private void cargarAtributos() {
        clienteApi.obtenerAtributosFisicosEmpleado(empleadoActivo.getIdEmpleado()).thenAccept(atributos -> {
            Platform.runLater(() -> {
                lblAgudezaVisual.setText(procesarAgudezaVisual(atributos.get("AGUDEZA_VISUAL")));
                lblCabeza.setText(procesarMapaAtributos(atributos.get("CABEZA")));
                lblOjos.setText(procesarMapaAtributos(atributos.get("OJOS")));
                lblBoca.setText(procesarMapaAtributos(atributos.get("BOCA")));
                lblOidos.setText(procesarMapaAtributos(atributos.get("OIDOS")));
                lblCuello.setText(procesarMapaAtributos(atributos.get("CUELLO")));
                lblAreaPrecordial.setText(procesarMapaAtributos(atributos.get("AREA_PRECORDIAL")));
                lblAbdomen.setText(procesarMapaAtributos(atributos.get("ABDOMEN")));
                lblColumnaVertebral.setText(procesarMapaAtributos(atributos.get("COLUMNA_VERTEBRAL")));
                lblLumbar.setText(procesarMapaAtributos(atributos.get("LUMBAR")));
                lblMiembrosToracicos.setText(procesarMapaAtributos(atributos.get("MIEMBROS_TORACICOS")));
                lblMiembrosPelvicos.setText(procesarMapaAtributos(atributos.get("MIEMBROS_PELVICOS")));
            });
        });
    }

    /**
     * Método auxiliar para convertir la lista de detalles en un String legible
     * Si no hay datos, devuelve "N/A"
     */
    private String procesarListaAntecedentes(List<DetalleResponse> lista) {
        if (lista == null || lista.isEmpty()) {
            return "N/A";
        }

        // Extraemos el campo 'valor' de cada objeto DetalleResponse y los unimos con comas
        return lista.stream()
                .map(DetalleResponse::getEspecificacion) // O el nombre del método que obtenga el texto en tu POJO
                .filter(valor -> valor != null && !valor.isBlank())
                .collect(Collectors.joining(", "));
    }

    private String procesarMapaAtributos(AtributoFisicoResponse atributo) {
        if (atributo == null) {
            return "Sin datos";
        }
        Map<String, Object> map = atributo.getPropiedades();
        
        if (map == null) {
            return "Sin propiedades registradas";
        }

        StringBuilder sb = new StringBuilder();

        // 2. Manejo genérico para el resto de las tablas
        map.forEach((nombre, datos) -> {
            if (!"tipo".equals(nombre) && datos instanceof Map) {
                Map<String, String> valores = (Map<String, String>) datos;
                String estado = valores.getOrDefault("estado", "N/A");
                String nota = valores.getOrDefault("nota", "");

                sb.append("• ").append(nombre).append(": ")
                        .append(estado);

                if (!nota.isEmpty()) {
                    sb.append(" (").append(nota).append(")");
                }
                sb.append("\n");
            }
        });

        return sb.toString().trim();
    }

    private String procesarAgudezaVisual(AtributoFisicoResponse atributo) {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> prop = atributo.getPropiedades();
        // Aquí accedes directamente a las llaves que ya conoces de tu JSON
        Map<String, String> od = (Map<String, String>) prop.get("ojo_derecho");
        Map<String, String> oi = (Map<String, String>) prop.get("ojo_izquierdo");

        sb.append("Ojo Derecho:\n Lentes: ").append(od.get("lentes"))
                .append(", Cromática: ").append(od.get("vision_cromatica"))
                .append("\nOjo Izquierdo:\n Lentes: ").append(oi.get("lentes"))
                .append(", Cromática: ").append(oi.get("vision_cromatica"))
                .append("\nNota: ").append(prop.get("nota"));

        return sb.toString();
    }

}
