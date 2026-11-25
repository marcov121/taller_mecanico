/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package observer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class OrdenSujeto implements Sujeto {
    private static final OrdenSujeto INSTANCIA = new OrdenSujeto();
    
    private final List<Observador> observadores = new CopyOnWriteArrayList<>();
    private String evento;
    private int idOrden;
    
    private OrdenSujeto() {}
    
    public static OrdenSujeto getInstancia() {
        return INSTANCIA;
    }

    @Override
    public void registrar(Observador o) {
        if (!observadores.contains(o)) observadores.add(o);
    }

    @Override
    public void quitar(Observador o) {
        observadores.remove(o);
    }

    @Override
    public void notificar() {
        for (Observador o : observadores) {
            o.actualizar(evento, idOrden);
        }
    }
    
    public void cambiarOrden(String evento, int idOrden) {
        this.evento = evento;
        this.idOrden = idOrden;
        notificar();
    }
}
