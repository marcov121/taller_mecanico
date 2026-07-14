/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.entities.Reserva;
import models.entities.ReservaDetalle;
import utils.MySqlConexion;

/**
 *
 * @author marco
 */
public class ReservaDao {
    public int save(Reserva reserva) {
        int salida = -1;
        Connection cn = null;
        PreparedStatement pstmReserva = null;
        PreparedStatement pstmAgenda = null;
        ResultSet rs = null;
        try {
            cn = MySqlConexion.getConexion();
            cn.setAutoCommit(false);
            String sqlAgenda = "UPDATE tb_agenda_disponibilidad SET est_slo_age_dis = 'ocupado' "
                    + "WHERE id_age_age_dis = ? AND est_slo_age_dis = 'libre'";
            pstmAgenda = cn.prepareStatement(sqlAgenda);
            pstmAgenda.setInt(1, reserva.getIdAgenda());
            int filasAgenda = pstmAgenda.executeUpdate();
            if (filasAgenda == 0) {
                cn.rollback();
                return -1;
            }
            String sqlReserva = "INSERT INTO tb_reserva (id_per_res, id_veh_res, id_age_res, mot_res) VALUES (?,?,?,?)";
            pstmReserva = cn.prepareStatement(sqlReserva, Statement.RETURN_GENERATED_KEYS);
            pstmReserva.setInt(1, reserva.getIdPersona());
            pstmReserva.setInt(2, reserva.getIdVehiculo());
            pstmReserva.setInt(3, reserva.getIdAgenda());
            pstmReserva.setString(4, reserva.getMotivo());
            pstmReserva.executeUpdate();
            rs = pstmReserva.getGeneratedKeys();
            int idGenerado = -1;
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }
            cn.commit();
            return idGenerado;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (cn != null) cn.rollback();
            } catch (Exception er) {
                er.printStackTrace();
            }
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmAgenda != null) pstmAgenda.close();
                if (pstmReserva != null) pstmReserva.close();
                if (rs != null) rs.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return salida;
    }
    
    public boolean cancelar(int idReserva) {
        Connection cn = null;
        PreparedStatement pstmBuscar = null;
        PreparedStatement pstmReserva = null;
        PreparedStatement pstmAgenda = null;
        ResultSet rs = null;
        try {
            cn = MySqlConexion.getConexion();
            cn.setAutoCommit(false);
            pstmBuscar = cn.prepareStatement("SELECT id_age_res FROM tb_reserva WHERE id_resv=?");
            pstmBuscar.setInt(1, idReserva);
            rs = pstmBuscar.executeQuery();
            if (!rs.next()) {
                cn.rollback();
                return false;
            }
            int idAgenda = rs.getInt("id_age_res");
            
            pstmReserva = cn.prepareStatement("UPDATE tb_reserva SET est_res_res='cancelada' WHERE id_resv=?");
            pstmReserva.setInt(1, idReserva);
            pstmReserva.executeUpdate();
            
            pstmAgenda = cn.prepareStatement("UPDATE tb_agenda_disponibilidad SET est_slo_age_dis='libre' WHERE id_age_age_dis=?");
            pstmAgenda.setInt(1, idAgenda);
            pstmAgenda.executeUpdate();
            
            cn.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (cn != null) cn.rollback();
            } catch (Exception er) {
                er.printStackTrace();
            }
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmAgenda != null) pstmAgenda.close();
                if (pstmReserva != null) pstmReserva.close();
                if (pstmBuscar != null) pstmBuscar.close();
                if (cn != null) cn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
    
    public List<ReservaDetalle> findAll() {
        List<ReservaDetalle> datos = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            cn = MySqlConexion.getConexion();
            String sql = "SELECT r.id_resv, CONCAT(p.nom_per, ' ', p.ape_per) AS cliente, "
                    + "v.pla_veh, "
                    + "CONCAT(a.fec_age_dis, ' ', a.hor_ini_age_dis, ' - ', a.hor_fin_age_dis) AS horario, "
                    + "r.mot_res, r.est_res_res "
                    + "FROM tb_reserva r "
                    + "INNER JOIN tb_persona p ON p.id_per = r.id_per_res "
                    + "INNER JOIN tb_vehiculo v ON v.id_veh = r.id_veh_res "
                    + "INNER JOIN tb_agenda_disponibilidad a ON a.id_age_age_dis = r.id_age_res "
                    + "ORDER BY r.id_resv DESC";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                ReservaDetalle d = new ReservaDetalle();
                d.setIdReserva(rs.getInt("id_resv"));
                d.setClienteNombreCompleto(rs.getString("cliente"));
                d.setPlacaVehiculo(rs.getString("pla_veh"));
                d.setFechaHorario(rs.getString("horario"));
                d.setMotivo(rs.getString("mot_res"));
                d.setEstado(rs.getString("est_res_res"));
                datos.add(d);
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
}
