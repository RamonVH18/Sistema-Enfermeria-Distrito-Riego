/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import clienteApi.ClienteApi;
import dtos.EmpleadoSinExpedienteDTO;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import request.AgregarExpedienteRequest;
import request.AntecedentesRequest;
import response.ExpedienteConfigResponse;
import utilerias.SesionExpedientes;

/**
 *
 * @author Ramon Valencia
 */
public class RegistroAntecedentesController implements Initializable{
    
    @FXML
    private ComboBox<EmpleadoSinExpedienteDTO> cbEmpleado;
    @FXML
    private ComboBox<String> cbTipoSangre, cbTipoAntecedente;
    @FXML
    private TextField txtNss, txtDescripcion;
    @FXML
    private Button btnAgregar, btnGuardarExpediente;
    @FXML
    private TableView<AntecedentesRequest> tblAntecedentes;
    @FXML
    private TableColumn colTipo, colDescripcion;

    private Map<String, Integer> antecedentes = new HashMap<>();

    ObservableList<AntecedentesRequest> listaAntecedentes = FXCollections.observableArrayList();

    private ClienteApi clienteApi = new ClienteApi();
    private ExpedienteConfigResponse config;
    private SesionExpedientes sesion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarComboBoxes();
        iniciarTablaAntecedentes();
        sesion = SesionExpedientes.getInstance();
        sesion.inyectarControllerAntecedentes(this);
        
    }

    
    private void iniciarTablaAntecedentes() {
        
        tblAntecedentes.setItems(listaAntecedentes);

        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoDetalle"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("detalle"));
    }
    
    private void iniciarComboBoxes() {
        clienteApi.obtenerInfoConfiguracionExpediente().thenAccept(info -> {
            config = info;
            Platform.runLater(() -> {
                // 1. Cargamos los items
                cbEmpleado.setItems(FXCollections.observableArrayList(info.getEmpleado()));

                // 2. Configuramos el conversor
                cbEmpleado.setConverter(new StringConverter<EmpleadoSinExpedienteDTO>() {
                    @Override
                    public String toString(EmpleadoSinExpedienteDTO empleado) {
                        return (empleado != null) ? empleado.getNombreEmpleado() : "";
                    }

                    @Override
                    public EmpleadoSinExpedienteDTO fromString(String string) {
                        // Corregido: antes decía EmpleadoSinExpedienteDTODTO
                        return null;
                    }
                });

                cbTipoSangre.setItems(FXCollections.observableArrayList(info.getTiposSangre()));
                antecedentes = info.getAntecedentes();
                cbTipoAntecedente.setItems(FXCollections.observableArrayList(antecedentes.keySet()));
            });
        }).exceptionally(ex -> {
            // Tip: Siempre maneja el error si el API falla para que no se quede la pantalla "congelada"
            ex.printStackTrace();
            return null;
        });
    }

    @FXML
    private void handleAgregarAntecedente() {
        Integer tipo = antecedentes.get(cbTipoAntecedente.getValue());
        String nombre = cbTipoAntecedente.getValue();
        String desc = txtDescripcion.getText().trim();

        // 1. Validar campos obligatorios
        if (tipo == null || desc.isEmpty()) {
//            mostrarAlerta("Campos incompletos", "Por favor seleccione un tipo y escriba una descripción.");
//            return;
        }

        // 2. Validar duplicados (UX)
        boolean duplicado = listaAntecedentes.stream()
                .anyMatch(a -> a.getIdDetalle().equals(tipo) && a.getDetalle().equalsIgnoreCase(desc));

        if (duplicado) {
//            mostrarAlerta("Dato duplicado", "Este antecedente ya ha sido agregado a la lista inferior.");
//            return;
        }

        // 3. Agregar a la tabla
        listaAntecedentes.add(new AntecedentesRequest(tipo, nombre, desc));

        // 4. Limpiar campos para nueva entrada rápida
        txtDescripcion.clear();
        txtDescripcion.requestFocus();
    }
    
    public AgregarExpedienteRequest obtenerDatos() {
        AgregarExpedienteRequest agregarExpediente = new AgregarExpedienteRequest();
        agregarExpediente.setAntecedentes(listaAntecedentes);
        agregarExpediente.setNss(txtNss.getText());
        agregarExpediente.setTipoSangre(cbTipoSangre.getValue());
        agregarExpediente.setIdEmpleado(cbEmpleado.getValue().getIdEmpleado());
        return agregarExpediente;
    }
    
}
