package tp3EquipoIdeal;

import java.util.List;

public class SolverResult {
    public List<Persona> equipoOptimo;
    public int calificacionTotal;
    public long tiempoEjecucionMs;
    public long casosBaseEjecutados;

    public SolverResult(List<Persona> equipo, int calificacion, long tiempo, long casosBase) {
        this.equipoOptimo = equipo;
        this.calificacionTotal = calificacion;
        this.tiempoEjecucionMs = tiempo;
        this.casosBaseEjecutados = casosBase;
    }
}

