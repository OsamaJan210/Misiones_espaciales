import java.util.Scanner;

import enums.ExperienciaTipo;
import enums.MissionStatus;
import enums.MissionType;

public class MisionColonizacion extends Mision{

    public MisionColonizacion(String nombre, int prioridad, int duracion, MissionType tipo, ExperienciaTipo experienciaTipo, int cantidadXP) {

        super(nombre,prioridad);
    }

    public MisionColonizacion(String nombre, int prioridad){

    }

    @Override
    public void acabarDeRegistrarDatos(String nombre, int prioridad, MissionStatus estado){
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.estado = estado;
        this.tipo = MissionType.COLONIZACION;
        ExperienciaTipo experiencia = ExperienciaTipo.ESTRATEGICA;
        Scanner scanner = new Scanner(System.in);
        this.duracion =0;
         do{
            System.out.println("Indica la duración de la mision de Colonización (Mínimo 6h): ");
            this.duracion = scanner.nextInt();
            if(duracion<6){
                System.out.println("***ERROR***\nLa misión no puede durar tan poco!!!");
            }
        }while(duracion<6);
        System.out.println("Indica la cantidad de experiencia estratégica que necesitas: ");
        int cantidadXP = scanner.nextInt();

        setExperiencia(experiencia, cantidadXP);
    }
    
}
