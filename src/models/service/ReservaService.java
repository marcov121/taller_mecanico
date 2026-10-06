/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.service;

import java.util.List;
import models.dao.AgendaDao;
import models.dao.ReservaDao;
import models.entities.AgendaDisponibilidad;
import models.entities.Reserva;
import models.entities.ReservaDetalle;

/**
 *
 * @author marco
 */
public class ReservaService {
    private ReservaDao reservaDao = new ReservaDao();
    private AgendaDao agendaDao = new AgendaDao();
    
    public List<AgendaDisponibilidad> listarHorariosDisponibles() {
        return agendaDao.findDisponibles();
    }
    
    public int registrarReserva(Reserva reserva) {
        return reservaDao.save(reserva);
    }
    
    public boolean cancelarReserva(int idReserva) {
        return reservaDao.cancelar(idReserva);
    }
    
    public List<ReservaDetalle> listarTodos() {
        return reservaDao.findAll();
    }
    
    public List<ReservaDetalle> listarPorPersona(int idPersona) {
        return reservaDao.findByPersona(idPersona);
    }

    public List<ReservaDetalle> buscarReservasPendientesByDocCliente(String numDoc) {
        return reservaDao.findReservasPendientesByDocCliente(numDoc);
    }

    public boolean convertirReservaAOrden(int idReserva) {
        return reservaDao.convertirAOrden(idReserva);
    }

    public List<AgendaDisponibilidad> generarHorariosDisponibles() {
        return agendaDao.generarDisponibles(7, java.time.LocalTime.of(9, 0), java.time.LocalTime.of(17, 0));
    }
    
    public int obtenerOCrearSlot(java.time.LocalDate fecha, java.time.LocalTime horaInicio, java.time.LocalTime horaFin) {
        return agendaDao.obtenerOCrearSlot(fecha, horaInicio, horaFin);
    }
}
