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
import static utils.UIUtilities.*;

/**
 *
 * @author Mario
 */
public class Control implements ControlInterface {

    private BarallaInterfaz _baralla;
    private ArrayList<JugadorInterface> _jugadores;
    private int _turno;
    private CartaInterfaz _efectoEnJuego;
    private int _efectoRobarCartas;
    private boolean _sentido;

    public Control(BarallaInterfaz baralla, List<JugadorInterface> jugadores) {
        this._baralla = baralla;
        this._baralla.barallar();
        this._jugadores = new ArrayList<>();
        for (JugadorInterface jugador : jugadores) {
            this._jugadores.add(jugador);
            jugador.addCartas(this._baralla.repartirCartes(7));
        }
        this._sentido = true;
        this._turno = 1;
        this._efectoEnJuego = null;

    }

    @Override
    public void iniciarPartida() {
        boolean finPartida = false;
        this._baralla.afegirMunt(this._baralla.repartirCartes(1).get(0));
        while (!finPartida) {
            System.out.println("En juego: " + this._baralla.veureMunt().get(this._baralla.veureMunt().size() - 1).getName());
            System.out.println("Turno del jugador " + getTurno().getId());
            boolean efectoResuelto = resolverEfectos(getTurno());
            if (efectoResuelto) {
                finPartida = iniciarTurno(getTurno());
            }
            if (!finPartida) {
                pasarTurno();
            }
        }
    }

    @Override
    public boolean iniciarTurno(JugadorInterface jugador) {
        int opcio = 0;
        boolean accioFeta = false;
        do {
            opcio = MenuCartas(jugador.getMano());
            if (opcio <= jugador.getMano().size()) {
                accioFeta = this._baralla.afegirMunt(jugador.getMano().get(opcio - 1));
                if (accioFeta) {
                    jugador.getMano().remove(opcio - 1);
                }
                usarCarta();
            } else {
                accioFeta = jugador.addCartas(this._baralla.repartirCartes(1));
                if (!accioFeta) {
                    System.out.println("No hay cartas, pasar turno?");
                    if (Menu("Si", "No") == 1) {
                        accioFeta = true;
                    }
                }

            }
        } while (!accioFeta);

        return jugador.getMano().isEmpty();
    }

    private void usarCarta() {
        CartaInterfaz cartaJugada = this._baralla.veureMunt().get(
                this._baralla.veureMunt().size() - 1);
        usarEfecto(cartaJugada);
    }

    @Override
    public JugadorInterface getTurno() {
        return this._jugadores.get(this._turno - 1);
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
        if (this._sentido) {
            this._turno++;
            if (this._turno == this._jugadores.size() + 1) {
                this._turno = 1;
            }
        } else {
            this._turno--;
            if (this._turno == 0) {
                this._turno = this._jugadores.size();
            }
        }
    }

    private void usarEfecto(CartaInterfaz cartaJugada) {
        switch (cartaJugada.getNumero()) {
            case "Pierde Turno ":
                pasarTurno();
                break;
            case "Cambio de Sentido ":
                invertirSentido();
                break;
            case "+2 ":
                this._efectoEnJuego = cartaJugada;
                this._efectoRobarCartas += 2;
                break;
            case "Comodin ":
                cambioColor(cartaJugada);
                break;
            case "+4 ":
                this._efectoEnJuego = cartaJugada;
                this._efectoRobarCartas += 4;
                cambioColor(cartaJugada);
                break;
            default:
                break;
        }
    }

    private void cambioColor(CartaInterfaz cartaJugada) {
        System.out.println("Color: ");
        int opcio = Menu("Rojo", "Azul",
                "Amarillo", "Verde");
        switch (opcio) {
            case 1:
                cartaJugada.setTipo("Rojo");
                cartaJugada.setName(cartaJugada.getName() + ANSI_RED + "Rojo" + ANSI_RESET);
                break;
            case 2:
                cartaJugada.setTipo("Azul");
                cartaJugada.setName(cartaJugada.getName() + ANSI_BLUE + "Azul" + ANSI_RESET);
                break;
            case 3:
                cartaJugada.setTipo("Amarillo");
                cartaJugada.setName(cartaJugada.getName() + ANSI_YELLOW + "Amarillo" + ANSI_RESET);
            case 4:
                cartaJugada.setTipo("Verde");
                cartaJugada.setName(cartaJugada.getName() + ANSI_GREEN + "Verde" + ANSI_RESET);
            default:
                break;
        }
    }

    private boolean resolverEfectos(JugadorInterface jugador) {
        boolean efectoResuelto = true;
        if (this._efectoRobarCartas != 0) {
            System.out.println("EFECTO!");
            System.out.println("Tienes que robar " + this._efectoRobarCartas + " cartas");
            ArrayList<CartaInterfaz> cartasCounter = buscarCounter(jugador);
            if (cartasCounter.isEmpty()) {
                efectoResuelto = resolverEfectoRobar(jugador);
            } else {
                int opcio = MenuCartas(cartasCounter);
                if (opcio <= cartasCounter.size()) {
                    efectoResuelto = this._baralla.afegirMunt(jugador.getMano().get(opcio - 1));
                    if (efectoResuelto) {
                        cartasCounter.remove(opcio - 1);
                        usarCarta();
                    }

                } else {
                    efectoResuelto = resolverEfectoRobar(jugador);
                }

            }

        }
        return efectoResuelto;
    }

    private ArrayList<CartaInterfaz> buscarCounter(JugadorInterface jugador) {
        ArrayList<CartaInterfaz> cartasCounter = new ArrayList<>();
        for (CartaInterfaz carta : jugador.getMano()) {
            if (carta.getNumero().equals(this._efectoEnJuego.getNumero())) {
                cartasCounter.add(carta);
            }
        }
        return cartasCounter;
    }

    private boolean resolverEfectoRobar(JugadorInterface jugador) {
        boolean efectoResuelto;
        System.out.println("Robas " + this._efectoRobarCartas + " cartas");
        jugador.addCartas(this._baralla.repartirCartes(this._efectoRobarCartas));
        this._efectoRobarCartas = 0;
        this._efectoEnJuego = null;
        efectoResuelto = true;
        return efectoResuelto;
    }

}
