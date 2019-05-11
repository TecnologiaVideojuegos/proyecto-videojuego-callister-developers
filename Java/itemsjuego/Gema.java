/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemsjuego;

import Combate.Elemento;
import chaoschild.Punto;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 *
 * @author victo
 */
public abstract class Gema extends Objeto{
    private Elemento elemento;
    private float mult;
    private int nivel;

    public Gema(Image imagen, String n, Elemento e, float mult) {
        super(imagen, n);
        elemento=e;
        this.mult=mult;
        setGem(true);
    }

    public Elemento getElemento() {
        return elemento;
    }

    public float getMult() {
        return mult;
    }
    

    
    
    @Override
    public void renderImagen(Punto p, int i){
       getImagen().draw(p.getX()+6, p.getY()+i*33+6);  
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    
    
}
