/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.entities;

// Patrón Prototype: crea una copia del objeto a partir de sí mismo
public class Persona implements Cloneable {
    
    protected int idPersona;
    protected String numeroDocumento;
    protected String nombres, apellidos, telefono, direccion;
    protected char sexo, estado; 

    public Persona() {
    }
    
    public Persona(int idPersona, String numeroDocumento, String nombres, String apellidos, String telefono, String direccion, char sexo) {
        this.idPersona = idPersona;
        this.numeroDocumento = numeroDocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.direccion = direccion;
        this.sexo = sexo;
        this.estado = '1';
    }
    
    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }
    
 
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
    
    @Override
    public Persona clone() {
        try {
            return (Persona) super.clone(); // campos primitivos/String (inmutables)
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
