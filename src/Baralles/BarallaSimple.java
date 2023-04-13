/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Baralles;

import Cartas.CartaEspecial;
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

    public BarallaSimple(List<String> palos, List<String> nombresDeCartaPorPalo) {
        this._cartes = new ArrayList<>();
        this._munt = new ArrayList<>();
            for (String palo : palos) {
                for (int i = 0; i < nombresDeCartaPorPalo.size(); i++) {
                    this._cartes.add(new CartaSimple(palo,i+1,nombresDeCartaPorPalo.get(i)+palo));
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
        this._munt.addAll(toReturn);
        return toReturn;
    }
    
    public void afegirCartes(List<CartaEspecial> cartesAAfegir){
        for (CartaEspecial cartaEspecial : cartesAAfegir) {
            this._cartes.add(cartaEspecial);
        }
    }

    @Override
    public List<CartaInterfaz> veureMunt() {
        return this._munt;
    }

}
