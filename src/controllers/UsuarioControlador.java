/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import models.dao.PersonaDao;
import models.dao.UsuarioDao;
import java.sql.Connection;
import java.sql.SQLException;
import models.entities.Persona;
import models.entities.Usuario;
import models.service.UsuarioService;
import utils.MySqlConexion;
import utils.Utils;

/**
 *
 * @author James
 */
public class UsuarioControlador {

    private UsuarioService usuarioService;

    public UsuarioControlador(){
        this.usuarioService = new UsuarioService();
    }

    public Usuario autenticar(String nombreUsuario, String passwordPlano) {
        return usuarioService.validarLogin(nombreUsuario, passwordPlano);
    }

    public boolean registrar(Persona p, Usuario u) {
        try {
            // Hasheamos la contraseña antes de procesar el registro (Lógica de control/seguridad)
            u.setPasswordHash(Utils.hashearPassword(u.getPasswordHash()));
            
            // Delegamos TODA la transacción al servicio
            usuarioService.registrarUsuario(p, u);
            
            return true; // Si no lanzó ninguna excepción, el registro fue exitoso
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Si algo falló, le avisa a la vista que no se pudo registrar
        }
    }
}
