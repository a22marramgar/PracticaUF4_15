/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import Baralles.BarallaEspecial;
import Baralles.BarallaInterfaz;
import Jugadors.Jugador;
import Jugadors.JugadorInterface;
import Partida.Control;
import Partida.ControlInterface;
import TipoBaralla.BarallaUno;
import java.util.ArrayList;
import javax.swing.BorderFactory;

/**
 *
 * @author Mario
 */
public class MenuUI extends javax.swing.JFrame {

    /**
     * Creates new form ContactEditorUI
     */
    public MenuUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        opcionJugadores = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        botonInicio = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UNO Version MarloMagic");
        setForeground(java.awt.Color.white);
        setIconImages(null);
        setPreferredSize(new java.awt.Dimension(1920, 1080));
        setSize(new java.awt.Dimension(1920, 1080));

        opcionJugadores.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        opcionJugadores.setForeground(new java.awt.Color(255, 255, 255));
        opcionJugadores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "3", "4", "5", "6", "7", "8" }));
        opcionJugadores.setToolTipText("");
        opcionJugadores.setAutoscrolls(true);
        opcionJugadores.setBorder(null);
        opcionJugadores.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        opcionJugadores.setFocusable(false);
        opcionJugadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionJugadoresActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 36)); // NOI18N
        jLabel1.setText("Jugadores");

        botonInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/UNO_reescalado.png"))); // NOI18N
        botonInicio.setBorder(null);
        botonInicio.setBorderPainted(false);
        botonInicio.setContentAreaFilled(false);
        botonInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonInicio.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/UNO_hover.png"))); // NOI18N
        botonInicio.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/UNO_focus.png"))); // NOI18N
        botonInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonInicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(botonInicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(opcionJugadores, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(opcionJugadores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(botonInicio)))
                .addContainerGap(393, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void opcionJugadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionJugadoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcionJugadoresActionPerformed

    private void botonInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonInicioActionPerformed
        int nJugadores = opcionJugadores.getSelectedIndex()+2;
        BarallaInterfaz baralla1 = new BarallaEspecial(BarallaUno.getPalos()
                        , BarallaUno.getNumerosCarta()
                        , BarallaUno.getCartasEspeciales());
        ArrayList<JugadorInterface> jugadores = new ArrayList<>();
        for (int i = 0; i < nJugadores; i++) {
            JugadorInterface nuevoJugador = new Jugador();
            jugadores.add(nuevoJugador);
        }
        this.dispose();
        
        JuegoUI juegoUI = new JuegoUI(baralla1, jugadores);
        juegoUI.setVisible(true);
        juegoUI.iniciarPartida();
    }//GEN-LAST:event_botonInicioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox<String> opcionJugadores;
    // End of variables declaration//GEN-END:variables
}
