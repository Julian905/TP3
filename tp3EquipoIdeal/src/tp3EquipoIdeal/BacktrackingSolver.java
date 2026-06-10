package tp3EquipoIdeal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BacktrackingSolver {
    private List<Persona> disponibles;
    private Incompatibilidades incomp;
    private Requerimientos req;
    
    private List<Persona> mejorEquipo;
    private int mejorCalificacion;
    private long casosBase;

    public BacktrackingSolver(List<Persona> disponibles, Incompatibilidades incomp, Requerimientos req) {
        this.disponibles = disponibles;
        this.incomp = incomp;
        this.req = req;
    }

    public SolverResult resolver() {
        long inicio = System.currentTimeMillis();
        mejorEquipo = new ArrayList<>();
        mejorCalificacion = -1;
        casosBase = 0;

        Map<Rol, Integer> conteoRoles = new HashMap<>();
        for (Rol r : Rol.values()) {
            conteoRoles.put(r, 0);
        }

        backtrack(0, new ArrayList<>(), conteoRoles, 0);

        long fin = System.currentTimeMillis();
        return new SolverResult(mejorEquipo, mejorCalificacion, (fin - inicio), casosBase);
    }

    private void backtrack(int indice, List<Persona> actual, Map<Rol, Integer> conteoRoles, int calificacionActual) {
        if (indice == disponibles.size()) {
            casosBase++;
            if (req.estaCompleto(conteoRoles)) {
                if (calificacionActual > mejorCalificacion) {
                    mejorCalificacion = calificacionActual;
                    mejorEquipo = new ArrayList<>(actual);
                }
            }
            return;
        }

        backtrack(indice + 1, actual, conteoRoles, calificacionActual);

        Persona p = disponibles.get(indice);
        int cantidadRolActual = conteoRoles.get(p.getRol());

        if (cantidadRolActual < req.getCupo(p.getRol()) && !incomp.esIncompatibleConEquipo(p, actual)) {
            actual.add(p);
            conteoRoles.put(p.getRol(), cantidadRolActual + 1);
            
            backtrack(indice + 1, actual, conteoRoles, calificacionActual + p.getCalificacion());
            
            actual.remove(actual.size() - 1);
            conteoRoles.put(p.getRol(), cantidadRolActual);
        }
    }
}
