/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Jugadors;

import Cartas.CartaInterfaz;
import java.util.List;

/**
 *
 * @author Mario
 */
public interface JugadorInterface {
    public List<CartaInterfaz> getMano();
    public int getId();
    public void addCartas(List<CartaInterfaz> lista);
    
}
