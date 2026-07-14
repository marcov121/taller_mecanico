/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.List;
import models.entities.Mecanico;
import models.entities.OrdenTrabajo;
import models.entities.OrdenTrabajoDetalle;
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
    
    public boolean registrar(OrdenTrabajo orden) {
        int idGenerado = ordenTrabajoService.registrarOrden(orden);
        if (idGenerado != -1) {
            orden.setIdOrdenTrabajo(idGenerado);
            return true;
        }
        return false;
    }
    
    public boolean finalizar(int idOrdenTrabajo) {
        return ordenTrabajoService.cambiarEstado(idOrdenTrabajo, "Finalizado");
    }
    
    public boolean cancelar(int idOrdenTrabajo) {
        return ordenTrabajoService.cambiarEstado(idOrdenTrabajo, "Cancelado");
    }
    
    public List<OrdenTrabajoDetalle> listarTodas() {
        return ordenTrabajoService.listarTodas();
    }
}
