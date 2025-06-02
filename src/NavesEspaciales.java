public class NavesEspaciales {
    
    private String nombre;
    private int autonomiaMaxima;
    private int autonomiaActual;
    private int capacidadCarga;
    private boolean sensoresCientificos;
    private int experienciaTotal;
    private int experienciaTecnica;
    private int experienciaCientifica;
    private int experienciaEstrategica;

    public NavesEspaciales(String nombre, int autonomiaMaxima, int autonomiaActual, int capacidadCarga, boolean sensoresCientificos, int experienciaTotal, int experienciaTecnica, int experienciaCientifica, int experienciaEstrategica) {
        this.nombre = nombre;
        this.autonomiaMaxima = autonomiaMaxima;
        this.autonomiaActual = autonomiaActual;
        this.capacidadCarga = capacidadCarga;
        this.sensoresCientificos = sensoresCientificos;
        this.experienciaTotal = experienciaTotal;
        this.experienciaTecnica = experienciaTecnica;
        this.experienciaCientifica = experienciaCientifica;
        this.experienciaEstrategica = experienciaEstrategica;
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
        return experienciaTotal;
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

    public boolean esApta(Mision m){
        if(autonomiaActual < m.getDuracion()) {
            return false;
        }
        switch (m.getTipo()) {
            case   EXPLORACION:
                return autonomiaMaxima > 1000;
        
            case RECOLECCION_DATOS:
                return sensoresCientificos;
            
            case COLONIZACION:
                return capacidadCarga >=500;
        }
        return false;
    }

}