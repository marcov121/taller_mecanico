package models.entities;

public class ReporteClienteItem {
    private String numeroDocumento;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String direccion;
    private int totalVehiculos;

    public ReporteClienteItem() {}

    public String getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public int getTotalVehiculos() { return totalVehiculos; }
    public void setTotalVehiculos(int totalVehiculos) { this.totalVehiculos = totalVehiculos; }
}
