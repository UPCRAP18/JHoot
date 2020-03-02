package JHoot;

import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import JHoot.Interfaces.Jugar;
import JHoot.Modelos.Configuracion;
import JHoot.Modelos.Jugador;
import JHoot.Modelos.Pregunta;

class JHoot implements Jugar {
    Jugador j1, j2;
    Configuracion configSel;
    List<Pregunta> preguntasQuiz;
    //static Timer timer;

    public void JugarJHoot() {
        Scanner sc = new Scanner(System.in);
        int opc = 0;
        while (opc != 4) {
            clearScreen();
            System.out.println("Bienvenido a JHoot!");
            System.out.println("Menu principal");
            System.out.println("1) 1 jugador");
            System.out.println("2) 1 vs 1 local");
            System.out.println("3) 1 vs 1 Online");
            System.out.println("4) Salir");
            System.out.print("Seleccione una opcion: ");
            opc = sc.nextInt();
            switch (opc) {
                case 1:
                    j1 = registerPlayer(sc);
                    selectConfigs(sc);
                    if (prepareGame()) {
                        PlayGame1P(j1, sc);
                        displayStats(j1, sc);
                    } else {
                        System.out.println("Ha ocurrido un error al preparar el juego");
                        System.exit(0);
                    }
                    break;
                case 2:
                    System.out.println("Aun no se ha implementado esto :)");
                    break;
                case 3:
                    System.out.println("Ni tampoco esto :)");
                    break;
                case 4:
                    System.out.println("¡Hasta luego!");
                    break;
            }
        }
        sc.close();
    }

    private void selectConfigs(Scanner sc) {
        Configuracion[] configs = getConfigs();
        int opc, index = 1;
        clearScreen();
        System.out.println("Menu de configuraciones");
        if (configs.length > 0) {
            for (Configuracion configuracion : configs) {
                System.out.println(index + ") " + configuracion.getNombre() + " -- " + configuracion.getCategoria());
            }
            System.out.print("Seleccione una configuracion: ");
            opc = sc.nextInt();
            configSel = configs[opc - 1];
        } else {
            System.out.println(
                    "No se han encontrado preguntas disponibles, " + "por favor, agregue al archivo de configuracion");
            System.exit(0);
        }
    }

    private boolean prepareGame() {
        clearScreen();
        List<Pregunta> pregList = Arrays.asList(getPreguntasForQuiz());
        Collections.shuffle(pregList);
        if (pregList.size() >= configSel.getPreguntas()) {
            preguntasQuiz = pregList.subList(0, configSel.getPreguntas());
        } else {
            System.out.println("No hay suficientes preguntas de la materia en el archivo, por favor, agregue mas");
            System.exit(0);
        }
        return true;
    }

    @Override
    public Pregunta[] getPreguntasForQuiz() {
        try {
            Object obj = new JSONParser().parse(
                    new FileReader(System.getProperty("user.dir") + "/src/main/java/JHoot/Configs/Preguntas.json"));
            JSONArray jObj = (JSONArray) obj;
            Pregunta[] pregs = new Pregunta[jObj.size()];
            for (int i = 0; i < jObj.size(); i++) {
                JSONObject raw = (JSONObject) jObj.get(i);
                String mat = (String) raw.get("Materia");
                if (mat.equals(configSel.getCategoria())) {
                    String ID = (String) raw.get("ID");
                    String Pregunta = (String) raw.get("Pregunta");
                    String Respuesta = (String) raw.get("Respuesta");
                    String Inc1 = (String) raw.get("Inc1");
                    String Inc2 = (String) raw.get("Inc2");
                    String Inc3 = (String) raw.get("Inc3");
                    pregs[i] = new Pregunta(ID, Pregunta, Respuesta, Inc1, Inc2, Inc3, mat);
                }
            }
            return pregs;
        } catch (Exception ex) {
            System.out.println("Ha ocurrido un error al abrir el archivo de Preguntas");
            System.err.println(ex.toString());
            System.exit(-1);
            return null;
        }

    }

    @Override
    public Jugador registerPlayer(Scanner sc) {
        clearScreen();
        String nombre, apellidos, nickname;
        System.out.print("Introduce tu nombre: ");
        nombre = sc.next();
        System.err.print("Introduce tus apellidos: ");
        apellidos = sc.next();
        System.err.print("Introduce tu nickname: ");
        nickname = sc.next();
        return new Jugador(nombre, apellidos, nickname);
    }

    @Override
    public Configuracion[] getConfigs() {
        try {

            Object obj = new JSONParser()
                    .parse(new FileReader(System.getProperty("user.dir") + "/src/main/java/JHoot/Configs/Games.json"));
            JSONArray jObj = (JSONArray) obj;
            Configuracion[] configs = new Configuracion[jObj.size()];
            for (int i = 0; i < jObj.size(); i++) {
                JSONObject raw = (JSONObject) jObj.get(i);
                String nombre = (String) raw.get("Nombre");
                String categoria = (String) raw.get("Categoria");
                int TPreg = ((Long) raw.get("Tiempo de pregunta")).intValue();
                int PB = ((Long) raw.get("Puntaje base por pregunta")).intValue();
                int Pregs = ((Long) raw.get("Preguntas")).intValue();
                configs[i] = new Configuracion(nombre, categoria, TPreg, PB, Pregs);
            }
            return configs;
        } catch (Exception ex) {
            System.out.println("Ha ocurrido un error al abrir el archivo de Configuraciones");
            System.err.println(ex.toString());
            System.exit(-1);
            return null;
        }
    }

    @Override
    public void PlayGame1P(Jugador player, Scanner sc) {
        clearScreen();
        String ready;
        System.out.println("¿Estas listo? s/n");
        ready = sc.nextLine();
        if (ready.equals("S") || ready.equals("s")) {
            //TODO IMPLEMENTAR BIEN EL TIMER
            int index = 1;
            for (Pregunta pregunta : preguntasQuiz) {
                int usr_resp;
                String[] resp = 
                    new String[] {pregunta.getRespuesta(), pregunta.getInc1(), pregunta.getInc2(), pregunta.getInc3()};
                List<String> respuestas = Arrays.asList(resp);
                Collections.shuffle(respuestas);
                clearScreen();
                System.out.println("Pregunta #" + index);
                System.out.println(pregunta.getPregunta());
                int resp_indx = 0;
                for (String respuesta : respuestas) {
                    System.out.println( (resp_indx + 1) + ") " + respuesta);
                    resp_indx++;
                }
                System.err.print("Selecciona una respuesta: ");
                usr_resp = sc.nextInt();
                if(respuestas.get(usr_resp-1).equals(pregunta.getRespuesta())){
                    System.out.println("¡Respuesta Correcta!");
                    int act_punt = player.getPuntaje();
                    act_punt += configSel.getPBase();
                    player.setPunt(act_punt);
                }else{
                    System.out.println("¡Te has equivocado! :c");
                    System.out.println("La respuesta correcta era: " + pregunta.getRespuesta());
                }
                
                System.out.println("Presiona un boton para continuar...");
                if(sc.nextLine().isEmpty()){
                    System.out.println();
                    sc.nextLine();
                }
                index++;
            }
        }else {
            PlayGame1P(j1, sc);
        }
    }

    @Override
    public void displayStats(Jugador player, Scanner sc){
        clearScreen();
        System.out.println("Felicidades Jugador: " + player.getNickname());
        System.out.println("Usted ha obtenido un puntaje de: " + player.getPuntaje());
        System.out.println("Presione un boton para continuar...");
        if(sc.nextLine().isEmpty()){
            System.out.println();
            sc.nextLine();
        }
    }

    private void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}