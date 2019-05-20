/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.enemigos;

import Combate.Oscuro;
import chaoschild.Punto;
import static java.lang.Math.abs;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author victo
 */
public class Ragebbit extends Enemigo{
    

    
    public Ragebbit(int x, int y) throws SlickException {
        super("resources/Enemigos/Ragebbit/Ragebbit.png","resources/Pantalla de Batalla/Ragebbit/Ragebbit (2).png", 64, 64, 5, 4, new int[]{7,4,3,3}, "Ragebbit", 74, 64, new boolean[]{false, true, false, false});
        super.setPosicion(new Punto(x,y));
        super.genHitboxes(new Punto(super.getPosicion().getX()+15, super.getPosicion().getY()+(30)), 40-11, 20);
        int[] frames={2, 2, 2, 2, 1};
        super.animaciones(frames);
        estadisticasb(new int[]{300, 50, 100,0, 0, 0, 0, 0,1,200, 5, 20, 15, 0, 20, 10, 9});
        setAnimest(1);
        setAnimbaseco(2);
        setAnimmag(2);
        setAnimdañar(3);
        setElemento(new Oscuro());
        setExpg(70);
        setPropobj(20);
        setRango(400);
        
        setSpeed(100);
       
    }
    
    public void draw(){
        if(getVelocidad().getX()==0 && getVelocidad().getY()==0){
            setAnimacion(4);
            getAnimacion().draw(getPosicion().getX(), getPosicion().getY());
            
            
        }else{
        getAnimacion().draw(getPosicion().getX(), getPosicion().getY());
        if(abs((int) getVelocidad().getX())<abs((int) getVelocidad().getY())){
            super.genHitboxes(new Punto(super.getPosicion().getX()+20, super.getPosicion().getY()+15), 20, 29);
        }else super.genHitboxes(new Punto(super.getPosicion().getX()+15, super.getPosicion().getY()+(30)), 40-11, 20);

        }
       
    }
//    @Override
//    public void drawC(Graphics g){
//            if(getAnimCom().isStopped()){
//                getAnimCom().restart();
//                setAnimacionCombate(1);   
//            }
//            g.draw(new Rectangle( getPosCombate().getX()+5, getPosCombate().getY()+64, 65,20));
//            g.drawString(getMultiplicadores()[0]+"/"+getEst()[0], getPosCombate().getX()+5, getPosCombate().getY()+64);
//            
//            getAnimCom().draw(getPosCombate().getX(), getPosCombate().getY());
//        
//       
//    }
    
    
    
    
}
