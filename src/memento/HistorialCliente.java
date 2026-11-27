package memento;

import java.util.ArrayDeque;
import java.util.Deque;

// CARETAKER: guarda los mementos, pero nunca los modifica ni mira lo que contienen
public class HistorialCliente {
    private final Deque<PersonaMemento> mementos = new ArrayDeque<>();
    
    public void agregar(PersonaMemento m) {
        mementos.push(m);
    }
    
    public PersonaMemento ultimo() {
        return mementos.peek();
    }
    
    public void quitarUltimo() {
        mementos.pop();
    }
    
    public boolean estaVacio() {
        return mementos.isEmpty();
    }
    
    public void limpiar() {
        mementos.clear();
    }
}
