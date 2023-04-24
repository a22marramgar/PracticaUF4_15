/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Baralles;

import Cartas.CartaInterfaz;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ausias
 */
public class BarallaEspecial extends BarallaSimple {
    
    private ArrayList<CartaInterfaz> _cartesEspecials;
    
    public BarallaEspecial(List<String> palos, List<String> numeros, List<CartaInterfaz> cartesEspecials) {
        super(palos,numeros);
        this._cartesEspecials = new ArrayList<>(cartesEspecials);
        super.afegirCartes(cartesEspecials);
    }
    
    @Override
    public boolean afegirMunt(CartaInterfaz carta){
        if(carta.getTipo().equals("Especial")
                ||super.veureMunt().isEmpty()
                ||super.veureMunt().get(super.veureMunt().size()-1).getTipo().equals(carta.getTipo())
                ||super.veureMunt().get(this.veureMunt().size()-1).getNumero()==carta.getNumero()){
            super.afegirMunt(carta);
            return true;
        }else{
            return false;
        }
    }
    
}
