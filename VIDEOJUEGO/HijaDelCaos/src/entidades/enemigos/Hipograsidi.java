/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.enemigos;

import Combate.MagiaTierra1;
import Combate.Planta;
import Combate.Terra;
import chaoschild.Punto;
import static java.lang.Math.abs;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class Hipograsidi extends Enemigo{
    
    public Hipograsidi(int x, int y) throws SlickException {
        super("resources/Enemigos/Hipograsidi/Hipograsidi.png","resources/Pantalla de Batalla/Hipograsidi/Hipograsidi.png", 64, 64, 4, 3, new int[]{2,4,2}, "Hipograsidi");
        super.setPosicion(new Punto(x,y));
        super.genHitboxes(new Punto(super.getPosicion().getX(), super.getPosicion().getY()+(30)), 41, 29);
        int[] frames={4,4,4,4};
        super.animaciones(frames);
        estadisticasb(new int[]{300, 50, 50,0, 0, 0, 0, 0,4,200, 5, 20, 15, 0, 20, 10, 9});
        aprenderMagia(new MagiaTierra1());
        setElemento(new Planta());
        setExpg(100);
        setPropobj(100);
    }
    
    public void draw(){
        int w=0;
        int h=30;
        if(getVelocidad().getX()==0 && getVelocidad().getY()==0){
            getAnimacion().getImage(0).draw(getPosicion().getX(), getPosicion().getY());

        }else{
        getAnimacion().draw(getPosicion().getX(), getPosicion().getY());
        if(abs((int) getVelocidad().getX())<abs((int) getVelocidad().getY())){
            if(getVelocidad().getY()<0){
                w=21;
                h=24;
            }else{
                w=21;
                h=5;
            }
            super.genHitboxes(new Punto(super.getPosicion().getX()+w, super.getPosicion().getY()+h), 24, 36);
        }else{ 
            if(getVelocidad().getX()<0){
                h=30;
                w=21;
            }
            super.genHitboxes(new Punto(super.getPosicion().getX()+w, super.getPosicion().getY()+h), 41, 29);
        }
        }
       
    }
    
}
