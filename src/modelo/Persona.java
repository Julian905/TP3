package modelo;

public class Persona {

    private String nombre;
    private String rol;
    private int puntaje;

    public Persona(String nombre, String rol, int puntaje) {
        this.nombre = nombre;
        this.rol = rol;
        this.puntaje = puntaje;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRol() {
        return rol;
    }

    public int getPuntaje() {
        return puntaje;
    }

    @Override
    public String toString() {
        return nombre + " - " + rol + " - " + puntaje;
    }
}