/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.entities;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author marco
 */
public class AgendaDisponibilidad {
    private int idAgenda;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String estado;

    public AgendaDisponibilidad() {
    }

    public AgendaDisponibilidad(int idAgenda, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, String estado) {
        this.idAgenda = idAgenda;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
    }

    public int getIdAgenda() {
        return idAgenda;
    }
    public void setIdAgenda(int idAgenda) {
        this.idAgenda = idAgenda;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public LocalTime getHoraInicio() {
        return horaInicio;
    }
    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }
    public LocalTime getHoraFin() {
        return horaFin;
    }
    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return fecha + " " + horaInicio + " " + horaFin;
    }
}
