/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import chaoschild.Punto;
import chaoschild.Vector;
import static java.lang.Math.abs;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class NPC extends Entidad{
    private int ultimo;
    
    public NPC(String ruta, int h, int w, int numAnimaciones, String nombre, int[] frames) throws SlickException {
        super(ruta, h, w, numAnimaciones, nombre);
        animaciones(frames);
        ultimo=1;
        
    }
    
    @Override
    public void movD(int speed){
        setVelocidad(new Vector(new Punto(speed,getVelocidad().getY())));
        setAnimacion(3);
        if(speed!=0){
            ultimo=3;
        }
    }
    @Override
    public void movI(int speed){
        setVelocidad(new Vector(new Punto(-speed,getVelocidad().getY())));
        setAnimacion(5);
        if(speed!=0){
            ultimo=5;
        }
    }
    @Override
    public void movAb(int speed){
        setVelocidad(new Vector(new Punto(getVelocidad().getX(), speed)));
        setAnimacion(1);
        if(speed!=0){
            ultimo=1;
        }
    }
    @Override
    public void movAr(int speed){
        setVelocidad(new Vector(new Punto(getVelocidad().getX(), -speed)));
        setAnimacion(7);
        if(speed!=0){
            ultimo=7;
        }
       
    }
    
    public void draw(){
        if(getVelocidad().getX()==0 && getVelocidad().getY()==0){
            setAnimacion(ultimo-1);
            getAnimacion().draw(getPosicion().getX(), getPosicion().getY());    
        }else{
        getAnimacion().draw(getPosicion().getX(), getPosicion().getY());
        } 
    }
    
    public void ia(boolean[] pasar, Vector v){
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
                    movD((int)v.getX());
                }else{
                    setVelocidad(new Vector(new Punto(0,super.getVelocidad().getY())));
                }
            }else{
                if(pasar[3]){
                    movI(-(int)v.getX());
                }else{
                    setVelocidad(new Vector(new Punto(0,super.getVelocidad().getY())));
                }   
            } 
        
            if(dirY){
                if(pasar[2]){
                    movAb((int)v.getY());
                }else{
                    setVelocidad(new Vector(new Punto(super.getVelocidad().getX(),0)));
                }
            }else{
                 if(pasar[0]){
                    movAr(-(int)v.getY());
                }else{
                    setVelocidad(new Vector(new Punto(super.getVelocidad().getX(), 0)));
                }
            }
        }else{
            if(dirY){
                if(pasar[2]){
                    movAb((int)v.getY());
                }else{
                    setVelocidad(new Vector(new Punto(super.getVelocidad().getX(),0)));
                }
            }else{
                 if(pasar[0]){
                    super.movAr(-(int)v.getY());
                }else{
                    setVelocidad(new Vector(new Punto(super.getVelocidad().getX(), 0)));
                }
            }
            if(dirX){
                if(pasar[1]){
                    movD((int)v.getX());
                }else{
                    setVelocidad(new Vector(new Punto(0,super.getVelocidad().getY())));
                }
            }else{
                if(pasar[3]){
                    movI(-(int)v.getX());
                }else{
                    setVelocidad(new Vector(new Punto(0,super.getVelocidad().getY())));
                }   
            } 
        }
    }
    public void update(int i, boolean[] pasar) throws SlickException{
        ia(pasar, getVelocidad());
        super.update(i);
    }
    
    
}
