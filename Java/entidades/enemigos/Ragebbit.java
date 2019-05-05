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
public class Ragebbit extends Enemigo{

    
    public Ragebbit(int x, int y) throws SlickException {
        super("resources/Enemigos/Ragebbit/Ragebbit.png","resources/Pantalla de Batalla/Ragebbit/Ragebbit (2).png", 64, 64, 5, 4, new int[]{7,4,3,3}, "Ragebbit", 64, 72);
        super.setPosicion(new Punto(x,y));
        super.genHitboxes(new Punto(super.getPosicion().getX()+9, super.getPosicion().getY()+(30)), 54-18, 24);
        int[] frames={2, 2, 2, 2, 1};
        super.animaciones(frames);
        estadisticasb(new int[]{100, 60, 80, 0, 0, 0, 0, 0, 0, 20, 5, 10, 10, 20, 0, 0, 20, 18});
        
    }
    
    public void draw(){
        if(getVelocidad().getX()==0 && getVelocidad().getY()==0){
            setAnimacion(4);
            getAnimacion().draw(getPosicion().getX(), getPosicion().getY());
        
            
        }else{
        getAnimacion().draw(getPosicion().getX(), getPosicion().getY());
        
        }
       
    }
}
