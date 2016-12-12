package proyecto69;

import io.Terminal;
/**
 *
 * @author Sergio
 */
public class Interfaz {
    
    /**Una partida nueva*/
    private Partida partida;
    
    /**
     * Constructor
     */   
    public Interfaz() {    
        partida = new Partida();
    }
    
    /**
     * mensaje con las reglas mas básicas del juego
     */
    public void mensajeDeReglas(){
        System.out.println("Bienvenido al juego del 69.");
        System.out.println("El juego consiste en lanzar unos dados para saber quien paga la ronda en el bar.");
        System.out.println("Las reglas son simples, gana el que se quede sin mondadientes,\n"
                + "según lo que saque al tirar los dados se le asignará unos determinados puntos\n"
                + "si saca un '1' se le asigna un 100, el '2','3','4,'5' se le asigna su valor facial,\n"
                + "y al '6' se le asigna 69, todos estos puntos se le suman y gana el que mas puntos tengas,\n"
                + "salvo que su suma sea igual a 69, lo cual hace que gane la partida, siempre que no sea desempatando,\n"
                + "el que menos puntos tenga recibe un mondadiente de los demas, el que llegue a 0 mondadientes \n"
                + "gana.");
    }
    
    /**
     * introduce los jugadores
     */
    public void introduceJugadores(){
        int jugadores = Terminal.inputInt("Inserte la cantidad de jugadores superior a 2: ");       
        while (!partida.minimoJugadores(jugadores)) {            
            jugadores = Terminal.inputInt("Inserte la cantidad de jugadores superior a 2: ");       
        }        
        for(int i=0; i<jugadores; i++){
            Jugador jugador = new Jugador(Terminal.input("Introduce el nombre del jugador número "+(i+1)+": ") );
            partida.agregarJugador(jugador);
        }
        
    }
    
    /**
     * muestra los dados 
     */
    public void mostrarDado(){
        for (int j = 0; j < partida.cantidadDados(); j++) {
                        System.out.println("   Dado:"+partida.valorDados(j));    
                    }        
    }
    
    /**
     * muestra los puntos
     */
    public void mostrarPuntos() {
        System.out.println("Resumen de puntos: ");
        for (int j = 0; j < partida.total(); j++){
            System.out.println("  "+partida.mostrarJugador(j)+": "+partida.verPuntos(j)+" puntos.");
        }
    }
    
    /**
     * comprueba si ha ganado con 69
     */
    public void ganador69(){
        for (int i = 0; i < partida.total(); i++) {
            if(partida.es69(partida.verPuntos(i))){
                System.out.println("Enhorabuena "+partida.mostrarJugador(i)+"!!! Has sacado 69, has ganado y sales de la partida.");
            }   
        }
    }
    
    /**
     * el desempate de la ronda
     */
    public void desempatar(){
        while (true) { 
              int i = 0;
              int cont = 0;
              for (int j = 0; j < partida.total(); j++) {
                int min = partida.puntuacionMinima();               
                int punt = partida.verPuntos(j);
                if(punt ==min){
                    String resp = Terminal.input(partida.mostrarJugador(j)+" pulse enter para tirar");
                    partida.tiraDadosDesempate(cont);
                    mostrarDado();
                    System.out.println("Un total de: "+partida.verPuntosDesempate(cont)+" puntos");
                    cont++;
                }
              }
              if(!partida.comprobarDesempate()) break;    
        }
    }
    
    /**
     * muestra los mondadientes
     */
    public void mostrarMondadientes(){
        for (int j = 0; j < partida.total(); j++) {
            System.out.println(partida.mostrarJugador(j)+" tiene "+partida.numMondadientes(partida.mostrarJugador(j))+" mondadientes.");        
        }
        for (int i = 0; i < partida.total(); i++) {
            if(partida.numMondadientes(partida.mostrarJugador(i))==0){
                System.out.println("Enhorabuena "+partida.mostrarJugador(i)+", te has quedado sin mondadientes y has ganado.");
            }
            
        }
        partida.comprobarMondadientes();
    }
    /**
     * logica del juego
     */
    public void run(){
        mensajeDeReglas();
        introduceJugadores();
        while(!partida.fin()){
            partida.rondaNueva(); 
            System.out.println("\n");
            System.out.println("Ronda: "+partida.numRonda());
            int i = 0;
            while (true) {                    
                String resp = Terminal.input(partida.mostrarJugador(i)+" pulse enter para tirar");
                partida.tiraDados(i);
                mostrarDado();
                System.out.println("Tiene: "+partida.verPuntos(i)+" puntos");
                i++;
                if(partida.total()==i) break;
            }
            System.out.println("-----------------------");
            mostrarPuntos();
            System.out.println("-----------------------");
            String resp = Terminal.input("");
            if(!partida.todos69()) ganador69();
            partida.comprobarRonda();
            if(!partida.comprobarSiHayDesempate())partida.asignarMondadientes();
            if(partida.total()==0) break;
            if(partida.comprobarSiHayDesempate()) {
                if(partida.total()>2) {
                    System.out.println("\nHay ronda de desempate");
                    while (true) { 
                        desempatar();
                        if(!partida.comprobarDesempate()) break;                     
                    }
                    partida.asignarMondadientesDesempate();
                    mostrarMondadientes();
                }
            }
            if(!partida.comprobarSiHayDesempate())mostrarMondadientes();              
        }
        System.out.println("\nPaga la ronda "+partida.mostrarJugador(0));
    }
    
    public static void main(String[] args) {
    new Interfaz().run();    
    }
}