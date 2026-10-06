package models.entities;

public class Servicio {
    private int    idServicio;
    private String descripcion;
    private double precioBase;

    public int    getIdServicio()  { return idServicio; }
    public String getDescripcion() { return descripcion; }
    public double getPrecioBase()  { return precioBase; }

    public void setIdServicio(int    v) { idServicio  = v; }
    public void setDescripcion(String v){ descripcion = v; }
    public void setPrecioBase(double v) { precioBase  = v; }
}
