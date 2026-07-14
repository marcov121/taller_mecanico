/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.entities;

import models.entities.Especialidad;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author James
 */
public class Mecanico extends Persona {
    
    private List<Especialidad> especialidades;
    
    public Mecanico() {
        super();
    }
    
    public Mecanico(int idPersona, String numeroDocumento, String nombres,
                     String apellidos, String telefono, String direccion, char sexo) {
        super(idPersona, numeroDocumento, nombres, apellidos, telefono, direccion, sexo);
        this.especialidades = new ArrayList<>();
    }

    @Override
    public String toString() {
        return nombres + " " + apellidos;
    }
}
