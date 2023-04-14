/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TipoBaralla;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Mario
 */
public class BarallaEspanyola {
    private static final ArrayList<String> palos = new ArrayList<>(Arrays.asList("Copas", "Bastos", "Espadas", "Oros"));
    private static final ArrayList<String> nombresCarta = new ArrayList<>(Arrays.asList("As de ", "2 de ", "3 de ", "4 de ", "5 de ", 
                "6 de ", "7 de ", "8 de ", "9 de ","Sota de ", "Caballo de ", "Rey de "));

    /**
     * @return the palos
     */
    public static ArrayList<String> getPalos() {
        return palos;
    }

    /**
     * @return the nombresCarta
     */
    public static ArrayList<String> getNumerosCarta() {
        return nombresCarta;
    }
}
