package controladores;

import clienteApi.ClienteApi;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

/**
 * Controlador para la pantalla de Informes de Salud.
 *
 * @author isaac
 */
public class InformesSaludController implements Initializable {

    @FXML
    private BorderPane pdfViewer;
    private ClienteApi clienteApi;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteApi = new ClienteApi();
    }

    private void renderizarPDF(VBox contenedor, byte[] pdf) {
        try (PDDocument document = PDDocument.load(pdf)) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);

            for (int page = 0; page < document.getNumberOfPages(); page++) {
                BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 150);

                Image imagenJavaFX = SwingFXUtils.toFXImage(bim, null);

                ImageView imageView = new ImageView(imagenJavaFX);
                imageView.setPreserveRatio(true);
                imageView.setSmooth(true);
                imageView.setFitWidth(pdfViewer.getWidth());

                imageView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 5);");

                contenedor.getChildren().add(imageView);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleGenerarReporte() {
        try {
            CompletableFuture<byte[]> pdfBusqueda = clienteApi.obtenerReporte();
            pdfBusqueda.thenAccept(pdf -> {
                Platform.runLater(() -> {
                    VBox contenedorPaginas = new VBox();
                    contenedorPaginas.setAlignment(Pos.CENTER);
                    contenedorPaginas.setSpacing(10);
                    contenedorPaginas.setPadding(new Insets(10));

                    ScrollPane scrollPane = new ScrollPane(contenedorPaginas);
                    scrollPane.setFitToWidth(true);
                    scrollPane.setStyle("-fx-background-color: #525659;");

                    renderizarPDF(contenedorPaginas, pdf);
                    Button btnGuardar = new Button("Guardar Reporte");
                    btnGuardar.setStyle("-fx-font-size: 14px; -fx-cursor: hand;");
                    btnGuardar.setOnAction(e -> guardarArchivo(pdf));

                    HBox topBar = new HBox(btnGuardar);
                    topBar.setPadding(new Insets(10));
                    topBar.setAlignment(Pos.CENTER_RIGHT);
                    topBar.setStyle("-fx-background-color: #323639;");

                    pdfViewer.setTop(topBar);
                    pdfViewer.setCenter(scrollPane);
                });
            });
        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("ERROR");
            alerta.setContentText("Error: " + e.getMessage());
            alerta.show();
        }
    }

    private void guardarArchivo(byte[] pdf) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Reporte");
        // Filtro para que solo permita guardar como .pdf
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos PDF", "*.pdf"));
        fileChooser.setInitialFileName("reporte_estado_salud_empleados.pdf");

        File archivoDestino = fileChooser.showSaveDialog(pdfViewer.getScene().getWindow());

        if (archivoDestino != null) {
            try {
                // Copiar el archivo desde la ruta temporal/origen a la ruta elegida por el usuario
                Files.write(archivoDestino.toPath(), pdf);
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("¡PDF guardado exitosamente!");
                alerta.setContentText("Archivo guardado exitosamente en: " + archivoDestino.getPath());
                alerta.show();
            } catch (IOException e) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("ERROR");
                alerta.setContentText("Error al guardar el archivo.");
                alerta.show();
            }
        }
    }
}
