package tp3EquipoIdeal;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import javax.swing.SwingUtilities;

public class AppController {
    
    public void onBotonResolverClick(List<Persona> disponibles, Incompatibilidades inc, Requerimientos req, Consumer<String> pantalla) {
        pantalla.accept("⏳ Iniciando cálculos en background...\n");

        CompletableFuture.supplyAsync(() -> {
            BacktrackingSolver btSolver = new BacktrackingSolver(disponibles, inc, req);
            return btSolver.resolver();
        }).thenAccept(resultadoBT -> {

            SwingUtilities.invokeLater(() -> {
                pantalla.accept("\n--- 🏆 SOLUCIÓN BACKTRACKING (EXACTA) ---");
                mostrarEstadisticasYEquipo(resultadoBT, pantalla);
            });
        });

        CompletableFuture.supplyAsync(() -> {
            HeuristicaSolver hSolver = new HeuristicaSolver(disponibles, inc, req);
            return hSolver.resolver();
        }).thenAccept(resultadoH -> {
            SwingUtilities.invokeLater(() -> {
                pantalla.accept("\n--- ⚡ SOLUCIÓN HEURÍSTICA (APROXIMADA) ---");
                mostrarEstadisticasYEquipo(resultadoH, pantalla);
            });
        });
    }

    private void mostrarEstadisticasYEquipo(SolverResult resultado, Consumer<String> pantalla) {
        pantalla.accept("\n⏱ Tiempo Total: " + resultado.tiempoEjecucionMs + " ms");
        pantalla.accept("🔍 Casos Base Analizados: " + resultado.casosBaseEjecutados);
        pantalla.accept("⭐ Calificación Total: " + resultado.calificacionTotal);
        pantalla.accept("👥 Equipo Seleccionado:");
        
        if (resultado.equipoOptimo.isEmpty()) {
            pantalla.accept("  [No se encontró un equipo que cumpla los requisitos]");
        } else {
            for (Persona p : resultado.equipoOptimo) {
                pantalla.accept("  - " + p.getNombre() + " | " + p.getRol() + " | Nota: " + p.getCalificacion()); 
            }
        }
        pantalla.accept("--------------------------------------------------\n");
    }
}

