/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.service;

import java.util.List;
import models.dao.VehiculoDao;
import models.entities.Marca;
import models.entities.Modelo;
import models.entities.Vehiculo;
import models.entities.VehiculoDetalle;

/**
 *
 * @author James
 */
public class VehiculoService {
    private VehiculoDao vehicuDao = new VehiculoDao();
    
    public List<Marca> findAllMarca(){
        return vehicuDao.findAllMarca();
    }
    
    public List<Modelo> findModeloByIdMarca(int idMarca){
        return vehicuDao.findAllModeloByMarca(idMarca);
    }
    
    public int registrarVehiculo(Vehiculo vehiculo) {
        return vehicuDao.save(vehiculo);
    }
    
    public List<VehiculoDetalle> listarTodos() {
        return vehicuDao.findAll();
    }
    
    public List<Vehiculo> findVehiculosByPersona(int idPersona) {
        return vehicuDao.findByPersona(idPersona);
    }

    public List<VehiculoDetalle> listarDetalleByPersona(int idPersona) {
        return vehicuDao.findDetalleByPersona(idPersona);
    }
    
    public boolean actualizarVehiculo(Vehiculo vehiculo) {
        return vehicuDao.update(vehiculo) > 0;
    }
    
    public boolean eliminarVehiculo(int id) {
        return vehicuDao.delete(id) > 0;
    }
}
