/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.List;
import models.entities.Marca;
import models.entities.Modelo;
import models.entities.Vehiculo;
import models.entities.VehiculoDetalle;
import models.service.VehiculoService;

/**
 *
 * @author James
 */
public class VehiculoControlador {
    
    private VehiculoService vehiculoService;
    
    public VehiculoControlador(){
        this.vehiculoService = new VehiculoService();
    }
    
    public List<Marca> findAllMarcas(){
        return vehiculoService.findAllMarca();
    }
    
    public List<Modelo> findModelosByMarca(int idMarca){
        return vehiculoService.findModeloByIdMarca(idMarca);
    }
    
    public boolean registrar(Vehiculo vehiculo) {
        int idGenerado = vehiculoService.registrarVehiculo(vehiculo);
        if (idGenerado != -1) {
            vehiculo.setIdVehiculo(idGenerado);
            return true;
        }
        return false;
    }
    
    public List<VehiculoDetalle> listarTodos() {
        return vehiculoService.listarTodos();
    }
    
    public List<Vehiculo> findVehiculosByPersona(int idPersona) {
        return vehiculoService.findVehiculosByPersona(idPersona);
    }

    public List<VehiculoDetalle> listarPorPersona(int idPersona) {
        return vehiculoService.listarDetalleByPersona(idPersona);
    }
    
    public boolean actualizar(Vehiculo vehiculo) {
        return vehiculoService.actualizarVehiculo(vehiculo);
    }
    
    public boolean eliminar(int id) {
        return vehiculoService.eliminarVehiculo(id);
    }
}
