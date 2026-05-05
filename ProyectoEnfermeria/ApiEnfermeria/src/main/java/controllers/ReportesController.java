package controllers;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import interfaces.IServicioExpedientes;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para la generación de reportes.
 *
 * @author isaac
 * @author Leonardo Flores Leyva
 */
@RestController
@RequestMapping("/reportes")
public class ReportesController {

    @Autowired
    private IServicioExpedientes servicioExpediente;

    @GetMapping("/salud")
    public void obtenerReporteSalud(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"reporte_salud.pdf\"");
        try (OutputStream os = response.getOutputStream()) {
            PdfWriter writer = new PdfWriter(os);
            PdfDocument pdfDoc = new PdfDocument(writer);
            try (Document doc = new Document(pdfDoc)) {
                doc.add(new Paragraph("RAMOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOON"));
                doc.add(new Paragraph("ISAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAC"));
                doc.add(new Paragraph("RODRIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII"));
                doc.add(new Paragraph("Generado en: " + LocalDateTime.now()));
            }
        }
    }
}