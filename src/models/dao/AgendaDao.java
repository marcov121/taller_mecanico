/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.sql.Statement;
import java.util.List;
import models.entities.AgendaDisponibilidad;
import utils.MySqlConexion;

/**
 *
 * @author marco
 */
public class AgendaDao {
    public List<AgendaDisponibilidad> findDisponibles() {
        List<AgendaDisponibilidad> datos = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            cn = MySqlConexion.getConexion();
            String sql = "SELECT id_age_age_dis, fec_age_dis, hor_ini_age_dis, hor_fin_age_dis "
                    + "FROM tb_agenda_disponibilidad "
                    + "WHERE est_slo_age_dis = 'libre' AND fec_age_dis >= CURDATE() "
                    + "ORDER BY fec_age_dis ASC, hor_ini_age_dis ASC";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                AgendaDisponibilidad a = new AgendaDisponibilidad();
                a.setIdAgenda(rs.getInt("id_age_age_dis"));
                a.setFecha(rs.getDate("fec_age_dis").toLocalDate());
                a.setHoraInicio(rs.getTime("hor_ini_age_dis").toLocalTime());
                a.setHoraFin(rs.getTime("hor_fin_age_dis").toLocalTime());
                datos.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return datos;
    }
    
    public List<AgendaDisponibilidad> generarDisponibles(int diasFuturos, java.time.LocalTime horaApertura, java.time.LocalTime horaCierre) {
        List<AgendaDisponibilidad> disponibles = new ArrayList<>();
        
        java.util.Set<String> ocupados = new java.util.HashSet<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            cn = MySqlConexion.getConexion();
            String sql = "SELECT fec_age_dis, hor_ini_age_dis FROM tb_agenda_disponibilidad "
                    + "WHERE est_slo_age_dis = 'ocupado' AND fec_age_dis >= CURDATE()";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String clave = rs.getDate("fec_age_dis").toLocalDate() + "_" + rs.getTime("hor_ini_age_dis").toLocalTime();
                ocupados.add(clave);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        java.time.LocalDate hoy = java.time.LocalDate.now();
        for (int dia=0; dia<=diasFuturos; dia++) {
            java.time.LocalDate fecha = hoy.plusDays(dia);
            if (fecha.getDayOfWeek() == java.time.DayOfWeek.SUNDAY) continue;
            java.time.LocalTime horaActual = horaApertura;
            while (horaActual.isBefore(horaCierre)) {
                java.time.LocalTime horaFin = horaActual.plusHours(1);
                boolean esPasado = fecha.equals(hoy) && horaActual.isBefore(java.time.LocalTime.now());
                String clave = fecha + "_" + horaActual;
                if (!ocupados.contains(clave) && !esPasado) {
                    AgendaDisponibilidad slot = new AgendaDisponibilidad();
                    slot.setIdAgenda(-1);
                    slot.setFecha(fecha);
                    slot.setHoraInicio(horaActual);
                    slot.setHoraFin(horaFin);
                    slot.setEstado("libre");
                    disponibles.add(slot);
                }
                horaActual = horaFin;
            }
        }
        return disponibles;
    }
    
    public int obtenerOCrearSlot(java.time.LocalDate fecha, java.time.LocalTime horaInicio, java.time.LocalTime horaFin) {
        int salida = -1;
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            cn = MySqlConexion.getConexion();
            String sqlBuscar = "SELECT id_age_age_dis FROM tb_agenda_disponibilidad "
                    + "WHERE fec_age_dis = ? AND hor_ini_age_dis = ?";
            pstm = cn.prepareStatement(sqlBuscar);
            pstm.setDate(1, Date.valueOf(fecha));
            pstm.setTime(2, Time.valueOf(horaInicio));
            rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_age_age_dis");
            }
            rs.close();
            pstm.close();
            
            String sqlInsertar = "INSERT INTO tb_agenda_disponibilidad (fec_age_dis, hor_ini_age_dis, hor_fin_age_dis, est_slo_age_dis) "
                    + "VALUES (?, ?, ?, 'libre')";
            pstm = cn.prepareStatement(sqlInsertar, Statement.RETURN_GENERATED_KEYS);
            pstm.setDate(1, java.sql.Date.valueOf(fecha));
            pstm.setTime(2, java.sql.Time.valueOf(horaInicio));
            pstm.setTime(3, java.sql.Time.valueOf(horaFin));
            pstm.executeUpdate();
            rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return salida;
    }
}
