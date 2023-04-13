/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Jugadors;

import Cartas.CartaInterfaz;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mario
 */
public class Jugador implements JugadorInterface{
    
    private int _id;
    private ArrayList<CartaInterfaz> _cartas;
    private static int currentId;
    
    public Jugador(){
        this._cartas = new ArrayList<>();
        this._id = currentId;
        currentId++;
    }

    @Override
    public List<CartaInterfaz> getMano() {
        return this._cartas;
    }

    @Override
    public int getId() {
        return this._id;
    }

    @Override
    public void addCartas(List<CartaInterfaz> lista) {
        this._cartas.addAll(lista);
    }
    
}
