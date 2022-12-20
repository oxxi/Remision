package hn.com.tigo.remision.utils;

public enum ModuleEnum {
    Module("Mantenimiento de %s"),
    Action_Update("Actualiza"),
    Action_Create("Agrega Elemento"),
    Action_Load("Consulta Elemento");


    private String description;

    ModuleEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

}
