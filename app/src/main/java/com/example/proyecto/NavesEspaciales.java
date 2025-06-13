package com.example.proyecto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class NavesEspaciales {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_ROJO = "\u001B[31m";
    public static final String ANSI_VERDE = "\u001B[32m";
    public static final String ANSI_AZUL = "\u001B[34m";
    
    private  static List<NavesEspaciales> naves = new ArrayList<>();

    private  String nombre;
    private  int autonomiaMaxima;
    private  int autonomiaActual;
    private  int capacidadCarga;
    private  boolean sensoresCientificos;
    private  int experienciaTecnica;
    private  int experienciaCientifica;
    private  int experienciaEstrategica;

    public NavesEspaciales(List<NavesEspaciales> navesEspaciales){
        NavesEspaciales.naves=navesEspaciales;
    }
    @JsonCreator
    public NavesEspaciales(
            @JsonProperty("nombre") String nombre,
            @JsonProperty("autonomiaMaxima") int autonomiaMaxima,
            @JsonProperty("autonomiaActual") int autonomiaActual,
            @JsonProperty("capacidadCarga") int capacidadCarga,
            @JsonProperty(value = "sensoresCientificos", defaultValue = "false") boolean sensoresCientificos, // Optional field
            @JsonProperty("experienciaTecnica") int experienciaTecnica,
            @JsonProperty("experienciaCientifica") int experienciaCientifica,
            @JsonProperty("experienciaEstrategica") int experienciaEstrategica) {        
                
        this.nombre = nombre;
        this.autonomiaMaxima = autonomiaMaxima;
        this.autonomiaActual = autonomiaActual;
        this.capacidadCarga = capacidadCarga;
        this.sensoresCientificos = sensoresCientificos;
        this.experienciaTecnica = experienciaTecnica;
        this.experienciaCientifica = experienciaCientifica;
        this.experienciaEstrategica = experienciaEstrategica;
        

    }

    public static NavesEspaciales registrarNave(Scanner scanner){

        scanner.nextLine();
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
            System.out.println("Error: Ya existe una nave con ese nombre. Registro de nave cancelado.");
            return null;
        }
        int autonomiaMaxima = leerEntero(scanner, "Autonomía máxima: ", 1);
        int autonomiaActual = leerEntero(scanner, "Autonomía actual: ", 0);

        if (autonomiaActual > autonomiaMaxima){
            System.out.println("Error: La autonomía actual no puede ser superior a la máxima. Registro cancelado.");
            return null;
        }

        int capacidadCarga = leerEntero(scanner, "Capacidad de carga: ", 1);

        System.out.println("¿Sensores cientificos? (true/false) ");
        boolean sensoresCientificos = scanner.nextBoolean();
        scanner.nextLine();

        int experienciaTecnica = leerEntero(scanner, "Experiencia técnica: ", 0);
        int experienciaCientifica = leerEntero(scanner, "Experiencia científica: ", 0);
        int experienciaEstrategica = leerEntero(scanner, "Experiencia estratégica: ", 0);
        
        NavesEspaciales nave = new NavesEspaciales(nombre, autonomiaMaxima, autonomiaActual,
        capacidadCarga, sensoresCientificos, experienciaTecnica,
        experienciaCientifica, experienciaEstrategica);

        naves.add(nave);
        System.out.println("Nave registrada exitosamente.");

        return nave;
    }

    //Método extra borrarNave()

    public static void borrarNave(Scanner scanner){
        scanner.nextLine();
        System.out.println("Ingrese el nombre de la nave que quieres eliminar");
        String NombreEliminar = scanner.nextLine();

        boolean naveEncontrada = false;
        for (int i = 0;i < naves.size();i++){
            if (naves.get(i).getNombre().equalsIgnoreCase(NombreEliminar)){
                naves.remove(i);
                System.out.println("Nave '"+ NombreEliminar + "' eliminada exitosamente");
                naveEncontrada = true;
                break;    
            }
        }
    
    if (!naveEncontrada){
        System.out.println("No se encontró ninguna nave con el nombre '"+ NombreEliminar + "'." );
        }
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
            System.out.println("- Nombre: " + nave.getNombre()+
            " Total: "+nave.getExperienciaTotal());
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
        return experienciaTecnica + experienciaCientifica + experienciaEstrategica;
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

    public void setAutonomiaActual(int autonomiaActual) {
        this.autonomiaActual = autonomiaActual;
    }
    public void setExperienciaEstrategica(int experienciaEstrategica) {
        this.experienciaEstrategica = experienciaEstrategica;
    }

    public void setExperienciaCientifica(int experienciaCientifica) {
        this.experienciaCientifica = experienciaCientifica;
    }

    public void setExperienciaTecnica(int experienciaTecnica) {
        this.experienciaTecnica = experienciaTecnica;
    }


    public static void logTablaNaves(){
        
            System.out.println("***NAVES***");
        String[] headers = {"Nombre", "Autonomía máxima", "Autonomía actual", "Capacidad Carga","Sensores científicos","XP técnica","XP científica","XP estratégica","XP total"};
        String[][] data = new String[naves.size()][9];
        for (int i = 0; i < naves.size(); i++) {
            NavesEspaciales nave = naves.get(i);
            data[i][0] = nave.getNombre();
            data[i][1] = String.valueOf(nave.getAutonomiaMaxima());
            data[i][2] = String.valueOf(nave.getAutonomiaActual());
            data[i][3] = String.valueOf(nave.getCapacidadCarga());
            data[i][4] = String.valueOf(nave.tieneSensoresCientificos());
            data[i][5] = String.valueOf(nave.getExperienciaTecnica());   
            data[i][6] = String.valueOf(nave.getExperienciaCientifica());   
            data[i][7] = String.valueOf(nave.getExperienciaEstrategica());   
            data[i][8] = String.valueOf(nave.getExperienciaTotal());           
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
        System.out.print(ANSI_VERDE+"|"+ANSI_RESET);
        for (int i = 0; i < headers.length; i++) {
            System.out.printf(ANSI_VERDE+" %-"+columnWidths[i]+"s |"+ANSI_RESET, headers[i]);
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

    public static void logNaves(){
        System.out.println("******NAVES*****");
        for (NavesEspaciales naves : naves){
            naves.logNave();
        }
    }
    public void logNave() {
        System.out.printf("""
                
                Nombre: %s
                Autonomía máxima: %d
                Autonomía actual: %d
                Capacidad de carga: %d
                Sensores científicos: %s
                Experiencia total: %d
                Técnica: %d
                Científica: %d
                Estratégica: %d
                """, nombre, autonomiaMaxima, autonomiaActual, capacidadCarga,
                sensoresCientificos ? "Sí" : "No",
                experienciaTecnica, experienciaCientifica, experienciaEstrategica);
    }
    
    private static int leerEntero(Scanner scanner, String mensaje, int minimo){
        int valor;
        while (true) {
            System.out.println(mensaje);
            try{
                valor = Integer.parseInt(scanner.nextLine());
                if (valor < minimo) {
                    System.out.println("Valor inválido, debe ser mayor o igual a "+ minimo + ".");
                } else{
                    return valor;
                }
            } catch (NumberFormatException e){
                System.out.println("Entrada inválida: Debes ingresar un número entero: ");
            }
        }
    }

    public static List<NavesEspaciales> getNaves() {
        return naves;
    }
    public static void generarNaves() {
    naves.add(new NavesEspaciales("Omega", 1500, 1500, 700, true, 2, 1, 2));
    naves.add(new NavesEspaciales("Alpha", 900, 900, 600, true, 2, 5, 3));
    naves.add(new NavesEspaciales("Beta", 1200, 1200, 400, true, 10, 0, 2));
}
    
    @Override
    public String toString() {
        return "NavesEspaciales{" +
                "nombre='" + nombre + '\'' +
                ", autonomiaMaxima=" + autonomiaMaxima +
                ", autonomiaActual=" + autonomiaActual +
                ", capacidadCarga=" + capacidadCarga +
                ", sensoresCientificos=" + sensoresCientificos +
                ", experienciaTecnica=" + experienciaTecnica +
                ", experienciaCientifica=" + experienciaCientifica +
                ", experienciaEstrategica=" + experienciaEstrategica +
                '}';
    }

    public static NavesEspaciales naveRandom(){
        Random random = new Random();

        String[] nombresNavesRandom = {"Black souls","Drakukeo","Goku","Pikachu","Joker", "Wynnship", "Titanic", "Bombardero", "Tralalero", "Switch", "Peñas", "Ryuji", "Omega", "Alice", };

        int longNombresNave = random.nextInt(nombresNavesRandom.length);
        int autonomiaMaxima = random.nextInt(1500, 3000) + 1;
        int autonomiaActual = autonomiaMaxima;
        int capacidadCarga = random.nextInt(1500, 3000) +1;
        boolean sensoresCientificos = true;
        int experienciaCientifica = random.nextInt(0,5)+1;
        int experienciaEstrategica = random.nextInt(0,5)+1;
        int experienciaTecnica = random.nextInt(0,5)+1;

        NavesEspaciales nuevaNave = new NavesEspaciales(nombresNavesRandom[longNombresNave], autonomiaMaxima, autonomiaActual, capacidadCarga, sensoresCientificos, experienciaTecnica, experienciaCientifica, experienciaEstrategica);
        naves.add(nuevaNave);
        
        return null;
    }
}