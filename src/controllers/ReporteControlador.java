package controllers;

import java.util.List;
import models.entities.OrdenTrabajoDetalle;
import models.entities.ReporteServicioItem;
import models.service.ReporteService;

public class ReporteControlador {
    private final ReporteService reporteService = new ReporteService();

    public List<OrdenTrabajoDetalle> listarOrdenesParaReporte(
            String fechaDesde, String fechaHasta, String estado) {
        return reporteService.listarOrdenesParaReporte(fechaDesde, fechaHasta, estado);
    }

    public List<ReporteServicioItem> listarServiciosMasSolicitados(
            String fechaDesde, String fechaHasta) {
        return reporteService.listarServiciosMasSolicitados(fechaDesde, fechaHasta);
    }
}
