/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.enemigos;

import Combate.MagiaOscura1;
import Combate.MagiaPlanta1;
import Combate.MagiaTierra1;
import Combate.Oscuro;
import Combate.Planta;
import Combate.Terra;
import chaoschild.Punto;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class EntOscuro extends Enemigo{
    
    public EntOscuro(int x, int y) throws SlickException {
        super("resources/Enemigos/Ent maldito/Ent maldito.png", "resources/Pantalla de Batalla/Ent maldito/Ent maldito.png", 84, 84, 4, 3,new int[]{4,4,3}, "Ent Maldito", 84, 84);
        super.setPosicion(new Punto(x,y));
        super.genHitboxes(new Punto(super.getPosicion().getX()+10, super.getPosicion().getY()+(40)), 50, 40);
        int[] frames={4,4,4,2};
        super.animaciones(frames);
        estadisticasb(new int[]{300, 50, 100,0, 0, 0, 0, 0,1,200, 5, 20, 15, 0, 20, 10, 9});
        aprenderMagia(new MagiaPlanta1());
        aprenderMagia(new MagiaOscura1());
        setElemento(new Planta());
        setExpg(100);
        setPropobj(100);
    }
    
}
