/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Baralles;

import Cartas.CartaInterfaz;
import Cartas.CartaSimple;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Mario
 */
public class BarallaSimple implements BarallaInterfaz {

    private ArrayList<CartaInterfaz> _cartes;
    private ArrayList<CartaInterfaz> _munt;

    public BarallaSimple(List<String> palos, List<String> numeros) {
        this._cartes = new ArrayList<>();
        this._munt = new ArrayList<>();
            for (String palo : palos) {
                for (String numero : numeros) {
                    this._cartes.add(new CartaSimple(palo,numero,numero+palo));
                }
            }

    }
    
    @Override
    public int cartesDisponibles(){
        return this._cartes.size();
    }

    @Override
    public void barallar() {
        Collections.shuffle(this._cartes);
    }

    @Override
    public List<CartaInterfaz> repartirCartes(int nCartes) {
        
        return repartirCartes(nCartes,false);
    }
    
    public List<CartaInterfaz> repartirCartes(int nCartes, boolean force) {
        ArrayList<CartaInterfaz> toReturn = new ArrayList<>();
        if(force){
            int aRobar = this._cartes.size()>nCartes? nCartes: this._cartes.size();
            for (int i = 0; i<aRobar; i++){
                toReturn.add(this._cartes.remove(0));
            }
        }else if(this._cartes.size()>=nCartes){
            for (int i = 0; i < nCartes; i++) {
                toReturn.add(this._cartes.remove(0));
            }
        }
        return toReturn;
    }
    
    public void afegirCartes(List<CartaInterfaz> cartesAAfegir){
        for (CartaInterfaz carta : cartesAAfegir) {
            this._cartes.add(carta);
        }
    }

    @Override
    public List<CartaInterfaz> veureMunt() {
        return this._munt;
    }

    @Override
    public boolean afegirMunt(CartaInterfaz carta) {     
      return this._munt.add(carta);
    }

}
