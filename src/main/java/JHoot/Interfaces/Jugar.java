package JHoot.Interfaces;

import java.util.Scanner;

import JHoot.Modelos.Configuracion;
import JHoot.Modelos.Jugador;
import JHoot.Modelos.Pregunta;

public interface Jugar {
    public Pregunta[] getPreguntasForQuiz();
    public Jugador registerPlayer(Scanner sc);
    public Configuracion[] getConfigs();
    
    public void PlayGame1P(Jugador player, Scanner sc);
    //public void PlayGame2P(Jugador p1, Jugador p2, Scanner sc);
    //public void PlayGameOnline(Jugador p1, Scanner sc);
    public void displayStats(Jugador player, Scanner sc);
    
}