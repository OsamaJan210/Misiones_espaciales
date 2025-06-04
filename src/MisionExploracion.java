import java.util.Scanner;

import enums.ExperienciaTipo;
import enums.MissionType;

public class MisionExploracion extends Mision{
    Scanner scanner = new Scanner(System.in);

    public MisionExploracion(String nombre, int prioridad) {

        super(nombre,prioridad);
    }

    @Override
    public void acabarDeRegistrarDatos(){
        MissionType tipo = MissionType.EXPLORACION;
        ExperienciaTipo experiencia = ExperienciaTipo.CIENTIFICA;
        do{
            System.out.println("Indica la duración de la mision de Exploración (Mínimo 8h): ");
            int duracion = scanner.nextInt();
            if(duracion<8){
                System.out.println("***ERROR***\nLa misión no puede durar tan poco!!!");
            }
        }while(duracion<8);
        System.out.println("Indica la cantidad de experiencia de exploración que necesita: ");
        int cantidadXP = scanner.nextInt();

        scanner.close();
    }
}
