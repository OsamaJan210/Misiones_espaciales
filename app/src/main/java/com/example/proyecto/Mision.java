package com.example.proyecto;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Scanner;

import com.example.proyecto.enums.ExperienciaTipo;
import com.example.proyecto.enums.MissionStatus;
import com.example.proyecto.enums.MissionType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "clase"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = MisionColonizacion.class, name = "COLONIZACION"),
    @JsonSubTypes.Type(value = MisionExploracion.class, name = "EXPLORACION"),
    @JsonSubTypes.Type(value = MisionRecoleccion.class, name = "RECOLECCION_DATOS")
})
public abstract class Mision{

    private static List<Mision> misiones = new ArrayList<>();
    protected String nombre;
    protected int duracion;
    protected int prioridad;
    protected MissionStatus estado;
    protected MissionType tipoMision;
    protected EnumMap<ExperienciaTipo, Integer> experienciaRequerida = new EnumMap<>(ExperienciaTipo.class);

    public Mision(List<Mision> misions){
        this.misiones=misions;
    }

    @JsonCreator
    public Mision(
        @JsonProperty("nombre") String nombre,
        @JsonProperty("prioridad") int prioridad) {   
            
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.estado = MissionStatus.PENDIENTE;
        }

    public Mision(){
        
    }

    public  MissionType getMissionType() {
        return tipoMision;
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

    public void setExperiencia(ExperienciaTipo tipoXP, int cantidad){
        experienciaRequerida.put(tipoXP, cantidad);
    }

    public int getExperiencia(ExperienciaTipo tipoXP) {
        return experienciaRequerida.getOrDefault(tipoXP, 0);
    }


    public int getPrioridad() {
        return prioridad;
    }

    public ExperienciaTipo getTipoExperiencia(){
         return experienciaRequerida.keySet().iterator().next();
    }

    public abstract String getExtraData();

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
            boolean tipoMisionIsOk = tipoMision.equals("-") || m.getMissionType().equalsName(tipoMision);
            boolean estadoMisionIsOk = estadoMision.equals("-") || m.getStatus().equalsName(estadoMision);
            boolean rangoMisionIsOK = m.getPrioridad()>=primerRango && m.getPrioridad()<=segundoRango;
            if(tipoMisionIsOk && estadoMisionIsOk && rangoMisionIsOK){
                result.add(m);
            }
        }
        for(Mision r : result){
            System.out.println("\nNombre: "+r.getNombre()+"\nTipo de mision: "+r.getMissionType()+"\nDuración: "+r.getDuracion()+"\nPrioridad: "+r.getPrioridad()+"\nEstado: "+r.getStatus());
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
 
    public static void logTablaMisiones(){
        
        System.out.println("***MISIONES***");
        String[] headers = {"Nombre", "Duración", "Prioridad", "Tipo de misión","Tipo de experiéncia","Estado de la misión","Extra"};
        String[][] data = new String[misiones.size()][7];
        for (int i = 0; i < misiones.size(); i++) {
            Mision mision = misiones.get(i);
            data[i][0] = mision.getNombre();
            data[i][1] = String.valueOf(mision.getDuracion());
            data[i][2] = String.valueOf(mision.getPrioridad());
            data[i][3] = String.valueOf(mision.getMissionType());
            data[i][4] = String.valueOf(mision.getTipoExperiencia());
            data[i][5] = String.valueOf(mision.getStatus());    
            data[i][6] = String.valueOf(mision.getExtraData());
        }

        imprimirTabla(headers, data);
    }

    public static void imprimirTabla(String[] headers, String[][]data){
        
        int[] columnWidths = new int[headers.length];

        // Calcular el ancho de cada columna
        for (int i = 0; i < headers.length; i++) {
            columnWidths[i] = headers[i].length();
            for (String[] row : data) {
                if (row[i].length() > columnWidths[i]) {
                    columnWidths[i] = row[i].length();
                }
            }
        }

        // Imprimir la línea superior
        imprimirLinea(columnWidths);

        // Imprimir los encabezados
        System.out.print("|");
        for (int i = 0; i < headers.length; i++) {
            System.out.printf(" %-"+columnWidths[i]+"s |", headers[i]);
        }
        System.out.println();

        // Línea separadora
        imprimirLinea(columnWidths);

        // Imprimir los datos
        for (String[] row : data) {
            System.out.print("|");
            for (int i = 0; i < row.length; i++) {
                System.out.printf(" %-"+columnWidths[i]+"s |", row[i]);
            }
            System.out.println();
        }

        // Línea inferior
        imprimirLinea(columnWidths);
    }

    public static void imprimirLinea(int[] columnWidths) {
        System.out.print("+");
        for (int width : columnWidths) {
            for (int i = 0; i < width + 2; i++) {
                System.out.print("-");
            }
            System.out.print("+");
        }
        System.out.println();
    }



    public static void logMisiones(){
        System.out.println("\n*****MISIONES*****");
        for (Mision misiones : misiones){
            misiones.logMision();
        }
    }
    public void logMision(){
        System.out.println("\nNombre: "+this.nombre+"\nTipo de mision: "+this.tipoMision+"\nDuración: "+this.duracion+"\nPrioridad: "+this.prioridad+"\nEstado: "+this.estado+"\nTipo de experiencia y cantidad: "+this.experienciaRequerida);
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