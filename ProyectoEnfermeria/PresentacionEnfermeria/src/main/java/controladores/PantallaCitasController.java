package controladores;

import clienteApi.ClienteApi;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import response.CitaPendienteResponse;
import utilerias.Utils;

/**
 *
 * @author Ramon Valencia
 */
public class PantallaCitasController implements Initializable {

    //OBJETOS DEL STAGE
    @FXML
    private DatePicker datePicker;
    @FXML
    private Pagination paginadorCitas;
    @FXML
    private Label lblFechaSeleccionada;

    // CONSTANTES
    private final int CITAS_POR_PAGINA = 5;

    private List<CitaPendienteResponse> citasPendientes = new ArrayList<>();
    private final ClienteApi cliente = new ClienteApi();

    /**
     * Inicializador del controlador
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCitasPendientes();
        generarDatePicker();
    }

    /**
     * Metodo para generar el date picker y agregarle un listener para poder
     * cambiar las fechas
     */
    private void generarDatePicker() {
        datePicker.valueProperty().addListener((observable, oldDate, newDate) -> {
            if (newDate != null) {
                cliente.obtenerCitasPorFecha(newDate)
                        .thenAccept(citas -> {
                            if (citas == null) {
                                this.citasPendientes = new ArrayList<>();
                            } else {
                                this.citasPendientes = citas;
                            }
                            Platform.runLater(() -> {
                                lblFechaSeleccionada.setText("Citas pendientes del " + Utils.obtenerFechaTraducida(newDate) + ":");
                                generarPaginador();
                            });                            
                        });
            }
        });
    }

    /**
     * Metodo encargado de cargar las citas pendientes
     */
    private void cargarCitasPendientes() {
        cliente.obtenerCitasPendientes().thenAccept(citas -> {
            Platform.runLater(() -> {
                this.citasPendientes = citas;
                generarPaginador();
            });
        });

    }

    /**
     * Metodo para cargar el paginador aqui es donde se muestran las citas
     * pendientes
     */
    public void generarPaginador() {
        Platform.runLater(() -> {

            paginadorCitas.setPageCount(0);
            paginadorCitas.setPageFactory(null); // Limpia la fábrica anterior

            // Si no hay citas pendientes, entonces el paginador solo muestra un mensaje en medio
            if (citasPendientes == null || citasPendientes.isEmpty()) {
                paginadorCitas.setPageCount(1);
                paginadorCitas.setCurrentPageIndex(0);
                paginadorCitas.setPageFactory(n -> new Label("No hay citas programadas."));
                return;
            }
            //  En esta parte es donde se calcula cuantas paginas habra en el paginador para que solo salgan n citas por pagina.
            int totalPaginas = (int) Math.ceil((double) citasPendientes.size() / CITAS_POR_PAGINA);
            paginadorCitas.setPageCount(totalPaginas);
            paginadorCitas.setPageFactory(this::crearPaginaCitas);
        });
    }

    /**
     * Metodo auxiliar del paginador que se encarga de crear los VBox que iran
     * en cada pagina Aqui tambien es donde se asocia cada objeto con un VBox
     *
     * @param pageIndex
     * @return
     */
    private VBox crearPaginaCitas(int pageIndex) {
        VBox contenedorDePagina = new VBox(10);
        contenedorDePagina.setPadding(new Insets(10));

        int inicio = pageIndex * CITAS_POR_PAGINA;
        int fin = Math.min(inicio + CITAS_POR_PAGINA, citasPendientes.size());

        for (int i = inicio; i < fin; i++) {
            // Pasamos el objeto real al creador de VBox
            VBox cardCita = crearCardIndividual(citasPendientes.get(i));
            contenedorDePagina.getChildren().add(cardCita);
        }

        return contenedorDePagina;
    }

    /**
     * Metodo auxiliar para crear el card individual de cada cita.
     *
     * @param cita
     * @return
     */
    private VBox crearCardIndividual(CitaPendienteResponse cita) {

        VBox card = new VBox(5);
        card.getStyleClass().add("cita-item-card");
        card.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-radius: 8; -fx-cursor: hand;");
        card.setPadding(new Insets(15));

        Label lblMotivo = new Label(cita.getMotivo());
        lblMotivo.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        String fechaFormateada = cita.getFechaHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        Label lblInfo = new Label("📅 " + fechaFormateada + " | 👤 " + cita.getNombreEmpleado());
        lblInfo.setStyle("-fx-text-fill: #666;");

        card.getChildren().addAll(lblMotivo, lblInfo);
        card.setUserData(cita);
        // Se añade para detectar cuando se le de clic.
        card.setOnMouseClicked(event -> {
            // Recuperamos el objeto guardado
            CitaPendienteResponse citaSeleccionada = (CitaPendienteResponse) card.getUserData();
            cargarDetalleCita(citaSeleccionada);

        });

        return card;
    }

    /**
     * Metodo encargado de abrir el detalle de una cita
     *
     * @param citaSeleccionada
     */
    private void cargarDetalleCita(CitaPendienteResponse citaSeleccionada) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/DetalleCita.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            DetallesCitaController controller = loader.getController();
            controller.initializeData(citaSeleccionada);

            scene.getStylesheets().add(getClass().getResource("/styles/DetalleCita.css").toExternalForm());

            Stage stage = new Stage();
            stage.setTitle("Detalle de la Cita - Distrito de Riego");

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            mostrarAlerta("Error", "Hubo un error al mostrar los detalles de esta cita. Contacte con Servicio Tecnico", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    /**
     * Metodo que se activa cuando se presiona el boton de nueva cita en la
     * pantalla. Se encarga de abrir la pantalla para agendar cita.
     */
    @FXML
    private void handleNuevaCita() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/AgendarCita.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            // Se le da la nueva pantalla el controlador actual para que le avise cuando se registro una cita y este controlador actualice el observer
            AgendarCitaController controller = loader.getController();
            controller.setObserver(this);

            scene.getStylesheets().add(getClass().getResource("/styles/AgendarCita.css").toExternalForm());

            Stage stage = new Stage();
            stage.setTitle("Agendar Nueva Cita - Distrito de Riego");

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            mostrarAlerta("Error", "Hubo un error al abrir la pantalla de Nueva Cita. Contacte con Servicio Tecnico", Alert.AlertType.ERROR);
        }
    }

    /**
     * Metodo que se activa en presentacion sirve para resetaer el paginador y
     * qeu aparezcan todas las citas.
     */
    @FXML
    private void handleReset() {
        datePicker.setValue(null);
        cargarCitasPendientes();
        lblFechaSeleccionada.setText("Citas Pendientes:");
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
}
