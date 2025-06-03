import java.util.Scanner;

import enums.ExperienciaTipo;
import enums.MissionType;

public class MisionRecoleccion extends Mision{
    Scanner scanner = new Scanner(System.in);

    public MisionRecoleccion(String nombre, int prioridad) {

        super(nombre,prioridad);
    }

    @Override
    public void acabarDeRegistrarDatos(){
        MissionType tipo = MissionType.RECOLECCION_DATOS;
        ExperienciaTipo experiencia = ExperienciaTipo.TECNICA;
        do{
            System.out.println("Indica la duración de la mision de Recolección de datos (Entre 4h y 8h): ");
            int duracion = scanner.nextInt();
            if(duracion<4){
                System.out.println("***ERROR***\nLa misión no puede durar tan poco!!!");
            }
            else if(duracion>8){
                System.out.println("***ERROR***\\nLa misión no puede durar tanto tiempo!!!");
            }
        }while(duracion<4 && duracion>8);
        System.out.println("Indica la cantidad de experiencia de recolección que necesita: ");
        int cantidadXP = scanner.nextInt();

        scanner.close();
    }
}
