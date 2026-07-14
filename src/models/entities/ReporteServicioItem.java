package models.entities;

public class ReporteServicioItem {
    private String servicio;
    private int    vecesSolicitado;
    private int    totalUnidades;
    private double totalIngresos;

    public String getServicio()             { return servicio; }
    public void   setServicio(String v)     { this.servicio = v; }

    public int    getVecesSolicitado()      { return vecesSolicitado; }
    public void   setVecesSolicitado(int v) { this.vecesSolicitado = v; }

    public int    getTotalUnidades()        { return totalUnidades; }
    public void   setTotalUnidades(int v)   { this.totalUnidades = v; }

    public double getTotalIngresos()        { return totalIngresos; }
    public void   setTotalIngresos(double v){ this.totalIngresos = v; }
}
