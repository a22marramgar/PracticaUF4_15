/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Application;

import Baralles.*;
import Jugadors.Jugador;
import Jugadors.JugadorInterface;
import Partida.*;
import TipoBaralla.BarallaUno;
import java.util.*;
import UI.MenuUI;
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
        /*BarallaInterfaz baralla1 = new BarallaEspecial(BarallaUno.getPalos()
                        , BarallaUno.getNumerosCarta()
                        , BarallaUno.getCartasEspeciales());
        int nJugadores = llegirInt("Numero de jugadors: ");
        ArrayList<JugadorInterface> jugadores = new ArrayList<>();
        for (int i = 0; i < nJugadores; i++) {
            JugadorInterface nuevoJugador = new Jugador();
            jugadores.add(nuevoJugador);
        }
        ControlInterface partida = new Control(baralla1, jugadores);
        partida.iniciarPartida();*/
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuUI().setVisible(true);
            }
        });

    }

}
