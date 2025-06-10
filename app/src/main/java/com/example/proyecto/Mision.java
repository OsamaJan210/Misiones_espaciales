package com.example.proyecto;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Scanner;

import com.example.proyecto.enums.ExperienciaTipo;
import com.example.proyecto.enums.MissionStatus;
import com.example.proyecto.enums.MissionType;


public abstract class Mision{

    private static List<Mision> misiones = new ArrayList<>();
    protected String nombre;
    protected int duracion;
    protected int prioridad;
    protected MissionStatus estado;
    protected MissionType tipo;
    protected EnumMap<ExperienciaTipo, Integer> experienciaRequerida = new EnumMap<>(ExperienciaTipo.class);

    public Mision(){
    }

    public  MissionType getMissionType() {
        return tipo;
    }

    public Mision(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.estado = MissionStatus.PENDIENTE;
    }

    public MissionStatus getStatus(){
        return estado;
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

    public static Mision registrarMision(Scanner scanner){
        scanner.nextLine();
        boolean nombreExistente = false;
        boolean prioridadExistente = false;
        boolean misionValida = true;
        String nombre;
        int prioridad;
        int opcion;
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
        mision.acabarDeRegistrarDatos(nombre, prioridad, mision.estado);
        scanner.nextLine();

        System.out.println("\nMision añadida correctamente\n");
        misiones.add(mision);
        return mision;
    }

    public static List<Mision> buscarMisiones(Scanner scanner){
        scanner.nextLine();
        System.out.println("Indica el tipo de misión que quieres buscar, si no te importa el tipo, escribe '-' (Exploración, Recolección, Colonización): ");
        String tipoMision = scanner.nextLine();
        System.out.println("Indica el estado de la misión que quieres buscar, si no quieres buscar por estado escribe '-'(Completada, Pendiente)");
        String estadoMision = scanner.nextLine();
        System.out.println("Indica un rango de inicio de prioridad de las misiones: ");
        int primerRango = scanner.nextInt();
        System.out.println("indica el segundo rango de prioridad de las misiones: ");
        int segundoRango = scanner.nextInt();
        List<Mision> result = new ArrayList<>();
        for (Mision m : misiones){
            boolean tipoMisionIsOk = tipoMision.equals("-") || m.getTipo().equalsName(tipoMision);
            boolean estadoMisionIsOk = estadoMision.equals("-") || m.getStatus().equalsName(estadoMision);
            boolean rangoMisionIsOK = m.getPrioridad()>=primerRango && m.getPrioridad()<=segundoRango;
            if(tipoMisionIsOk && estadoMisionIsOk && rangoMisionIsOK){
                result.add(m);
            }
        }
        for(Mision r : result){
            System.out.println("\nNombre: "+r.getNombre()+"\nTipo de mision: "+r.getTipo()+"\nDuración: "+r.getDuracion()+"\nPrioridad: "+r.getPrioridad()+"\nEstado: "+r.getStatus());
        }
        if(result.isEmpty())
            System.out.println("No se encontró ninguna misión con esos parametros.");
        return result;

    }
   /* public static Mision buscarMisiones(Scanner scanner){

        scanner.nextLine();
        System.out.println("Ingrese el nombre de la misión a buscar: ");
        String nombreMision = scanner.nextLine();

        for (Mision m : misiones){
            if (m.getNombre().equalsIgnoreCase(nombreMision)){
                System.out.println("Misión encontrada: ");
                m.logMision();
                return m;
            }
        }

        System.out.println("No se encontró ninguna misión con ese nombre.");
        return null;

    }*/

    public static void logMisiones(){
        System.out.println("\n*****MISIONES*****");
        for (Mision misiones : misiones){
            misiones.logMision();
        }
    }
    public void logMision(){
        System.out.println("\nNombre: "+this.nombre+"\nTipo de mision: "+this.tipo+"\nDuración: "+this.duracion+"\nPrioridad: "+this.prioridad+"\nEstado: "+this.estado+"\nTipo de experiencia y cantidad: "+this.experienciaRequerida);
    }

    public static void generarMisiones() {
        Mision mision1 = new MisionExploracion("Mision Exploracion", 2, 10, MissionType.EXPLORACION, ExperienciaTipo.CIENTIFICA, 4, 500);
        Mision mision2 = new MisionExploracion("a", 4, 20, MissionType.EXPLORACION, ExperienciaTipo.CIENTIFICA, 1, 500);
        Mision mision3 = new MisionColonizacion("Mision Colonización", 1, 40, MissionType.COLONIZACION, ExperienciaTipo.ESTRATEGICA, 5, 1000);
        Mision mision4 = new MisionColonizacion("Mision Colonización 2", 3, 5, MissionType.COLONIZACION, ExperienciaTipo.ESTRATEGICA, 2, 1000);
        Mision mision5 = new MisionRecoleccion("Mision Recolección de datos", 5, 15, MissionType.RECOLECCION_DATOS, ExperienciaTipo.TECNICA, 4, true);
        Mision mision6 = new MisionRecoleccion("Mision Recolección de datos 2", 6, 15, MissionType.RECOLECCION_DATOS, ExperienciaTipo.TECNICA, 1, true);

        misiones.add(mision1);
        misiones.add(mision2);
        misiones.add(mision3);
        misiones.add(mision4);
        misiones.add(mision5);
        misiones.add(mision6);
    }
    //Test de instanseof
    public static Mision test() {
        for(Mision m : misiones){
            if(m instanceof MisionColonizacion){
                MisionColonizacion recolectar = (MisionColonizacion) m;
                System.out.println("***Misión Colonización: "+m.getNombre()+", carga: "+recolectar.getCarga());
            }
            if(m instanceof MisionExploracion){
                MisionExploracion recolectar = (MisionExploracion) m;
                System.out.println("***Misión Exploración: "+m.getNombre()+", autonomía: "+recolectar.getAutonomia());
            }
        }
        return null;
    }
    public static List<Mision> getMisiones() {
        return misiones;
    }

    public MissionType getTipo(){
        return tipo;
    }

    public static void misionesPendientes(){
        System.out.println("== Misiones pendientes ==\n");
        for(Mision m : misiones){
            if(m.getStatus().equals(MissionStatus.PENDIENTE)){
                System.out.println("- "+m.getNombre()+"\n");
            }
        }
    }
    public static void misionesCompletadas(){
        System.out.println("== Misiones completadas ==\n");
        for(Mision m : misiones){
            if(m.getStatus().equals(MissionStatus.COMPLETADA)){
                System.out.println("- "+m.getNombre()+"\n");
            }
        }
    }

    public static void borrarMision(Scanner scanner){
        boolean misionExiste = false;
        scanner.nextLine();
        System.out.println("Escribe el nombre de la misión que quieres eliminar: ");
        String nombre = scanner.nextLine();
        for(Mision m : misiones){
            if(m.getStatus().equals(MissionStatus.PENDIENTE) && nombre.equalsIgnoreCase(m.getNombre())){
                misiones.remove(m);
                System.out.println("\nMision eliminada correctamente.\n");
                misionExiste = true;
                break;
            }
        }
        if(!misionExiste){
            System.out.println("No existe esta misión");
        }
        return;
    }
}