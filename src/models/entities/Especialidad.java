/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.entities;

/**
 *
 * @author James
 */
public class Especialidad {
    private int idEspecialidad;
    private String descripcion;

    public Especialidad(int idEspecialidad, String descripcion) {
        this.idEspecialidad = idEspecialidad;
        this.descripcion = descripcion;
    }
    public Especialidad() {
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }
    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
