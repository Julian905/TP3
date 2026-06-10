package tp3EquipoIdeal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeuristicaSolver {
    private List<Persona> disponibles;
    private Incompatibilidades incomp;
    private Requerimientos req;

    public HeuristicaSolver(List<Persona> disponibles, Incompatibilidades incomp, Requerimientos req) {
        this.disponibles = new ArrayList<>(disponibles);
        this.incomp = incomp;
        this.req = req;
    }

    public SolverResult resolver() {
        long inicio = System.currentTimeMillis();
        
        disponibles.sort((p1, p2) -> Integer.compare(p2.getCalificacion(), p1.getCalificacion()));

        List<Persona> equipo = new ArrayList<>();
        Map<Rol, Integer> conteoRoles = new HashMap<>();
        int calificacionTotal = 0;

        for (Persona p : disponibles) {
            int cantidadRol = conteoRoles.getOrDefault(p.getRol(), 0);
            
            if (cantidadRol < req.getCupo(p.getRol()) && !incomp.esIncompatibleConEquipo(p, equipo)) {
                equipo.add(p);
                conteoRoles.put(p.getRol(), cantidadRol + 1);
                calificacionTotal += p.getCalificacion();
            }
        }

        long fin = System.currentTimeMillis();
        return new SolverResult(equipo, calificacionTotal, (fin - inicio), 1); // Casos base no aplica aquí
    }
}
