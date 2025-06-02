import enums.ExperienciaTipo;
import enums.MissionType;

public class MisionColonizacion extends Mision{

    public MisionColonizacion(String nombre, int duracion, int prioridad, MissionType tipo, ExperienciaTipo experienciaRequerida) {
        super(nombre, duracion, prioridad, tipo, experienciaRequerida);
    }
}