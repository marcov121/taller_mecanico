package models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.entities.OrdenTrabajoDetalle;
import models.entities.ReporteServicioItem;
import utils.MySqlConexion;

public class ReporteDao {

    // =========================================================================
    // Reporte 1 — Órdenes de Trabajo (con filtros opcionales)
    // =========================================================================
    public List<OrdenTrabajoDetalle> findOrdenesParaReporte(
            String fechaDesde, String fechaHasta, String estado) {

        List<OrdenTrabajoDetalle> datos = new ArrayList<>();
        String sql =
            "SELECT o.id_ord_trab, v.pla_veh, "
            + "CONCAT(pc.nom_per,' ',pc.ape_per) AS cliente, "
            + "CONCAT(pm.nom_per,' ',pm.ape_per) AS mecanico, "
            + "e.des_est_ord_tra AS estado, o.fec_ing_ord_tra, o.tot_ord_tra "
            + "FROM tb_orden_trabajo o "
            + "INNER JOIN tb_vehiculo v  ON v.id_veh         = o.id_veh_ord_tra "
            + "INNER JOIN tb_persona pc  ON pc.id_per        = v.id_per_veh "
            + "INNER JOIN tb_mecanico m  ON m.id_per_mec     = o.id_mec_ord_tra "
            + "INNER JOIN tb_persona pm  ON pm.id_per        = m.id_per_mec "
            + "INNER JOIN tb_estado_orden_trabajo e "
            + "       ON e.id_est_est_ord_tra = o.id_est_est_ord_tra "
            + "WHERE (? = '' OR DATE(o.fec_ing_ord_tra) >= ?) "
            + "  AND (? = '' OR DATE(o.fec_ing_ord_tra) <= ?) "
            + "  AND (? = 'Todos' OR e.des_est_ord_tra = ?) "
            + "ORDER BY o.fec_ing_ord_tra DESC";

        try (Connection cn = MySqlConexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, fechaDesde); ps.setString(2, fechaDesde);
            ps.setString(3, fechaHasta); ps.setString(4, fechaHasta);
            ps.setString(5, estado);     ps.setString(6, estado);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrdenTrabajoDetalle d = new OrdenTrabajoDetalle();
                    d.setIdOrdenTrabajo(rs.getInt("id_ord_trab"));
                    d.setPlacaVehiculo(rs.getString("pla_veh"));
                    d.setClienteNombreCompleto(rs.getString("cliente"));
                    d.setMecanicoNombreCompleto(rs.getString("mecanico"));
                    d.setEstado(rs.getString("estado"));
                    d.setFechaIngreso(String.valueOf(rs.getTimestamp("fec_ing_ord_tra")));
                    d.setTotal(rs.getDouble("tot_ord_tra"));
                    datos.add(d);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datos;
    }

    // =========================================================================
    // Reporte 2 — Servicios más solicitados (con filtro de fecha opcional)
    // =========================================================================
    public List<ReporteServicioItem> findServiciosMasSolicitados(
            String fechaDesde, String fechaHasta) {

        List<ReporteServicioItem> datos = new ArrayList<>();
        String sql =
            "SELECT s.des_ser AS servicio, "
            + "COUNT(d.id_det_tra_ser)                          AS veces_solicitado, "
            + "SUM(d.can_det_tra_ser)                           AS total_unidades, "
            + "SUM(d.pre_uni_det_tra_ser * d.can_det_tra_ser)   AS total_ingresos "
            + "FROM tb_detalle_trabajo_servicio d "
            + "INNER JOIN tb_servicio s "
            + "       ON s.id_serv = d.id_ser_det_tra_ser "
            + "INNER JOIN tb_orden_trabajo o "
            + "       ON o.id_ord_trab = d.id_ord_det_tra_ser "
            + "WHERE (? = '' OR DATE(o.fec_ing_ord_tra) >= ?) "
            + "  AND (? = '' OR DATE(o.fec_ing_ord_tra) <= ?) "
            + "GROUP BY s.id_serv, s.des_ser "
            + "ORDER BY veces_solicitado DESC";

        try (Connection cn = MySqlConexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, fechaDesde); ps.setString(2, fechaDesde);
            ps.setString(3, fechaHasta); ps.setString(4, fechaHasta);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ReporteServicioItem item = new ReporteServicioItem();
                    item.setServicio(rs.getString("servicio"));
                    item.setVecesSolicitado(rs.getInt("veces_solicitado"));
                    item.setTotalUnidades(rs.getInt("total_unidades"));
                    item.setTotalIngresos(rs.getDouble("total_ingresos"));
                    datos.add(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datos;
    }
}
