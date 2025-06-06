import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import enums.MissionStatus;
import enums.MissionType;

public class Simulacion {

        static List<Mision> misiones = Mision.getMisiones();
        static List<NavesEspaciales> naves = NavesEspaciales.getNaves();
        static List<Mision> misionesPendientes = new ArrayList<>();

    public static void simularCiclo() {

        //Listar Misiones y naves
        int contador=0;      
        int contador2=0;
        for(Mision mision: misiones){
            if(mision.getStatus()==MissionStatus.PENDIENTE){
                misionesPendientes.add(mision);
                contador++;
            }
        }
        System.out.println("Misiones pendientes: "+ contador);

        for(NavesEspaciales nave : naves){
            contador2++;
        }
        System.out.println("Naves disponibles: "+ contador2);

        //Ordenar las misiones en el array
       /* for(Mision mision : misionesPendientes){
            misionesPendientes.sort(mision.getPrioridad());
        } */

        //Mostrar las misiones ya ordenadas, depende del tipo de misión mostrará diferente información
        for(Mision mision : misionesPendientes){
            if(mision instanceof MisionColonizacion){
                MisionColonizacion colonizacion = (MisionColonizacion) mision;
                System.out.println("Evaluando misión: "+mision.getNombre()+" [prioridad: "+mision.getPrioridad()+", Requiere carga >=: "+colonizacion.getCarga()+" experiencia estratégica: "+ colonizacion.getXP()+"\n");
            }
            else if(mision instanceof MisionExploracion){
                MisionExploracion exploracion = (MisionExploracion) mision;
                System.out.println("Evaluando misión: "+mision.getNombre()+" [prioridad: "+mision.getPrioridad()+", Requiere autonomía >=: "+exploracion.getAutonomia()+" experiencia científica: "+exploracion.getXP()+"\n");
            }
            else{
                MisionRecoleccion recoleccion = (MisionRecoleccion) mision;
                System.out.println("Evaluando misión: "+mision.getNombre()+" [prioridad: "+mision.getPrioridad()+", Requiere: sensores científicos = true, experiencia técnica: "+recoleccion.getXP()+"\n");
            }
            //Bucle para mirar las naves y mirar si la nave puede hacer la misión
            for(NavesEspaciales nave : naves){
                if(mision.getTipo()==MissionType.COLONIZACION){
                    nave.getCapacidadCarga()>=MisionColonizacion.getCarga() && nave.getExperienciaEstrategica()>= MisionColonizacion.getXP();
                    navesAptas.add(nave);
                    naves.remove(nave);
                    misionesPendientes.remove(mision);
                    System.out.println("Nave seleccionada: "+nave.getNombre()+"\nEjecutando misión...\nExperiencia ganada +1 Estratégica\nEvento aleatorio: "+eventoAleatorio()+"\nAutonomía restante: "+(nave.getAutonomiaMaxima()-MisionColonizacion.getDuracion()));
                }
                else if(mision.getTipo()==MissionType.EXPLORACION){
                    nave.getAutonomiaActual()>=MisionExploracion.getAutonomia() && nave.getExperienciaCientifica()>= MisionExploracion.getXP();
                    navesAptas.add(nave);
                    naves.remove(nave);
                    misionesPendientes.remove(mision);
                    System.out.println("Nave seleccionada: "+nave.getNombre()+"\nEjecutando misión...\nExperiencia ganada +1 Científica\nEvento aleatorio: "+eventoAleatorio()+"\nAutonomía restante: "+(nave.getAutonomiaMaxima()-MisionExploracion.getDuracion()));
                }
                else if(mision.getTipo()==MissionType.RECOLECCION_DATOS){
                    nave.tieneSensoresCientificos()==true && nave.getExperienciaTecnica()>= MisionRecoleccion.getXP();
                    navesAptas.add(nave);
                    naves.remove(nave);
                    misionesPendientes.remove(mision);
                    System.out.println("Nave seleccionada: "+nave.getNombre()+"\nEjecutando misión...\nExperiencia ganada +1 Técnica\nEvento aleatorio: "+eventoAleatorio()+"\nAutonomía restante: "+(nave.getAutonomiaMaxima()-MisionRecoleccion.getDuracion()));
                }
                else{
                        System.out.println("No hay naves aptas para esta misión");
                    }
                nave.getAutonomiaActual() = nave.getautonomiaMaxima();
                }
                System.out.println("Autonomía restaurada en todas las naves");
                return;
        } 
    }
    private static String eventoAleatorio() {
        Random random = new Random();
        int numero = random.nextInt(101);
        if(numero<=10){
            return "La misión ha fallado!!!\n no se gana experiencia";
        }
        else if(numero>10 && numero<=15){
            return "Evento de mejora tecnológica!!!\n la nave gana 2 de experiencia extra";
        }
        else if(numero>15 && numero<=20){
            return "Se ha descubierto algo especial!!!";
        }
        return "Ninguno";
    }
}

