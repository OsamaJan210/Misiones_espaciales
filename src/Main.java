import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import enums.ExperienciaTipo;
import enums.MissionType;

public class Main {
    public static void main(String[] args) {
        //List<NavesEspaciales> naves = new ArrayList<>();
        //List<Mision> misiones = new ArrayList<>();
        
        Scanner scanner = new Scanner(System.in);

        /*NavesEspaciales nave1 = new NavesEspaciales("Omega", 1500, 1500, 700, true,0,0,0,0);
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
        naves.add(nave3);*/
        int opcion = 0;
        do{
            System.out.println("\n*****Bienvenido al simulador espacial*****\n\n¿Que deseas hacer?\n\n1)Registrar Mision\n2)Registar Nave\n3)Simular un ciclo\n4)Mostrar estado general\n5)Buscar Mision\n6)Ranking de naves\n7)Salir");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                  Mision.registrarMision();
                break;
                case 2:
                  //  registarNave();
                break;
                case 3:
                   // simularCiclo();
                break;
                case 4:
                  //  mostrarEstado();
                break;
                case 5:
                  //  buscarMisiones();
                break;
                case 6:
                  //  generarRanking();
                break;
                case 7:
                    System.out.println("Adios!");
                break;
            }
        }while (opcion!=7);
        //Logs para verificar el funcionamiento
        Mision.logMisiones();

        scanner.close();
        }
}