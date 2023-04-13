/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Partida;

import Cartas.CartaInterfaz;
import Jugadors.JugadorInterface;
import java.util.List;

/**
 *
 * @author Mario
 */
public interface ControlInterface {
    public void iniciarPartida();
    public void iniciarTurno(JugadorInterface jugador);
    public void mostrarCartas(List<CartaInterfaz> cartas);
    public JugadorInterface getTurno();
    /**
     * 
     * @return true ascendente, false descendente
     */
    public boolean getSentido();
    public void invertirSentido();
    public void pasarTurno();
}
