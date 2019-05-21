/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import chaoschild.Punto;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class Civil1 extends NPC{
    
    public Civil1(String nombre, int x, int y) throws SlickException {
        super("resources/NPC/Civil 1/Civil 1.png", 65, 64, 8, nombre,new int[] {4,4,4,4,4,4,2,2});
        setPosicion(new Punto(x, y));
        genHitboxes(new Punto(super.getPosicion().getX()+22, super.getPosicion().getY()+(47)), 19, 19);
        animaciones(new int[] {4,4,4,4,4,4,2,2}, new int[] {300, 300, 300, 300, 300, 300, 300, 300}, new boolean[]{true, true, true, true,true, true, true, true});
    }
    
}
