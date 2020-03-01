package JHoot.Modelos;

public abstract class Persona {
    private String nombre;
    private String apellidos;

    Persona(String nombre, String apellidos){
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public void setName(String name){
        this.nombre = name;
    }

    public void setApellidos(String apellidos){
        this.apellidos = apellidos;
    }

    public String getName(){
        return this.nombre;
    }

    public String getApellidos(){
        return this.apellidos;
    }

}