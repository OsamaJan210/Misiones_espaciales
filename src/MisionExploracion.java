import java.util.Scanner;

import enums.ExperienciaTipo;
import enums.MissionStatus;
import enums.MissionType;

public class MisionExploracion extends Mision{
    Scanner scanner = new Scanner(System.in);
    static int autonomiaMision = 1000;
    static int cantidadXP = 0;

    public MisionExploracion(String nombre, int prioridad, int duracion, MissionType tipo, ExperienciaTipo experienciaTipo, int cantidadXP, int autonomiaMision) {
        super(nombre,prioridad);
        this.duracion = duracion;
        this.tipo = tipo;
        this.setExperiencia(experienciaTipo, cantidadXP);
    }

    public MisionExploracion(String nombre, int prioridad){

    }

    public int getAutonomia(){
        return autonomiaMision;
    }

    public int getDuracion(){
        return duracion;
    }

    public int getXP(){
        return cantidadXP;
    }
    
    @Override
    public void acabarDeRegistrarDatos(String nombre, int prioridad, MissionStatus estado){
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.estado = MissionStatus.PENDIENTE;
        this.tipo = MissionType.EXPLORACION;
        ExperienciaTipo experiencia = ExperienciaTipo.CIENTIFICA;
        do{
            System.out.println("Indica la cantidad de autonomía que necesitaría la misión: ");
            autonomiaMision = scanner.nextInt();
            if(autonomiaMision<0){
                System.out.println("***ERROR***\nLa autonomía no puede ser inferior a 0!!!");
            }
        }while(autonomiaMision<0);
        this.duracion = 0;
        do{
            System.out.println("Indica la duración de la mision de Exploración (Mínimo 8h): ");
            duracion = scanner.nextInt();
            if(duracion<8){
                System.out.println("***ERROR***\nLa misión no puede durar tan poco!!!");
            }
        }while(duracion<8);
        do{
            System.out.println("Indica la cantidad de experiencia de exploración que necesitas: ");
            cantidadXP = scanner.nextInt();
            if(cantidadXP<0){
                System.out.println("***ERROR***\n La experiéncia de la misión no puede ser negativa!!!");
            }
        }while(cantidadXP<0);

        setExperiencia(experiencia, cantidadXP);
    }
}
