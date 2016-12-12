/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Random;

public class MiRandom {

   /** Generador de nÃºmeros aleatorias */
   private static Random generador = new Random();
      
   /**
    * Obtiene una cantidad de nÃºmeros aleatorios entre 0
    * y un nÃºmero mÃ¡ximo (sin incluir).
    * @param mayor     LÃ­mite superior
    * @param cantidad  Cantidad de nÃºmeros aleatorias a obtener.
    * @return  Los nÃºmeros aletorios obtenidos
    */
   public static int[] obtenerAleatorios(int mayor, int cantidad) {
      int[] resp = new int[cantidad];
      for(int i=0; i<cantidad; i++) {
         int aleatorio = generador.nextInt(mayor-i);
         int j;
         for(j=0; j<i; j++) {
            if(resp[j]>aleatorio) break;
            aleatorio++;
         }
         for(int k=i-1; k>=j; k--) {
            resp[k+1] = resp[k];
         }
         resp[j] = aleatorio;
      }
      return resp;
   }

   /**
    * Desordena un conjunto de nÃºmeros incluidos en un array.
    * @param numeros  El conjunto de nÃºmeros.
    */
   public static void desordenar(int[] numeros) {
      //Algoritmo de Fisher-Yates
      for(int i=numeros.length-1; i>0; i--) {
         int idx = generador.nextInt(i+1);
         int num = numeros[idx];
         numeros[idx] = numeros[i];
         numeros[i] = num;
      }
   }
}
