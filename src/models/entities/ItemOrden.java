package models.entities;

import java.util.List;

public class ItemOrden {
    private final int idItem;
    private final int cantidad;
    private final double precioUnitario;

    public ItemOrden(int idItem, int cantidad, double precioUnitario) {
        this.idItem = idItem;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public int getIdItem() {
        return idItem;
    }
    public int getCantidad() {
        return cantidad;
    }
    public double getPrecioUnitario() {
        return precioUnitario;
    }
    
    public double subtotal() {
        return cantidad * precioUnitario;
    }
    
    public static double total(List<ItemOrden> items) {
        return items.stream().mapToDouble(ItemOrden::subtotal).sum();
    }
}
