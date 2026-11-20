/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bridge;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author marco
 */
public class FormatoCsv implements FormatoReporte {
    @Override
    public File exportar(String titulo, String periodo, String[] columnas, List<String[]> filas, String pie) throws Exception {
        File archivo = File.createTempFile("reporte_", ".csv");
        try (PrintWriter out = new PrintWriter(archivo, "UTF-8")) {
            out.print('\uFEFF');
            out.println(celda(titulo));
            out.println(celda(periodo));
            out.println(String.join(",", columnas));
            for (String[] fila : filas) {
                StringBuilder linea = new StringBuilder();
                for (int i=0; i<fila.length; i++) {
                    if (i>0) linea.append(',');
                    linea.append(celda(fila[i]));
                }
                out.println(linea);
            }
            out.println(celda(pie));
        }
        return archivo;
    }
    
    private String celda(String valor) {
        String v = valor == null ? "" : valor.replace("\"", "\"\"");
        return "\"" + v + "\"";
    }
}
