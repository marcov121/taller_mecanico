package models.entities;

public class ReporteMecanicoItem {
    private String nombres;
    private String apellidos;
    private int totalOrdenes;
    private int ordenesFinalizadas;
    private double totalFacturado;

    public ReporteMecanicoItem() {}

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public int getTotalOrdenes() { return totalOrdenes; }
    public void setTotalOrdenes(int totalOrdenes) { this.totalOrdenes = totalOrdenes; }
    public int getOrdenesFinalizadas() { return ordenesFinalizadas; }
    public void setOrdenesFinalizadas(int ordenesFinalizadas) { this.ordenesFinalizadas = ordenesFinalizadas; }
    public double getTotalFacturado() { return totalFacturado; }
    public void setTotalFacturado(double totalFacturado) { this.totalFacturado = totalFacturado; }
}
