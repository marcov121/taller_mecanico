/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.entities;

/**
 *
 * @author marco
 */
public class OrdenTrabajo {
    private int idOrdenTrabajo;
    private Integer idReserva;
    private int idVehiculo;
    private int idMecanico;
    private String observacionCliente;
    private double total;

    public OrdenTrabajo() {
    }
    public OrdenTrabajo(int idOrdenTrabajo, Integer idReserva, int idVehiculo, int idMecanico, String observacionCliente, double total) {
        this.idOrdenTrabajo = idOrdenTrabajo;
        this.idReserva = idReserva;
        this.idVehiculo = idVehiculo;
        this.idMecanico = idMecanico;
        this.observacionCliente = observacionCliente;
        this.total = total;
    }

    public int getIdOrdenTrabajo() {
        return idOrdenTrabajo;
    }
    public void setIdOrdenTrabajo(int idOrdenTrabajo) {
        this.idOrdenTrabajo = idOrdenTrabajo;
    }
    public Integer getIdReserva() {
        return idReserva;
    }
    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }
    public int getIdVehiculo() {
        return idVehiculo;
    }
    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }
    public int getIdMecanico() {
        return idMecanico;
    }
    public void setIdMecanico(int idMecanico) {
        this.idMecanico = idMecanico;
    }
    public String getObservacionCliente() {
        return observacionCliente;
    }
    public void setObservacionCliente(String observacionCliente) {
        this.observacionCliente = observacionCliente;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    
    // Patrón Builder: la orden tiene varios campos y algunos opcionales.
    // se arma paso a paso de forma legible y build() valida los obligatorios, evitando órdenes incompletas.
    public static Builder builder() { return new Builder(); }
    
    public static class Builder {
        private Integer idReserva;
        private int idVehiculo, idMecanico;
        private String observacionCliente;
        private double total;
        
        public Builder vehiculo(int id) { this.idVehiculo = id; return this; }
        public Builder mecanico(int id) { this.idMecanico = id; return this; }
        public Builder reserva(Integer id) { this.idReserva = id; return this; }
        public Builder observacion(String obs) { this.observacionCliente = obs; return this; }
        public Builder total(double total) { this.total = total; return this; }
        
        public OrdenTrabajo build() {
            if (idVehiculo <= 0) throw new IllegalStateException("La orden requiere un vehículo");
            if (idMecanico <= 0) throw new IllegalStateException("La orden requiere un mecánico");
            return new OrdenTrabajo(0, idReserva, idVehiculo, idMecanico, observacionCliente, total);
        }
    }
}
