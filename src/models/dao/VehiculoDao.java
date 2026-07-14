/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.entities.Marca;
import models.entities.Modelo;
import models.entities.Vehiculo;
import models.entities.VehiculoDetalle;
import utils.MySqlConexion;

/**
 *
 * @author James
 */
public class VehiculoDao {
    
    public int save(Vehiculo vehiculo) {
        int salida = -1;
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            cn = MySqlConexion.getConexion();
            String sql = "INSERT INTO tb_vehiculo (id_per_veh, id_mod_veh, pla_veh, ani_veh, col_veh) VALUES (?,?,?,?,?)";
            pstm = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, vehiculo.getIdPersona());
            pstm.setInt(2, vehiculo.getIdModelo());
            pstm.setString(3, vehiculo.getPlaca());
            if (vehiculo.getAnio() != null) {
                pstm.setInt(4, vehiculo.getAnio());
            } else {
                pstm.setNull(4, java.sql.Types.INTEGER);
            }
            pstm.setString(5, vehiculo.getColor());
            
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
    
    public int update(Vehiculo vehiculo) {
        int salida = -1;
        Connection cn = null;
        PreparedStatement pstm = null;
        try {
            cn = MySqlConexion.getConexion();
            String sql = "UPDATE tb_vehiculo SET id_mod_veh=?, pla_veh=?, ani_veh=?, col_veh=? WHERE id_veh=?";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, vehiculo.getIdModelo());
            pstm.setString(2, vehiculo.getPlaca());
            if (vehiculo.getAnio() != null) {
                pstm.setInt(3, vehiculo.getAnio());
            } else {
                pstm.setNull(3, java.sql.Types.INTEGER);
            }
            pstm.setString(4, vehiculo.getColor());
            pstm.setInt(5, vehiculo.getIdVehiculo());
            return pstm.executeUpdate();
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
        return salida;
    }
    
    public int delete(int id) {
        int salida = -1;
        Connection cn = null;
        PreparedStatement pstm = null;
        try {
            cn = MySqlConexion.getConexion();
            String sql = "DELETE FROM tb_vehiculo WHERE id_veh=?";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, id);
            return pstm.executeUpdate();
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
        return salida;
    }
    
    public List<VehiculoDetalle> findAll() {
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<VehiculoDetalle> datos = new ArrayList<>();
        
        try {
            cn = MySqlConexion.getConexion();
            String sql = "SELECT v.id_veh, CONCAT(p.nom_per, ' ', p.ape_per) AS cliente, p.num_doc_per, ma.nom_mar, mo.nom_mod, v.pla_veh, v.ani_veh, v.col_veh FROM tb_vehiculo v "
                    + "INNER JOIN tb_persona p ON p.id_per = v.id_per_veh "
                    + "INNER JOIN tb_modelo mo ON mo.id_mod = v.id_mod_veh "
                    + "INNER JOIN tb_marca ma ON ma.id_mar = mo.id_mar_mod "
                    + "ORDER BY v.id_veh DESC";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                VehiculoDetalle d = new VehiculoDetalle();
                d.setIdVehiculo(rs.getInt("id_veh"));
                d.setClienteNombreCompleto(rs.getString("cliente"));
                d.setDocumentoCliente(rs.getString("num_doc_per"));
                d.setMarca(rs.getString("nom_mar"));
                d.setModelo(rs.getString("nom_mod"));
                d.setPlaca(rs.getString("pla_veh"));
                int anio = rs.getInt("ani_veh");
                d.setAnio(rs.wasNull() ? null : anio);
                d.setColor(rs.getString("col_veh"));
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
    
    public List<Marca> findAllMarca(){
        Connection cn =null;
        PreparedStatement ps = null;    
        ResultSet rs = null;
       //Crear objeto de colección list usando el implementador Arraylist
        List<Marca> datos = new ArrayList<>();
        Marca d = null;
        try{
            cn = MySqlConexion.getConexion();
            String sql = "SELECT * FROM tb_marca order by id_mar asc";
           ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                d = new Marca();
                d.setIdMarca(rs.getInt(1));
                d.setNombreMarca(rs.getString(2));
               datos.add(d);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if(rs !=null) rs.close();
                if(ps != null) ps.close();
                if(cn != null) cn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return datos;
    }
    
    public List<Modelo> findAllModeloByMarca(int idMarca){
        Connection cn =null;
        PreparedStatement ps = null;    
        ResultSet rs = null;
       //Crear objeto de colección list usando el implementador Arraylist
        List<Modelo> datos = new ArrayList<>();
        Modelo d = null;
        try{
            cn = MySqlConexion.getConexion();
            String sql = "SELECT id_mod, nom_mod FROM tb_modelo WHERE id_mar_mod = ?";
           ps = cn.prepareStatement(sql);
           ps.setInt(1, idMarca);
            rs = ps.executeQuery();
            
            while(rs.next()){
                d = new Modelo();
                d.setIdModelo(rs.getInt(1));
                d.setNombreModelo(rs.getString(2));
               datos.add(d);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if(rs !=null) rs.close();
                if(ps != null) ps.close();
                if(cn != null) cn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return datos;
    }
    
    public List<Vehiculo> findByPersona(int idPersona) {
        List<Vehiculo> datos = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            cn = MySqlConexion.getConexion();
            String sql = "SELECT id_veh, id_per_veh, id_mod_veh, pla_veh, ani_veh, col_veh "
                    + "FROM tb_vehiculo WHERE id_per_veh = ? ORDER BY id_veh DESC";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, idPersona);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                Vehiculo v = new Vehiculo();
                v.setIdVehiculo(rs.getInt("id_veh"));
                v.setIdPersona(rs.getInt("id_per_veh"));
                v.setIdModelo(rs.getInt("id_mod_veh"));
                v.setPlaca(rs.getString("pla_veh"));
                int anio = rs.getInt("ani_veh");
                v.setAnio(rs.wasNull() ? null : anio);
                v.setColor(rs.getString("col_veh"));
                datos.add(v);
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
