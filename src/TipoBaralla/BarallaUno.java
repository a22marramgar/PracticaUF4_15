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
            Arrays.asList(ANSI_RED+"Rojo"+ANSI_RESET, ANSI_YELLOW+"Amarillo"+ANSI_RESET
                    , ANSI_BLUE+"Azul"+ANSI_RESET, ANSI_GREEN+"Verde"+ANSI_RESET));
    private static final ArrayList<String> nombresCarta = new ArrayList<>(
            Arrays.asList("0 ","1 ","1 ", "2 ","2 ", "3 ","3 ", "4 ","4 ", "5 ","5 ", 
                "6 ","6 ", "7 ","7 ", "8 ","8 ", "9 ","9 ", "Pierde Turno ","Pierde Turno ", 
                "Cambio de Sentido ","Cambio de Sentido ", "+2 ","+2 "));
    private static final ArrayList<CartaInterfaz> cartasEspeciales = new ArrayList<>
        (Arrays.asList(new CartaEspecial(0, "Comodin"),new CartaEspecial(0, "Comodin")
                ,new CartaEspecial(0, "Comodin"),new CartaEspecial(0, "Comodin")
                ,new CartaEspecial(0, "+4 negro"),new CartaEspecial(0, "+4 negro")
                ,new CartaEspecial(0, "+4 negro"),new CartaEspecial(0, "+4 negro")));

    /**
     * @return the palos
     */
    public static ArrayList<String> getPalos() {
        return palos;
    }

    /**
     * @return the nombresCarta
     */
    public static ArrayList<String> getNombresCarta() {
        return nombresCarta;
    }
}
