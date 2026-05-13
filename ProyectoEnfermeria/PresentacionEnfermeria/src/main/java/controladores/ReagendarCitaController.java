package controladores;

import contratos.IClienteApi;
import injector.ClienteApiInjector;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import request.ReagendarCitaRequest;
import utilerias.HorarioEnfermero;

/**
 *
 * @author Leonardo Flores Leyva
 */
public class ReagendarCitaController implements Initializable{
    @FXML
    private Label lbNombreEmpleado;
    @FXML
    private Label lbFechaHoraOriginal;
    @FXML
    private DatePicker dpNuevaFecha;
    @FXML
    private ComboBox<LocalTime> cbNuevaHora;
    
    private Integer idCita = -1;
    private LocalDateTime fechaHoraOriginal = LocalDateTime.MAX;
    private String nombreEmpleado = "N/A";
    
    private final IClienteApi cliente = ClienteApiInjector.toClienteApi();
    
    private DetallesCitaController origen;
    
    private final LocalTime HORA_INICIO = HorarioEnfermero.HORA_INICIO; // 8:00 AM
    private final LocalTime HORA_FIN = HorarioEnfermero.HORA_FIN;   // 4:00 PM
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocalTime hora = HORA_INICIO;
        while (hora.isBefore(HORA_FIN.plusSeconds(1))) {
            cbNuevaHora.getItems().add(hora);
            hora = hora.plusMinutes(15);
        }
    }
    
    public void obtenerCita(Integer idCita, LocalDateTime fechaHoraOriginal, String nombreEmpleado, DetallesCitaController origen){
        if(idCita != null && idCita >= 1) { this.idCita = idCita; }
        if(fechaHoraOriginal != null) { this.fechaHoraOriginal = fechaHoraOriginal; }
        if(nombreEmpleado != null) { this.nombreEmpleado = nombreEmpleado; }
        
        this.origen = origen;
        
        lbNombreEmpleado.setText(this.nombreEmpleado);
        lbFechaHoraOriginal.setText(String.format("%d/%d/%d - %d:%d:%d", 
                this.fechaHoraOriginal.getDayOfMonth(),
                this.fechaHoraOriginal.getMonthValue(),
                this.fechaHoraOriginal.getYear(),
                this.fechaHoraOriginal.getHour(),
                this.fechaHoraOriginal.getMinute(),
                this.fechaHoraOriginal.getSecond()
        ));
    }
    
    private boolean validarReagendacion(){
        
        if(idCita == null || idCita < 1){
            mostrarAlerta("Error", "No existe una cita asociada. Contacte con el departamento de sistemas.", Alert.AlertType.ERROR);
            return false;
        }
        
        LocalDate fechaSeleccionada = dpNuevaFecha.getValue();
        LocalTime horaSeleccionada = cbNuevaHora.getValue();
        
        if (fechaSeleccionada == null || horaSeleccionada == null) {
            mostrarAlerta("Error", "Agregue una fecha y hora válida", Alert.AlertType.ERROR);
            return false;
        }
        
        LocalDateTime fechaHoraSeleccionada = LocalDateTime.of(fechaSeleccionada, horaSeleccionada);
        
        if(!fechaHoraSeleccionada.isAfter(LocalDateTime.now())){
            mostrarAlerta("Error", "La nueva fecha y hora debe ser futura", Alert.AlertType.ERROR);
            return false;
        }
        
        if(!fechaHoraSeleccionada.isAfter(fechaHoraOriginal)){
            mostrarAlerta("Error", "La nueva fecha y hora debe estar después de la original", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }
    
    @FXML
    private void handleReagendacion(){
        if(validarReagendacion()){
            ReagendarCitaRequest request = new ReagendarCitaRequest(idCita, LocalDateTime.of(dpNuevaFecha.getValue(), cbNuevaHora.getValue()));
            cliente.reagendarCita(request).thenAccept(resultado -> {
                mostrarAlerta("¡Cita Reagendada!", "¡La cita ha sido reagendada con éxito!", Alert.AlertType.INFORMATION);
                cerrarVentana();
                if(origen != null){ origen.cerrarVentana(); }
            }).exceptionally(ex -> {
                mostrarAlerta("Error", "La cita no pudo ser reagendada: " + ex.getCause().getMessage(), Alert.AlertType.ERROR);
                return null;
            });
        }
    }
    
    @FXML
    private void cancelar(){
        cerrarVentana();
    }
    
    /**
     * Metodo Auxiliar para mostrar una ventana
     *
     * @param titulo
     * @param mensaje
     * @param tipo
     */
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Platform.runLater(() -> {
            Alert alerta = new Alert(tipo);
            alerta.setTitle(titulo);
            alerta.setHeaderText(null);
            alerta.setContentText(mensaje);
            alerta.showAndWait();
       });
    }
    
    private void cerrarVentana() {
        Platform.runLater(() -> {
            if (lbNombreEmpleado.getScene() != null && lbNombreEmpleado.getScene().getWindow() != null) {
                Stage stage = (Stage) lbNombreEmpleado.getScene().getWindow();
                stage.close();
            }
        });
    }
}