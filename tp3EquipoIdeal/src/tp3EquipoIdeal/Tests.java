package tp3EquipoIdeal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class Tests {

    private Incompatibilidades incomp;
    private Requerimientos req;
    private Persona p1, p2, p3, p4;

    @BeforeEach
    public void setup() {
        incomp = new Incompatibilidades();
        req = new Requerimientos();
        
        p1 = new Persona("Ana", Rol.LIDER_DE_PROYECTO, 5);
        p2 = new Persona("Juan", Rol.LIDER_DE_PROYECTO, 4);
        p3 = new Persona("Luis", Rol.PROGRAMADOR, 5);
        p4 = new Persona("Marta", Rol.PROGRAMADOR, 3);
    }

    @Test
    public void testIncompatibilidad() {
        incomp.agregarIncompatibilidad(p1, p3);
        assertTrue(incomp.sonIncompatibles(p1, p3));
        assertTrue(incomp.sonIncompatibles(p3, p1)); 
        assertFalse(incomp.sonIncompatibles(p1, p2));
        
        assertTrue(incomp.esIncompatibleConEquipo(p3, Arrays.asList(p4, p1)));
    }

    @Test
    public void testBacktrackingSolver() {
        req.setCupo(Rol.LIDER_DE_PROYECTO, 1);
        req.setCupo(Rol.PROGRAMADOR, 1);
        
        incomp.agregarIncompatibilidad(p1, p3);
        
        List<Persona> disponibles = Arrays.asList(p1, p2, p3, p4);
        BacktrackingSolver solver = new BacktrackingSolver(disponibles, incomp, req);
        SolverResult resultado = solver.resolver();
        
        assertEquals(9, resultado.calificacionTotal);
        assertTrue(resultado.equipoOptimo.contains(p2));
        assertTrue(resultado.equipoOptimo.contains(p3));
    }
}

