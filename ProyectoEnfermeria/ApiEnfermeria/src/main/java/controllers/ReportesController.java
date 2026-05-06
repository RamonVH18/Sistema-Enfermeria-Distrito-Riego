package controllers;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import dtos.ReporteRegistroDTO;
import interfaces.IServicioExpedientes;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
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

        List<ReporteRegistroDTO> registros = servicioExpediente.obtenerUltimoRegistroPacientes();

        PdfWriter writer = new PdfWriter(response.getOutputStream());
        PdfDocument pdf = new PdfDocument(writer);

        pdf.setDefaultPageSize(PageSize.LETTER.rotate());

        try (Document document = new Document(pdf)) {
            document.setMargins(20, 20, 20, 20);
            document.add(new Paragraph("Reporte de Estado de Salud de Empleados")
                    .setFontSize(16)
                    .setTextAlignment(TextAlignment.CENTER));
            Table table = new Table(UnitValue.createPercentArray(new float[]{
                10, 10, 5, 4, 8, 8, 5, 5, 5, 5, 5, 5, 5, 5
            })).useAllAvailableWidth();
            String[] encabezados = {
                "Nombre", "Depto.", "Sexo", "Edad", "NSS", "Fecha Ult. Cons.",
                "Alt", "Peso", "P. Sist", "P. Diast", "Gluc", "Oxig", "F.C.", "Temp"
            };
            for (String h : encabezados) {
                table.addHeaderCell(new Cell()
                        .add(new Paragraph(h).setFontSize(9))
                        .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                        .setTextAlignment(TextAlignment.CENTER));
            }

            for (ReporteRegistroDTO reg : registros) {
                table.addCell(crearCelda(reg.getNombre()));
                table.addCell(crearCelda(reg.getDepartamento()));
                table.addCell(crearCelda(reg.getSexo()));
                table.addCell(crearCelda(String.valueOf(reg.getEdad())));
                table.addCell(crearCelda(String.valueOf(reg.getNss())));
                table.addCell(crearCelda(reg.getFechaRegistro().toString()));
                table.addCell(crearCelda(String.valueOf(reg.getAltura())));
                table.addCell(crearCelda(String.valueOf(reg.getPeso())));
                table.addCell(crearCelda(String.valueOf(reg.getPresionSistolica())));
                table.addCell(crearCelda(String.valueOf(reg.getPresionDiatolica())));
                table.addCell(crearCelda(String.valueOf(reg.getGlucosa())));
                table.addCell(crearCelda(String.valueOf(reg.getOxigenacion())));
                table.addCell(crearCelda(String.valueOf(reg.getFrecuenciaCardiaca())));
                table.addCell(crearCelda(String.valueOf(reg.getTemperatura())));
            }
            document.add(table);
        }
    }

    private Cell crearCelda(String texto) {
        return new Cell().add(new Paragraph(texto != null ? texto : "-")
                .setFontSize(8))
                .setTextAlignment(TextAlignment.LEFT);
    }
}