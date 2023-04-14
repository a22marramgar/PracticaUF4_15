package utils;

import Cartas.CartaInterfaz;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Clase de utilidades
 *
 * @author Mario
 *
 */
public class UIUtilities {
    
    public static final String ANSI_RESET = "\u001B[0m";
public static final String ANSI_BLACK = "\u001B[30m";
public static final String ANSI_RED = "\u001B[31m";
public static final String ANSI_GREEN = "\u001B[32m";
public static final String ANSI_YELLOW = "\u001B[33m";
public static final String ANSI_BLUE = "\u001B[34m";
public static final String ANSI_PURPLE = "\u001B[35m";
public static final String ANSI_CYAN = "\u001B[36m";
public static final String ANSI_WHITE = "\u001B[37m";

    private static Scanner input = new Scanner(System.in);

    /**
     * Utilitza l'escaner per assegurar que hem introduit un valor enter
     *
     * @return int
     */
    public static int llegirInt() {
        int resultat = 1;
        boolean valid = false;
        do {
            valid = input.hasNextInt();
            if (valid) {
                resultat = input.nextInt();
                input.nextLine();
            } else if (input.hasNext()) {
                input.nextLine();
                System.out.println("Valor incorrecte");
            }
        } while (!valid);
        return resultat;
    }

    /**
     * Demana un valor de tipus Float
     *
     * @param f Text que es mostra per pantalla per a demanar un float
     * @return float
     */
    public static float llegirFloat(String f) {
        float numero = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print(f);
            try {
                numero = input.nextFloat();
                input.nextLine();
                valido = true;
            } catch (Exception e) {
                input = new Scanner(System.in);
                System.err.println("Error: " + e);
                System.out.println("Vuelve a intentarlo");
            }
        }

        return numero;
    }

    /**
     * Demana un valor de tipus double
     *
     * @param f Text que es mostra per pantalla per a demanar un double
     * @return double
     */
    public static double llegirDouble(String f) {
        double numero = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print(f);
            try {
                numero = input.nextDouble();
                input.nextLine();
                valido = true;
            } catch (Exception e) {
                input = new Scanner(System.in);
                System.err.println("Error: " + e);
                System.out.println("Vuelve a intentarlo");
            }
        }

        return numero;
    }

    /**
     * Demana un valor de tipus int
     *
     * @param f Text que es mostra per pantalla per a demanar un int
     * @return int
     */
    public static int llegirInt(String f) {
        int numero = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print(f);
            try {
                numero = input.nextInt();
                input.nextLine();
                valido = true;
            } catch (Exception e) {
                input = new Scanner(System.in);
                System.err.println("Error: " + e);
                System.out.println("Vuelve a intentarlo");
            }
        }

        return numero;
    }

    /**
     * Demana un valor de tipus String
     *
     * @param f Text que es mostra per pantalla per a demanar un String
     * @return String
     */
    public static String llegirString(String f) {
        String texto = null;
        boolean valido = false;
        while (!valido) {
            System.out.print(f);
            try {
                texto = input.nextLine();
                valido = true;
            } catch (Exception e) {
                input = new Scanner(System.in);
                System.err.println("Error: " + e);
                System.out.println("Vuelve a intentarlo");
            }
        }
        return texto;
    }

    /**
     * Agafa un int entre un rang de valors
     *
     * @param max int més gran
     * @param min int més petit
     * @param mensaje Mensaje que se mostrara cuando el numero no cumpla las
     * condiciones
     * @return int que es trobi en el rang especificat
     */
    public static int escollirOpcio(int min, int max, String mensaje) {
        int opcio = 0;
        boolean valid = false;
        do {
            opcio = llegirInt();
            if (opcio <= max && opcio >= min) {
                valid = true;
            } else {
                System.out.println(mensaje);
            }
        } while (!valid);
        return opcio;
    }

    /**
     * Buida la consola afegint salts de linia
     */
    public static void clearScreen() {
        System.out.print("\n\n\n\n\n\n\n\n\n\n");
        System.out.flush();
    }

    /**
     * Funcion para mostrar por pantalla un menu y elegir una opcion
     *
     * @param opciones Las opciones que tendra el menu en String
     * @return int opcio
     */
    public static int Menu(String... opciones) {
        int opcio;
        int num = 1;
        System.out.println("---------------");
        for (String a : opciones) {
            System.out.println(num + ". " + a);
            num++;
        }
        opcio = escollirOpcio(1, num, "Invalid");
        return opcio;
    }

    /**
     * Funcion para mostrar por pantalla un menu y elegir una opcion
     *
     * @param datos ArrayList de Strings con las opciones
     * @return la opcion
     */
    public static int MenuAL(List<String> datos) {
        int opcio;
        int num = 1;
        System.out.println("--------------");
        for (String a : datos) {
            System.out.println(num + ". " + a);
            num++;
        }
        opcio = escollirOpcio(1, num, "Invalid");
        return opcio;
    }
    
     /**
     * Funcion para mostrar por pantalla un menu y elegir una opcion
     *
     * @return la opcion
     */
    public static int MenuCartas(List<CartaInterfaz> cartas) {
        int opcio;
        int num = 1;
        System.out.println("--------------");
        for (CartaInterfaz a : cartas) {
            System.out.println(num + ". " + a.getName());
            num++;
        }
        System.out.println(num + ". Robar");
        num++;
        opcio = escollirOpcio(1, num, "Invalid");
        return opcio;
    }

    /**
     * Funcion para mostrar por pantalla un menu y elegir una opcion
     *
     * @param packageName
     * @return la opcion
     */
    public static int MenuClassesInPackage(String packageName) {
        AccessingAllClassesInPackage instance = new AccessingAllClassesInPackage();

        Set<Class> classes = instance.findAllClassesUsingClassLoader(
                packageName);
        int opcio;
        int num = 1;
        System.out.println("--------------");
        for (Class clase : classes) {
            System.out.println(num + ". " + clase.getSimpleName());
            num++;
        }
        opcio = escollirOpcio(1, num, "Invalid");
        return opcio;
    }

    /**
     * Funcion para centrar texto al imprimir una tabla en rango determinado
     *
     * @param texto el texto añadir
     * @param espacio el numero de espacios que tendra
     * @return el texto ya formateado
     */
    public static String CentrarTexto(String texto, int espacio) {
        int espIzq = (espacio - texto.length()) / 2;
        texto = String.format("%" + (espIzq + texto.length()) + "s", texto);
        texto = String.format("%-" + espacio + "s", texto);
        return texto;
    }

}
