/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.entities;

/**
 *
 * @author marco
 */
public class ReservaDetalle {
    private int idReserva;
    private String clienteNombreCompleto;
    private String placaVehiculo;
    private String fechaHorario;
    private String motivo;
    private String estado;

    public ReservaDetalle() {
    }

    public ReservaDetalle(int idReserva, String clienteNombreCompleto, String placaVehiculo, String fechaHorario, String motivo, String estado) {
        this.idReserva = idReserva;
        this.clienteNombreCompleto = clienteNombreCompleto;
        this.placaVehiculo = placaVehiculo;
        this.fechaHorario = fechaHorario;
        this.motivo = motivo;
        this.estado = estado;
    }

    public int getIdReserva() {
        return idReserva;
    }
    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }
    public String getClienteNombreCompleto() {
        return clienteNombreCompleto;
    }
    public void setClienteNombreCompleto(String clienteNombreCompleto) {
        this.clienteNombreCompleto = clienteNombreCompleto;
    }
    public String getPlacaVehiculo() {
        return placaVehiculo;
    }
    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }
    public String getFechaHorario() {
        return fechaHorario;
    }
    public void setFechaHorario(String fechaHorario) {
        this.fechaHorario = fechaHorario;
    }
    public String getMotivo() {
        return motivo;
    }
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
