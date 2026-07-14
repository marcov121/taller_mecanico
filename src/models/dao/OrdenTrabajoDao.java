/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.entities.Mecanico;
import models.entities.OrdenTrabajo;
import models.entities.OrdenTrabajoDetalle;
import models.entities.Vehiculo;
import utils.MySqlConexion;

/**
 *
 * @author marco
 */
public class OrdenTrabajoDao {
    public Vehiculo findVehiculoByPlaca(String placa) {
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            cn = MySqlConexion.getConexion();
            String sql = "SELECT v.id_veh, v.id_per_veh, v.pla_veh, CONCAT(p.nom_per, ' ', p.ape_per) AS cliente "
                    + "FROM tb_vehiculo v "
                    + "INNER JOIN tb_persona p ON p.id_per = v.id_per_veh "
                    + "WHERE v.pla_veh = ?";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, placa);
            rs = pstm.executeQuery();
            if (rs.next()) {
                Vehiculo v = new Vehiculo();
                v.setIdVehiculo(rs.getInt("id_veh"));
                v.setIdPersona(rs.getInt("id_per_veh"));
                v.setPlaca(rs.getString("pla_veh"));
                v.setNombreCliente(rs.getString("cliente"));
                return v;
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
        return null;
    }

    public List<Mecanico> findMecanicosActivos() {
        List<Mecanico> datos = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            cn = MySqlConexion.getConexion();
            String sql = "SELECT m.id_per_mec, p.nom_per, p.ape_per "
                    + "FROM tb_mecanico m "
                    + "INNER JOIN tb_persona p ON p.id_per = m.id_per_mec "
                    + "WHERE m.est_mec = '1' ORDER BY p.nom_per, p.ape_per";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Mecanico m = new Mecanico();
                m.setIdPersona(rs.getInt("id_per_mec"));
                m.setNombres(rs.getString("nom_per"));
                m.setApellidos(rs.getString("ape_per"));
                datos.add(m);
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
    
    public int save(OrdenTrabajo orden) {
        int salida = -1;
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            cn = MySqlConexion.getConexion();
            String sql = "INSERT INTO tb_orden_trabajo (id_veh_ord_tra, id_mec_ord_tra, id_est_est_ord_tra, obs_cli_ord_tra, tot_ord_tra) "
                    + "VALUES (?, ?, (SELECT id_est_est_ord_tra FROM tb_estado_orden_trabajo WHERE des_est_ord_tra = 'En proceso'), ?, 0)";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, orden.getIdVehiculo());
            pstm.setInt(2, orden.getIdMecanico());
            pstm.setString(3, orden.getObservacionCliente());
            int filas = pstm.executeUpdate();
            if (filas > 0) {
                rs = pstm.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
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
    
    public boolean actualizarEstado(int idOrdenTrabajo, String nombreEstado) {
        Connection cn = null;
        PreparedStatement pstm = null;
        try {
            cn = MySqlConexion.getConexion();
            String sql = "UPDATE tb_orden_trabajo SET id_est_est_ord_tra = "
                    + "(SELECT id_est_est_ord_tra FROM tb_estado_orden_trabajo WHERE des_est_ord_tra = ?), "
                    + "fec_ent_ord_tra = CASE WHEN ? = 'Finalizado' THEN NOW() ELSE fec_ent_ord_tra END "
                    + "WHERE id_ord_trab = ?";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, nombreEstado);
            pstm.setString(2, nombreEstado);
            pstm.setInt(3, idOrdenTrabajo);
            return pstm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
    
    public List<OrdenTrabajoDetalle> findAll() {
        List<OrdenTrabajoDetalle> datos = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            cn = MySqlConexion.getConexion();
            String sql = "SELECT o.id_ord_trab, v.pla_veh, CONCAT(p.nom_per, ' ', p.ape_per) AS cliente, "
                    + "CONCAT(pm.nom_per, ' ', pm.ape_per) AS mecanico, e.des_est_ord_tra, o.fec_ing_ord_tra, o.tot_ord_tra "
                    + "FROM tb_orden_trabajo o "
                    + "INNER JOIN tb_vehiculo v ON v.id_veh = o.id_veh_ord_tra "
                    + "INNER JOIN tb_persona p ON p.id_per = v.id_per_veh "
                    + "INNER JOIN tb_mecanico m ON m.id_per_mec = o.id_mec_ord_tra "
                    + "INNER JOIN tb_persona pm ON pm.id_per = m.id_per_mec "
                    + "INNER JOIN tb_estado_orden_trabajo e ON e.id_est_est_ord_tra = o.id_est_est_ord_tra "
                    + "ORDER BY o.id_ord_trab DESC";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                OrdenTrabajoDetalle d = new OrdenTrabajoDetalle();
                d.setIdOrdenTrabajo(rs.getInt("id_ord_trab"));
                d.setPlacaVehiculo(rs.getString("pla_veh"));
                d.setClienteNombreCompleto(rs.getString("cliente"));
                d.setMecanicoNombreCompleto(rs.getString("mecanico"));
                d.setEstado(rs.getString("des_est_ord_tra"));
                d.setFechaIngreso(String.valueOf(rs.getTimestamp("fec_ing_ord_tra")));
                d.setTotal(rs.getDouble("tot_ord_tra"));
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
