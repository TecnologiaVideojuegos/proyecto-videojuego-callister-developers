/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.enemigos;

import Combate.Agua;
import Combate.MagiaAgua1;
import chaoschild.Punto;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class SlimeBase extends Slime{
    
    public SlimeBase(int x, int y) throws SlickException {
        super("resources/Enemigos/Slimes/Slime de agua.png", "resources/Pantalla de Batalla/Slimes/Slime de agua.png", "Slime de Agua");
        super.setPosicion(new Punto(x, y));
        super.genHitboxes(new Punto(super.getPosicion().getX()+4, super.getPosicion().getY()+(40)), 40, 14);
        aprenderMagia(new MagiaAgua1());
        setElemento(new Agua());
    }
    
}
