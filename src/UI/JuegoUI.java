/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import Baralles.BarallaInterfaz;
import Cartas.CartaInterfaz;
import Jugadors.JugadorInterface;
import Partida.Control;
import Partida.ControlInterface;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import javax.swing.JButton;
import javax.swing.JLabel;
import static utils.UIUtilities.Menu;
import static utils.UIUtilities.MenuCartas;

/**
 *
 * @author Mario
 */
public class JuegoUI extends javax.swing.JFrame {

    private BarallaInterfaz _baralla;
    private ArrayList<JugadorInterface> _jugadores;
    private int _turno;
    private CartaInterfaz _efectoEnJuego;
    private int _efectoRobarCartas;
    private boolean _sentido;
    private CountDownLatch latch;

    /**
     * Creates new form JuegoUI
     */
    public JuegoUI(BarallaInterfaz baralla, List<JugadorInterface> jugadores) {
        initComponents();
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

    public void iniciarPartida() {
        panelColores.setVisible(false);
        boolean finPartida = false;
        this._baralla.afegirMunt(this._baralla.repartirCartes(1).get(0));
        usarCarta();
            String nomCartaMunt = this._baralla.veureMunt().get(this._baralla.veureMunt().size() - 1).getName();
            pile.setIcon(new javax.swing.ImageIcon("src\\imagenes\\" + nomCartaMunt.replace(" ", "") + ".png"));
            labelJugador.setText("Jugador " + getTurno().getId());

            //TODO AQUI ME HE QUEDADO, SEGUIR POR RESOLVEREFECTOS
            boolean efectoResuelto = resolverEfectos(getTurno());
            if (efectoResuelto) {
                finPartida = iniciarTurno(getTurno());
            }

    }

    public boolean iniciarTurno(JugadorInterface jugador) {
        int opcio = 0;
        boolean accioFeta = false;
        ArrayList<JButton> cartasMano = new ArrayList<>();
        cartasMano.add(cartasMano0);
        cartasMano.add(cartasMano1);
        cartasMano.add(cartasMano1);
        cartasMano.add(cartasMano3);
        cartasMano.add(cartasMano4);
        cartasMano.add(cartasMano5);
        cartasMano.add(cartasMano6);
        for (int i = 0; i < cartasMano.size(); i++) {

            if (jugador.getMano().size() > i) {
                cartasMano.get(i).setIcon(new javax.swing.ImageIcon("src\\imagenes\\" + jugador.getMano().get(i).getName()
                        .replace(" ", "") + ".png"));
            } else {
                cartasMano.get(i).setIcon(null);
            }
        }
        /*if (opcio <= jugador.getMano().size()) {
            accioFeta = this._baralla.afegirMunt(jugador.getMano().get(opcio - 1));
            if (accioFeta) {
                jugador.getMano().remove(opcio - 1);
                usarCarta();
            }

        } else {
            accioFeta = jugador.addCartas(this._baralla.repartirCartes(1));
            if (!accioFeta) {
                System.out.println("No hay cartas, pasar turno?");
                if (Menu("Si", "No") == 1) {
                    accioFeta = true;
                }
            }

        }*/

        return jugador.getMano().isEmpty();
    }

    public JugadorInterface getTurno() {
        return this._jugadores.get(this._turno - 1);
    }

    private void usarCarta() {
        CartaInterfaz cartaJugada = this._baralla.veureMunt().get(
                this._baralla.veureMunt().size() - 1);
        usarEfecto(cartaJugada);
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
                cambioColor(cartaJugada);
                this._efectoEnJuego = cartaJugada;
                this._efectoRobarCartas += 4;
                break;
            default:
                break;
        }
    }

    private boolean resolverEfectos(JugadorInterface jugador) {
        boolean efectoResuelto = true;
        if (this._efectoRobarCartas != 0) {
            this.labelEfecto.setText("Tienes que robar " + this._efectoRobarCartas + " cartas");
            ArrayList<CartaInterfaz> cartasCounter = buscarCounter(jugador);
            if (cartasCounter.isEmpty()) {
                efectoResuelto = resolverEfectoRobar(jugador);
            } else {
                ArrayList<JButton> cartasMano = new ArrayList<>();
                cartasMano.add(cartasMano0);
                cartasMano.add(cartasMano1);
                cartasMano.add(cartasMano1);
                cartasMano.add(cartasMano3);
                cartasMano.add(cartasMano4);
                cartasMano.add(cartasMano5);
                cartasMano.add(cartasMano6);
                for (int i = 0; i < cartasMano.size(); i++) {
                    if (cartasCounter.size() > i) {
                        cartasMano.get(i).setIcon(new javax.swing.ImageIcon("src\\imagenes\\" + cartasCounter.get(i).getName()
                                .replace(" ", "") + ".png"));
                    } else {
                        cartasMano.get(i).setIcon(null);
                    }
                }
                int opcio = MenuCartas(cartasCounter);
                if (opcio <= cartasCounter.size()) {
                    int posicion = buscarCarta(cartasCounter.get(opcio - 1), jugador.getMano());
                    efectoResuelto = !(this._baralla.afegirMunt(jugador.getMano().remove(posicion)));

                    if (!efectoResuelto) {
                        usarCarta();
                    }

                } else {
                    efectoResuelto = resolverEfectoRobar(jugador);
                }

            }

        }
        return efectoResuelto;
    }

    private int buscarCarta(CartaInterfaz cartaBuscada, List<CartaInterfaz> lista) {
        for (int i = 0; i < lista.size(); i++) {
            if (cartaBuscada == lista.get(i)) {
                return i;
            }
        }
        return -1;
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
        labelEfecto.setText("Robas " + this._efectoRobarCartas + " cartas");
        jugador.addCartas(this._baralla.repartirCartes(this._efectoRobarCartas));
        this._efectoRobarCartas = 0;
        this._efectoEnJuego = null;
        efectoResuelto = true;
        return efectoResuelto;
    }

    private void cambioColor(CartaInterfaz cartaJugada) {
        this._efectoEnJuego = cartaJugada;
        panelColores.setVisible(true);
        botonRobar.setEnabled(false);
        latch = new CountDownLatch(1);
        esperar();
        this._efectoEnJuego = null;
    }

    public void esperar() {
        try {
            latch.await(); // Llamando al método await() para bloquear la ejecución hasta que se haga clic en un botón
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void invertirSentido() {
        this._sentido = !this._sentido;
    }

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
        labelJugador.setText("Jugador " + getTurno().getId());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoCartas = new javax.swing.ButtonGroup();
        deck = new javax.swing.JLabel();
        botonRobar = new javax.swing.JButton();
        pile = new javax.swing.JLabel();
        botonAnterior = new javax.swing.JButton();
        botonSiguiente = new javax.swing.JButton();
        labelJugador = new javax.swing.JLabel();
        panelColores = new javax.swing.JPanel();
        botonAmarillo = new javax.swing.JButton();
        botonRojo = new javax.swing.JButton();
        botonVerde = new javax.swing.JButton();
        botonAzul = new javax.swing.JButton();
        labelEfecto = new javax.swing.JLabel();
        cartasMano0 = new javax.swing.JButton();
        cartasMano1 = new javax.swing.JButton();
        cartasMano2 = new javax.swing.JButton();
        cartasMano3 = new javax.swing.JButton();
        cartasMano4 = new javax.swing.JButton();
        cartasMano5 = new javax.swing.JButton();
        cartasMano6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UNO!");

        deck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Backside.png"))); // NOI18N

        botonRobar.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 36)); // NOI18N
        botonRobar.setText("Robar");
        botonRobar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRobarActionPerformed(evt);
            }
        });

        botonAnterior.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 36)); // NOI18N
        botonAnterior.setText("<");

        botonSiguiente.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 36)); // NOI18N
        botonSiguiente.setText(">");

        labelJugador.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 36)); // NOI18N

        botonAmarillo.setBackground(new java.awt.Color(255, 255, 0));
        botonAmarillo.setToolTipText("");
        botonAmarillo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAmarilloActionPerformed(evt);
            }
        });

        botonRojo.setBackground(new java.awt.Color(255, 0, 0));
        botonRojo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRojoActionPerformed(evt);
            }
        });

        botonVerde.setBackground(new java.awt.Color(0, 204, 0));
        botonVerde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVerdeActionPerformed(evt);
            }
        });

        botonAzul.setBackground(new java.awt.Color(0, 51, 255));
        botonAzul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAzulActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelColoresLayout = new javax.swing.GroupLayout(panelColores);
        panelColores.setLayout(panelColoresLayout);
        panelColoresLayout.setHorizontalGroup(
            panelColoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelColoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelColoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelColoresLayout.createSequentialGroup()
                        .addComponent(botonRojo, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(botonAmarillo, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelColoresLayout.createSequentialGroup()
                        .addComponent(botonVerde, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonAzul, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        panelColoresLayout.setVerticalGroup(
            panelColoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelColoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelColoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonRojo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonAmarillo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelColoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonVerde, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonAzul, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        labelEfecto.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 36)); // NOI18N

        cartasMano0.setBackground(null);
        cartasMano0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/0Amarillo.png"))); // NOI18N
        grupoCartas.add(cartasMano0);
        cartasMano0.setContentAreaFilled(false);
        cartasMano0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartasMano0ActionPerformed(evt);
            }
        });

        cartasMano1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/0Amarillo.png"))); // NOI18N
        grupoCartas.add(cartasMano1);
        cartasMano1.setContentAreaFilled(false);

        cartasMano2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/0Amarillo.png"))); // NOI18N
        grupoCartas.add(cartasMano2);
        cartasMano2.setContentAreaFilled(false);

        cartasMano3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/0Amarillo.png"))); // NOI18N
        grupoCartas.add(cartasMano3);
        cartasMano3.setContentAreaFilled(false);

        cartasMano4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/0Amarillo.png"))); // NOI18N
        grupoCartas.add(cartasMano4);
        cartasMano4.setContentAreaFilled(false);

        cartasMano5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/0Amarillo.png"))); // NOI18N
        grupoCartas.add(cartasMano5);
        cartasMano5.setContentAreaFilled(false);

        cartasMano6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/0Amarillo.png"))); // NOI18N
        grupoCartas.add(cartasMano6);
        cartasMano6.setContentAreaFilled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pile)
                        .addGap(143, 143, 143))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(labelJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(deck)
                .addGap(56, 56, 56)
                .addComponent(botonRobar, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(186, 186, 186))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(botonAnterior)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cartasMano0)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelEfecto, javax.swing.GroupLayout.PREFERRED_SIZE, 1045, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(panelColores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cartasMano1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cartasMano2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cartasMano3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cartasMano4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cartasMano5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cartasMano6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonSiguiente)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(botonRobar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pile)
                            .addComponent(deck)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(labelJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelColores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelEfecto, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(515, 515, 515)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(botonAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(159, 159, 159))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(botonSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(154, 154, 154))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cartasMano1)
                            .addComponent(cartasMano0)
                            .addComponent(cartasMano2)
                            .addComponent(cartasMano3)
                            .addComponent(cartasMano4)
                            .addComponent(cartasMano5)
                            .addComponent(cartasMano6))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonRobarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRobarActionPerformed
        // TODO add your handling code here:
        JugadorInterface jugador = getTurno();
        boolean accioFeta = jugador.addCartas(this._baralla.repartirCartes(1));
        if(accioFeta){
            pasarTurno();
        }
    }//GEN-LAST:event_botonRobarActionPerformed

    private void botonRojoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRojoActionPerformed
        // TODO add your handling code here:
        this._efectoEnJuego.setTipo("Rojo");
        this._efectoEnJuego.setName(this._efectoEnJuego.getName() + this._efectoEnJuego.getTipo());
        panelColores.setVisible(false);
        botonRobar.setEnabled(true);
        latch.countDown();
    }//GEN-LAST:event_botonRojoActionPerformed

    private void botonAmarilloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAmarilloActionPerformed
        // TODO add your handling code here:
        this._efectoEnJuego.setTipo("Amarillo");
        this._efectoEnJuego.setName(this._efectoEnJuego.getName() + this._efectoEnJuego.getTipo());
        panelColores.setVisible(false);
        botonRobar.setEnabled(true);
        latch.countDown();
    }//GEN-LAST:event_botonAmarilloActionPerformed

    private void botonVerdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVerdeActionPerformed
        // TODO add your handling code here:
        this._efectoEnJuego.setTipo("Verde");
        this._efectoEnJuego.setName(this._efectoEnJuego.getName() + this._efectoEnJuego.getTipo());
        panelColores.setVisible(false);
        botonRobar.setEnabled(true);
        latch.countDown();
    }//GEN-LAST:event_botonVerdeActionPerformed

    private void botonAzulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAzulActionPerformed
        // TODO add your handling code here:
        this._efectoEnJuego.setTipo("Azul");
        this._efectoEnJuego.setName(this._efectoEnJuego.getName() + this._efectoEnJuego.getTipo());
        panelColores.setVisible(false);
        botonRobar.setEnabled(true);
        latch.countDown();
    }//GEN-LAST:event_botonAzulActionPerformed

    private void cartasMano0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartasMano0ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cartasMano0ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAmarillo;
    private javax.swing.JButton botonAnterior;
    private javax.swing.JButton botonAzul;
    private javax.swing.JButton botonRobar;
    private javax.swing.JButton botonRojo;
    private javax.swing.JButton botonSiguiente;
    private javax.swing.JButton botonVerde;
    private javax.swing.JButton cartasMano0;
    private javax.swing.JButton cartasMano1;
    private javax.swing.JButton cartasMano2;
    private javax.swing.JButton cartasMano3;
    private javax.swing.JButton cartasMano4;
    private javax.swing.JButton cartasMano5;
    private javax.swing.JButton cartasMano6;
    private javax.swing.JLabel deck;
    private javax.swing.ButtonGroup grupoCartas;
    private javax.swing.JLabel labelEfecto;
    private javax.swing.JLabel labelJugador;
    private javax.swing.JPanel panelColores;
    private javax.swing.JLabel pile;
    // End of variables declaration//GEN-END:variables

}
