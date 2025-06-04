import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Scanner;

import enums.ExperienciaTipo;
import enums.MissionStatus;
import enums.MissionType;

public abstract class Mision{

    static List<Mision> misiones = new ArrayList<>();
    protected String nombre;
    protected int duracion;
    protected int prioridad;
    protected static MissionStatus estado;
    protected MissionType tipo;
    protected EnumMap<ExperienciaTipo, Integer> experienciaRequerida = new EnumMap<>(ExperienciaTipo.class);

    public Mision(){
    }

    public Mision(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.estado = MissionStatus.PENDIENTE;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setExperiencia(ExperienciaTipo tipo, int cantidad){
        experienciaRequerida.put(tipo, cantidad);
    }

    public int getExperiencia(ExperienciaTipo tipo) {
        return experienciaRequerida.getOrDefault(tipo, 0);
    }


    public int getPrioridad() {
        return prioridad;
    }
    public abstract void acabarDeRegistrarDatos(String nombre, int prioridad, MissionStatus estado);

    public static Mision registrarMision(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("Prioridad: ");
        int prioridad = scanner.nextInt();

        System.out.println("Tipo de mision: 1)Colonización, 2)Exploración, 3)Recolección de datos): ");
        int opcion = scanner.nextInt();
        Mision mision;
        if (opcion == 1) {
            mision = new MisionColonizacion(nombre, prioridad);
        }
        else if(opcion ==2){
            mision = new MisionExploracion(nombre, prioridad);
        }
        else{
            mision = new MisionRecoleccion(nombre, prioridad);
        }
        mision.acabarDeRegistrarDatos(nombre, prioridad, estado);
        scanner.nextLine();

        System.out.println("\nMision añadida correctamente\n");
        misiones.add(mision);
        return mision;
    }

    public static Mision buscarMisiones(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre de la misión a buscar: ");
        String nombreMision = scanner.nextLine();

        for (Mision m : misiones){
            System.out.println("Nombre mision: "+m.getNombre());
            if (m.getNombre().equalsIgnoreCase(nombreMision)){
                System.out.println("Misión encontrada: ");
                m.logMision();
                return m;
            }
        }

        System.out.println("No se encontró ninguna misión con ese nombre.");
        return null;

    }

    public static void logMisiones(){
        System.out.println("*****MISIONES*****");
        for (Mision misiones : misiones){
            misiones.logMision();
        }
    }
    public void logMision(){
        System.out.println("\nNombre: "+this.nombre+"\nTipo de mision: "+this.tipo+"\nDuración: "+this.duracion+"\nPrioridad: "+this.prioridad+"\nEstado: "+this.estado+"\nTipo de experiencia y cantidad: "+this.experienciaRequerida);
    }
}