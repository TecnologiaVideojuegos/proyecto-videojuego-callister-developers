/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.enemigos;

import chaoschild.Punto;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class Geobro extends Enemigo{
    
    public Geobro(int x, int y) throws SlickException {
        super("resources/Enemigos/Geobro/Geobro.png", 54, 54, 4);
        
        super.setPosicion(new Punto(x,y));
        super.genHitboxes(new Punto(super.getPosicion().getX()+9, super.getPosicion().getY()+(30)), 54-18, 24);
        int[] frames={4,4,4,4};
        super.animaciones(frames);
    }
    
}
