/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto69;

import java.util.Random;

/**
 *
 * @author Sergio
 */
enum Dado {
   uno(100),
   dos(2),
   tres(3),
   cuatro(4),
   cinco(5),
   seis(60);

    //Crea el azar
    private static Random generador = new Random();
    //La puntos que aporta cada valor del dado
    private final int puntuacion;
    
   /**
    * Constructor
    * @param puntuacion 
    */
    private Dado (int puntuacion) {
        this.puntuacion = puntuacion;
    }
    
    /**
     * devuelve la puntuación
     * @return la puntuación
     */
    public int getPuntuacion() {
        return puntuacion;
    }      
    
    /**
     * Tirada del dado
     * @return El valor del dado
     */
    public static Dado tirarDado(){        
        int posicion = generador.nextInt(Dado.values().length);
        return Dado.values()[posicion];
    }
    
    /**
     * muestra el valor facial del dado
     * @return el valor facial
     */
    public int getValorFacial(){
        return ordinal()+1;
    }

}   
