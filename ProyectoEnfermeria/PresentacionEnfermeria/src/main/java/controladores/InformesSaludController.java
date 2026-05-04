package controladores;

import clienteApi.ClienteApi;
import dtos.ReporteEmpleadosDTO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import response.ReporteEmpleadosResponse;

/**
 * Controlador para la pantalla de Informes de Salud.
 * 
 * @author isaac
 */
public class InformesSaludController implements Initializable {

    @FXML
    private Label lblTotalEmpleados;
    @FXML
    private Label lblCitasRealizadas;
    @FXML
    private Label lblCitasPendientes;
    @FXML
    private Label lblCoberturaExpedientes;
    @FXML
    private Label lblCumplimientoDesc;
    @FXML
    private Label lblPerfilClinicoDesc;
    @FXML
    private Label lblDistribucionDesc;

    private ClienteApi clienteApi;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteApi = new ClienteApi();
        cargarDatosReporte();
    }

    private void cargarDatosReporte() {
        clienteApi.obtenerReporte().thenAccept(response -> {
            Platform.runLater(() -> {
                ReporteEmpleadosDTO dto = response.getReporte();
                actualizarUI(dto);
            });
        }).exceptionally(ex -> {
            System.err.println("Error al cargar reporte: " + ex.getMessage());
            return null;
        });
    }

    private void actualizarUI(ReporteEmpleadosDTO dto) {
        lblTotalEmpleados.setText(String.valueOf(dto.getTotalEmpleados()));
        lblCitasRealizadas.setText(String.valueOf(dto.getProductividadCitas()));
        lblCitasPendientes.setText(String.valueOf(dto.getCitasPendientes()));
        lblCoberturaExpedientes.setText(String.format("%.1f%%", dto.getTasaCobertura()));

        // Perspectivas dinámicas
        lblCumplimientoDesc.setText(String.format("El sistema cuenta con un %.1f%% de cobertura de expedientes médicos. Se recomienda alcanzar el 100%% para asegurar la atención integral de todos los empleados.", dto.getTasaCobertura()));
        
        lblPerfilClinicoDesc.setText(String.format("El promedio de edad de los empleados es de %.1f años. Basado en los registros médicos, el seguimiento preventivo es fundamental.", dto.getPromedioEdad()));

        if (dto.getDistribucionPorDepartamento() != null && !dto.getDistribucionPorDepartamento().isEmpty()) {
            String principalDepto = dto.getDistribucionPorDepartamento().entrySet().stream()
                    .max(java.util.Map.Entry.comparingByValue())
                    .map(java.util.Map.Entry::getKey)
                    .orElse("N/A");
            lblDistribucionDesc.setText(String.format("El departamento con mayor número de empleados es '%s'. La distribución por departamentos ayuda a identificar las áreas con mayor demanda potencial de servicios de salud.", principalDepto));
        }
    }

    @FXML
    private void handleGenerarReporte() {
        // Por ahora, refrescamos los datos
        cargarDatosReporte();
        System.out.println("Generando reporte actualizado...");
    }
}
