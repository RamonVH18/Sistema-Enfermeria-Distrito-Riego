package controladores;

import clienteApi.ClienteApi;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import response.CitaPendienteResponse;
import utilerias.Utils;

/**
 *
 * @author Ramon Valencia
 * @author Leonardo Flores Leyva
 */
public class DetallesCitaController implements Initializable {

    @FXML
    private Label lblPaciente;
    @FXML
    private Label lblIdEmpleado;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblHora;
    @FXML
    private TextArea txtDescripcion;

    private CitaPendienteResponse cita;

    private final ClienteApi cliente = new ClienteApi();

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    public void initializeData(CitaPendienteResponse cita) {
        this.cita = cita;
        lblPaciente.setText(cita.getNombreEmpleado());
        lblIdEmpleado.setText(cita.getIdEmpleado().toString());
        lblFecha.setText(Utils.formatoFecha(cita.getFechaHora().toLocalDate()));
        lblHora.setText(Utils.formatoHora(cita.getFechaHora().toLocalTime()));
        txtDescripcion.setText(cita.getMotivo());
    }

    @FXML
    private void cancelarCita() {
        Integer idCita = cita.getIdCita();
        cliente.cancelarCita(idCita).thenAccept(res -> {
            mostrarAlerta("Exito", String.format("La cita con el id: %s fue cancelada exitosamente.", idCita), Alert.AlertType.INFORMATION);
            cerrarVentana();
        })
                .exceptionally(ex -> {
                    mostrarAlerta("Error de Conexion", "No se pudo cancelar la cita", Alert.AlertType.ERROR);
                    return null;
                });

    }

    @FXML
    private void handleReagendar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/ReagendarCita.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            ReagendarCitaController controller = loader.getController();
            controller.obtenerCita(cita.getIdCita(), cita.getFechaHora(), cita.getNombreEmpleado(), this);

            scene.getStylesheets().add(getClass().getResource("/styles/reagendar.css").toExternalForm());

            Stage stage = new Stage();
            stage.setTitle("Reagendar Cita - Distrito de Riego");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            mostrarAlerta("Error", "Hubo un error al abrir la pantalla de Reagendar Cita. Contacte con el departamento de sistemas.", Alert.AlertType.ERROR);
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void marcarAsistencia() {
        Integer idCita = cita.getIdCita();
        cliente.completarCita(idCita).thenAccept(res -> {
            mostrarAlerta("Exito", String.format("La cita con el id: %s fue acompletada exitosamente.", idCita), Alert.AlertType.INFORMATION);
            cerrarVentana();
        })
                .exceptionally(ex -> {
                    mostrarAlerta("Error de Conexion", "No se pudo  confirmar la cita", Alert.AlertType.ERROR);
                    return null;
                });
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

    public void cerrarVentana() {
        Platform.runLater(() -> {
            if (lblPaciente.getScene() != null && lblPaciente.getScene().getWindow() != null) {
                Stage stage = (Stage) lblPaciente.getScene().getWindow();
                stage.close();
            }
        });
    }
}