import java.util.ArrayList;
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
    protected MissionStatus estado;
    protected MissionType tipo;
    protected ExperienciaTipo experienciaRequerida;

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

    public int getPrioridad() {
        return prioridad;
    }
    public abstract void acabarDeRegistrarDatos();

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
        mision.acabarDeRegistrarDatos();
        scanner.nextLine();

        System.out.println("\nMision añadida correctamente\n");
        scanner.close();
        return mision;
    }
    public static void logMisiones(){
        for (Mision misiones : misiones){
            misiones.logMision();
        }
    }
    public void logMision(){
        System.out.println("Nombre: "+this.nombre+"\nTipo de mision: "+this.tipo+"\nDuración: "+this.duracion+"\nPrioridad: "+this.prioridad+"\nEstado: "+this.estado);
    }
}