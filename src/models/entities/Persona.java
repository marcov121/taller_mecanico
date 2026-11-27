package models.entities;

import memento.PersonaMemento;

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
    
    // Patrón Prototype: crea una copia del objeto a partir de sí mismo.
    @Override
    public Persona clone() {
        try {
            return (Persona) super.clone(); // campos primitivos/String (inmutables)
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
    
    /* PATRON MEMENTO: Persona es el ORIGINATOR. guardar() crea una instantanea de sus datos
        editables y restaurar() vuelve a ellos.
    */
    public PersonaMemento guardar() {
        return new PersonaMemento(nombres, apellidos, telefono, direccion, sexo);
    }
    
    public void restaurar(PersonaMemento m) {
        this.nombres = m.getNombres();
        this.apellidos = m.getApellidos();
        this.telefono = m.getTelefono();
        this.direccion = m.getDireccion();
        this.sexo = m.getSexo();
    }
}
