import enums.ExperienciaTipo;
import enums.MissionStatus;
import enums.MissionType;

public class Mision{

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
}