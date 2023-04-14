/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Application;

import Baralles.*;
import Cartas.CartaInterfaz;
import Jugadors.Jugador;
import Jugadors.JugadorInterface;
import Partida.Control;
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
        BarallaInterfaz baralla1 = new BarallaEspecial(BarallaUno.getPalos()
                        , BarallaUno.getNumerosCarta()
                        , BarallaUno.getCartasEspeciales());
        int nJugadores = llegirInt("Numero de jugadors: ");
        ArrayList<JugadorInterface> jugadores = new ArrayList<>();
        for (int i = 0; i < nJugadores; i++) {
            JugadorInterface nuevoJugador = new Jugador();
            jugadores.add(nuevoJugador);
        }
        Control partida = new Control(baralla1, jugadores);
        partida.iniciarPartida();

    }

    /**
     * NO FUNCIONA BIEN
     * @return
     * @throws AssertionError 
     */
    private static BarallaInterfaz escollirBaralla() throws AssertionError {
        int opcioBaralla = MenuClassesInPackage("TipoBaralla");
        BarallaInterfaz barallaEscollida;
        switch (opcioBaralla) {
            case 1:
                barallaEscollida = new BarallaEspecial(BarallaUno.getPalos()
                        , BarallaUno.getNumerosCarta()
                        , BarallaUno.getCartasEspeciales());
                break;
            case 2:
                barallaEscollida = new BarallaSimple(BarallaEspanyola.getPalos()
                        ,BarallaEspanyola.getNumerosCarta());
                break;
            default:
                throw new AssertionError();
        }
        return barallaEscollida;
    }

}
