/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practicauf4_15;

import Baralles.*;
import Cartas.CartaInterfaz;
import Cartas.CartaSimple;
import TipoBaralla.BarallaEspanyola;
import java.util.*;
import static utils.UIUtilities.*;

/**
 *
 * @author ausias
 */
public class PracticaUF4_15 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int nCartes;

        BarallaInterfaz baralla1 = new BarallaSimple(BarallaEspanyola.getPalos(), BarallaEspanyola.getNombresCarta());
        int opcio = 0;
        do {
            opcio = Menu("Barrejar", "Seguent carta",
                    "Cartes disponibles", "Demanar cartes",
                    "Repartir cartes", "Veure munt", "Reiniciar",
                    "Sortir");
            switch (opcio) {
                case 1:
                    baralla1.barallar();
                    break;
                case 2:
                    baralla1.repartirCartes(1);
                    break;
                case 3:
                    System.out.println(baralla1.cartesDisponibles());
                    break;
                case 4:
                    nCartes = llegirInt("Quantes cartes es demanen?");
                    List<CartaSimple> listaRepartida = baralla1.repartirCartes(nCartes);
                    if (listaRepartida.isEmpty()) {
                        System.out.println("No hi ha cartes suficients");
                    } else {
                        System.out.println("Cartes repartides: ");
                        for (Object carta : listaRepartida) {
                            System.out.println(((CartaInterfaz) carta).getName());
                        }
                    }
                    break;
                case 5:
                    nCartes = llegirInt("Quantes cartes vols repartir?");
                    System.out.println("Cartes repartides: ");
                    for (Object carta : baralla1.repartirCartes(nCartes, true)) {
                        System.out.println(((CartaInterfaz) carta).getName());
                    }
                    break;
                case 6:
                    for (Object carta : baralla1.veureMunt()) {
                        System.out.println(((CartaInterfaz) carta).getName());
                    }
                    break;
                case 7:
                    baralla1 = new BarallaSimple(BarallaEspanyola.getPalos(), BarallaEspanyola.getNombresCarta());
                    break;
                default:
                    break;
            }
        } while (opcio != 8);

    }

}
