/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Partida;

import Baralles.BarallaInterfaz;
import Cartas.CartaInterfaz;
import Jugadors.JugadorInterface;
import java.util.ArrayList;
import java.util.List;
import utils.UIUtilities;

/**
 *
 * @author Mario
 */
public class Control implements ControlInterface{

    private BarallaInterfaz _baralla;
    private ArrayList<JugadorInterface> _jugadores;
    private int _turno;
    private boolean _sentido;
    
    public Control(BarallaInterfaz baralla, List<JugadorInterface> jugadores){
        this._baralla = baralla;
        this._jugadores = new ArrayList<>();
        for (JugadorInterface jugador : jugadores) {
            this._jugadores.add(jugador);
        }
        this._sentido = true;
        this._turno = 1;
    }
    
    @Override
    public void iniciarPartida() {
        boolean finPartida = false;
        while(!finPartida){
            iniciarTurno(getTurno());
            
        }
    }

    @Override
    public void iniciarTurno(JugadorInterface jugador) {
        mostrarCartas(jugador.getMano());
        
    }

    @Override
    public JugadorInterface getTurno() {
        return this._jugadores.get(this._turno-1);
    }

    @Override
    public boolean getSentido() {
        return this._sentido;
    }

    @Override
    public void invertirSentido() {
        this._sentido = !this._sentido;
    }

    @Override
    public void pasarTurno() {
        if(this._sentido){
            this._turno++;
            if(this._turno == this._jugadores.size()+1){
                this._turno = 0;
            }
        }else{
            this._turno--;
            if(this._turno == 0){
                this._turno = this._jugadores.size();
            }
        }
    }

    @Override
    public void mostrarCartas(List<CartaInterfaz> cartas) {
        UIUtilities.MenuCartas(cartas);
    }
    
}
