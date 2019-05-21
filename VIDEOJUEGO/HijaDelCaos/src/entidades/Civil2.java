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
 * @author Carlos
 */
public class Civil2 extends NPC{
    
    public Civil2(String nombre, int x, int y) throws SlickException {
        super("resources/NPC/Civil 2/Civil 2.png", 65, 64, 8, nombre,new int[] {4,4,4,4,4,4,2,2});
        setPosicion(new Punto(x, y));
        genHitboxes(new Punto(super.getPosicion().getX()+22, super.getPosicion().getY()+(47)), 19, 19);
        animaciones(new int[] {4,4,4,4,4,4,2,2}, new int[] {300, 300, 300, 300, 300, 300, 300, 300}, new boolean[]{true, true, true, true,true, true, true, true});
    }
    
}
