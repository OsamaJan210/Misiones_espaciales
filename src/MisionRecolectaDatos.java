import enums.ExperienciaTipo;
import enums.MissionType;

public class MisionRecolectaDatos extends Mision{

    public MisionRecolectaDatos(String nombre, int duracion, int prioridad, MissionType tipo, ExperienciaTipo experienciaRequerida) {
        super(nombre, duracion, prioridad, tipo, experienciaRequerida);
    }
}
