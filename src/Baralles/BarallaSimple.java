/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Baralles;

import Cartas.CartaSimple;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Mario
 */
public class BarallaSimple implements BarallaInterfaz<CartaSimple> {

    private ArrayList<CartaSimple> _cartes;
    private ArrayList<CartaSimple> _munt;

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
    public List<CartaSimple> repartirCartes(int nCartes) {
        
        return repartirCartes(nCartes,false);
    }
    
    public List<CartaSimple> repartirCartes(int nCartes, boolean force) {
        ArrayList<CartaSimple> toReturn = new ArrayList<>();
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

    @Override
    public List<CartaSimple> veureMunt() {
        return this._munt;
    }

}
