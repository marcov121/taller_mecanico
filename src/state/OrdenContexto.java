package state;

// CONTEXT: la orden tiene un estado y delega las preguntas
public class OrdenContexto {
    private EstadoOrden estado;
    
    public OrdenContexto(String nombreEstado) {
        setEstado(crearEstado(nombreEstado));
    }
    
    public void setEstado(EstadoOrden estado) {
        this.estado = estado;
    }
    
    public String getNombreEstado() {
        return estado.getNombre();
    }
    
    public boolean puedeFinalizar() {
        return estado.puedeFinalizar();
    }
    
    public boolean puedeCancelar() {
        return estado.puedeCancelar();
    }
    
    // Traduce el texto de la BD al objeto estado; un estado desconocido no permite ninguna acción
    private static EstadoOrden crearEstado(String nombre) {
        if (nombre == null) return new BorradorEstado();
        switch (nombre) {
            case "Activa":
                return new ActivaEstado();
            case "En proceso":
                return new EnProcesoEstado();
            case "Finalizada":
                return new FinalizadaEstado();
            case "Entregada":
                return new EntregadaEstado();
            case "Cancelada":
                return new CanceladaEstado();
            default:
                return new BorradorEstado();
        }
    }
}
