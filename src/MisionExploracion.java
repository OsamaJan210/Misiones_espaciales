import enums.ExperienciaTipo;
import enums.MissionType;

public class MisionExploracion extends Mision{

    public MisionExploracion(String nombre, int duracion, int prioridad, MissionType tipo,
            ExperienciaTipo experienciaRequerida) {
        super(nombre, duracion, prioridad, tipo, experienciaRequerida);
    }
    
}
