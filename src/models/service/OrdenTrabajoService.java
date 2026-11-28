package models.service;

import java.util.List;
import models.dao.OrdenTrabajoDao;
import models.entities.ItemOrden;
import models.entities.Mecanico;
import models.entities.OrdenTrabajo;
import models.entities.OrdenTrabajoDetalle;
import models.entities.Producto;
import models.entities.Servicio;
import models.entities.Vehiculo;

public class OrdenTrabajoService {
    private OrdenTrabajoDao ordenTrabajoDao = new OrdenTrabajoDao();
    
    public Vehiculo buscarVehiculoPorPlaca(String placa) {
        return ordenTrabajoDao.findVehiculoByPlaca(placa);
    }

    public List<Mecanico> listarMecanicosActivos() {
        return ordenTrabajoDao.findMecanicosActivos();
    }
    
    public int registrarOrden(OrdenTrabajo orden) {
        return ordenTrabajoDao.save(orden);
    }
    
    public boolean cambiarEstado(int idOrdenTrabajo, String nombreEstado) {
        return ordenTrabajoDao.actualizarEstado(idOrdenTrabajo, nombreEstado);
    }
    
    public List<OrdenTrabajoDetalle> listarTodas() {
        return ordenTrabajoDao.findAll();
    }

    public List<OrdenTrabajoDetalle> listarPorPersona(int idPersona) {
        return ordenTrabajoDao.findByPersona(idPersona);
    }

    public List<Servicio> listarServicios() {
        return ordenTrabajoDao.findServicios();
    }

    public List<Producto> listarProductos() {
        return ordenTrabajoDao.findProductosActivos();
    }

    public boolean guardarDetalleServicio(int idOT, int idServicio, double precio) {
        return ordenTrabajoDao.saveDetalleServicio(idOT, idServicio, precio);
    }

    public boolean guardarDetalleProducto(int idOT, int idProducto, int cantidad, double precio) {
        return ordenTrabajoDao.saveDetalleProducto(idOT, idProducto, cantidad, precio);
    }

    public boolean actualizarTotal(int idOT, double total) {
        return ordenTrabajoDao.actualizarTotal(idOT, total);
    }

    public List<Object[]> obtenerDetalleServicio(int idOT) {
        return ordenTrabajoDao.findDetalleServicioByOT(idOT);
    }

    public List<Object[]> obtenerDetalleProducto(int idOT) {
        return ordenTrabajoDao.findDetalleProductoByOT(idOT);
    }
    
    public int registrarOrdenCompleta(OrdenTrabajo orden, boolean esServicio, List<ItemOrden> items) {
        int idOT = registrarOrden(orden);
        if (idOT <= 0) return -1;
        for (ItemOrden item : items) {
            if (esServicio) {
                guardarDetalleServicio(idOT, item.getIdItem(), item.getPrecioUnitario());
            } else {
                guardarDetalleProducto(idOT, item.getIdItem(), item.getCantidad(), item.getPrecioUnitario());
            }
        }
        actualizarTotal(idOT, ItemOrden.total(items));
        return idOT;
    }
}
