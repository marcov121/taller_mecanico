package state;

public class BorradorEstado implements EstadoOrden {
    @Override
    public String getNombre() {
        return "Borrador";
    }

    @Override
    public boolean puedeFinalizar() {
        return false;
    }

    @Override
    public boolean puedeCancelar() {
        return false;
    }
}
