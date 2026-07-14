/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.entities;

/**
 *
 * @author marco
 */
public class OrdenTrabajoDetalle {
    private int idOrdenTrabajo;
    private String placaVehiculo;
    private String clienteNombreCompleto;
    private String mecanicoNombreCompleto;
    private String estado;
    private String fechaIngreso;
    private double total;

    public OrdenTrabajoDetalle() {
    }
    public OrdenTrabajoDetalle(int idOrdenTrabajo, String placaVehiculo, String clienteNombreCompleto, String mecanicoNombreCompleto, String estado, String fechaIngreso, double total) {
        this.idOrdenTrabajo = idOrdenTrabajo;
        this.placaVehiculo = placaVehiculo;
        this.clienteNombreCompleto = clienteNombreCompleto;
        this.mecanicoNombreCompleto = mecanicoNombreCompleto;
        this.estado = estado;
        this.fechaIngreso = fechaIngreso;
        this.total = total;
    }

    public int getIdOrdenTrabajo() {
        return idOrdenTrabajo;
    }
    public void setIdOrdenTrabajo(int idOrdenTrabajo) {
        this.idOrdenTrabajo = idOrdenTrabajo;
    }
    public String getPlacaVehiculo() {
        return placaVehiculo;
    }
    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }
    public String getClienteNombreCompleto() {
        return clienteNombreCompleto;
    }
    public void setClienteNombreCompleto(String clienteNombreCompleto) {
        this.clienteNombreCompleto = clienteNombreCompleto;
    }
    public String getMecanicoNombreCompleto() {
        return mecanicoNombreCompleto;
    }
    public void setMecanicoNombreCompleto(String mecanicoNombreCompleto) {
        this.mecanicoNombreCompleto = mecanicoNombreCompleto;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getFechaIngreso() {
        return fechaIngreso;
    }
    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
}
