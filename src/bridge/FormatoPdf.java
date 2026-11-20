package bridge;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class FormatoPdf implements FormatoReporte {

    @Override
    public File exportar(String titulo, String periodo, String[] columnas, List<String[]> filas, String pie) throws Exception {
        File archivo = File.createTempFile("reporte_", ".pdf");
        Document doc = new Document(PageSize.A4.rotate(), 36, 36, 54, 36);
        PdfWriter.getInstance(doc, new FileOutputStream(archivo));
        doc.open();
        Font negrita = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
        doc.add(new Paragraph(titulo, new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD)));
        doc.add(new Paragraph(periodo, new Font(Font.FontFamily.HELVETICA, 10)));
        doc.add(Chunk.NEWLINE);
        PdfPTable tabla = new PdfPTable(columnas.length);
        tabla.setWidthPercentage(100);
        for (String[] fila : filas) {
            for (String valor : fila) {
                tabla.addCell(valor);
            }
        }
        doc.add(tabla);
        doc.add(Chunk.NEWLINE);
        doc.add(new Paragraph(pie, negrita));
        doc.close();
        return archivo;
    }
    
}
