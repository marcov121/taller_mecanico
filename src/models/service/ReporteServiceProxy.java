package models.service;

import java.util.ArrayList;
import java.util.List;
import models.entities.OrdenTrabajoDetalle;
import models.entities.ReporteServicioItem;
import models.entities.Rol;
import models.entities.Usuario;
import utils.SesionUsuario;

public class ReporteServiceProxy implements IReporteService {
    private ReporteService reporteReal;
    
    private boolean tieneAcceso() {
        Usuario u = SesionUsuario.getInstancia().getUsuario();
        return u != null && u.getIdRol() == Rol.ADMINISTRADOR;
    }
    
    private ReporteService real() {
        if (reporteReal == null) {
            reporteReal = new ReporteService();
        }
        return reporteReal;
    }

    @Override
    public List<OrdenTrabajoDetalle> listarOrdenesParaReporte(String fechaDesde, String fechaHasta, String estado) {
        if (tieneAcceso()) {
            return real().listarOrdenesParaReporte(fechaDesde, fechaHasta, estado);
        }
        System.out.println("Acceso denegado: los reportes son solo para administradores.");
        return new ArrayList<>();
    }

    @Override
    public List<ReporteServicioItem> listarServiciosMasSolicitados(String fechaDesde, String fechaHasta) {
        if (tieneAcceso()) {
            return real().listarServiciosMasSolicitados(fechaDesde, fechaHasta);
        }
        System.out.println("Acceso denegado: los reportes son solo para administradores.");
        return new ArrayList<>();
    }
    
}
