/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.SQLException;
import models.entities.Persona;
import models.entities.Usuario;
import models.service.UsuarioService;

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

    // Responsabilidad Única: se quitó el hasheo de aquí. La contraseña ya llega hasheada
    // desde la vista (igual que en el login); hashearla otra vez generaba un hash del hash
    // y el usuario registrado nunca podría iniciar sesión.
    public boolean registrar(Persona p, Usuario u) {
        try {
            // Delegamos TODA la transacción al servicio
            usuarioService.registrarUsuario(p, u);
            
            return true; // Si no lanzó ninguna excepción, el registro fue exitoso
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Si algo falló, le avisa a la vista que no se pudo registrar
        }
    }
}
