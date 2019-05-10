/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Combate;

import chaoschild.Punto;
import entidades.Entidad;
import entidades.EntidadCombate;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author victo
 */
public class Magia{
    private int coste;
    private int daño;
    private Elemento elemento;
    private Animation anim;
    private SpriteSheet sprite;
    private Punto posicion;
    private String nombre;
    private Sound sonido;
    private int tipo;

    public Magia(int coste, int daño, Elemento elemento, String ruta, int fps, String n, String musica, int tipo) throws SlickException {
        this.coste = coste;
        this.daño = daño;
        this.elemento = elemento;
        this.anim = new Animation();
        this.sprite = new SpriteSheet(ruta, 64, 64);
        posicion=new Punto(0,0);
        setAnim(fps);
        nombre=n;
        sonido=new Sound(musica);
        this.tipo=tipo;
        
        
    }
    
    
    private void setAnim(int fps){
        int animaciones=sprite.getWidth()/64;
        for (int i=0;i<animaciones;i++){
            anim.addFrame(sprite.getSubImage(i, 0), fps);
        }
        anim.setLooping(false);
        
    }
    
    public void draw(){
        if(!anim.isStopped()){
            anim.draw(posicion.getX(), posicion.getY());
            if(!sonido.playing()){
                sonido.play((float) 1.01, (float) 0.5);
            } 
        }   
    }

    public int getCoste() {
        return coste;
    }

    public int getDaño() {
        return daño;
    }

    public Elemento getElemento() {
        return elemento;
    }

    public Animation getAnim() {
        return anim;
    }

    public int usar(EntidadCombate usa,EntidadCombate recive){
        int dam;
        anim.restart();
        posicion=recive.getPosCombate();
        dam=usa.getEst()[5]+daño;
        return dam;
    }
    
    @Override
    public String toString(){
        return nombre;
    }
            
    
    public void setPosicion(EntidadCombate e){
        posicion=e.getPosCombate();
    }

    public int getTipo() {
        return tipo;
    }
    
    
   
    
}
