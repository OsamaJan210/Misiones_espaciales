package com.example.proyecto.enums;

public enum MissionType {
    EXPLORACION("Exploracion"),
    RECOLECCION_DATOS("Recoleccion"),
    COLONIZACION("Colonizacion");


    private final String name; 
    MissionType(String string) {
        name = string;
    }

    public String toString() {
       return this.name;
    }

    public boolean equalsName(String otherName) {
        return name.equalsIgnoreCase(otherName);
    }
}
