/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package state;

/**
 *
 * @author marco
 */
public class CanceladaEstado implements EstadoOrden {
    @Override
    public String getNombre() {
        return "Cancelada";
    }

    @Override
    public boolean puedeFinalizar() {
        return false;
    }

    @Override
    public boolean puedeCancelar() {
        return false;
    }
    
}
