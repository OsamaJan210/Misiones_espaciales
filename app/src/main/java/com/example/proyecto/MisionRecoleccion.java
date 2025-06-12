package com.example.proyecto;
import java.util.Scanner;

import com.example.proyecto.enums.ExperienciaTipo;
import com.example.proyecto.enums.MissionStatus;
import com.example.proyecto.enums.MissionType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MisionRecoleccion extends Mision{
    Scanner scanner = new Scanner(System.in);
    @JsonProperty("xp")
    private int cantidadXP = 0;

    public MisionRecoleccion(String nombre, int prioridad, int duracion, MissionType tipo, ExperienciaTipo experienciaTipo, int cantidadXP, boolean requiereCientifico) {

        super(nombre,prioridad);
        this.duracion = duracion;
        this.tipoMision = tipo;
        requiereCientifico = true;
        this.setExperiencia(experienciaTipo, cantidadXP);
    }

    public MisionRecoleccion(String nombre, int prioridad){

    }

    public MisionRecoleccion() {
    super();
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
        return "";
    }
    

    @Override
    public void acabarDeRegistrarDatos(String nombre, int prioridad, MissionStatus estado){
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.estado = MissionStatus.PENDIENTE;
        this.tipoMision = MissionType.RECOLECCION_DATOS;
        ExperienciaTipo experiencia = ExperienciaTipo.TECNICA;
        this.duracion =0;
        do{
            System.out.println("Indica la duración de la mision de Recolección de datos (Entre 4h y 8h): ");
            duracion = scanner.nextInt();
            if(duracion<4){
                System.out.println("***ERROR***\nLa misión no puede durar tan poco!!!");
            }
            else if(duracion>8){
                System.out.println("***ERROR***\\nLa misión no puede durar tanto tiempo!!!");
            }
        }while(duracion<4 && duracion>8);
        do{
            System.out.println("Indica la cantidad de experiencia de recolección que necesitas: ");
            cantidadXP = scanner.nextInt();
            if(cantidadXP<0){
                System.out.println("***ERROR***\n La experiéncia de la misión no puede ser negativa!!!");
            }
        }while(cantidadXP<0);

        setExperiencia(experiencia, cantidadXP);
    }
}
