package com.example.proyecto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

import com.example.proyecto.enums.ExperienciaTipo;
import com.example.proyecto.enums.MissionType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int opcion = 0;
        do{
            System.out.println("\n*****Bienvenido al simulador espacial*****\n\n"+
            "¿Que deseas hacer?\n\n"+
            "1)Registrar Mision\n"+
            "2)Borrar misión\n"+
            "3)Registar Nave\n"+
            "4)Borrar Nave\n"+
            "5)Simular un ciclo\n"+
            "6)Mostrar estado general\n"+
            "7)Buscar Mision\n"+
            "8)Ranking de naves\n"+
            "9)Generar Naves y misiones\n"+
            "10)Listar Naves y misiones\n"+
            "11)Tests\n"+
            "12)Guardar datos\n"+
            "13)Cargar datos\n"+
            "14)Mostrar tabla\n"+
            "15)Salir\n");
            opcion = scanner.nextInt();
            
            switch (opcion) {
                case 1:
                  Mision.registrarMision(scanner);
                break;
                case 2:
                  Mision.borrarMision(scanner);
                break;
                case 3:
                  NavesEspaciales.registrarNave(scanner);
                break;
                case 4:
                  NavesEspaciales.borrarNave(scanner);
                break;
                case 5:
                  Simulacion.simularCiclo();
                break;
                case 6:
                  NavesEspaciales.mostrarEstado();
                  Mision.misionesPendientes();
                  Mision.misionesCompletadas();
                break;
                case 7:
                  Mision.buscarMisiones(scanner);
                break;
                case 8:
                  NavesEspaciales.generarRanking();
                break;
                case 9:
                  Mision.generarMisiones();
                  NavesEspaciales.generarNaves();  
                  System.out.println("\nNaves y Misiones generados correctamente\n");
                break;
                case 10:
                  Mision.logMisiones();
                  NavesEspaciales.logNaves();
                break;
                case 11:
                  testRegistroNaveExitosa();
                  expTotalCorrecta();
                  testNaveDuplicada();
                  rankingOrdenaCorrectamente();
                break;
                case 12:
                  guardarDatos();
                break;
                case 13:
                  new NavesEspaciales(readNaves());
                  List<Mision> misionesLeidas = readMisiones();
                  if (misionesLeidas != null) {
                      Mision.getMisiones().addAll(misionesLeidas);
                  }
                break;
                case 14:
                  Mision.logTablaMisiones();
                break;
                case 15:
                  System.out.println("Adios!");
                break;

            }
        }while (opcion!=15);
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
    private static void guardarDatos() {
      List<NavesEspaciales> naves = NavesEspaciales.getNaves();
      List<Mision> misiones = Mision.getMisiones();
      ObjectMapper mapper = new ObjectMapper();

      try{
        File jsonMisiones = new File("Misiones.json");
        mapper.writeValue(jsonMisiones, misiones);
        File jsonNaves = new File("Naves.json");
        mapper.writeValue(jsonNaves, naves);
        System.out.println("Naves y misiones guardadas correctamente!");
      } 
      catch (IOException e){
        e.printStackTrace();
      }
    }

    public static List<NavesEspaciales> readNaves(){
        ObjectMapper mapper = new ObjectMapper();
        try{
            List<NavesEspaciales> naves = mapper.readValue(
                    new File("Naves.json"),
                    new TypeReference<List<NavesEspaciales>>() {}
            );
            return naves;
        }
        catch (Exception ex){
             System.out.println("Exception");
                ex.printStackTrace();
        }
        return null;
    }

    public static List<Mision> readMisiones(){
      ObjectMapper mapper = new ObjectMapper();
      List<Mision> misiones= new ArrayList<>();
      
      try{
        List<MissionDTO> misionesDTO = mapper.readValue(new File("Misiones.json"), new TypeReference<List<MissionDTO>>() {}
        );
        for(MissionDTO local:misionesDTO){
          System.out.println(local.getNombre());
          if (local.getMissionType().equals("RECOLECCION_DATOS")) {
            Mision mision= new MisionRecoleccion(local.getNombre(), local.getPrioridad(), local.getDuracion(), MissionType.RECOLECCION_DATOS, ExperienciaTipo.TECNICA, local.getXp(), true);
            misiones.add(mision);
          }
          if (local.getMissionType().equals("EXPLORACION")) {
             Mision mision= new MisionExploracion(local.getNombre(), local.getPrioridad(), local.getDuracion(), MissionType.EXPLORACION, ExperienciaTipo.CIENTIFICA, local.getXp(), local.getAutonomia() );
            misiones.add(mision);
          }
          if (local.getMissionType().equals("COLONIZACION")) {
             Mision mision= new MisionColonizacion(local.getNombre(), local.getPrioridad(), local.getDuracion(), MissionType.COLONIZACION, ExperienciaTipo.ESTRATEGICA, local.getXp(), local.getCarga());
            misiones.add(mision);
          }

        }
        return misiones;
      }
      catch (Exception ex){
        System.out.println("Exception");
        ex.printStackTrace();
      }
      return null;
    }
}