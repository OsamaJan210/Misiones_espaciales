import enums.ExperienciaTipo;
import enums.MissionType;

public class MisionRecoleccion extends Mision{

    public MisionRecoleccion(String nombre, int duracion, int prioridad, MissionType tipo,
            ExperienciaTipo experienciaRequerida) {
        super(nombre, duracion, prioridad, tipo, experienciaRequerida);
    }
    
}
