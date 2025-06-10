package com.example.proyecto;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.example.proyecto.enums.MissionStatus;

public class Simulacion {

    public static void simularCiclo() {
        // Obtener las listas de misiones y naves
        List<Mision> misiones = Mision.getMisiones();
        List<NavesEspaciales> naves = NavesEspaciales.getNaves();

        // Listas para misiones pendientes, asignadas y naves utilizadas
        List<Mision> misionesPendientes = new ArrayList<>();
        List<Mision> misionesAsignadas = new ArrayList<>();
        List<NavesEspaciales> navesUsadas = new ArrayList<>();

        // 1. Filtrar misiones pendientes
        for (Mision mision : misiones) {
            if (mision.getStatus() == MissionStatus.PENDIENTE) {
                misionesPendientes.add(mision);
            }
        }

        System.out.println("Misiones pendientes: " + misionesPendientes.size());
        System.out.println("Naves disponibles: " + naves.size());

        // 2. Ordenar misiones por prioridad (descendente)
        misionesPendientes.sort((m1, m2) -> Integer.compare(m2.getPrioridad(), m1.getPrioridad()));

        // 3. Evaluar cada misión y asignar una nave apta si existe
        for (Mision mision : misionesPendientes) {
            System.out.println("\nEvaluando misión: " + mision.getNombre() + " [prioridad: " + mision.getPrioridad() + "]");

            boolean asignada = false;

            for (NavesEspaciales nave : naves) {
                if (!navesUsadas.contains(nave) && esNaveAptaParaMision(nave, mision)) {
                    procesarMision(nave, mision);
                    misionesAsignadas.add(mision);
                    navesUsadas.add(nave);
                    asignada = true;
                    break;
                }
            }

            if (!asignada) {
                System.out.println("No hay naves aptas para esta misión.");
            }
        }

        // 4. Eliminar misiones asignadas de la lista principal (opcional)
        // misiones.removeAll(misionesAsignadas);

        // 5. Restaurar la autonomía de las naves no utilizadas
        for (NavesEspaciales nave : naves) {
            if (!navesUsadas.contains(nave)) {
                nave.setAutonomiaActual(nave.getAutonomiaMaxima());
            }
        }

        System.out.println("\nAutonomía restaurada en todas las naves no utilizadas.");
    }

    // Verifica si la nave es apta para la misión
    private static boolean esNaveAptaParaMision(NavesEspaciales nave, Mision mision) {
        switch (mision.getMissionType()) {
            case COLONIZACION:
                MisionColonizacion mCol = (MisionColonizacion) mision;
                return nave.getCapacidadCarga() >= mCol.getCarga() &&
                        nave.getExperienciaEstrategica() >= mCol.getXP() &&
                        nave.getAutonomiaActual() >= mCol.getDuracion();
            case EXPLORACION:
                MisionExploracion mExp = (MisionExploracion) mision;
                return nave.getAutonomiaActual() >= mExp.getAutonomia() &&
                        nave.getExperienciaCientifica() >= mExp.getXP();
            case RECOLECCION_DATOS:
                MisionRecoleccion mRec = (MisionRecoleccion) mision;
                return nave.tieneSensoresCientificos() &&
                        nave.getExperienciaTecnica() >= mRec.getXP() &&
                        nave.getAutonomiaActual() >= mRec.getDuracion();
            default:
                return false;
        }
    }

    // Ejecuta la misión, modifica experiencia y autonomía, muestra eventos aleatorios
    private static void procesarMision(NavesEspaciales nave, Mision mision) {
        System.out.println("Nave seleccionada: " + nave.getNombre());
        System.out.println("Ejecutando misión...");

        String evento = eventoAleatorio();
        System.out.println("Evento aleatorio: " + evento);

        switch (mision.getMissionType()) {
            case COLONIZACION:
                nave.setExperienciaEstrategica(nave.getExperienciaEstrategica() + 1);
                nave.setAutonomiaActual(nave.getAutonomiaActual() - ((MisionColonizacion) mision).getDuracion());
                break;
            case EXPLORACION:
                nave.setExperienciaCientifica(nave.getExperienciaCientifica() + 1);
                nave.setAutonomiaActual(nave.getAutonomiaActual() - ((MisionExploracion) mision).getAutonomia());
                break;
            case RECOLECCION_DATOS:
                nave.setExperienciaTecnica(nave.getExperienciaTecnica() + 1);
                nave.setAutonomiaActual(nave.getAutonomiaActual() - ((MisionRecoleccion) mision).getDuracion());
                break;
        }
        mision.estado  = MissionStatus.COMPLETADA;
        System.out.println("Experiencia ganada +1");
        System.out.println("Autonomía restante: " + nave.getAutonomiaActual());
    }

    // Genera un evento aleatorio
    private static String eventoAleatorio() {
        Random random = new Random();
        int numero = random.nextInt(101); // entre 0 y 100

        if (numero <= 10) {
            return "¡La misión ha fallado! No se gana experiencia.";
        } else if (numero <= 15) {
            return "¡Evento de mejora tecnológica! La nave gana 2 de experiencia extra.";
        } else if (numero <= 20) {
            return "¡Se ha descubierto algo especial!";
        }
        return "Ninguno";
    }
}
