/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bridge;

import java.io.File;

/**
 *
 * @author marco
 */
public abstract class Reporte {
    protected FormatoReporte formato;
    
    public Reporte(FormatoReporte formato) {
        this.formato = formato;
    }
    
    public abstract File generar() throws Exception;
}
