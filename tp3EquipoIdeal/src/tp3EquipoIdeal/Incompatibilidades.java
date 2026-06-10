package tp3EquipoIdeal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Incompatibilidades {
    private Map<Persona, Set<Persona>> grafo;

    public Incompatibilidades() {
        this.grafo = new HashMap<>();
    }

    public void agregarIncompatibilidad(Persona p1, Persona p2) {
        grafo.putIfAbsent(p1, new HashSet<>());
        grafo.putIfAbsent(p2, new HashSet<>());
        grafo.get(p1).add(p2);
        grafo.get(p2).add(p1); 
    }

    public boolean sonIncompatibles(Persona p1, Persona p2) {
        return grafo.containsKey(p1) && grafo.get(p1).contains(p2);
    }

    public boolean esIncompatibleConEquipo(Persona p, List<Persona> equipoActual) {
        for (Persona miembro : equipoActual) {
            if (sonIncompatibles(p, miembro)) {
                return true;
            }
        }
        return false;
    }
}
