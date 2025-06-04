import java.util.ArrayList;
import java.util.Comparator;
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

        boolean nombreNaveExiste = false;
        for (NavesEspaciales nave: naves ){
            if (nave.getNombre().equalsIgnoreCase(nombre)){
                nombreNaveExiste = true;
                break;
            }
        }
        if (nombreNaveExiste) {
            System.out.println("Error: Ya existe una nove con ese nombre. Registro de nave cancelado.");
            return null;
        }

        System.out.println("Autonomia máxima: ");
        int autonomiaMaxima = scanner.nextInt();

        System.out.println("Autonomia actual: ");
        int autonomiaActual = scanner.nextInt();

        System.out.println("Capacidad de carga: ");
        int capacidadCarga = scanner.nextInt();

        if (autonomiaMaxima <= 0 || autonomiaActual <= 0 || capacidadCarga <= 0){
            System.out.println("Error: La autonomia y carga deben ser valores mayores que 0. Registro cancelado.");
            return null;
        }

        if (autonomiaActual > autonomiaMaxima){
            System.out.println("Error: La autonomía actual no puede ser superior a la máxima. Registro cancelado.");
            return null;
        }

        System.out.println("¿Sensores cientificos? (true/false) ");
        boolean sensoresCientificos = scanner.nextBoolean();

//        System.out.println("Experiencia total: ");
//        int experienciaTotal = scanner.nextInt();

        System.out.println("Experiencia técnica: ");
        int experienciaTecnica = scanner.nextInt();

        System.out.println("Experiencia cientifica: ");
        int experienciaCientifica = scanner.nextInt();

        System.out.println("Experiencia estratégica: ");
        int experienciaEstrategica = scanner.nextInt();
        scanner.nextLine();

        int experienciaTotal=experienciaTecnica+experienciaCientifica+experienciaEstrategica;
        NavesEspaciales nave = new NavesEspaciales(nombre, autonomiaMaxima, autonomiaActual,
        capacidadCarga, sensoresCientificos, experienciaTotal, experienciaTecnica,
        experienciaCientifica, experienciaEstrategica);

        naves.add(nave);
        System.out.println("Nave registrada exitosamente.");

        return nave;
    }

    public static void mostrarEstado(){
        System.out.println("== Estadísticas de naves ==");
        for(NavesEspaciales nave : naves){

            System.out.printf("- %s: Total: %d | Científica: %d | Técnica: %d | \n",
                    nave.getNombre(), nave.getExperienciaTotal(), nave.getExperienciaCientifica(),nave.getExperienciaTecnica());
            System.out.printf("Estratégica: %d\n", nave.getExperienciaEstrategica());
        }


    }
    public static void generarRanking(){
        System.out.println("== Ranking ==");
        naves.sort(Comparator.comparingInt(NavesEspaciales::getExperienciaTotal).reversed());
        for(NavesEspaciales nave : naves){
            System.out.println("- Nombre: " + nave.getNombre()+"- Experiencia total: "+ nave.getExperienciaTotal());
        }


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

    public static void logNaves(){
        System.out.println("******NAVES*****");
        for (NavesEspaciales naves : naves){
            naves.logNave();
        }
    }
    public void logNave(){
        System.out.println("\nNombre: "+this.nombre+"\nAutonomia máxima: "+this.autonomiaMaxima+"\nAutonomia actual: "+this.autonomiaActual+"\nCapacidad de carga: "+this.capacidadCarga+"\nSensores cientificos: "+this.sensoresCientificos+"\nExperiencia total: "+this.experienciaTotal+"\nExperiencia técnica: "+this.experienciaTecnica+"\nExperiencia científica: "+this.experienciaCientifica+"\nExperiencia estratégica: "+this.experienciaEstrategica);
    }

    @Override
    public String toString() {
        return "NavesEspaciales{" +
                "nombre='" + nombre + '\'' +
                ", autonomiaMaxima=" + autonomiaMaxima +
                ", autonomiaActual=" + autonomiaActual +
                ", capacidadCarga=" + capacidadCarga +
                ", sensoresCientificos=" + sensoresCientificos +
                ", experienciaTotal=" + experienciaTotal +
                ", experienciaTecnica=" + experienciaTecnica +
                ", experienciaCientifica=" + experienciaCientifica +
                ", experienciaEstrategica=" + experienciaEstrategica +
                '}';
    }

    public static List<NavesEspaciales> getNaves() {
        return naves;
    }
}