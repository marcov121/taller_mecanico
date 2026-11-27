package memento;

// MEMENTO: guarda una instantanea de los datos editables de una Persona.
public class PersonaMemento {
    private final String nombres;
    private final String apellidos;
    private final String telefono;
    private final String direccion;
    private final char sexo;

    public PersonaMemento(String nombres, String apellidos, String telefono, String direccion, char sexo) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.direccion = direccion;
        this.sexo = sexo;
    }

    public String getNombres() {
        return nombres;
    }
    public String getApellidos() {
        return apellidos;
    }
    public String getTelefono() {
        return telefono;
    }
    public String getDireccion() {
        return direccion;
    }
    public char getSexo() {
        return sexo;
    }
}
