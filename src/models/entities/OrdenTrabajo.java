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
}
