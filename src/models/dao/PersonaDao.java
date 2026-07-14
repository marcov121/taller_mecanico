/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.dao;

import interfaces.ICrud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.entities.Persona;
import utils.MySqlConexion;

/**
 *
 * @author James
 */
public class PersonaDao implements ICrud<Persona, Integer>{
    
    public int save(Persona persona, Connection cn) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "INSERT INTO tb_persona (num_doc_per, nom_per, ape_per, tel_per, dir_per, sexo_per) VALUES (?,?,?,?,?,?)";
            ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, persona.getNumeroDocumento());
            ps.setString(2, persona.getNombres());
            ps.setString(3, persona.getApellidos());
            ps.setString(4, persona.getTelefono());
            ps.setString(5, persona.getDireccion());
            ps.setString(6, String.valueOf(persona.getSexo()));

            int filas = ps.executeUpdate();
            if (filas > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) return rs.getInt(1);
            }
            return -1;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            // NO se cierra cn — la gestiona el service
        }
    }
    
    @Override
    public int save(Persona persona) {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        int resultado = -1;
        try{
            cn = MySqlConexion.getConexion();

            String sql = "INSERT INTO tb_persona (num_doc_per, nom_per, ape_per, tel_per, dir_per, sexo_per) VALUES (?,?,?,?,?,?)";

            ps = cn.prepareStatement(sql);
            ps.setString(1, persona.getNumeroDocumento());
            ps.setString(2, persona.getNombres());
            ps.setString(3, persona.getApellidos());
            ps.setString(4, persona.getTelefono());
            ps.setString(5, persona.getDireccion());
            ps.setString(6, String.valueOf(persona.getSexo()));
            
            int filas = ps.executeUpdate();

            if (filas > 0) {
            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if(ps != null) ps.close();
                if(cn != null) cn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        return resultado;
    }

    

    @Override
    public int update(Persona d) {
        int salida = -1;
        Connection cn = null;
        PreparedStatement pstm = null;
        try {
            cn = MySqlConexion.getConexion();
            String sql = "UPDATE tb_persona SET nom_per=?, ape_per=?, tel_per=?, dir_per=?, sexo_per=? WHERE id_per=?";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, d.getNombres());
            pstm.setString(2, d.getApellidos());
            pstm.setString(3, d.getTelefono());
            pstm.setString(4, d.getDireccion());
            pstm.setString(5, String.valueOf(d.getSexo()));
            pstm.setInt(6, d.getIdPersona());
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

    @Override
    public int deleteByCode(Integer code) {
        int salida = -1;
        Connection cn = null;
        PreparedStatement pstm = null;
        try {
            cn = MySqlConexion.getConexion();
            String sql = "UPDATE tb_persona SET est_per='0' WHERE id_per=?";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, code);
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

    public List<Persona> findAllClientes() {
        List<Persona> datos = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            cn = MySqlConexion.getConexion();
            String sql = "SELECT tp.id_per, tp.num_doc_per, tp.nom_per, tp.ape_per, tp.tel_per, tp.dir_per, tp.sexo_per, tu.est_usu "
                    + "FROM tb_persona tp INNER JOIN tb_usuario tu ON tp.id_per = tu.id_per_usu "
                    + "WHERE tu.id_rol_usu = 1";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Persona p = new Persona();
                p.setIdPersona(rs.getInt("id_per"));
                p.setNumeroDocumento(rs.getString("num_doc_per"));
                p.setNombres(rs.getString("nom_per"));
                p.setApellidos(rs.getString("ape_per"));
                p.setTelefono(rs.getString("tel_per"));
                p.setDireccion(rs.getString("dir_per"));
                String sexo = rs.getString("sexo_per");
                p.setSexo(sexo != null ? sexo.charAt(0) : ' ');
                String est = rs.getString("est_usu");
                p.setEstado(est != null ? est.charAt(0) : '0');
                datos.add(p);
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
    public Persona findById(Integer code) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Persona findByDocumento(String numeroDocumento) {
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Persona persona = null;
        
        try {
            cn = MySqlConexion.getConexion();
            String sql = "SELECT tp.id_per, tp.num_doc_per, tp.nom_per, tp.ape_per, tp.tel_per, tp.dir_per, tp.sexo_per "
                    + "FROM tb_persona tp INNER JOIN tb_usuario tu ON tp.id_per = tu.id_per_usu"
                    + " WHERE tp.num_doc_per=? AND tp.est_per='1' AND tu.id_rol_usu = 1";
            
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, numeroDocumento);
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                persona = new Persona(
                        rs.getInt("id_per"),
                        rs.getString("num_doc_per"),
                        rs.getString("nom_per"),
                        rs.getString("ape_per"),
                        rs.getString("tel_per"),
                        rs.getString("dir_per"),
                        rs.getString("sexo_per") != null ? rs.getString("sexo_per").charAt(0) : ' '
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return persona;
    }

    @Override
    public List<Persona> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
