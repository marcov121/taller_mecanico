package state;

// ConcreteState: En proceso, se puede finalizar o cancelar
public class EnProcesoEstado implements EstadoOrden {
    @Override
    public String getNombre() {
        return "En proceso";
    }

    @Override
    public boolean puedeFinalizar() {
        return true;
    }

    @Override
    public boolean puedeCancelar() {
        return true;
    }
}
