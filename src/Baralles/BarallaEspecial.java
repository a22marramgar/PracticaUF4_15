/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Baralles;

import Cartas.CartaEspecial;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ausias
 */
public class BarallaEspecial extends BarallaSimple {
    
    private ArrayList<CartaEspecial> _cartesEspecials;
    
    public BarallaEspecial(List<String> palos, List<String> nombresDeCartaPorPalo, List<CartaEspecial> cartesEspecials) {
        super(palos, nombresDeCartaPorPalo);
        this._cartesEspecials = new ArrayList<>(cartesEspecials);
        super.afegirCartes(cartesEspecials);
    }
    
}
