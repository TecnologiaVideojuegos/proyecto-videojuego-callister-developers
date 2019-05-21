/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.enemigos;

import Combate.Luz;
import Combate.MagiaLuz1;
import Combate.MagiaOscura1;
import Combate.MagiaPlanta1;
import Combate.Planta;
import chaoschild.Punto;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class Clerigo extends Jefe{
    
    public Clerigo(int x, int y) throws SlickException {
        super("resources/Enemigos/Clerigo/Clerigo.png", "resources/Pantalla de Batalla/Clerigo/Clerigo.png", 64,64, 4, 4, new int[]{3, 2, 2, 2}, "Relix", 60, 64);
        super.setPosicion(new Punto(x,y));
        super.genHitboxes(new Punto(super.getPosicion().getX()+10, super.getPosicion().getY()+(40)), 50, 40);
        int[] frames={3,3,3,3};
        super.animaciones(frames);
        estadisticasb(new int[]{90, 50, 90, 100, 0, 50 ,0, 0, 5, 200, 5, 20, 15, 0, 20, 10, 9});
        aprenderMagia(new MagiaLuz1());
        setElemento(new Luz());
        setExpg(100);
        setPropobj(100);
    }
    
}
