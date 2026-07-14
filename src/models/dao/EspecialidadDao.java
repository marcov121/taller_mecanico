/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.dao;

import interfaces.ICrud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.entities.Especialidad;
import utils.MySqlConexion;

/**
 *
 * @author marco
 */
public class EspecialidadDao implements ICrud<Especialidad, Integer> {

    @Override
    public int save(Especialidad d) {
        int salida = -1;
        Connection cn = null;
        PreparedStatement pstm = null;
        try {
            cn = MySqlConexion.getConexion();
            String sql = "INSERT INTO tb_especialidad (id_esp_esp, des_esp) VALUES (?,?)";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, d.getIdEspecialidad());
            pstm.setString(2, d.getDescripcion());
            salida = pstm.executeUpdate();
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

    @Override
    public int update(Especialidad d) {
        int salida = -1;
        Connection cn = null;
        PreparedStatement pstm = null;
        try {
            cn = MySqlConexion.getConexion();
            String sql = "UPDATE tb_especialidad SET des_esp = ? WHERE id_esp_esp = ?";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, d.getDescripcion());
            pstm.setInt(2, d.getIdEspecialidad());
            salida = pstm.executeUpdate();
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

    @Override
    public int deleteByCode(Integer code) {
        int salida = -1;
        Connection cn = null;
        PreparedStatement pstm = null;
        try {
            cn = MySqlConexion.getConexion();
            String sql = "DELETE FROM tb_especialidad WHERE id_esp_esp = ?";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, code);
            salida = pstm.executeUpdate();
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

    @Override
    public List<Especialidad> findAll() {
        List<Especialidad> datos = new ArrayList<Especialidad>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            cn = MySqlConexion.getConexion();
            String sql = "SELECT * from tb_especialidad";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Especialidad esp = new Especialidad();
                esp.setIdEspecialidad(rs.getInt(1));
                esp.setDescripcion(rs.getString(2));
                datos.add(esp);
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

    @Override
    public Especialidad findById(Integer code) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
