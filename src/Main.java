import enums.ExperienciaTipo;
import enums.MissionType;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Simulador de Misiones Espaciales!");

        Mision mision=new Mision("OSama",10,10, MissionType.COLONIZACION, ExperienciaTipo.CIENTIFICA);
        System.out.println(mision.toString());
    }
}