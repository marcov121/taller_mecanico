/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author James
 */
public class Mecanico extends Persona {
    // Liskov: se inicializa siempre para que cualquier Mecanico sea utilizable donde se espera una Persona
    private List<Especialidad> especialidades = new ArrayList<>();
    
    public List<Especialidad> getEspecialidades() { return especialidades; }
    public void setEspecialidades(List<Especialidad> e) { this.especialidades = (e != null) ? e : new ArrayList<>(); }
    
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
    
    // Prototype + Liskov: devuelve un Mecanico (retorno covariante) y copia su propia lista,
    // para que el clon no comparta especialidades con el original. Respeta el contrat de Persona.clone(}.
    @Override
    public Mecanico clone() {
        Mecanico copia = (Mecanico) super.clone();
        copia.especialidades = (this.especialidades != null) ? new ArrayList<>(this.especialidades) : new ArrayList<>();
        return copia;
    }
}