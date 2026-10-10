/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import models.entities.Usuario;

// Patrón Singleton: solo existe una sesión activa a la vez, con acceso encapsulado.
public final class SesionUsuario {
    private static final SesionUsuario INSTANCIA = new SesionUsuario();
    private Usuario usuario;
    private SesionUsuario() {}
    public static SesionUsuario getInstancia() { return INSTANCIA; }
    public void iniciar(Usuario usuario) { this.usuario = usuario; }
    public void cerrar() { this.usuario = null; }
    public Usuario getUsuario() { return usuario; }
    public int getCodigoUsuario() { return usuario != null ? usuario.getPersona().getIdPersona() : 0; }
    public String getNombreCompleto() {
        return usuario != null ? usuario.getPersona().getApellidos() + " " + usuario.getPersona().getNombres() : "";
    }
}
