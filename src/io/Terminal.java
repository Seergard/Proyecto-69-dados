// Importante: para poder exportarse IOTerminal a otro
// proyecto, debe estar metida en un paquete.
package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Permite la lectura de datos de teclado.
 */
public class Terminal {

   private static BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

   /**
    * Obtiene una cadena del teclado
    * @param mensaje Texto de peticiÃ³n
    * @return La cadena introducida
    */
   public static String input(String mensaje) {
      System.out.print(mensaje);
      try {
         return console.readLine();
      }
      catch(IOException e) {
         e.printStackTrace();
         assert false;
         return null;
      }
   }

   /**
    * Obtiene un nÃºmero entero del teclado
    * @param mensaje Texto de peticiÃ³n
    * @param defaultValue  NÃºmero predeterminado, si no se escribe nada.
    * @return El nÃºmero introducido
    */
   public static int inputInt(String mensaje, Integer defaultValue) {
      while(true) {
         String line = input(mensaje);
         if(line.isEmpty() && defaultValue!=null) return defaultValue;
         try {
            return Integer.parseInt(line);
         }
         catch(NumberFormatException e) {
            System.out.println("No ha escrito un entero");
         }
      }
   }

   public static int inputInt(String mensaje) {
      return inputInt(mensaje, null);
   }

   /**
    * Obtiene un nÃºmero real de teclado
    * @param mensaje Texto de peticiÃ³n
    * @param defaultValue  NÃºmero predeterminado, si no se escribe nada.
    * @return El nÃºmero real expresado como double
    */
   public static double inputDouble(String mensaje, Double defaultValue) {
      while(true) {
         String line = input(mensaje);
         if(line.isEmpty() && defaultValue!=null) return defaultValue;
         try {
            return Double.parseDouble(line);
         }
         catch(NumberFormatException e) {
            System.out.println("No ha escrito un número real");
         }
      }
   }

   public static double inputDouble(String mensaje) {
      return inputDouble(mensaje, null);
   } 

   public static int menu(String[] opciones) {
      return menu(opciones, ">>> Elija una opción: ", false);
   }

   public static int menu(String[] opciones, boolean cancelar) {
      return menu(opciones, ">>> Elija una opción: ", cancelar);
   }

   public static int menu(String[] opciones, String leyenda) {
      return menu(opciones, leyenda, false);
   }

   /**
    * Muestra un menÃº por pantalla y devuelve la opciÃ³n elegida
    * @param  opciones  La leyenda de cada Ã­tem que se mostrarÃ¡ por pantalla.
    * @param  leyenda   Texto con que se requerirÃ¡ hacer la selecciÃ³n.
    * @param  cancelar  Si se desea incluir la posibilidad de cancelar la elecciÃ³n
    * @return La posiciÃ³n de la opciÃ³n el elegida (se empieza en 0,
    *         aunque en el menÃº se muestre un 1). Se devuelve -1, si se
    *         cancela la elecciÃ³n.
    */
   public static int menu(String[] opciones, String leyenda, boolean cancelar) {
      if(opciones.length == 0) return -1;

      int eleccion;

      for(int i=0; i<opciones.length ; i++) {
         System.out.printf("[%d] %s\n", i+1, opciones[i]);
      }
      if(cancelar) {
         System.out.println("[0] Cancelar");
      }
      System.out.println("------------");
      while(true) {
         eleccion = inputInt(leyenda);
         if(eleccion<1 || eleccion>opciones.length) {
            if(!cancelar || eleccion != 0) {
               System.err.println("ERROR. Opción inválida");
               continue;
            }
         }
         break;
      }
      return eleccion-1;
   }

   /**
    * Permite obtener una respuesta afirmativa o negativa.
    * @param mensaje El testo de la pregunta a la que hay que responder
    * @return true, si se respondiÃ³ que sÃ­.
    */
   public static boolean sino(String mensaje) {
      while(true) {
         String resp = input(mensaje).toLowerCase();
         switch(resp) {
            case "s":
            case "si":
            case "sí":
               return true;
            case "n":
            case "no":
               return false;
            default:
               System.err.println("ERROR. Debe responder 's' o 'n'.");
         }
      }
   }

   public static void main(String[] args) {
      int num = Terminal.inputInt("Escribe algo: ", 0);
      System.out.println("Número escrito: " + num);
   }
}