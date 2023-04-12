/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Baralles;

import Cartas.CartaInterfaz;
import java.util.List;

/**
 *
 * @author Mario
 */
public interface BarallaInterfaz<T>{
    public void barallar();
    public List<T> repartirCartes(int nCartes);
    public List<T> repartirCartes(int nCartes, boolean force);
    public List<T> veureMunt();
    public int cartesDisponibles();
}
