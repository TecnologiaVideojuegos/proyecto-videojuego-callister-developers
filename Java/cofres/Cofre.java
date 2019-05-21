/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cofres;

import org.newdawn.slick.Image;
import chaoschild.Punto;
import itemsjuego.Objeto;
import java.util.ArrayList;
import juego.Juego;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Carlos
 */
public class Cofre {
    private Punto posicion;
    private Rectangle hitbox;
    private ArrayList<Rectangle> areaAccion;
    private boolean abierto;
    private boolean abriendo;
    private Animation animacion;
    private SpriteSheet sprite;
    private Image imagen;
    private Sound abrir;
    private Objeto item;
    private Juego juego;
    
    public Cofre(Punto pos, Objeto item, Juego juego) throws SlickException{
        this.juego = juego;
        this.posicion = pos;
        this.abierto = false;
        this.abriendo = false;
        this.sprite = new SpriteSheet("resources/Cofres/cofre_anim.png", 33, 47);
        this.abrir = new Sound("resources/sonido/cofre/abrir_cofre.ogg");
        this.animacion = new Animation(sprite, 200);
        this.animacion.setLooping(false);
        this.imagen = new Image("resources/Cofres/cofre_cerrado.png");
        this.hitbox = new Rectangle(this.posicion.getX(), this.posicion.getY(), imagen.getWidth(), imagen.getHeight());
        this.areaAccion = new ArrayList();
        this.item = item;
        genAreaAccion();
    }
    
    public Cofre(int X, int Y, Objeto item, Juego juego) throws SlickException{
        this(new Punto(X, Y), item, juego);
    }
    
    public Cofre(){}
    
    private void genAreaAccion(){
        float w = imagen.getWidth();
        float h = imagen.getHeight();
        float x = posicion.getX() - w;
        float y = posicion.getY() - h;
        
        
        for(int i = 0;i < 3;i++){
            this.areaAccion.add(new Rectangle(x + w * i, y, w, h));
        }
        for(int i = 1;i < 3;i++){
            this.areaAccion.add(new Rectangle(x, y + h * i, w, h));
        }
        this.areaAccion.add(new Rectangle(x + w * 2, y + h, w, h));
        this.areaAccion.add(new Rectangle(x + w, y + h * 2, w, h));
        this.areaAccion.add(new Rectangle(x + w * 2, y + h * 2, w, h));
    }
    
    public Objeto abrir() throws SlickException{
        Objeto obj;
        if(!abierto){
            this.abierto = true;
            this.abriendo = true;
            this.imagen = new Image("resources/Cofres/cofre_abierto.png");
            this.posicion = new Punto(this.posicion.getX(), this.posicion.getY() - 16);
            this.abrir.play();
            obj = this.item;
            this.juego.setTextoDialogo("Has encontrado " + this.item.toString() + ".");
            this.juego.activarDialogo();
            
            this.item = null;
        } else {
            obj = this.item;
        }
        
        return obj;
    }
    
    public void draw(){
        if(!abriendo){
            this.imagen.draw(this.posicion.getX(), this.posicion.getY());
        } else {
            this.animacion.draw(posicion.getX(), posicion.getY());
        }
        if(animacion.isStopped()){
            this.abriendo = false;
        }
    }
    
    public ArrayList<Rectangle> getAreaAccion(){
        return this.areaAccion;
    }
    
    public boolean isAbierto(){
        return this.abierto;
    }
    
    public Rectangle getHitBox(){
        return this.hitbox;
    }
    
    public boolean isAbriendo(){
        return this.abriendo;
    }
}
