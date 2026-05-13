package controladores;

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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
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
    private final Map<String, Node> pantallas = new HashMap<>();
    private final Map<String, Object> controladores = new HashMap<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            cargarPantalla("MenuPrincipal", "/vistas/panelPrincipal.fxml");
            cargarPantalla("PantallaCitas", "/vistas/pantallaCitas.fxml");
            cargarPantalla("HistorialPacientes", "/vistas/HistorialPacientes.fxml");
            cargarPantalla("InformesSalud", "/vistas/InformesSalud.fxml");
            mostrarPantalla("MenuPrincipal", "/styles/menuprincipal.css");
        } catch (IOException e) {
            System.err.println("Error al cargar el menú principal: " + e.getMessage());
        }
    }

    private void cargarPantalla(String nombre, String ruta) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
        Node pantalla = loader.load();
        pantalla.setVisible(false);
        pantallas.put(nombre, pantalla);
        mainLayout.getChildren().add(pantalla);

    }

    private void mostrarPantalla(String nombre, String estilo) {
        Node nodo = pantallas.get(nombre);
        if (nodo == null) {
            return;
        }

        // Limpiar estilos anteriores y aplicar el nuevo
        nodo.getStyleClass().removeIf(s -> s.endsWith("-theme")); // Convención de nombres
        if (estilo != null && !estilo.isEmpty()) {
            nodo.getStyleClass().add(estilo + "-theme");
        }

        // Lógica de visibilidad
        pantallas.values().forEach(n -> n.setVisible(false));
        nodo.setVisible(true);
        nodo.toFront();
    }

    @FXML
    private void mostrarPanelPrincipal() {
        mostrarPantalla("MenuPrincipal", "/styles/menuprincipal.css");

    }

    @FXML
    private void mostrarCitas() {
        mostrarPantalla("PantallaCitas", "/styles/pantallaCitas.css");
    }

    @FXML
    private void mostrarHistorial() {
        mostrarPantalla("HistorialPacientes", "/styles/HistorialPacientes.css");
    }

    @FXML
    private void mostrarInformes() {
        mostrarPantalla("InformesSalud", "/styles/InformesSalud.css");
    }

    @FXML
    private void logout() {
        // Crea menú de confirmación
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmación");
        confirmAlert.setHeaderText("¿Está seguro de que desea cerrar la sesión?");
        
        // Muestra el menú al usuario
        ButtonType resultado = confirmAlert.showAndWait().orElse(null);
        
        // Si hace click en el botón de 'OK'
        if (resultado != null && resultado == ButtonType.OK) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/vistas/IniciarSesion.fxml"));
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("/styles/menuprincipal.css").toExternalForm());

                Stage stageMenu = (Stage) mainLayout.getScene().getWindow();

                Stage stage = new Stage();
                stage.setScene(scene);

                // Mostrar ventana de inicio de sesión
                stage.show();

                // Cerrar ventana actual
                stageMenu.close();

            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Ha ocurrido un error inesperado al intentar cerrar la sesión.");
                alert.showAndWait();
            }
        }
    }
}