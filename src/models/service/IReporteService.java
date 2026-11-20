package models.service;

import java.util.List;
import models.entities.OrdenTrabajoDetalle;
import models.entities.ReporteServicioItem;

public interface IReporteService {
    List<OrdenTrabajoDetalle> listarOrdenesParaReporte(String fechaDesde, String fechaHasta, String estado);
    List<ReporteServicioItem> listarServiciosMasSolicitados(String fechaDesde, String fechaHasta);
}
