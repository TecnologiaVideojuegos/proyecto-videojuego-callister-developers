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
public class Paula extends Aliado{
    
    public Paula(float x, float y) throws SlickException {
        super("resources/Personajes/Paula/Paula_1.png", "resources/Pantalla de Batalla/Paula/Batalla_Paula_1.png", 64, 64, 4, 4, new int[]{3,2,2,2}, "Paula");
        combatir();
        setPosCombate(new Punto(x,y));
        estadisticasb(new int[]{80, 70, 90, 60, 20, 0, 35,0, 1, 15, 5, 10, 15, 20, 10, 20, 18});
        setAnimest(0);
        setAnimbaseco(2);
        setAnimmag(1);
        setAnimdañar(3);
        int[] frames={3,3,3,3};
        super.animaciones(frames);
    }
    
    public int ataqueBasico(){
        int dmg= super.ataqueBasico();
        dmg=(int) (dmg+0.9*getEst()[5]);
        return dmg;
    }
    
}
