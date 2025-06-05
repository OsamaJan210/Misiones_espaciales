import java.util.List;

import enums.MissionStatus;
import enums.MissionType;

public class Simulacion {

    static List<Mision> misionesPendientes;
    static List<NavesEspaciales> navesAptas;

    public static void simularCiclo() {
        int contador=0;
        for(Mision mision : misiones){
            if(mision.getStatus()==MissionStatus.PENDIENTE) contador ++;
        }
        System.out.println("Misiones pendientes: "+ contador);

        int contador2=0;
        for(NavesEspaciales nave : naves){
            contador2++;
        }
        System.out.println("Naves disponibles: "+ contador2);

        for(Mision mision: misiones){
            if(mision.getStatus()==MissionStatus.PENDIENTE){
                misionesPendientes.add(mision);
            }
        }
        misionesPendientes.sort(mision.getPrioridad);
        System.out.println("Evaluando misión: ");
        for(Mision mision : misionesPendientes){
            System.out.println(mision.getNombre()+" [prioridad: "+mision.getPrioridad()+"]\nRequiere: ");
            if(mision.MissionType==MissionType.COLONIZACION){
                System.out.println("carga>="+MisionColonizacion.getCarga()+", experiencia estratégica>= "+MisionColonizacion.getXP());
            }
            else if(mision.MissionType==MissionType.EXPLORACION){
                System.out.println("autonomía>="+MisionExploracion.getAutonomia()+", experiencia científica>= "+MisionExploracion.getXP());
            }
            else{
                System.out.println("sensores científicos = true, experiencia técnica >="+MisionRecoleccion.getXP());
            }

            for(NavesEspaciales nave : naves){
                if(mision.MissionType==MissionType.COLONIZACION){
                    if(nave.getCapacidadCarga()>=MisionColonizacion.getCarga() && nave.getExperienciaEstrategica()>= MisionColonizacion.getXP()){
                        navesAptas.add(nave);
                        naves.remove(nave);
                    }
                    else if(nave.getAutonomiaActual()>=MisionExploracion.getAutonomia() && nave.getExperienciaCientifica()>= MisionExploracion.getXP()){
                        navesAptas.add(nave);
                        naves.remove(nave);
                    }
                    else if(nave.tieneSensoresCientificos()==true && nave.getExperienciaTecnica()>= MisionRecoleccion.getXP()){
                        navesAptas.add(nave);
                        naves.remove(nave);
                    }
                    else{
                        System.out.println("No hay naves aptas para esta misión");
                    }
                }
            }
        }
    }
}

