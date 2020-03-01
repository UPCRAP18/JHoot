package JHoot.Modelos;

public class Pregunta {
    private String 
        ID, Pregunta, Respuesta, 
        Inc1, Inc2, Inc3,
        Materia;

    Pregunta(String id, String pregunta, String respuesta, String inc1, String inc2, String inc3, String materia){
        this.ID = id;
        this.Pregunta = pregunta;
        this.Respuesta = respuesta;
        this.Inc1 = inc1;
        this.Inc2 = inc2;
        this.Inc3 = inc3;
        this.Materia = materia;
    }

    public void setPregunta(String pregunta){ this.Pregunta = pregunta; }

    public void setRespuesta(String respuesta){ this.Respuesta = respuesta; }

    public void setInc1(String inc){ this.Inc1 = inc; }

    public void setInc2(String inc){ this.Inc2 = inc; }

    public void setInc3(String inc){ this.Inc3 = inc; }

    public void setMateria(String materia){ this.Materia = materia; }

    public String getPregunta(){ return this.Pregunta; }

    public String getRespuesta(){ return this.Respuesta; }
    
    public String getInc1(){ return this.Inc1; }

    public String getInc2(){ return this.Inc2; }

    public String getInc3(){ return this.Inc3; }

    public String getMateria(){ return this.Materia; }

    @Override
    public String toString(){
        return ("ID: " + this.ID + "\n" + 
                "Pregunta: " + this.Pregunta + "\n" + 
                "Respuesta: " + this.Respuesta + "\n" + 
                "Incorrecta 1: " + this.Inc1 + "\n" + 
                "Incorrecta 2: " + this.Inc2 + "\n" + 
                "Incorrecta 3: " + this.Inc3 + "\n" + 
                "Materia: " + this.Materia + "\n");
    }

}