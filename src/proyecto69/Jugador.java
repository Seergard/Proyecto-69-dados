/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto69;

/**
 *
 * @author Sergio
 */
public class Jugador {
    //Nombre del jugador
    private String nombre;
    //Sus mondadientes de inicio
    private int mondadientes;
    
    /**
     * constructor 
     * @param nom el nombre del jugador
     */
    public Jugador(String nom){
        this.nombre(nom);
        this.mondadientes=5;
    }
    
    /**
     * getter de nombre
     * @return el nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * da el nombre
     * @param nombre  el nombre del jugador
     */
    public void nombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * ver los mondadientes.
     * @return numero de mondadientes
     */
    public int verMondadientes() {
        return mondadientes;
    }
    
    /**
     * El jugador quita un mondadiente
     */
    public void quitaMondadiente(){
       mondadientes--;
    }
    
    @Override
    public String toString(){
        return nombre;
    }
    
    /**
     * El jugador recibe los mondadientes
     * @param cantidad Los mondadientes que recibe
     */
    public void agregarMondadientes(int cantidad){
        mondadientes=mondadientes + cantidad;    
    }   
}
