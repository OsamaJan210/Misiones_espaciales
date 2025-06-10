package com.example.proyecto.enums;

public enum MissionStatus {
    PENDIENTE("Pendiente"),
    COMPLETADA("Completada"),
    FALLIDA("Fallida");

    private final String name; 
    MissionStatus(String string) {
        name = string;
    }

    public String toString() {
       return this.name;
    }

    public boolean equalsName(String otherName) {
        return name.equalsIgnoreCase(otherName);
    }
}
