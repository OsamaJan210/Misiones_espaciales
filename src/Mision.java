import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import enums.ExperienciaTipo;
import enums.MissionStatus;
import enums.MissionType;

public class Mision{
    Scanner scanner = new Scanner(System.in);

    List<Mision> misiones = new ArrayList<>();
    private String nombre;
    private int duracion;
    private int prioridad;
    protected MissionStatus estado;
    protected MissionType tipo;
    protected ExperienciaTipo experienciaRequerida;

    public Mision(String nombre, int duracion, int prioridad, MissionType tipo, ExperienciaTipo experienciaRequerida) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.prioridad = prioridad;
        this.tipo = tipo;
        this.experienciaRequerida = experienciaRequerida;
        this.estado = MissionStatus.PENDIENTE;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public MissionStatus getEstado() {
        return estado;
    }

    public void setEstado(MissionStatus estado) {
        this.estado = estado;
    }

    public MissionType getTipo() {
        return tipo;
    }

    public void setTipo(MissionType tipo) {
        this.tipo = tipo;
    }

    public ExperienciaTipo getExperienciaRequerida() {
        return experienciaRequerida;
    }

    public void setExperienciaRequerida(ExperienciaTipo experienciaRequerida) {
        this.experienciaRequerida = experienciaRequerida;
    }

    @Override
    public String toString() {
        return "Mision{" +
                "nombre='" + nombre + '\'' +
                ", duracion=" + duracion +
                ", prioridad=" + prioridad +
                ", estado=" + estado +
                ", tipo=" + tipo +
                ", experienciaRequerida=" + experienciaRequerida +
                '}';
    }

    public void registrarMision(){
        System.out.println("Nombre: ");
        this.nombre = scanner.nextLine();
        System.out.println("Tipo de mision: 1)Exploración, 2)Recolección de datos, 3)Colonización): ");
        int opcion = scanner.nextInt();
        if (opcion == 1) {
            this.tipo = MissionType.EXPLORACION;
        }
        else if(opcion ==2){
            this.tipo = MissionType.RECOLECCION_DATOS;
        }
        else{
            this.tipo = MissionType.COLONIZACION;
        }
        System.out.println("Duración: ");
        this.duracion = scanner.nextInt();
        System.out.println("Prioridad: ");
        this.prioridad = scanner.nextInt();
        System.out.println("Experiencia requerida: 1)Cientifica, 2)Tecnica, 3)Estrategica): ");
        int opcion2 = scanner.nextInt();
        if(opcion2 == 1){
            this.experienciaRequerida = ExperienciaTipo.CIENTIFICA;
        }
        else if(opcion2 == 2){
            this.experienciaRequerida = ExperienciaTipo.TECNICA;
        }
        else{
            this.experienciaRequerida = ExperienciaTipo.ESTRATEGICA;
        }
        Mision nuevamision = new Mision(nombre, opcion, opcion2, tipo, experienciaRequerida);
        misiones.add(nuevamision);

        System.out.println("\nMision añadida correctamente\n");
    }

    public void logMisiones(){
        for (Mision misiones : this.misiones){
            misiones.logMision();
        }
    }
    public void logMision(){
        System.out.println("Nombre: "+this.nombre+"\nTipo de mision: "+this.tipo+"\nDuración: "+this.duracion+"\nPrioridad: "+this.prioridad+"\nEstado: "+this.estado);
    }
}