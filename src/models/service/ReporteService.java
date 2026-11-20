package models.service;

import java.util.List;
import models.dao.ReporteDao;
import models.entities.OrdenTrabajoDetalle;
import models.entities.ReporteServicioItem;

public class ReporteService implements IReporteService {
    private final ReporteDao reporteDao = new ReporteDao();

    @Override
    public List<OrdenTrabajoDetalle> listarOrdenesParaReporte(String fechaDesde, String fechaHasta, String estado) {
        return reporteDao.findOrdenesParaReporte(fechaDesde, fechaHasta, estado);
    }

    @Override
    public List<ReporteServicioItem> listarServiciosMasSolicitados(String fechaDesde, String fechaHasta) {
        return reporteDao.findServiciosMasSolicitados(fechaDesde, fechaHasta);
    }
}
