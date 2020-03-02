package JHoot.Modelos;

public class Configuracion {
    private String Nombre, Categoria;
    private int TPregunta, PBase, Preguntas;

    public Configuracion(String nombre, String cat, int TPreg, int PBase, int pregs){
        this.Nombre = nombre;
        this.Categoria = cat;
        this.TPregunta = TPreg;
        this.PBase = PBase;
        this.Preguntas = pregs;
    }

    public void setNombre(String nombre) { this.Nombre = nombre; }
    public void setCategoria(String cat) { this.Categoria = cat; }
    public void setTPregunta(int tpreg) { this.TPregunta= tpreg; }
    public void setPBase(int pbase) { this.PBase = pbase; }
    public void setPreguntas(int pregs) { this.Preguntas = pregs; }

    public String getNombre() { return this.Nombre; }
    public String getCategoria() { return this.Categoria; }
    public int getTPregunta() { return this.TPregunta; }
    public int getPBase() { return this.PBase; }
    public int getPreguntas() { return this.Preguntas; }
    
}