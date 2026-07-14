package utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import models.entities.OrdenTrabajoDetalle;
import models.entities.ReporteServicioItem;

public class PdfGenerador {

    // ── Fuentes ──────────────────────────────────────────────────────────────
    private static final Font F_TITULO    = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD,  BaseColor.WHITE);
    private static final Font F_SUBTITULO = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);
    private static final Font F_NORMAL    = new Font(Font.FontFamily.HELVETICA, 10);
    private static final Font F_NEGRITA   = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
    private static final Font F_TH        = new Font(Font.FontFamily.HELVETICA,  9, Font.BOLD,  BaseColor.WHITE);
    private static final Font F_TD        = new Font(Font.FontFamily.HELVETICA,  9);

    // ── Colores ───────────────────────────────────────────────────────────────
    private static final BaseColor COLOR_HEADER  = new BaseColor(41, 128, 185);
    private static final BaseColor COLOR_ROW_ALT = new BaseColor(235, 243, 251);

    // =========================================================================
    // Reporte 1 — Órdenes de Trabajo
    // =========================================================================
    public static File generarReporteOrdenes(
            List<OrdenTrabajoDetalle> datos,
            String fechaDesde, String fechaHasta, String estado)
            throws DocumentException, IOException {

        File archivo = File.createTempFile("reporte_ordenes_", ".pdf");
        Document doc = new Document(PageSize.A4.rotate(), 36, 36, 54, 36);
        PdfWriter.getInstance(doc, new FileOutputStream(archivo));
        doc.open();

        agregarEncabezado(doc, "Reporte de Órdenes de Trabajo");
        doc.add(new Paragraph("Período : " + nvl(fechaDesde) + "  al  " + nvl(fechaHasta), F_NORMAL));
        doc.add(new Paragraph("Estado  : " + estado, F_NORMAL));
        doc.add(Chunk.NEWLINE);

        PdfPTable tabla = new PdfPTable(new float[]{5, 10, 22, 22, 13, 18, 10});
        tabla.setWidthPercentage(100);
        agregarHeaderTabla(tabla, "#OT", "Placa", "Cliente", "Mecánico", "Estado", "Fecha ingreso", "Total S/.");

        final int[] idx = {0};
        datos.forEach(o -> {
            BaseColor bg = (idx[0]++ % 2 == 0) ? BaseColor.WHITE : COLOR_ROW_ALT;
            agregarCelda(tabla, String.valueOf(o.getIdOrdenTrabajo()), bg, Element.ALIGN_CENTER);
            agregarCelda(tabla, o.getPlacaVehiculo(),          bg, Element.ALIGN_CENTER);
            agregarCelda(tabla, o.getClienteNombreCompleto(),  bg, Element.ALIGN_LEFT);
            agregarCelda(tabla, o.getMecanicoNombreCompleto(), bg, Element.ALIGN_LEFT);
            agregarCelda(tabla, o.getEstado(),                 bg, Element.ALIGN_CENTER);
            agregarCelda(tabla, o.getFechaIngreso(),           bg, Element.ALIGN_CENTER);
            agregarCelda(tabla, String.format("%.2f", o.getTotal()), bg, Element.ALIGN_RIGHT);
        });

        double totalGeneral = datos.stream().mapToDouble(OrdenTrabajoDetalle::getTotal).sum();
        PdfPCell etiqueta = new PdfPCell(new Phrase("TOTAL GENERAL:", F_NEGRITA));
        etiqueta.setColspan(6);
        etiqueta.setHorizontalAlignment(Element.ALIGN_RIGHT);
        etiqueta.setPadding(6);
        tabla.addCell(etiqueta);
        PdfPCell celdaTotal = new PdfPCell(new Phrase(String.format("%.2f", totalGeneral), F_NEGRITA));
        celdaTotal.setHorizontalAlignment(Element.ALIGN_RIGHT);
        celdaTotal.setPadding(6);
        tabla.addCell(celdaTotal);

        doc.add(tabla);
        doc.add(Chunk.NEWLINE);
        doc.add(new Paragraph("Total de registros: " + datos.size(), F_NORMAL));
        doc.close();
        return archivo;
    }

    // =========================================================================
    // Reporte 2 — Servicios más solicitados
    // =========================================================================
    public static File generarReporteServicios(
            List<ReporteServicioItem> datos,
            String fechaDesde, String fechaHasta)
            throws DocumentException, IOException {

        File archivo = File.createTempFile("reporte_servicios_", ".pdf");
        Document doc = new Document(PageSize.A4, 36, 36, 54, 36);
        PdfWriter.getInstance(doc, new FileOutputStream(archivo));
        doc.open();

        agregarEncabezado(doc, "Servicios más solicitados");
        doc.add(new Paragraph("Período: " + nvl(fechaDesde) + "  al  " + nvl(fechaHasta), F_NORMAL));
        doc.add(Chunk.NEWLINE);

        PdfPTable tabla = new PdfPTable(new float[]{45, 15, 15, 25});
        tabla.setWidthPercentage(100);
        agregarHeaderTabla(tabla, "Servicio", "Veces solicitado", "Unidades", "Ingresos S/.");

        final int[] idx = {0};
        datos.forEach(s -> {
            BaseColor bg = (idx[0]++ % 2 == 0) ? BaseColor.WHITE : COLOR_ROW_ALT;
            agregarCelda(tabla, s.getServicio(),                              bg, Element.ALIGN_LEFT);
            agregarCelda(tabla, String.valueOf(s.getVecesSolicitado()),       bg, Element.ALIGN_CENTER);
            agregarCelda(tabla, String.valueOf(s.getTotalUnidades()),         bg, Element.ALIGN_CENTER);
            agregarCelda(tabla, String.format("%.2f", s.getTotalIngresos()), bg, Element.ALIGN_RIGHT);
        });

        double totalIngresos = datos.stream().mapToDouble(ReporteServicioItem::getTotalIngresos).sum();
        PdfPCell etiqueta = new PdfPCell(new Phrase("TOTAL INGRESOS:", F_NEGRITA));
        etiqueta.setColspan(3);
        etiqueta.setHorizontalAlignment(Element.ALIGN_RIGHT);
        etiqueta.setPadding(6);
        tabla.addCell(etiqueta);
        PdfPCell celdaTotal = new PdfPCell(new Phrase(String.format("%.2f", totalIngresos), F_NEGRITA));
        celdaTotal.setHorizontalAlignment(Element.ALIGN_RIGHT);
        celdaTotal.setPadding(6);
        tabla.addCell(celdaTotal);

        doc.add(tabla);
        doc.add(Chunk.NEWLINE);
        doc.add(new Paragraph("Servicios distintos: " + datos.size(), F_NORMAL));
        doc.close();
        return archivo;
    }

    // =========================================================================
    // Helpers privados
    // =========================================================================
    private static void agregarEncabezado(Document doc, String titulo) throws DocumentException {
        PdfPTable header = new PdfPTable(1);
        header.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell(new Phrase(titulo, F_TITULO));
        cell.setBackgroundColor(COLOR_HEADER);
        cell.setPadding(12);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(Rectangle.NO_BORDER);
        header.addCell(cell);
        doc.add(header);
        doc.add(Chunk.NEWLINE);
        doc.add(new Paragraph("TALLER MECÁNICO", F_SUBTITULO));
        doc.add(new Paragraph("Generado: " + new Date(), F_NORMAL));
        doc.add(Chunk.NEWLINE);
    }

    private static void agregarHeaderTabla(PdfPTable tabla, String... columnas) {
        for (String col : columnas) {
            PdfPCell cell = new PdfPCell(new Phrase(col, F_TH));
            cell.setBackgroundColor(COLOR_HEADER);
            cell.setPadding(7);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla.addCell(cell);
        }
    }

    private static void agregarCelda(PdfPTable tabla, String texto, BaseColor bg, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(nvl(texto), F_TD));
        cell.setBackgroundColor(bg);
        cell.setPadding(5);
        cell.setHorizontalAlignment(alignment);
        tabla.addCell(cell);
    }

    private static String nvl(String valor) {
        return (valor != null && !valor.isEmpty()) ? valor : "–";
    }
}
