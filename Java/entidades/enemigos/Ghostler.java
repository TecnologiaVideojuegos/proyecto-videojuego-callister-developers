/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.enemigos;

import Combate.MagiaOscura1;
import Combate.MagiaPlanta1;
import Combate.Oscuro;
import Combate.Planta;
import chaoschild.Punto;
import chaoschild.Vector;
import static java.lang.Math.abs;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class Ghostler extends Enemigo{
    
    public Ghostler(int x, int y) throws SlickException {
        super("resources/Enemigos/Ghostler/Ghostler.png", "resources/Pantalla de Batalla/Ghostler/Ghostler Batalla.png", 64, 64, 4, 3, new int[]{3,3,3}, "Ghostler", 84, 84);
        super.setPosicion(new Punto(x,y));
        super.genHitboxes(new Punto(super.getPosicion().getX(), super.getPosicion().getY()),64, 64);
        int[] frames={4,3,3,4};
        super.animaciones(frames);
        estadisticasb(new int[]{300, 50, 100,0, 0, 0, 0, 0,1,200, 5, 20, 15, 0, 20, 10, 9});
        aprenderMagia(new MagiaOscura1());
        setElemento(new Oscuro());
        setPropmagia(80);
        setExpg(100);
        setPropobj(100);
    }
    
    public void ia(boolean[] pasar, Vector v){
        pasar=new boolean[]{true, true, true, true};
        boolean dirX;//True + False -
        boolean dirY;//True + False-
        
        if(v.getX()<0){
            dirX=false;
        }else dirX=true;
        if(v.getY()<0){
            dirY=false;
        }else dirY=true;
        
        
        
           
        if(abs((int)v.getX())<abs((int)v.getY())){
            if(dirX){
                if(pasar[1]){
                    super.movD((int)v.getX());
                }else{
                    super.setVelocidad(new Vector(new Punto(0,super.getVelocidad().getY())));
                }
            }else{
                if(pasar[3]){
                    super.movI(-(int)v.getX());
                }else{
                    super.setVelocidad(new Vector(new Punto(0,super.getVelocidad().getY())));
                }   
            } 
        
            if(dirY){
                if(pasar[2]){
                    super.movAb((int)v.getY());
                }else{
                    super.setVelocidad(new Vector(new Punto(super.getVelocidad().getX(),0)));
                }
            }else{
                 if(pasar[0]){
                    super.movAr(-(int)v.getY());
                }else{
                    super.setVelocidad(new Vector(new Punto(super.getVelocidad().getX(), 0)));
                }
            }
        }else{
            if(dirY){
                if(pasar[2]){
                    super.movAb((int)v.getY());
                }else{
                    super.setVelocidad(new Vector(new Punto(super.getVelocidad().getX(),0)));
                }
            }else{
                 if(pasar[0]){
                    super.movAr(-(int)v.getY());
                }else{
                    super.setVelocidad(new Vector(new Punto(super.getVelocidad().getX(), 0)));
                }
            }
            if(dirX){
                if(pasar[1]){
                    super.movD((int)v.getX());
                }else{
                    super.setVelocidad(new Vector(new Punto(0,super.getVelocidad().getY())));
                }
            }else{
                if(pasar[3]){
                    super.movI(-(int)v.getX());
                }else{
                    super.setVelocidad(new Vector(new Punto(0,super.getVelocidad().getY())));
                }   
            } 
        }
    }
    
}
