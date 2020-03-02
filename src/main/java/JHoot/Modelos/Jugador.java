package JHoot.Modelos;

public class Jugador extends Persona {
    private String nickName;
    private int puntaje;
    
    public Jugador(String name, String apellidos, String nickname){
        super(name, apellidos);
        this.nickName = nickname;
        this.puntaje = 0;
    }

    public void setNickname(String nick){
        this.nickName = nick;
    }

    public void setPunt(int newPunt){
        this.puntaje = newPunt;
    }

    public String getNickname(){
        return this.nickName;
    }

    public int getPuntaje(){
        return this.puntaje;
    }

}