package models.service;

import java.util.List;
import models.dao.ReporteDao;
import models.entities.OrdenTrabajoDetalle;
import models.entities.ReporteServicioItem;

public class ReporteService {
    private final ReporteDao reporteDao = new ReporteDao();

    public List<OrdenTrabajoDetalle> listarOrdenesParaReporte(
            String fechaDesde, String fechaHasta, String estado) {
        return reporteDao.findOrdenesParaReporte(fechaDesde, fechaHasta, estado);
    }

    public List<ReporteServicioItem> listarServiciosMasSolicitados(
            String fechaDesde, String fechaHasta) {
        return reporteDao.findServiciosMasSolicitados(fechaDesde, fechaHasta);
    }
}
