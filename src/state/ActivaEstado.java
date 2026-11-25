package state;

// ConcreteState: Activa, solo se puede cancelar
public class ActivaEstado implements EstadoOrden {
    @Override
    public String getNombre() {
        return "Activa";
    }

    @Override
    public boolean puedeFinalizar() {
        return false;
    }

    @Override
    public boolean puedeCancelar() {
        return true;
    }
}
