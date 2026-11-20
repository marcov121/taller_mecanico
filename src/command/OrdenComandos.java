/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command;

import models.service.OrdenTrabajoService;

public class OrdenComandos {
    public interface Command {
        boolean ejecutar();
    }
    
    public static class FinalizarOrdenCommand implements Command {
        private final OrdenTrabajoService servicio;
        private final int idOrden;
        
        public FinalizarOrdenCommand(OrdenTrabajoService servicio, int idOrden) {
            this.servicio = servicio;
            this.idOrden = idOrden;
        }
        
        @Override
        public boolean ejecutar() {
            return servicio.cambiarEstado(idOrden, "Finalizada");
        }
    }
    
    public static class CancelarOrdenCommand implements Command {
        private final OrdenTrabajoService servicio;
        private final int idOrden;
        
        public CancelarOrdenCommand(OrdenTrabajoService servicio, int idOrden) {
            this.servicio = servicio;
            this.idOrden = idOrden;
        }
        
        @Override
        public boolean ejecutar() {
            return servicio.cambiarEstado(idOrden, "Cancelada");
        }
    }
    
    public static class ControlOrdenes {
        public boolean ejecutar(Command comando) {
            return comando.ejecutar();
        }
    }
}
