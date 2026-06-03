package principal;

import java.util.ArrayList;

import algoritmo.BuscadorEquipo;
import algoritmo.HiloBusqueda;
import modelo.Incompatibilidad;
import modelo.Persona;
import modelo.Requerimiento;

public class Main {

    public static void main(String[] args) {

        Persona p1 = new Persona("Juan", "Lider", 5);
        Persona p2 = new Persona("Ana", "Arquitecto", 4);
        Persona p3 = new Persona("Pedro", "Programador", 5);
        Persona p4 = new Persona("Luis", "Programador", 10);
        Persona p5 = new Persona("Maria", "Tester", 4);

        ArrayList<Persona> personas = new ArrayList<>();

        personas.add(p1);
        personas.add(p2);
        personas.add(p3);
        personas.add(p4);
        personas.add(p5);

        ArrayList<Incompatibilidad> incompatibilidades = new ArrayList<>();

        incompatibilidades.add(new Incompatibilidad(p2, p3));

        Requerimiento req = new Requerimiento(1, 1, 1, 1);

        BuscadorEquipo buscador =
                new BuscadorEquipo(personas, incompatibilidades, req);

        HiloBusqueda hilo = new HiloBusqueda(buscador);

        hilo.start();
    }
}