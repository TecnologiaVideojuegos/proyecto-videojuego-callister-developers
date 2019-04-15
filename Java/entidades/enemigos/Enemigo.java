/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.enemigos;

import chaoschild.Punto;
import chaoschild.Vector;
import entidades.Entidad;
import static entidades.Lucia.getLucia;
import static java.lang.Math.abs;
import static java.lang.Math.abs;
import static java.lang.Math.abs;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class Enemigo extends Entidad{

    public Enemigo(String ruta, int h, int w, int numAnimaciones) throws SlickException {
        super(ruta, h, w, numAnimaciones);

    }
    
    
    public Vector calcRuta() throws SlickException{
        Vector v=new Vector(super.getPosicion(), getLucia().getPosicion() );
        int modulo=(int)v.getModulo();
        
        if(v.getModulo()<500){
            if (modulo!=0){
                v=new Vector(new Punto(v.getX()/modulo*50, v.getY()/modulo*50));
                super.setVelocidad(v);
            }
        }else v = new Vector(new Punto(0,0));
        return v;
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
    
 
    

    
    public Entidad getEntidad(){
        return super.getEnt();
    }
        
    public void actualizar(boolean[] pasar) throws SlickException{
        ia(pasar, calcRuta());
    }
}