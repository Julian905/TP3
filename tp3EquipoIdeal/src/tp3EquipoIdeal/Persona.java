package tp3EquipoIdeal;

import java.util.Objects;

public class Persona {
    private String nombre;
    private Rol rol;
    private int calificacion;

    public Persona(String nombre, Rol rol, int calificacion) {
        this.nombre = nombre;
        this.rol = rol;
        this.calificacion = calificacion;
    }

    public String getNombre() { 
        return nombre; 
    }
    
    public Rol getRol() { 
        return rol; 
    }
    
    public int getCalificacion() { 
        return calificacion; 
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return nombre.equals(persona.nombre);
    }
    
    @Override
    public int hashCode() { 
        return Objects.hash(nombre); 
    }
    
    @Override
    public String toString() { 
        return nombre + " (" + rol + ") [" + calificacion + "]"; 
    }
}
