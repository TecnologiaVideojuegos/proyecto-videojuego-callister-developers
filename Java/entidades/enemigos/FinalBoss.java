/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.enemigos;

import Combate.Fuego;
import Combate.MagiaFuego1;
import Combate.MagiaLuz1;
import Combate.MagiaOscura1;
import Combate.Oscuro;
import chaoschild.Punto;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class FinalBoss extends Jefe{
    
    public FinalBoss(int x, int y) throws SlickException {
        super("resources/Enemigos/Boss_Final/Boss_Final.png", "resources/Pantalla de Batalla/Boss_Final/Boss_Final.png", 64, 64, 4, 4, new int[]{3, 2, 2, 2}, "Director", 60, 64);
        combatir();
        setPosicion(new Punto(x,y));
        super.genHitboxes(new Punto(super.getPosicion().getX()+15, super.getPosicion().getY()+(34)), 24, 24);
        estadisticasb(new int[]{500, 50, 100, 0, 0,0,0,0,5,500,5,20,20,0,10,10,9});
        setAnimest(0);
        setAnimbaseco(2);
        setAnimmag(1);
        setAnimdañar(3);
        int[] frames={3,3,3,3};
        super.animaciones(frames);
        setElemento(new Oscuro());
        aprenderMagia(new MagiaFuego1());
        aprenderMagia(new MagiaLuz1());
        aprenderMagia(new MagiaOscura1());
        setExpg(500);
        setPropobj(100);
        setPropmagia(30);
    }
    
}
