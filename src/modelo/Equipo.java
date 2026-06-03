package modelo;

import java.util.ArrayList;

public class Equipo {

    private ArrayList<Persona> integrantes;

    public Equipo() {
        integrantes = new ArrayList<>();
    }

    public void agregarPersona(Persona p) {
        integrantes.add(p);
    }

    public void sacarPersona(Persona p) {
        integrantes.remove(p);
    }

    public ArrayList<Persona> getIntegrantes() {
        return integrantes;
    }

    public int getPuntajeTotal() {

        int total = 0;

        for (Persona p : integrantes) {
            total += p.getPuntaje();
        }

        return total;
    }

    public int cantidadPersonas() {
        return integrantes.size();
    }

    public Equipo copiar() {

        Equipo copia = new Equipo();

        for (Persona p : integrantes) {
            copia.agregarPersona(p);
        }

        return copia;
    }

    public int cantidadLideres() {

        int cantidad = 0;

        for (Persona p : integrantes) {
            if (p.getRol().equals("Lider")) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public int cantidadArquitectos() {

        int cantidad = 0;

        for (Persona p : integrantes) {
            if (p.getRol().equals("Arquitecto")) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public int cantidadProgramadores() {

        int cantidad = 0;

        for (Persona p : integrantes) {
            if (p.getRol().equals("Programador")) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public int cantidadTesters() {

        int cantidad = 0;

        for (Persona p : integrantes) {
            if (p.getRol().equals("Tester")) {
                cantidad++;
            }
        }

        return cantidad;
    }
}