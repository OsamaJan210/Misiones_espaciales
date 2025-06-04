import java.util.Scanner;

import enums.ExperienciaTipo;
import enums.MissionStatus;
import enums.MissionType;

public class MisionColonizacion extends Mision{
    Scanner scanner = new Scanner(System.in);

    public MisionColonizacion(String nombre, int prioridad, int duracion, MissionType tipo, ExperienciaTipo experienciaTipo, int cantidadXP) {

        super(nombre,prioridad);
        this.duracion = duracion;
        this.tipo = tipo;
        this.setExperiencia(experienciaTipo, cantidadXP);
    }

    public MisionColonizacion(String nombre, int prioridad){

    }

    @Override
    public void acabarDeRegistrarDatos(String nombre, int prioridad, MissionStatus estado){
        this.nombre = nombre;
        this.prioridad = prioridad;
        Mision.estado = estado;
        this.tipo = MissionType.COLONIZACION;
        ExperienciaTipo experiencia = ExperienciaTipo.ESTRATEGICA;
        this.duracion =0;
         do{
            System.out.println("Indica la duración de la mision de Colonización (Mínimo 6h): ");
            this.duracion = scanner.nextInt();
            if(duracion<6){
                System.out.println("***ERROR***\nLa misión no puede durar tan poco!!!");
            }
        }while(duracion<6);
        int cantidadXP =0;
        do{
            System.out.println("Indica la cantidad de experiencia estratégica que necesitas: ");
            cantidadXP = scanner.nextInt();
            if(cantidadXP<0){
                System.out.println("***ERROR***\n La experiéncia de la misión no puede ser negativa!!!");
            }
        }while(cantidadXP<0);


        setExperiencia(experiencia, cantidadXP);
    }
    
}
