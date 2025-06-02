import java.util.ArrayList;
import java.util.List;

import enums.ExperienciaTipo;
import enums.MissionType;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Simulador de Misiones Espaciales!");
        List<NavesEspaciales> naves = new ArrayList<>();
        List<Mision> misiones = new ArrayList<>();

        NavesEspaciales nave1 = new NavesEspaciales("Omega", 1500, 1500, 700, true,0,0,0,0);
        NavesEspaciales nave2 = new NavesEspaciales("Alpha", 900, 900, 600, true,0,0,0,0);
        NavesEspaciales nave3 = new NavesEspaciales("Beta", 1200, 1200, 400, true,0,0,0,0);

        Mision mision1 = new Mision("Orbita Epsilon", 8, 10,MissionType.COLONIZACION,  ExperienciaTipo.ESTRATEGICA);
        Mision mision2 = new Mision("Colonizar Kappa", 4, 9,MissionType.EXPLORACION,  ExperienciaTipo.CIENTIFICA);
        Mision mision3 = new Mision("Escaneo Delta", 6, 1,MissionType.RECOLECCION_DATOS,  ExperienciaTipo.TECNICA);

        misiones.add(mision1);
        misiones.add(mision2);
        misiones.add(mision3);

        naves.add(nave1);
        naves.add(nave2);
        naves.add(nave3);
        


    }
}