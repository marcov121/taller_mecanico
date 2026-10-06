package models.entities;

public class Producto {
    private int    idProducto;
    private String sku;
    private String descripcion;
    private double precioVenta;
    private int    stock;

    public int    getIdProducto()  { return idProducto; }
    public String getSku()         { return sku; }
    public String getDescripcion() { return descripcion; }
    public double getPrecioVenta() { return precioVenta; }
    public int    getStock()       { return stock; }

    public void setIdProducto(int    v) { idProducto  = v; }
    public void setSku(String v)        { sku         = v; }
    public void setDescripcion(String v){ descripcion = v; }
    public void setPrecioVenta(double v){ precioVenta = v; }
    public void setStock(int v)         { stock       = v; }
}
