package algoritmo;

import java.util.ArrayList;

import modelo.Equipo;
import modelo.Incompatibilidad;
import modelo.Persona;
import modelo.Requerimiento;

public class BuscadorEquipo {

    private ArrayList<Persona> personas;
    private ArrayList<Incompatibilidad> incompatibilidades;
    private Requerimiento requerimiento;

    private Equipo mejorEquipo;

    public BuscadorEquipo(ArrayList<Persona> personas,
                          ArrayList<Incompatibilidad> incompatibilidades,
                          Requerimiento requerimiento) {

        this.personas = personas;
        this.incompatibilidades = incompatibilidades;
        this.requerimiento = requerimiento;

        this.mejorEquipo = new Equipo();
    }

    public Equipo buscar() {

        Equipo actual = new Equipo();

        backtracking(actual, 0);

        return mejorEquipo;
    }
    
    private void backtracking(Equipo actual, int indice) {

        if (indice >= personas.size()) {

            if (cumpleRequerimientos(actual)) {

                if (actual.getPuntajeTotal() > mejorEquipo.getPuntajeTotal()) {
                    mejorEquipo = actual.copiar();
                }
            }

            return;
        }

        Persona personaActual = personas.get(indice);

        if (esCompatible(personaActual, actual)) {

            actual.agregarPersona(personaActual);

            backtracking(actual, indice + 1);

            actual.sacarPersona(personaActual);
        }

        backtracking(actual, indice + 1);
    }

    private boolean cumpleRequerimientos(Equipo equipo) {

        return equipo.cantidadLideres() == requerimiento.getLideres()
                && equipo.cantidadArquitectos() == requerimiento.getArquitectos()
                && equipo.cantidadProgramadores() == requerimiento.getProgramadores()
                && equipo.cantidadTesters() == requerimiento.getTesters();
    }

    private boolean esCompatible(Persona persona, Equipo equipo) {

        for (Persona integrante : equipo.getIntegrantes()) {

            for (Incompatibilidad inc : incompatibilidades) {

                if ((inc.getPersona1() == persona && inc.getPersona2() == integrante)
                        || (inc.getPersona2() == persona && inc.getPersona1() == integrante)) {

                    return false;
                }
            }
        }

        return true;
    }
}