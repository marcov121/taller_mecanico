/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package observer;

import java.util.HashMap;
import java.util.Map;

public class ContadorOrdenesObserver implements Observador {
    private final Map<String, Integer> conteo = new HashMap<>();

    @Override
    public void actualizar(String evento, int idOrden) {
        conteo.merge(evento, 1, Integer::sum);
        System.out.println("[RESUMEN] registradas=" + total("REGISTRADA")
                + ", finalizadas=" + total("FINALIZADA")
                + ", canceladas=" + total("CANCELADA")
        );
    }
    
    public int total(String evento) {
        return conteo.getOrDefault(evento, 0);
    }
}
