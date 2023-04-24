/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TipoBaralla;

import Cartas.CartaEspecial;
import Cartas.CartaInterfaz;
import java.util.ArrayList;
import java.util.Arrays;
import static utils.UIUtilities.*;

/**
 *
 * @author ausias
 */
public class BarallaUno {

    /**
     * @return the cartasEspeciales
     */
    public static ArrayList<CartaInterfaz> getCartasEspeciales() {
        return cartasEspeciales;
    }
    private static final ArrayList<String> palos = new ArrayList<>(
            Arrays.asList("Rojo", "Amarillo", "Azul", "Verde"));
    private static final ArrayList<String> numerosCarta = new ArrayList<>(
            Arrays.asList("0 ","1 ","1 ", "2 ","2 ", "3 ","3 ", "4 ","4 ", "5 ","5 ", 
                "6 ","6 ", "7 ","7 ", "8 ","8 ", "9 ","9 ", "Pierde Turno ","Pierde Turno ", 
                "Cambio de Sentido ","Cambio de Sentido ", "+2 ","+2 "));
    private static final ArrayList<CartaInterfaz> cartasEspeciales = new ArrayList<>
        (Arrays.asList(new CartaEspecial("Comodin ", "Comodin "),new CartaEspecial("Comodin ", "Comodin ")
                ,new CartaEspecial("Comodin ", "Comodin "),new CartaEspecial("Comodin ", "Comodin ")
                ,new CartaEspecial("+4 ", "+4 "),new CartaEspecial("+4 ", "+4 ")
                ,new CartaEspecial("+4 ", "+4 "),new CartaEspecial("+4 ", "+4 ")));

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
        return numerosCarta;
    }
}
