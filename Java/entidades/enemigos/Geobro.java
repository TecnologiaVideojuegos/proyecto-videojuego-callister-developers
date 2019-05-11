/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.enemigos;

import Combate.MagiaTierra1;
import Combate.Terra;
import chaoschild.Punto;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class Geobro extends Enemigo{
    
    public Geobro(int x, int y) throws SlickException {
        super("resources/Enemigos/Geobro/Geobro.png","resources/Pantalla de Batalla/Geobro/Pantalla de batalla Geobro.png", 54, 54, 4, 3, new int[]{4,3,3}, "Geobro");
        super.setPosicion(new Punto(x,y));
        super.genHitboxes(new Punto(super.getPosicion().getX()+9, super.getPosicion().getY()+(30)), 54-18, 24);
        int[] frames={4,4,4,4};
        super.animaciones(frames);
        estadisticasb(new int[]{100, 30, 50, 0, 0, 0, 0, 0, 1, 20, 5, 10, 10, 5, 0, 0, 20, 10});
        aprenderMagia(new MagiaTierra1());
        setElemento(new Terra());
        setExpg(100);
        setPropobj(20);
        
    }
    
   
    
}
