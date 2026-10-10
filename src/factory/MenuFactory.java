/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import javax.swing.JFrame;
import models.entities.Rol;
import models.entities.Usuario;
import views.frmMenuCliente;
import views.frmMenuPrincipal;

// Patrón Factory: la decisión de qué menú crear según el rol está centralizada aquí.
public final class MenuFactory {
    private MenuFactory() {}
    public static JFrame crearMenu(Usuario usuario) {
        if (usuario == null) return new frmMenuPrincipal();
        switch (usuario.getIdRol()) {
            case Rol.CLIENTE: return new frmMenuCliente(usuario);
            case Rol.ADMINISTRADOR:
            default: return new frmMenuPrincipal(usuario);
        }
    }
}
