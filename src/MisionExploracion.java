import java.util.Scanner;

import enums.ExperienciaTipo;
import enums.MissionStatus;
import enums.MissionType;

public class MisionExploracion extends Mision{
    Scanner scanner = new Scanner(System.in);

    public MisionExploracion(String nombre, int prioridad, int duracion, MissionType tipo, ExperienciaTipo experienciaTipo, int cantidadXP) {

        super(nombre,prioridad);
    }

    public MisionExploracion(String nombre, int prioridad){

    }

    @Override
    public void acabarDeRegistrarDatos(String nombre, int prioridad, MissionStatus estado){
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.estado = estado;
        this.tipo = MissionType.EXPLORACION;
        ExperienciaTipo experiencia = ExperienciaTipo.CIENTIFICA;
        this.duracion = 0;
        do{
            System.out.println("Indica la duración de la mision de Exploración (Mínimo 8h): ");
            duracion = scanner.nextInt();
            if(duracion<8){
                System.out.println("***ERROR***\nLa misión no puede durar tan poco!!!");
            }
        }while(duracion<8);
        System.out.println("Indica la cantidad de experiencia de exploración que necesita: ");
        int cantidadXP = scanner.nextInt();

        setExperiencia(experiencia, cantidadXP);
    }
}
