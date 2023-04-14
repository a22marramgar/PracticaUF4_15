/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Partida;

import Jugadors.JugadorInterface;

/**
 *
 * @author Mario
 */
public interface ControlInterface {
    public void iniciarPartida();
    public boolean iniciarTurno(JugadorInterface jugador);
    public JugadorInterface getTurno();
    /**
     * 
     * @return true ascendente, false descendente
     */
    public boolean getSentido();
    public void invertirSentido();
    public void pasarTurno();
}
