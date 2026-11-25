/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package observer;

/**
 *
 * @author marco
 */
public interface Sujeto {
    void registrar(Observador o);
    void quitar(Observador o);
    void notificar();
}
