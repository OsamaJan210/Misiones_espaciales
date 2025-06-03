import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NavesEspaciales {
    
    private static List<NavesEspaciales> naves = new ArrayList<>();

    private String nombre;
    private int autonomiaMaxima;
    private int autonomiaActual;
    private int capacidadCarga;
    private boolean sensoresCientificos;
    private int experienciaTotal;
    private int experienciaTecnica;
    private int experienciaCientifica;
    private int experienciaEstrategica;

    public NavesEspaciales(String nombre, int autonomiaMaxima, int autonomiaActual, int capacidadCarga, boolean sensoresCientificos, int experienciaTotal, int experienciaTecnica, int experienciaCientifica, int experienciaEstrategica) {
        this.nombre = nombre;
        this.autonomiaMaxima = autonomiaMaxima;
        this.autonomiaActual = autonomiaActual;
        this.capacidadCarga = capacidadCarga;
        this.sensoresCientificos = sensoresCientificos;
        this.experienciaTotal = experienciaTotal;
        this.experienciaTecnica = experienciaTecnica;
        this.experienciaCientifica = experienciaCientifica;
        this.experienciaEstrategica = experienciaEstrategica;
    }

    public static NavesEspaciales registrarNave(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Nombre de la nave: ");
        String nombre = scanner.nextLine();

        System.out.println("Autonomia máxima: ");
        int autonomiaMaxima = scanner.nextInt();

        System.out.println("Autonomia actual: ");
        int autonomiaActual = scanner.nextInt();

        System.out.println("Capacidad de carga: ");
        int capacidadCarga = scanner.nextInt();

        System.out.println("¿Sensores cientificos? (true/false) ");
        boolean sensoresCientificos = scanner.nextBoolean();

        System.out.println("Experiencia total: ");
        int experienciaTotal = scanner.nextInt();

        System.out.println("Experiencia técnica: ");
        int experienciaTecnica = scanner.nextInt();

        System.out.println("Experiencia cientifica: ");
        int experienciaCientifica = scanner.nextInt();

        System.out.println("Experiencia estratégica: ");
        int experienciaEstrategica = scanner.nextInt();
        scanner.nextLine();
        scanner.close();

        NavesEspaciales nave = new NavesEspaciales(nombre, autonomiaMaxima, autonomiaActual,
        capacidadCarga, sensoresCientificos, experienciaTotal, experienciaTecnica,
        experienciaCientifica, experienciaEstrategica);

        naves.add(nave);
        System.out.println("Nave registrada exitosamente.");

        return nave;
    }
    public String getNombre() {
        return nombre;
    }

    public int getAutonomiaMaxima() {
        return autonomiaMaxima;
    }

    public int getAutonomiaActual() {
        return autonomiaActual;
    }

    public int getCapacidadCarga() {
        return capacidadCarga;
    }

    public boolean tieneSensoresCientificos() {
        return sensoresCientificos;
    }

    public int getExperienciaTotal() {
        return experienciaTotal;
    }

    public int getExperienciaTecnica() {
        return experienciaTecnica;
    }

    public int getExperienciaCientifica() {
        return experienciaCientifica;
    }

    public int getExperienciaEstrategica() {
        return experienciaEstrategica;
    }

    public boolean esApta(Mision m){
        if(autonomiaActual < m.getDuracion()) {
            return false;
        }
        switch (m.getTipo()) {
            case   EXPLORACION:
                return autonomiaMaxima > 1000;
        
            case RECOLECCION_DATOS:
                return sensoresCientificos;
            
            case COLONIZACION:
                return capacidadCarga >=500;
        }
        return false;
    }

}