/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Application;

import Baralles.*;
import Cartas.CartaInterfaz;
import TipoBaralla.BarallaEspanyola;
import TipoBaralla.BarallaUno;
import java.util.*;
import static utils.UIUtilities.*;

/**
 *
 * @author Mario
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BarallaInterfaz baralla1 = escollirBaralla();
        int opcio = 0;
        do {
            opcio = Menu("Barrejar", "Seguent carta",
                    "Cartes disponibles", "Demanar cartes",
                    "Repartir cartes", "Veure munt", "Reiniciar",
                    "Sortir");
            clearScreen();
            switch (opcio) {
                case 2:
                    List<CartaInterfaz> cartaRepartida = baralla1.repartirCartes(1);
                    System.out.println(cartaRepartida.get(0).getName());
                    break;
                case 6:
                    for (Object carta : baralla1.veureMunt()) {
                        System.out.println(((CartaInterfaz) carta).getName());
                    }
                    break;
                default:
                    break;
            }
        } while (opcio != 8);

    }

    private static BarallaInterfaz escollirBaralla() throws AssertionError {
        int opcioBaralla = MenuClassesInPackage("TipoBaralla");
        BarallaInterfaz barallaEscollida;
        switch (opcioBaralla) {
            case 1:
                barallaEscollida = new BarallaEspecial(BarallaUno.getPalos()
                        , BarallaUno.getNombresCarta()
                        , BarallaUno.getCartasEspeciales());
                break;
            case 2:
                barallaEscollida = new BarallaSimple(BarallaEspanyola.getPalos()
                        ,BarallaEspanyola.getNombresCarta());
                break;
            default:
                throw new AssertionError();
        }
        return barallaEscollida;
    }

}
