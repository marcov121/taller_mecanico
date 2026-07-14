/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.service;

import java.util.List;
import models.dao.PersonaDao;
import models.entities.Persona;

/**
 *
 * @author marco
 */
public class PersonaService {
    private PersonaDao personaDao = new PersonaDao();
    
    public Persona buscarPorDocumento(String numeroDocumento) {
        return personaDao.findByDocumento(numeroDocumento);
    }
    
    public boolean actualizar(Persona persona) {
        return personaDao.update(persona) > 0;
    }
    
    public boolean eliminar(int idPersona) {
        return personaDao.deleteByCode(idPersona) > 0;
    }

    public List<Persona> listarTodos() {
        return personaDao.findAll();
    }

    public List<Persona> listarClientes() {
        return personaDao.findAllClientes();
    }
}
