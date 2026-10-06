/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.List;
import models.entities.Mecanico;
import models.entities.OrdenTrabajo;
import models.entities.OrdenTrabajoDetalle;
import models.entities.Producto;
import models.entities.Servicio;
import models.entities.Vehiculo;
import models.service.OrdenTrabajoService;

/**
 *
 * @author marco
 */
public class OrdenTrabajoControlador {
    private OrdenTrabajoService ordenTrabajoService;
    
    public OrdenTrabajoControlador() {
        this.ordenTrabajoService = new OrdenTrabajoService();
    }
    
    public Vehiculo buscarVehiculoPorPlaca(String placa) {
        return ordenTrabajoService.buscarVehiculoPorPlaca(placa);
    }

    public List<Mecanico> listarMecanicosActivos() {
        return ordenTrabajoService.listarMecanicosActivos();
    }
    
    public int registrar(OrdenTrabajo orden) {
        int idGenerado = ordenTrabajoService.registrarOrden(orden);
        if (idGenerado > 0) orden.setIdOrdenTrabajo(idGenerado);
        return idGenerado;
    }
    
    public boolean finalizar(int idOrdenTrabajo) {
        return ordenTrabajoService.cambiarEstado(idOrdenTrabajo, "Finalizada");
    }

    public boolean cancelar(int idOrdenTrabajo) {
        return ordenTrabajoService.cambiarEstado(idOrdenTrabajo, "Cancelada");
    }
    
    public List<OrdenTrabajoDetalle> listarTodas() {
        return ordenTrabajoService.listarTodas();
    }

    public List<OrdenTrabajoDetalle> listarPorPersona(int idPersona) {
        return ordenTrabajoService.listarPorPersona(idPersona);
    }

    public List<Servicio> listarServicios() {
        return ordenTrabajoService.listarServicios();
    }

    public List<Producto> listarProductos() {
        return ordenTrabajoService.listarProductos();
    }

    public boolean guardarDetalleServicio(int idOT, int idServicio, double precio) {
        return ordenTrabajoService.guardarDetalleServicio(idOT, idServicio, precio);
    }

    public boolean guardarDetalleProducto(int idOT, int idProducto, int cantidad, double precio) {
        return ordenTrabajoService.guardarDetalleProducto(idOT, idProducto, cantidad, precio);
    }

    public boolean actualizarTotal(int idOT, double total) {
        return ordenTrabajoService.actualizarTotal(idOT, total);
    }

    public List<Object[]> obtenerDetalleServicio(int idOT) {
        return ordenTrabajoService.obtenerDetalleServicio(idOT);
    }

    public List<Object[]> obtenerDetalleProducto(int idOT) {
        return ordenTrabajoService.obtenerDetalleProducto(idOT);
    }
}
