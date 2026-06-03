package algoritmo;

import modelo.Equipo;

public class HiloBusqueda extends Thread {

    private BuscadorEquipo buscador;

    public HiloBusqueda(BuscadorEquipo buscador) {
        this.buscador = buscador;
    }

    @Override
    public void run() {

        Equipo resultado = buscador.buscar();

        System.out.println("Equipo encontrado:");
        System.out.println(resultado.getIntegrantes());
        System.out.println("Puntaje total: " + resultado.getPuntajeTotal());
    }
}