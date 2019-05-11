/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapas;

import chaoschild.Punto;

/**
 *
 * @author Carlos
 */
public class Puerta {
    private Punto posicion;
    
    public Puerta(Punto posicion){
        this.posicion = posicion;
    }
    
    public Puerta(int x, int y){
        this.posicion = new Punto(x, y);
    }
    
    public Puerta(){
    }

    public Punto getPosicion() {
        return posicion;
    }

    public void setPosicion(Punto posicion) {
        this.posicion = posicion;
    }
}
