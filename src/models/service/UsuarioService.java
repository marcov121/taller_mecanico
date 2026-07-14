/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.service;

import models.dao.PersonaDao;
import models.dao.UsuarioDao;
import models.entities.Persona;
import models.entities.Usuario;
import utils.MySqlConexion;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author James
 */
public class UsuarioService {
    private PersonaDao personaDAO = new PersonaDao();
    private UsuarioDao usuarioDAO = new UsuarioDao();

    public void registrarUsuario(Persona persona, Usuario usuario) throws SQLException {
        Connection con = MySqlConexion.getConexion();
        
        try {
            // 1. Desactivar el autocommit para iniciar la transacción manual
            con.setAutoCommit(false);
            
            // 2. Registrar la persona y obtener el ID autogenerado
            int idPersonaGenerado = personaDAO.save(persona, con);
            
            if (idPersonaGenerado == -1) {
                throw new SQLException("No se pudo registrar la persona, abortando transacción.");
            }
            
            // 3. Asignar el ID recuperado al objeto persona
            persona.setIdPersona(idPersonaGenerado);
            
            // 4. Vincular la persona ya identificada al usuario
            usuario.setPersona(persona);
            
            // 5. Registrar el usuario pasando la misma conexión
            int filasUsuario = usuarioDAO.save(usuario, con); 
            
            if (filasUsuario <= 0) {
                throw new SQLException("No se pudo registrar el usuario, abortando transacción.");
            }
            
            // 6. Si todo fue exitoso, confirmar los cambios en MySQL
            con.commit();
            System.out.println("Transacción completada con éxito.");
            
        } catch (SQLException e) {
            // 7. Si algo falló en cualquier punto, deshacer todo en cascada
            if (con != null) {
                con.rollback();
                System.err.println("Error en el registro. Se ejecutó Rollback de seguridad.");
            }
            throw e; // Lanza la excepción hacia el controlador para que sepa que falló
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true); // Siempre restaurar el estado por defecto del pool
                    con.close(); 
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public Usuario validarLogin(String username, String password){
        return usuarioDAO.validarLogin(username, password);
    }
}
