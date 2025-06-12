package com.example.proyecto;
import java.util.Scanner;

import com.example.proyecto.enums.ExperienciaTipo;
import com.example.proyecto.enums.MissionStatus;
import com.example.proyecto.enums.MissionType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MisionExploracion extends Mision{
    Scanner scanner = new Scanner(System.in);
    private int autonomia = 1000;
    @JsonProperty("xp")
    private int cantidadXP = 0;

    public MisionExploracion(String nombre, int prioridad, int duracion, MissionType tipo, ExperienciaTipo experienciaTipo, int cantidadXP, int autonomia) {
        super(nombre,prioridad);
        this.duracion = duracion;
        this.tipoMision = tipo;
        this.autonomia = autonomia;
        this.setExperiencia(experienciaTipo, cantidadXP);
    }

    public MisionExploracion(String nombre, int prioridad){

    }
    public MisionExploracion() {
    super();
}

    public int getAutonomia(){
        return autonomia;
    }

    public void setAutonomia(int autonomia) {
        this.autonomia = autonomia;
    }

    public int getDuracion(){
        return duracion;
    }

    public int getXP() {
        return cantidadXP;
    }

    public void setXP(int xp) {
        this.cantidadXP = xp;
    }

    @Override
    public String getExtraData() {
        return String.valueOf(autonomia);
    }
    
    
    @Override
    public void acabarDeRegistrarDatos(String nombre, int prioridad, MissionStatus estado){
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.estado = MissionStatus.PENDIENTE;
        this.tipoMision = MissionType.EXPLORACION;
        ExperienciaTipo experiencia = ExperienciaTipo.CIENTIFICA;
        do{
            System.out.println("Indica la cantidad de autonomía que necesitaría la misión: ");
            autonomia = scanner.nextInt();
            if(autonomia<0){
                System.out.println("***ERROR***\nLa autonomía no puede ser inferior a 0!!!");
            }
        }while(autonomia<0);
        this.duracion = 0;
        do{
            System.out.println("Indica la duración de la mision de Exploración (Mínimo 8h): ");
            duracion = scanner.nextInt();
            if(duracion<8){
                System.out.println("***ERROR***\nLa misión no puede durar tan poco!!!");
            }
        }while(duracion<8);
        do{
            System.out.println("Indica la cantidad de experiencia de exploración que necesitas: ");
            cantidadXP = scanner.nextInt();
            if(cantidadXP<0){
                System.out.println("***ERROR***\n La experiéncia de la misión no puede ser negativa!!!");
            }
        }while(cantidadXP<0);

        setExperiencia(experiencia, cantidadXP);
    }
}
