/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.entities;

/**
 *
 * @author marco
 */
public class Reserva {
    private int idReserva;
    private int idPersona;
    private int idVehiculo;
    private int idAgenda;
    private String motivo;
    private String estado;

    public Reserva() {
    }

    public Reserva(int idReserva, int idPersona, int idVehiculo, int idAgenda, String motivo, String estado) {
        this.idReserva = idReserva;
        this.idPersona = idPersona;
        this.idVehiculo = idVehiculo;
        this.idAgenda = idAgenda;
        this.motivo = motivo;
        this.estado = estado;
    }

    public int getIdReserva() {
        return idReserva;
    }
    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }
    public int getIdPersona() {
        return idPersona;
    }
    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }
    public int getIdVehiculo() {
        return idVehiculo;
    }
    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }
    public int getIdAgenda() {
        return idAgenda;
    }
    public void setIdAgenda(int idAgenda) {
        this.idAgenda = idAgenda;
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
