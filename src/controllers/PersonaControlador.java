/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.List;
import models.entities.Persona;
import models.service.PersonaService;

/**
 *
 * @author marco
 */
public class PersonaControlador {
    private PersonaService personaService;
    
    public PersonaControlador() {
        this.personaService = new PersonaService();
    }
    
    public List<Persona> listarTodos(){
        return personaService.listarTodos();
    }

    public List<Persona> listarClientes() {
        return personaService.listarClientes();
    }
    
    public Persona buscarPorDocumento(String numeroDocumento) {
        return personaService.buscarPorDocumento(numeroDocumento);
    }
    
    public boolean actualizar(Persona persona) {
        return personaService.actualizar(persona);
    }
    
    public boolean eliminar(int idPersona) {
        return personaService.eliminar(idPersona);
    }
    
}
