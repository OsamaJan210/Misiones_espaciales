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
        Mision.estado = MissionStatus.PENDIENTE;
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
        boolean nombreExistente = false;
        boolean prioridadExistente = false;
        boolean misionValida = true;
        String nombre;
        int prioridad;
        int opcion;
        Scanner scanner = new Scanner(System.in);
        do{
            nombreExistente = false;
            System.out.println("Nombre: ");
            nombre = scanner.nextLine();
            for (Mision misiones : misiones){
                if(misiones.getNombre().equalsIgnoreCase(nombre)){
                    System.out.println("***ERROR***\nEl nombre de esta misión ya existe!!!");
                    nombreExistente = true;
                }
            }
        }while(nombreExistente==true);
        do{
            prioridadExistente = false;
            System.out.println("Prioridad: ");
            prioridad = scanner.nextInt();
            for(Mision misiones : misiones){
                if(misiones.getPrioridad()==prioridad){
                    System.out.println("***ERROR***\nEsta prioridad ya la tiene otra misión!!!!");
                    prioridadExistente = true;
                }
            }
        }while (prioridadExistente==true);
        do{
            misionValida = true;
            System.out.println("Tipo de mision: 1)Colonización, 2)Exploración, 3)Recolección de datos): ");
            opcion = scanner.nextInt();
            if(opcion>3 || opcion<1){
                System.out.println("***ERROR***\nSolo hay 3 tipos de misiones!!!");
                misionValida=false;
            }
        }while(misionValida==false);
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

