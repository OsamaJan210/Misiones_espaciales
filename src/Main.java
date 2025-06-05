import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        testRegistroNaveExitosa();
        expTotalCorrecta();
        testNaveDuplicada();
        rankingOrdenaCorrectamente();

        Scanner scanner = new Scanner(System.in);

        int opcion = 0;
        do{
            System.out.println("\n*****Bienvenido al simulador espacial*****\n\n"+
            "¿Que deseas hacer?\n\n"+
            "1)Registrar Mision\n"+
            "2)Registar Nave\n"+
            "3)Simular un ciclo\n"+
            "4)Mostrar estado general\n"+
            "5)Buscar Mision\n"+
            "6)Ranking de naves\n"+
            "7)Generar Naves y misiones\n"+
            "8)Listar Naves y misiones\n"+
            "9)Salir\n"+
            "10)Tests temporales");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                  Mision.registrarMision(scanner);
                break;
                case 2:
                  NavesEspaciales.registrarNave(scanner);
                break;
                case 3:
                  Simulacion.simularCiclo();
                break;
                case 4:
                  NavesEspaciales.mostrarEstado();
                break;
                case 5:
                  Mision.buscarMisiones(scanner);
                break;
                case 6:
                  NavesEspaciales.generarRanking();
                break;
                case 7:
                  Mision.generarMisiones();
                  NavesEspaciales.generarNaves();  
                  System.out.println("\nNaves y Misiones generados correctamente\n");
                break;
                case 8:
                  Mision.logMisiones();
                  NavesEspaciales.logNaves();
                break;
                case 9:
                  System.out.println("Adios!");
                break;
                case 10:
                  Mision.test();
                break;

            }
        }while (opcion!=9);
        scanner.close();
        }

    public static void testRegistroNaveExitosa(){
    NavesEspaciales.getNaves().clear();

    NavesEspaciales nave = new NavesEspaciales("Fragata Plasma", 1000, 800, 500, true, 3, 2, 1);
        NavesEspaciales.getNaves().add(nave);

    if (NavesEspaciales.getNaves().size() == 1 &&
          NavesEspaciales.getNaves().get(0).getNombre().equals("Fragata Plasma")){
          System.out.println("Nave de prueba registrada con éxito");
        } else {
          System.out.println("Error: Nave no registrada correctamente");
        }
    }

    public static void expTotalCorrecta(){
        NavesEspaciales nave = new NavesEspaciales("Bellatrix", 1200, 900, 400, false, 4, 3, 2);
        if (nave.getExperienciaTotal() == 9) {
            System.out.println("Experiencia total calculada correctamente.");
        } else {
            System.out.println("Error: Experiencia total calculada incorrectamente");
        }
    }


    public static void testNaveDuplicada(){
        NavesEspaciales.getNaves().clear();
        NavesEspaciales nave1 = new NavesEspaciales("Orion", 1000, 800, 500, true, 1, 1, 1);
        NavesEspaciales nave2 = new NavesEspaciales("Orion", 900, 700, 300, true, 1, 1, 1);
    
        NavesEspaciales.getNaves().add(nave1);
        boolean duplicada = false;
        for (NavesEspaciales n :NavesEspaciales.getNaves()){
            if (n.getNombre().equalsIgnoreCase(nave2.getNombre())){
                duplicada = true;
                break;
            }
        }

        if (duplicada){
            System.out.println("Test de los duplicados pasado con éxito");
        } else {
            System.out.println("Error: Test duplicados fallido");
        }
    
    }

    public static void rankingOrdenaCorrectamente(){
        NavesEspaciales.getNaves().clear();

        NavesEspaciales nave1 = new NavesEspaciales("A", 500, 500, 500, true, 1, 1, 1);
        NavesEspaciales nave2 = new NavesEspaciales("B", 500, 500, 500, true, 3, 3, 3);
        NavesEspaciales nave3 = new NavesEspaciales("C", 500, 500, 500, false, 2, 2, 2);

        NavesEspaciales.getNaves().add(nave1);
        NavesEspaciales.getNaves().add(nave2);
        NavesEspaciales.getNaves().add(nave3);

        NavesEspaciales.getNaves().sort((a, b) -> Integer.compare(b.getExperienciaTotal(), a.getExperienciaTotal()));
        if (NavesEspaciales.getNaves().get(0).getNombre().equals("B") &&
            NavesEspaciales.getNaves().get(1).getNombre().equals("C") &&
            NavesEspaciales.getNaves().get(2).getNombre().equals("A")) {
                System.out.println("Test ranking pasado correctamente");
            } else {
                System.out.println("Error: Tests Ranking fallidos");
            }
    }
}

