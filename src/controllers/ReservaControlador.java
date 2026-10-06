/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.List;
import models.entities.AgendaDisponibilidad;
import models.entities.Reserva;
import models.entities.ReservaDetalle;
import models.service.ReservaService;

/**
 *
 * @author marco
 */
public class ReservaControlador {
    private ReservaService reservaService;
    
    public ReservaControlador() {
        this.reservaService = new ReservaService();
    }
    
    public List<AgendaDisponibilidad> listarHorariosDisponibles() {
        return reservaService.listarHorariosDisponibles();
    }
    
    public boolean registrar(Reserva reserva) {
        int idGenerado = reservaService.registrarReserva(reserva);
        if (idGenerado != -1) {
            reserva.setIdReserva(idGenerado);
            return true;
        }
        return false;
    }
    
    public boolean cancelar(int idReserva) {
        return reservaService.cancelarReserva(idReserva);
    }
    
    public List<ReservaDetalle> listarTodas() {
        return reservaService.listarTodos();
    }
    
    public List<ReservaDetalle> listarPorPersona(int idPersona) {
        return reservaService.listarPorPersona(idPersona);
    }

    public List<ReservaDetalle> buscarReservasPorDocCliente(String numDoc) {
        return reservaService.buscarReservasPendientesByDocCliente(numDoc);
    }

    public boolean convertirAOrden(int idReserva) {
        return reservaService.convertirReservaAOrden(idReserva);
    }

    public List<AgendaDisponibilidad> generarHorariosDisponibles() {
        return reservaService.generarHorariosDisponibles();
    }

    public int obtenerOCrearSlot(java.time.LocalDate fecha, java.time.LocalTime horaInicio, java.time.LocalTime horaFin) {
        return reservaService.obtenerOCrearSlot(fecha, horaInicio, horaFin);
    }
}
