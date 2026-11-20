/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bridge;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import models.entities.ReporteServicioItem;

/**
 *
 * @author marco
 */
public class ReporteServicios extends Reporte {
    private final List<ReporteServicioItem> datos;
    private final String desde, hasta;
    
    public ReporteServicios(List<ReporteServicioItem> datos, String desde, String hasta, FormatoReporte formato) {
        super(formato);
        this.datos = datos;
        this.desde = desde;
        this.hasta = hasta;
    }

    @Override
    public File generar() throws Exception {
        String[] columnas = {"Servicio", "Veces solicitado", "Unidades", "Ingresos S/."};
        List<String[]> filas = new ArrayList<>();
        double total = 0;
        for (ReporteServicioItem s : datos) {
            filas.add(new String[] {
                s.getServicio(), String.valueOf(s.getVecesSolicitado()), String.valueOf(s.getTotalUnidades()), String.format("%.2f", s.getTotalIngresos())
            });
            total += s.getTotalIngresos();
        }
        return formato.exportar("Servicios más solicitados", "Periodo: " + desde + " al " + hasta, columnas, filas, "TOTAL INGRESOS: " + String.format("%.2f", total));
    }
}
