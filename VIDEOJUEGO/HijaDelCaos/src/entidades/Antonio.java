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
public class Antonio extends Aliado{
    
    public Antonio(float x, float y) throws SlickException {
        super("resources/Personajes/Antonio/Antonio.png", "resources/Pantalla de Batalla/Antonio/Batalla_Antonio1.png", 64, 64, 4, 4, new int[]{3,2,2,2}, "Antonio", 64, 64);
        combatir();
        setPosCombate(new Punto(x,y));
        estadisticasb(new int[]{100, 70, 110, 80, 20,0, 20, 0, 1, 20,5,20,15,0,10,10,9});
        setAnimest(0);
        setAnimbaseco(2);
        setAnimmag(1);
        setAnimdañar(3);
        int[] frames={3,3,3,3};
        super.animaciones(frames);
    }

    public Antonio() {
    }
    
    
    
    public int ataqueBasico(){
        int dmg= super.ataqueBasico();
        dmg=(int) (0.5*dmg+getEst()[3]);
        return dmg;
    }
    
    
}
