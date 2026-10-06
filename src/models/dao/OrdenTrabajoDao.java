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
import models.entities.Producto;
import models.entities.Servicio;
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
            String sql = "INSERT INTO tb_orden_trabajo (id_res_ord_tra, id_veh_ord_tra, id_mec_ord_tra, id_est_est_ord_tra, fec_ing_ord_tra, obs_cli_ord_tra, tot_ord_tra) "
                    + "VALUES (?, ?, ?, (SELECT id_est_est_ord_tra FROM tb_estado_orden_trabajo WHERE des_est_ord_tra = 'En proceso'), NOW(), ?, 0)";
            pstm = cn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            if (orden.getIdReserva() != null) {
                pstm.setInt(1, orden.getIdReserva());
            } else {
                pstm.setNull(1, java.sql.Types.INTEGER);
            }
            pstm.setInt(2, orden.getIdVehiculo());
            pstm.setInt(3, orden.getIdMecanico());
            pstm.setString(4, orden.getObservacionCliente());
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
                    + "fec_ent_ord_tra = CASE WHEN ? = 'Finalizada' THEN NOW() ELSE fec_ent_ord_tra END "
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

    public List<OrdenTrabajoDetalle> findByPersona(int idPersona) {
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
                    + "WHERE v.id_per_veh = ? "
                    + "ORDER BY o.id_ord_trab DESC";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, idPersona);
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

    public List<Servicio> findServicios() {
        List<Servicio> datos = new ArrayList<>();
        try (Connection cn = MySqlConexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(
                     "SELECT id_serv, des_ser, pre_bas_ser FROM tb_servicio ORDER BY des_ser");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Servicio s = new Servicio();
                s.setIdServicio(rs.getInt("id_serv"));
                s.setDescripcion(rs.getString("des_ser"));
                s.setPrecioBase(rs.getDouble("pre_bas_ser"));
                datos.add(s);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return datos;
    }

    public List<Producto> findProductosActivos() {
        List<Producto> datos = new ArrayList<>();
        try (Connection cn = MySqlConexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(
                     "SELECT id_prod, sku_pro, des_pro, pre_ven_pro, sto_act_pro "
                     + "FROM tb_producto WHERE est_pro='1' AND sto_act_pro > 0 ORDER BY des_pro");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("id_prod"));
                p.setSku(rs.getString("sku_pro"));
                p.setDescripcion(rs.getString("des_pro"));
                p.setPrecioVenta(rs.getDouble("pre_ven_pro"));
                p.setStock(rs.getInt("sto_act_pro"));
                datos.add(p);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return datos;
    }

    public boolean saveDetalleServicio(int idOT, int idServicio, double precioUni) {
        try (Connection cn = MySqlConexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(
                     "INSERT INTO tb_detalle_trabajo_servicio "
                     + "(id_ord_det_tra_ser, id_ser_det_tra_ser, can_det_tra_ser, pre_uni_det_tra_ser) "
                     + "VALUES (?, ?, 1, ?)")) {
            ps.setInt(1, idOT);
            ps.setInt(2, idServicio);
            ps.setDouble(3, precioUni);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public boolean saveDetalleProducto(int idOT, int idProducto, int cantidad, double precioUni) {
        try (Connection cn = MySqlConexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(
                     "INSERT INTO tb_detalle_trabajo_producto "
                     + "(id_ord_det_tra_pro, id_pro_det_tra_pro, can_det_tra_pro, pre_uni_det_tra_pro) "
                     + "VALUES (?, ?, ?, ?)")) {
            ps.setInt(1, idOT);
            ps.setInt(2, idProducto);
            ps.setInt(3, cantidad);
            ps.setDouble(4, precioUni);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public List<Object[]> findDetalleServicioByOT(int idOT) {
        List<Object[]> datos = new ArrayList<>();
        try (Connection cn = MySqlConexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(
                     "SELECT s.des_ser, d.pre_uni_det_tra_ser, d.can_det_tra_ser, "
                     + "(d.pre_uni_det_tra_ser * d.can_det_tra_ser) AS subtotal "
                     + "FROM tb_detalle_trabajo_servicio d "
                     + "INNER JOIN tb_servicio s ON s.id_serv = d.id_ser_det_tra_ser "
                     + "WHERE d.id_ord_det_tra_ser = ?")) {
            ps.setInt(1, idOT);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    datos.add(new Object[]{
                        rs.getString("des_ser"),
                        String.format("%.2f", rs.getDouble("pre_uni_det_tra_ser")),
                        rs.getInt("can_det_tra_ser"),
                        String.format("%.2f", rs.getDouble("subtotal"))
                    });
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return datos;
    }

    public List<Object[]> findDetalleProductoByOT(int idOT) {
        List<Object[]> datos = new ArrayList<>();
        try (Connection cn = MySqlConexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(
                     "SELECT p.des_pro, p.sku_pro, d.pre_uni_det_tra_pro, d.can_det_tra_pro, "
                     + "(d.pre_uni_det_tra_pro * d.can_det_tra_pro) AS subtotal "
                     + "FROM tb_detalle_trabajo_producto d "
                     + "INNER JOIN tb_producto p ON p.id_prod = d.id_pro_det_tra_pro "
                     + "WHERE d.id_ord_det_tra_pro = ?")) {
            ps.setInt(1, idOT);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    datos.add(new Object[]{
                        rs.getString("des_pro"),
                        rs.getString("sku_pro"),
                        String.format("%.2f", rs.getDouble("pre_uni_det_tra_pro")),
                        rs.getInt("can_det_tra_pro"),
                        String.format("%.2f", rs.getDouble("subtotal"))
                    });
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return datos;
    }

    public boolean actualizarTotal(int idOT, double total) {
        try (Connection cn = MySqlConexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(
                     "UPDATE tb_orden_trabajo SET tot_ord_tra = ? WHERE id_ord_trab = ?")) {
            ps.setDouble(1, total);
            ps.setInt(2, idOT);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
}
