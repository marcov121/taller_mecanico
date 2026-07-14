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
import java.util.ArrayList;
import java.util.List;
import models.entities.Persona;
import models.entities.Usuario;
import utils.MySqlConexion;

/**
 *
 * @author James
 */
public class UsuarioDao implements ICrud<Persona, Integer>{

    public Usuario validarLogin(String username, String password){
        Connection cn =null;
        PreparedStatement ps = null;    
        ResultSet rs = null;
        Persona persona = null;
        Usuario usuario = null;
        
        try{
            cn = MySqlConexion.getConexion();
            String sql = "SELECT p.id_per, u.id_rol_usu, u.usu_usu, u.pas_h_usu, u.est_usu, "
                   + "       p.num_doc_per, p.nom_per, p.ape_per, p.tel_per, p.dir_per, p.sexo_per "
                   + "FROM tb_usuario u "
                   + "INNER JOIN tb_persona p ON p.id_per = u.id_per_usu "
                   + "WHERE u.usu_usu = ? AND u.pas_h_usu = ? AND u.est_usu = '1'";
            
            ps = cn.prepareStatement(sql);
            
            ps.setString(1, username);
            ps.setString(2, password);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                persona = new Persona(
                        rs.getInt("id_per"),
                        rs.getString("num_doc_per"),
                        rs.getString("nom_per"),
                        rs.getString("ape_per"),
                        rs.getString("tel_per"),
                        rs.getString("dir_per"),
                        rs.getString("sexo_per").charAt(0)
                    );

                usuario = new Usuario(persona, rs.getInt("id_rol_usu"),
                            rs.getString("usu_usu"), rs.getString("pas_h_usu"));
                usuario.setEstado(rs.getString("est_usu").charAt(0));
                    return usuario;
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
        return null;
    }

    public int save(Usuario usuario, Connection cn) throws SQLException {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO tb_usuario (id_per_usu, id_rol_usu, usu_usu, pas_h_usu, est_usu) VALUES (?,?,?,?,?)";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, usuario.getPersona().getIdPersona());
            ps.setInt(2, usuario.getIdRol());
            ps.setString(3, usuario.getNombreUsuario());
            ps.setString(4, usuario.getPasswordHash());
            ps.setString(5, String.valueOf(usuario.getEstado()));
            return ps.executeUpdate();
        } finally {
            if (ps != null) ps.close();
            // NO se cierra cn — la gestiona el service
        }
    }

    @Override
    public int save(Persona d) {
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        int resultado = -1;
        try {
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cn = MySqlConexion.getConexion();
                String sql = "INSERT INTO tb_persona (num_doc_per, nom_per, ape_per, tel_per, dir_per, sexo_per) VALUES (?,?,?,?,?,?)";
                pstm = cn.prepareStatement(sql);
                pstm.setString(1, d.getNumeroDocumento());
                pstm.setString(2, d.getNombres());
                pstm.setString(3, d.getApellidos());
                pstm.setString(4, d.getTelefono());
                pstm.setString(5, d.getDireccion());
                pstm.setString(6, String.valueOf(d.getSexo()));
                
                int filas = pstm.executeUpdate();
                if (filas > 0) {
                    rs = pstm.getGeneratedKeys();
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            } catch (Exception ex) {
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
            String sql = "UPDATE tb_persona SET nom_per=?, ape_per=?, tel_per=?, dir_per=? WHERE id_per=?";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, d.getNombres());
            pstm.setString(2, d.getApellidos());
            pstm.setString(3, d.getTelefono());
            pstm.setString(4, d.getDireccion());
            pstm.setInt(5, d.getIdPersona());
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Persona> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
            String sql = "SELECT id_per, num_doc_per, nom_per, ape_per, tel_per, dir_per, sexo_per FROM tb_persona WHERE num_doc_per=? AND est_per='1'";
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
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return persona;
    }
}
