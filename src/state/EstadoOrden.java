package state;

// STATE: lo que una orden puede hacer depende de su estado actual
public interface EstadoOrden {
    String getNombre();
    boolean puedeFinalizar();
    boolean puedeCancelar();
}
