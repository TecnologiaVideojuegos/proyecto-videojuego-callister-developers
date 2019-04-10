/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import chaoschild.Punto;
import chaoschild.Vector;
import itemsjuego.Objeto;
import java.util.ArrayList;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author victo
 */
public class Lucia extends Entidad{
    
    private static Lucia Lucia=null;
    private ArrayList<Objeto> inventario;
    
    private int ancho, alto;
    public Lucia(int x, int y) throws SlickException {
        super("resources/Personajes/Lucia/Lucia.png", 52, 34, 4);
        ancho=34;
        alto=54;
        super.setPosicion(new Punto(x, y));
        super.genHitboxes(new Punto(super.getPosicion().getX()+2, super.getPosicion().getY()+(52-24)), 24, 24);
        int[] frames={3,3,3,3};
        super.animaciones(frames);
        inventario=new ArrayList();
    }
    
    
    public static Lucia getLucia() throws SlickException{
        if(Lucia==null){
            Lucia=new Lucia(300, 300);
        }
        return Lucia;
    }
    
    
    public void actualizar(Input entrada, boolean[] pasar){
        
        if(entrada.isKeyDown(Input.KEY_W)){
           
            if(pasar[0]){
                super.movAr(150);
            }else{
                super.setVelocidad(new Vector(new Punto(super.getVelocidad().getX(), 0)));
            }

        }
        if(entrada.isKeyDown(Input.KEY_A)){
            if(pasar[3]){
                super.movI(150);
            }else{
                super.setVelocidad(new Vector(new Punto(0,super.getVelocidad().getY())));
            }

        }
        if(entrada.isKeyDown(Input.KEY_D)){
            if(pasar[1]){
                super.movD(150);
            }else{
                super.setVelocidad(new Vector(new Punto(0,super.getVelocidad().getY())));
            }

        }
        if(entrada.isKeyDown(Input.KEY_S)){
            if(pasar[2]){
                super.movAb(150);
            }else{
                super.setVelocidad(new Vector(new Punto(super.getVelocidad().getX(),0)));
            }

        }
        if(!entrada.isKeyDown(Input.KEY_S)&&!entrada.isKeyDown(Input.KEY_W)){
            super.setVelocidad(new Vector(new Punto(super.getVelocidad().getX(), 0)));
        }
        if(!entrada.isKeyDown(Input.KEY_A)&&!entrada.isKeyDown(Input.KEY_D)){
            super.setVelocidad(new Vector(new Punto(0,super.getVelocidad().getY())));
        }
        
    }
    
}
