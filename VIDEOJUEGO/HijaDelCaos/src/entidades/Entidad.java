/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import chaoschild.Punto;
import chaoschild.Vector;
import java.awt.BorderLayout;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author victo
 */
public abstract class Entidad implements Externalizable{
    private SpriteSheet sprite;
    private Animation animacion;
    private Punto posicion;
    private Vector velocidad;
    private Rectangle hitbox;
    private Animation[] direcciones;
    private int w,h,wh,hh,wp,hp;
    private Rectangle[] hitParedes;
    private boolean par;
    private String nombre;
    private int animid;
    private Punto objetivo;
    private boolean cr;//control remoto
    private int speed;
    private String ruta1;
    private boolean[] limitar;
    private int[] prueba;
    
    public Entidad(String ruta, int h, int w, int numAnimaciones, String nombre) throws SlickException{
        
        
        direcciones=new Animation[numAnimaciones];
        this.w=w;
        this.h=h;
        velocidad=new Vector(new Punto (0, 0));
        sprite=new SpriteSheet(ruta, w, h);
        animacion=new Animation(true);
        animacion.addFrame(sprite.getSprite(0,0), 150);
        hitbox=new Rectangle(0,0,0,0);
        hitParedes=new Rectangle[4];
        par=false;
        this.nombre=nombre;
        animid=0;
        limitar=new boolean[numAnimaciones];
        this.ruta1=ruta;
    }

    public Entidad() {
    }
    
    
    
    public void update(int delta) throws SlickException{
        float x = posicion.getX() + velocidad.getX() * ((float) delta / 500);
        float y = posicion.getY() + velocidad.getY() * ((float) delta / 500);
        
        /*
        float x = posicion.getX() + velocidad.getX() * ((float) delta / 1000);
        float y = posicion.getY() + velocidad.getY() * ((float) delta / 1000);
*/
        posicion = new Punto(x, y);
        hitbox.setLocation(x, y);
        if(par){
            genHitboxes(new Punto(posicion.getX()+wp, posicion.getY()+hp), wh, hh);
        }
        if(cr){            
            velocidad=new Vector(posicion, objetivo);     
            float modulo= velocidad.getModulo();
            velocidad=new Vector(new Punto(velocidad.getX()/modulo*speed, velocidad.getY()/modulo*speed));
            if((int)posicion.getX()==(int)objetivo.getX()&&(int)posicion.getY()==(int)objetivo.getY()){
                velocidad=new Vector(new Punto(0,0));
                cr=false;
            }
        }
        
        
    }
    
    public void draw(){
        if(velocidad.getX()==0 && velocidad.getY()==0){
            animacion.getImage(0).draw(posicion.getX(), posicion.getY());
            
        }else{
        animacion.draw(posicion.getX(), posicion.getY());
        
        }
       
    }
    
    public void drawCombate(){
        animacion.draw(posicion.getX(), posicion.getY());
    }
    
    public void spriteMov(int direccion, int numFrames){//Direccion: 0 arriba, 1 derecha, 2 abajo, 3 izq
        Animation aux =new Animation(); 
        for (int j=0;j<numFrames;j++){
            aux.addFrame(sprite.getSprite(j,direccion), 150);
        }
        direcciones[direccion]=new Animation();
        direcciones[direccion]=aux;
        limitar[direccion]=true;
    }
    
    public void animaciones(int[] frames){//Frames es un array con el numero de frames en cada animacion
        int a=direcciones.length;
        
        for(int i=0;i<a;i++){
            try{
                spriteMov(i,frames[i]);
            }catch(Exception e){
                System.out.println(this+" Animaciones sin espacio : Asignando más animaciones de las que le han sido asignadas a la entidad. "+e);
            }
            
        }
    }
    
    public void setAnimacion(int a){
       animacion=direcciones[a];
       animid=a;  
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
    
    public Entidad getEnt(){
        return this;
    }
    
    
    public void spriteMov(int direccion, int numFrames, int espacio, boolean limited){//Direccion: 0 arriba, 1 derecha, 2 abajo, 3 izq
        Animation aux =new Animation(); 
        for (int j=0;j<numFrames;j++){
            aux.addFrame(sprite.getSprite(j,direccion), espacio);
        }
        aux.setLooping(limited);
        direcciones[direccion]=new Animation();
        direcciones[direccion]=aux;
        limitar[direccion]=limited;
    }
    
    public void animaciones(int[] frames, int[] espacios, boolean[] limited){//Frames es un array con el numero de frames en cada animacion
        int a=direcciones.length;
        
        for(int i=0;i<a;i++){
            try{
                spriteMov(i,frames[i], espacios[i], limited[i]);
            }catch(Exception e){
                System.out.println(this+" Animaciones1 : Asignando más animaciones de las que le han sido asignadas a la entidad. "+e);
            }
            
        }
    }
    
    public void animaciones(int[] frames, int espacios, boolean[] limited){//Frames es un array con el numero de frames en cada animacion
        int a=direcciones.length;
        
        for(int i=0;i<a;i++){
            try{
                spriteMov(i,frames[i], espacios, limited[i]);
            }catch(Exception e){
                System.out.println(nombre+" Animaciones2 : Asignando más animaciones de las que le han sido asignadas a la entidad. "+e);
            }
            
        }
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String n){
        nombre = n;
    }
    
    public String toString(){
        return nombre;
    }

    public Animation getAnimacion() {
        return animacion;
    }

    public int getAnimid() {
        return animid;
    }
    
    public void irA(Punto p, int speed){
        cr=true;
        objetivo=p;
        this.speed=speed;
    }
    
    public Animation getAnimation(int i){
        return direcciones[i];
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        
        posicion.writeExternal(out);
        
        //velocidad.writeExternal(out);
        out.writeInt(w);
        out.writeInt(h);
        out.writeInt(wh);
        out.writeInt(hh);
        out.writeInt(wp);
        out.writeInt(hp);
        out.writeBoolean(par);
        out.writeUTF(nombre);
        out.writeInt(animid);
        out.writeBoolean(cr);
        out.writeInt(speed);

        out.writeUTF(ruta1);    
        out.writeInt(direcciones.length);
        out.writeObject(limitar);
        int[] espacios=new int[direcciones.length];
        int[] frames=new int[direcciones.length];
        for(int i = 0; i < direcciones.length; i++) {
            espacios[i]=direcciones[i].getDuration(1);
            frames[i]=direcciones[i].getFrameCount();
        }
        
        out.writeObject(frames);
        out.writeObject(espacios);
        
        //int[] frames, int[] espacios, boolean[] limited
        
        
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
        posicion=new Punto();
        posicion.readExternal(oi);
        w=oi.readInt();
        h=oi.readInt();
        wh=oi.readInt();
        hh=oi.readInt();
        wp=oi.readInt();
        hp=oi.readInt();
        par=oi.readBoolean();
        nombre=oi.readUTF();
        animid=oi.readInt();
        cr=oi.readBoolean();
        speed=oi.readInt();
        ruta1=oi.readUTF();
        direcciones=new Animation[oi.readInt()];
        limitar=(boolean[]) oi.readObject();   
        int[] frames=new int[direcciones.length];
        frames=(int[]) oi.readObject();
        int[] espacios=new int[direcciones.length];
        espacios=(int[]) oi.readObject();
        try {
            sprite=new SpriteSheet(ruta1, w, h);
        } catch (SlickException ex) {
            Logger.getLogger(Entidad.class.getName()).log(Level.SEVERE, null, ex);
        }
        animacion=new Animation(true);
        hitbox=new Rectangle(0,0,0,0);
        hitParedes=new Rectangle[4];   
        animaciones(frames, espacios, limitar);
        animacion.addFrame(sprite.getSprite(0,0), 150);
        velocidad=new Vector(new Punto(0,0), new Punto(0,0));
        System.out.println("-----------------------------------------"+toString()+" Cargado con Exito--------------------------------------");

    }
       

//        velocidad=new Vector();
//        //velocidad.readExternal(oi);


//        ruta1=oi.readUTF();

//        System.out.println(direcciones.length);

//        limitar=new boolean[direcciones.length];
//        limitar=(boolean[]) oi.readObject();

//    }
    
    
    }
    

