import enums.ExperienciaTipo;
import enums.MissionType;

public class MisionRecoleccion extends Mision{

    public MisionRecoleccion(String nombre, int prioridad) {

        super(nombre,prioridad);
    }
    @Override
    public void acabarDeRegistrarDatos(){
    }
}
