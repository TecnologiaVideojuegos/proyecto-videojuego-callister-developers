/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slick.video;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class Protagonista extends Entidad{
    

    
    
public Protagonista(float x, float y, String ruta) throws SlickException {
    super(x, y, ruta);
}

    
    



@Override
public void actualizar(Input entrada,boolean[]paredes ) throws SlickException{

    boolean t=true;
 
    if(entrada.isKeyDown(Input.KEY_UP)){
        if (paredes[0]){
             t=false;   
        }
        if(t){
            super.setVelocidad(new Vector(new Punto(0, 0)));
        }else{
            super.setVelocidad(new Vector(new Punto(0, -300)));
        }
        
        super.setAnimacion(0);
        

 }else if(entrada.isKeyDown(Input.KEY_DOWN)){

    if (paredes[2]){
        t=false;
    }
        if(t){
            super.setVelocidad(new Vector(new Punto(0, 0)));
        }else{
            super.setVelocidad(new Vector(new Punto(0, 300)));
        }
       
         super.setAnimacion(2);
         
    } else if(entrada.isKeyDown(Input.KEY_RIGHT)){

        if (paredes[1]){
            t=false;
        }
        if(t){
            super.setVelocidad(new Vector(new Punto(0, 0)));
        }else{
            super.setVelocidad(new Vector(new Punto(300, 0)));
        }
       
        super.setAnimacion(1);
        
    } else if(entrada.isKeyDown(Input.KEY_LEFT)){

        if (paredes[3]){
            t=false;   
        }
        if(t){
            super.setVelocidad(new Vector(new Punto(0, 0)));
        }else{
            super.setVelocidad(new Vector(new Punto(-300, 0)));
        }
        
         super.setAnimacion(3);
         
    }
    else {
        super.setVelocidad(new Vector(new Punto(0, 0)));
        
        }

        //animacion.addFrame(sprite.getSprite(estadoSprite[aux], direccion), 150);
                    
        
 
    }
}
