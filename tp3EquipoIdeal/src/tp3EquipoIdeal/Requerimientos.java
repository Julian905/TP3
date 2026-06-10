package tp3EquipoIdeal;

import java.util.HashMap;
import java.util.Map;

public class Requerimientos {
    private Map<Rol, Integer> cupos;

    public Requerimientos() {
        this.cupos = new HashMap<>();
    }

    public void setCupo(Rol rol, int cantidad) {
        cupos.put(rol, cantidad);
    }

    public int getCupo(Rol rol) {
        return cupos.getOrDefault(rol, 0);
    }

    public boolean estaCompleto(Map<Rol, Integer> conteoActual) {
        for (Rol rol : Rol.values()) {
            if (conteoActual.getOrDefault(rol, 0) != getCupo(rol)) {
                return false;
            }
        }
        return true;
    }
}
