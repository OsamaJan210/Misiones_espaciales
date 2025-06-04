import java.util.Scanner;

public class Simulacion {

    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    Mision.registrarMision();
                    break;
                case 2:
                    NavesEspaciales.registrarNave();
                    break;
                case 3:
                    // simularCiclo();
                    break;
                case 4:
                    Mision.logMisiones();
                    NavesEspaciales.logNaves();
                    break;
                case 5:
                    // buscarMisiones();
                    break;
                case 6:
                    // generarRanking();
                    break;
                case 7:
                    System.out.println("¡Adiós!");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 7);
    }

    private void mostrarMenu() {
        System.out.println("\n***** Bienvenido al Simulador Espacial *****");
        System.out.println("1) Registrar Misión");
        System.out.println("2) Registrar Nave");
        System.out.println("3) Simular un ciclo");
        System.out.println("4) Mostrar estado general");
        System.out.println("5) Buscar misión");
        System.out.println("6) Ranking de naves");
        System.out.println("7) Salir");
        System.out.print("Elige una opción: ");
    }
}
