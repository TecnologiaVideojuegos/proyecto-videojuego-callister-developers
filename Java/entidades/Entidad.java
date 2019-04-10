/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import chaoschild.Punto;
import chaoschild.Vector;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author victo
 */
public class Entidad {
    private SpriteSheet sprite;
    private Animation animacion;
    private Punto posicion;
    private Vector velocidad;
    private Rectangle hitbox;
    private Animation[] direcciones;
    private int w,h,wh,hh,wp,hp;
    private Rectangle[] hitParedes;
    private boolean par;
    
    public Entidad(String ruta, int h, int w, int numAnimaciones) throws SlickException{
        direcciones=new Animation[numAnimaciones];

        velocidad=new Vector(new Punto (0, 0));
        sprite=new SpriteSheet(ruta, w, h);
        animacion=new Animation(true);
        animacion.addFrame(sprite.getSprite(0,0), 150);
        hitbox=new Rectangle(0,0,0,0);
        hitParedes=new Rectangle[4];
        par=false;
    }
    
    public void update(int delta) throws SlickException{
        float x = posicion.getX() + velocidad.getX() * ((float) delta / 1000);
        float y = posicion.getY() + velocidad.getY() * ((float) delta / 1000);
        posicion = new Punto(x, y);
        hitbox.setLocation(x, y);
        if(par){
            genHitboxes(new Punto(posicion.getX()+wp, posicion.getY()+hp), wh, hh);
        }
        
        
    }
    
    public void draw(){
        if(velocidad.getX()==0 && velocidad.getY()==0){
            animacion.getImage(0).draw(posicion.getX(), posicion.getY());
            
        }else{
        animacion.draw(posicion.getX(), posicion.getY());
        
        }
       
    }
    
    public void spriteMov(int direccion, int numFrames){//Direccion: 0 arriba, 1 derecha, 2 abajo, 3 izq
        Animation aux =new Animation(); 
        for (int j=0;j<numFrames;j++){
            aux.addFrame(sprite.getSprite(j,direccion), 150);
        }
        direcciones[direccion]=new Animation();
        direcciones[direccion]=aux;
    }
    
    public void animaciones(int[] frames){//Frames es un array con el numero de frames en cada animacion
        int a=direcciones.length;
        
        for(int i=0;i<a;i++){
            try{
                spriteMov(i,frames[i]);
            }catch(Exception e){
                System.out.println(this+" : Asignando más animaciones de las que le han sido asignadas a la entidad. "+e);
            }
            
        }
    }
    
    public void setAnimacion(int a){
        switch(a){
            case 0: animacion=direcciones[0];
            break;        
            case 1: animacion=direcciones[1];
            break;
            case 2: animacion=direcciones[2];
            break;        
            case 3: animacion=direcciones[3];
            break;
        }
                
    }
    
    public void genHitboxes(Punto p, int wi,int hi){
        int ancho=2;
        if(!par){
            hh=hi;
            wh=wi;
            wp=(int)(-posicion.getX()+p.getX());
            hp=(int)(-posicion.getY()+p.getY());
            par=true;
        }
        hitParedes[0]=new Rectangle(p.getX()+ancho,p.getY(), wi, ancho);
        hitParedes[1]=new Rectangle(p.getX()+wi+ancho,p.getY()+ancho, ancho, hi-ancho);
        hitParedes[2]=new Rectangle(p.getX()+ancho,p.getY()+hi, wi, ancho);
        hitParedes[3]=new Rectangle(p.getX(),p.getY()+ancho, ancho, hi-ancho);
        
    }
    
    public void movD(int speed){
        velocidad=new Vector(new Punto(speed,velocidad.getY()));
        animacion=direcciones[1];
    }
    public void movI(int speed){
        velocidad=new Vector(new Punto(-speed,velocidad.getY()));
        animacion=direcciones[2];
    }
    public void movAr(int speed){
        velocidad=new Vector(new Punto(this.velocidad.getX(),-speed));
        animacion=direcciones[3];
    }
    public void movAb(int speed){
        velocidad=new Vector(new Punto(this.velocidad.getX(),speed));
        animacion=direcciones[0];
    }
    
    
    
    public Punto getPosicion() {
        return posicion;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
     
    

    public void setPosicion(Punto posicion) {
        this.posicion = posicion;
    }

    public void setVelocidad(Vector v){
        velocidad=v;
    }

    public Rectangle[] getHitParedes() {
        return hitParedes;
    }

    public Vector getVelocidad() {
        return velocidad;
    }
    
    
    
    }
    

