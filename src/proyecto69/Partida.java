package proyecto69;

import java.util.ArrayList;

/**
 * proyeto 69
 * @author Sergio
 */
public class Partida {
    //almacena los jugadores en la partida
    public final ArrayList<Jugador> jugadores;
    //Puntuacion de los jugadores
    public ArrayList puntuacion;
    //Puntuacion de los jugadores
    public ArrayList puntuacionDesempate;
    //Los dados a utilizar
    private final Dado[] dados;
    //El valor de los dados
    private final int[] dadoValor;
    //El numero de dados
    private final int numDados=3;
    //Número de ronda
    private int ronda;
    //Minimo jugadores
    private final int minJug = 2;

    
    /**
     * Constructor
     */
    public Partida(){
        jugadores = new ArrayList<>();
        puntuacion = new ArrayList<>();
        puntuacionDesempate = new ArrayList<>();
        dados = new Dado[numDados];
        dadoValor = new int[numDados];
        ronda = 0;
    }
    
    /**
     * El total de jugadores activos
     * @return el número de jugadores
     */
    public int total() {
        return jugadores.size();
    }
    
    /**
     * La cantidad de dados
     * @return el número de dados
     */
    public int cantidadDados(){
        return numDados;
    }
    
    /**
     * Suma una ronda.
     * @return el número de rondas ya inclementada
     */
    public int rondaNueva() {
        return ronda++;
    }
    
    /**
     * muestra el número de rondas
     * @return la ronda por la que vá
     */
    public int numRonda() {
        return ronda;
    }
    
    /**
     * Comprueba que se introduce el mínimo de jugadores.
     * @param jugadores se le introduce el número de jugadores que van a entrar
     * @return true si el numero es mayor o igual al mínimo. false, si el numero es menor al mínimo.
     */
    public boolean minimoJugadores(int jugadores) {
        return jugadores >= minJug;
    }
    
    /**
     * Comprueba que se haya finalizado la partida, sabiendo esto si solo queda un jugador.
     * @return true si hay uno o menos jugadores. false si hay mas de un jugador.
     */
    public boolean fin(){
        return total() <= 1;
    }
    
    /**
     * muestra el jugador que hay en las posición indicada.
     * @param pos la posición donde esta el jugador
     * @return el jugador a mostrar.
     */
    public Jugador mostrarJugador(int pos){
        return jugadores.get(pos);
    }
    
    /**
     * Elimina un jugador
     * @param pos la posición donde está el jugador.
     */
    private void quitarJugador(int pos){
        jugadores.remove(pos);
    }
    
    /**
    * Añade un jugador a la partida
    * @param jugador  El jugador a añadir
    * @return devuelve el jugador
    */
    public Jugador agregarJugador(Jugador jugador){
        jugadores.add(jugador);
        return jugador;
    }
    
    /**
     * Si todos sacan 69
     * @return true si todos han sacado 69, false si alguno no ha sacado 69
     */
    public boolean todos69() {
        int cont = 0;
        for (int i = 0; i < total(); i++) {
            if(es69(verPuntos(i))){
                cont++;
            }            
        }
        return cont==total();
    }
    
    /**
     * asigna los mondadientes que le corres ponde a cada jugador
     */
    public void asignarMondadientes(){
        int cont = 0;
        int min= puntuacionMinima();
        for (int i = 0; i < total(); i++) {
            if(min!=verPuntos(i)){
                quitarMondadiente(mostrarJugador(i));
                cont++;
            }
        }
        for (int i = 0; i < total(); i++) {
            
            if(min==verPuntos(i)){
                agregarMondadientes(mostrarJugador(i),cont);
            } 
        }
    }
    
    /**
     * asigna los mondadientes que le corres ponde a cada jugador en caso de empate
     */
    public void asignarMondadientesDesempate(){
        int min = puntuacionMinima();
        int minDes=puntuacionMinimaDesempate();
        int cont = 0;
        int cont2=0;
        int jugador = 0;
        for (int i = 0; i < total(); i++) {
            if(min!=verPuntos(i)){
                //System.out.println(min+" "+verPuntos(i));
                quitarMondadiente(mostrarJugador(i));
                cont++;
            }
            else{
                if(minDes!=verPuntosDesempate(cont2)){
                    //System.out.println(minDes+" "+verPuntosDesempate(i));
                    quitarMondadiente(mostrarJugador(i));
                    cont++;
                    cont2++;
                }
                else{
                    jugador = i;
                    cont2++;
                }
            }
        }
        agregarMondadientes(mostrarJugador(jugador),cont);   
    }
    
    
    /**
     * Comprueba el número de mondadientes de cada jugador, en caso de que no tenga, se elimina el jugador.
     */
    public void comprobarMondadientes(){
        for (int i = 0; i < total(); i++) {
            if(numMondadientes(mostrarJugador(i))<=0){
                quitarJugador(i);    
            }
        }
    }
    
    /**
     * comprueba la ronda, si todos tienen 69, sigue a la siguiente ronda, si alguno tiene 69, lo elimina.
     */
    public void comprobarRonda() {
        int cont = 0;
        for (int i = 0; i < total(); i++) {
            if(todos69()){
                break;
            }    
            else if(es69(verPuntos(i))) {
                quitarJugador(i);
            }
        }
    }

    /**
     * hace una tirada el jugador de la posición indicada
     * @param pos la posición del jugador 
     */
    public void tiraDados(int pos){
        int punt=0;
        for(int i=0; i<dados.length; i++){
            dados[i]=Dado.tirarDado();
            dadoValor[i]=valorFacial(i);
        }
        for(Dado d : dados) {
            punt=punt+d.getPuntuacion();
        }
        puntuacion.add(pos, punt);  
    }  

    /**
     * hace una tirada el jugador de la posición indicada en caso de empate
     * @param pos la posicion
     */
    public void tiraDadosDesempate(int pos){
        int punt=0;
        for(int i=0; i<dados.length; i++){
            dados[i]=Dado.tirarDado();
            dadoValor[i]=valorFacial(i);
        }
        for(Dado d : dados) {
            punt=punt+d.getPuntuacion();
        }
        puntuacionDesempate.add(pos, punt);  
    }

    /**
     * comprueba que ha sacado un 69.
     * @param punt los puntos que se le introduce
     * @return true si los puntos introducidos son igual a 69, false si no
     */
    public boolean es69(int punt){
        return punt ==69;
    }
    
    /**
     * devuelve el valor facial del dado
     * @param pos la posición del dado
     * @return el valor facial del dado
     */
    public int valorFacial(int pos){
       return dados[pos].getValorFacial();
    }
    
    /**
     * el valor de los dados(sus puntos)
     * @param pos la posicion de los dados
     * @return el valor del dado.
     */
    public int valorDados(int pos){
        return dadoValor[pos];
    }
    
    /**
     * Saca la puntuación mínima de todas las puntuaciones de la ronda.
     * @return la puntuación minima.
     */
    public int puntuacionMinima(){
        int minima= 400;
        for(int i=0; i<total(); i++){
            int punt = (int)puntuacion.get(i);
            if(punt<=minima ){
               minima=(int)punt; 
            }
            else if(punt == 69);
        }
        return minima;   
    }
    
    /**
     * Saca la puntuación mínima de todas las puntuaciones de desempate de la ronda.
     * @return la puntuación minima del desempate.
     */
    public int puntuacionMinimaDesempate() {
        int minima= 400;
        for(int i=0; i<puntuacionDesempate.size(); i++){
            int punt = (int)puntuacionDesempate.get(i);
            if(punt<=minima ){
               minima=(int)punt; 
            }
            else if(es69(punt));
        }
        return minima;
        
    }
    /**
     * muestra los puntos segun la posición
     * @param pos la posición de los puntos
     * @return los puntos
     */
    public int verPuntos(int pos){
        return (int) puntuacion.get(pos);
    }
    
    /**
     * muestra los puntos de desempate segun la posición
     * @param pos la posición de los puntos
     * @return  los puntos
     */
    public int verPuntosDesempate(int pos){
        return (int) puntuacionDesempate.get(pos);
    }
    
    /**
     * comprueba que haya 2 o mas jugadores con la puntuación mínima
     * @return true si hay mas de un jugador con la puntuación mínima false si solo hay uno con puntuación mínima.
     */
    public boolean comprobarSiHayDesempate() {
        int min=puntuacionMinima();
        int cont = 0;
        for (int i = 0; i < total(); i++) {
            if(puntuacion.get(i).equals(min)) {
            cont++;
            }
            else continue;
        }
        return cont>1;
    }
    
    
    /**
     * comprueba que haya 2 o mas jugadores con la puntuación mínima en el desempate.
     * @return true si hay mas de un jugador con la puntuación mínima false si solo hay uno con puntuación mínima.
     */
    public boolean comprobarDesempate() {
        int min=puntuacionMinimaDesempate();
        int cont = 0;
        for (int i = 0; i < puntuacionDesempate.size(); i++) {
            if(puntuacionDesempate.get(i).equals(min) || es69(verPuntos(i))) {
            cont++;
            }
            else continue;
        }
        return cont>1;
    }
    
    /**
     * el numero de mondadientes del jugador indicado.
     * @param jugador el jugador a ver mondadientes
     * @return el jugador
     */
    public int numMondadientes(Jugador jugador){
       return jugador.verMondadientes();
    }
    
    /**
     * quita mondadientes al jugador indicado
     * @param jugador el jugador a quitar mondadiente
     */
    private void quitarMondadiente(Jugador jugador){
        jugador.quitaMondadiente();
    }
    
    /**
     * agrega mondadientes al jugador indicado
     * @param jugador el jugador a poner mondadientes
     * @param cantidad  la cantidad de mondadientes.
     */
    private void agregarMondadientes(Jugador jugador, int cantidad){
        jugador.agregarMondadientes(cantidad);
    }     
}
