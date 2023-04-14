/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cartas;

/**
 *
 * @author ausias
 */
public class CartaSimple implements CartaInterfaz {
    private String _name;
    private String _tipo;
    private String _numero;
    
    public CartaSimple(String tipo, String numero, String nombre){
        this._tipo = tipo;
        this._numero = numero;
        this._name = nombre;
    }
    
    public String informacioCarta(){
        return this.getNumero()+" "+this.getTipo();
    }

    /**
     * @return the _tipo
     */
    @Override
    public String getTipo() {
        return _tipo;
    }

    /**
     * @param _tipo the _tipo to set
     */
    @Override
    public void setTipo(String _tipo) {
        this._tipo = _tipo;
    }

    /**
     * @return the _numero
     */
    @Override
    public String getNumero() {
        return this._numero;
    }

    /**
     * @param _numero the _numero to set
     */
    public void setNumero(String _numero) {
        this._numero = _numero;
    }

    @Override
    public String getName() {
        return this._name;
    }
    @Override
    public void setName(String nombre){
        this._name = nombre;
    }

}
