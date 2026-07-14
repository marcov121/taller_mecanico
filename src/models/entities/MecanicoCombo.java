/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.entities;

/**
 *
 * @author marco
 */
public class MecanicoCombo {
    private int idMecanico;
    private String nombreCompleto;

    public MecanicoCombo() {
    }
    public MecanicoCombo(int idMecanico, String nombreCompleto) {
        this.idMecanico = idMecanico;
        this.nombreCompleto = nombreCompleto;
    }

    public int getIdMecanico() {
        return idMecanico;
    }
    public void setIdMecanico(int idMecanico) {
        this.idMecanico = idMecanico;
    }
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
    @Override
    public String toString() {
        return nombreCompleto;
    }
}
