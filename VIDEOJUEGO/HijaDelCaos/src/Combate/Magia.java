/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Combate;

import chaoschild.Punto;
import entidades.Entidad;
import entidades.EntidadCombate;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author victo
 */
public class Magia implements Externalizable{
    private int coste;
    private int daño;
    private Elemento elemento;
    private Animation anim;
    private SpriteSheet sprite;
    private Punto posicion;
    private String nombre;
    private Sound sonido;
    private int tipo;
    private String aruta;
    private String sruta;
    

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
        aruta=ruta;
        sruta=musica;
        
        
    }

    public Magia() {
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
        usa.setMult(1, usa.getMultiplicadores()[1]-coste);
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
    
    private void seleccionarElemento(String a) throws SlickException{
        switch (a){
            case "Planta":elemento=new Planta();
                break;
            case "Agua":elemento=new Agua();
                break;
            case "Fuego":elemento=new Fuego();
                break;
            case "Rayo":elemento=new Rayo();
                break;
            case "Tierra":elemento=new Terra();
                break;
            case "Luz":elemento=new Luz();
                break;
            case "Oscuro":elemento=new Oscuro();
                break;
            
        }
    }

    @Override
    public void writeExternal(ObjectOutput oo) throws IOException {
       oo.writeInt(coste);
       oo.writeInt(daño);
       oo.writeUTF(elemento.toString());
       posicion.writeExternal(oo);
       oo.writeUTF(nombre);
       oo.writeInt(tipo);
       oo.writeUTF(aruta);
       oo.writeUTF(sruta);
       oo.writeInt(anim.getDuration(1));
       

    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
        coste=oi.readInt();
        daño=oi.readInt();
        try {
            seleccionarElemento(oi.readUTF());
        } catch (SlickException ex) {
            Logger.getLogger(Magia.class.getName()).log(Level.SEVERE, null, ex);
        }
        posicion=new Punto();
        posicion.readExternal(oi);
        nombre=oi.readUTF();
        tipo=oi.readInt();
        aruta=oi.readUTF();
        sruta=oi.readUTF();
        this.anim = new Animation();
        try {
            this.sprite = new SpriteSheet(aruta, 64, 64);
        } catch (SlickException ex) {
            Logger.getLogger(Magia.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            sonido=new Sound(sruta);
        } catch (SlickException ex) {
            Logger.getLogger(Magia.class.getName()).log(Level.SEVERE, null, ex);
        }
        setAnim(oi.readInt());
        
        
    }

   
    
    
   
    
}
