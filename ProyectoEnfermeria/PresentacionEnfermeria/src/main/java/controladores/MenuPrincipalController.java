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
import javafx.scene.layout.StackPane;

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
    private Map<String, Node> pantallas = new HashMap<>();
    private Map<String, Object> controladores = new HashMap<>();

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

}
