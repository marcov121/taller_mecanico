/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package observer;

import java.time.LocalDateTime;
import utils.SesionUsuario;

public class AuditoriaObserver implements Observador {
    @Override
    public void actualizar(String evento, int idOrden) {
        System.out.println("[AUDITORIA] " + LocalDateTime.now()
                + " | usuario: " + SesionUsuario.getInstancia().getNombreCompleto()
                + " | orden #" + idOrden + " | " + evento
        );
    }
    
}
