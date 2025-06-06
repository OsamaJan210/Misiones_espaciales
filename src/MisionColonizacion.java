import java.util.Scanner;

import enums.ExperienciaTipo;
import enums.MissionStatus;
import enums.MissionType;

public class MisionColonizacion extends Mision{
    Scanner scanner = new Scanner(System.in);
    static int carga = 500;
    static int cantidadXP =0;

    public MisionColonizacion(String nombre, int prioridad, int duracion, MissionType tipo, ExperienciaTipo experienciaTipo, int cantidadXP, int carga) {

        super(nombre,prioridad);
        this.duracion = duracion;
        this.tipo = tipo;
        this.setExperiencia(experienciaTipo, cantidadXP);
    }

    public MisionColonizacion(String nombre, int prioridad){

    }

    public int getDuracion(){
        return duracion;
    }
    
    public int getCarga() {
        return carga;
    }

    public int getXP(){
        return cantidadXP;
    }

    @Override
    public void acabarDeRegistrarDatos(String nombre, int prioridad, MissionStatus estado){
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.estado = MissionStatus.PENDIENTE;
        this.tipo = MissionType.COLONIZACION;
        ExperienciaTipo experiencia = ExperienciaTipo.ESTRATEGICA;
        do{
            System.out.println("Indica la cantidad de carga que necesitaría la misión: ");
            carga = scanner.nextInt();
            if(carga<0){
                System.out.println("***ERROR***\nLa carga no puede ser inferior a 0!!!");
            }
        }while(carga<0);
        this.duracion =0;
         do{
            System.out.println("Indica la duración de la mision de Colonización (Mínimo 6h): ");
            this.duracion = scanner.nextInt();
            if(duracion<6){
                System.out.println("***ERROR***\nLa misión no puede durar tan poco!!!");
            }
        }while(duracion<6);
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
