/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import models.entities.Rol;
import models.entities.Usuario;
import views.frmMenuCliente;
import views.frmMenuPrincipal;

// Patrón Factory: la decisión de qué menú crear según el rol está centralizada aquí.
public abstract class MenuFactory {
    protected abstract JFrame crear(Usuario usuario);
    
    private static final Map<Integer, MenuFactory> FABRICAS = new HashMap<>();
    static {
        FABRICAS.put(Rol.CLIENTE, new MenuClienteFactory());
        FABRICAS.put(Rol.ADMINISTRADOR, new MenuAdministradorFactory());
    }
    
    public static JFrame crearMenu(Usuario usuario) {
        if (usuario == null) return new frmMenuPrincipal();
        MenuFactory fabrica = FABRICAS.get(usuario.getIdRol());
        if (fabrica == null) fabrica = FABRICAS.get(Rol.ADMINISTRADOR);
        
        return fabrica.crear(usuario);  // antes crearMenu(usuario)
    }
}

class MenuClienteFactory extends MenuFactory {
    @Override
    protected JFrame crear(Usuario usuario) {
        return new frmMenuCliente(usuario);
    }
}

class MenuAdministradorFactory extends MenuFactory {
    @Override
    protected JFrame crear(Usuario usuario) {
        return new frmMenuPrincipal(usuario);
    }
}