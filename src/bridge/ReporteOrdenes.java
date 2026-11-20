/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bridge;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import models.entities.OrdenTrabajoDetalle;

/**
 *
 * @author marco
 */
public class ReporteOrdenes extends Reporte {
    private final List<OrdenTrabajoDetalle> datos;
    private final String desde, hasta, estado;
    
    public ReporteOrdenes(List<OrdenTrabajoDetalle> datos, String desde, String hasta, String estado, FormatoReporte formato) {
        super(formato);
        this.datos = datos;
        this.desde = desde;
        this.hasta = hasta;
        this.estado = estado;
    }
    
    @Override
    public File generar() throws Exception {
        String[] columnas = {"#OT", "Placa", "Cliente", "Mecánico", "Estado", "Fecha ingreso", "Total S/."};
        List<String[]> filas = new ArrayList<>();
        double total = 0;
        for (OrdenTrabajoDetalle o : datos) {
            filas.add(new String[] {
                String.valueOf(o.getIdOrdenTrabajo()), o.getPlacaVehiculo(), o.getClienteNombreCompleto(), o.getMecanicoNombreCompleto(),
                o.getEstado(), o.getFechaIngreso(), String.format("%.2f", o.getTotal())
            });
            total += o.getTotal();
        }
        return formato.exportar("Reporte de Órdenes de Trabajo", "Periodo: " + desde + " al " + hasta + " | Estado: " + estado,
                columnas, filas, "TOTAL GENERAL: " + String.format("%.2f", total));
    }
}
