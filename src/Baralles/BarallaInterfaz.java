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
public interface BarallaInterfaz{
    public void barallar();
    public List<CartaInterfaz> repartirCartes(int nCartes);
    public List<CartaInterfaz> repartirCartes(int nCartes, boolean force);
    public List<CartaInterfaz> veureMunt();
    public int cartesDisponibles();
}
