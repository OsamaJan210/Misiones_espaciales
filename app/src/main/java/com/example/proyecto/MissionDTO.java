package com.example.proyecto;

public class MissionDTO {

    private String nombre;
    private int duracion;
    private int prioridad;
    private String tipoExperiencia;
    private int carga;
    private int autonomia;
    private int xp;
    private String missionType;
    private String status;


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

public int getAutonomia() {
    return autonomia;
}

public void setAutonomia(int autonomia) {
    this.autonomia = autonomia;
}

public String getTipo() {
    return tipoExperiencia;
}

public void setTipoExperiencia(String tipoExperiencia) {
    this.tipoExperiencia = tipoExperiencia;
}

public int getCarga() {
    return carga;
}

public void setCarga(int carga) {
    this.carga = carga;
}

public int getXp() {
    return xp;
}

public void setXp(int xp) {
    this.xp = xp;
}

public String getMissionType() {
    return missionType;
}

public void setMissionType(String missionType) {
    this.missionType = missionType;
}

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}

}
