/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import Combate.Elemento;
import chaoschild.Punto;
import itemsjuego.Gema;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class Aliado extends EntidadCombate implements Externalizable{
    
    private Gema arma, armor;


    public Aliado(String ruta, String rutaC, int h, int w, int numAnimaciones, int numC, int[] frames, String nombre) throws SlickException {
        super(ruta, rutaC, h, w, numAnimaciones, numC, frames, nombre);
        arma=null;
        armor=null;
        setPosicion(new Punto(0,0));

    }
    public Aliado(String ruta, String rutaC, int h, int w, int numAnimaciones, int numC, int[] frames, String nombre, int a) throws SlickException {
        super(ruta, rutaC, h, w, numAnimaciones, numC, frames, nombre, a);
        arma=null;
        armor=null;
        setPosicion(new Punto(0,0));

        
    }

    public Aliado(String ruta, String rutaC, int h, int w, int numAnimaciones, int numC, int[] frames, String nombre, int wc, int hc) throws SlickException {
        super(ruta, rutaC, h, w, numAnimaciones, numC, frames, nombre, wc, hc);
        arma=null;
        armor=null;
        setPosicion(new Punto(0,0));
    }

    public Aliado() {
    }
    
    

    public Gema getArma() {
        return arma;
    }

    public Gema getArmor() {
        return armor;
    }

    public void setArma(Gema arma) {
        setTieneGema(arma.getNivel());
        this.arma = arma;
    }

    public void setArmor(Gema armor) {
         setTieneGemaD(armor.getNivel());
        this.armor = armor;
    }
    
    
    
    public void recivirDañoMagia(int dmg, Elemento elem){
        float res=1;
        try {
            if(armor.getElemento().mult(elem)>0) res=(float) (1-armor.getMult()-0.2);
        } catch (Exception e) {
        }
        getMultiplicadores()[0]=(int) (getMultiplicadores()[0]-((int)((double)dmg+0.7*(double)getEst()[3]))*res);
        if(getMultiplicadores()[0]<0){
            setMult(0, 0);
        }
        setAnimacionCombate(getAnimdañar());
       playDaño();
    }
    
    
    public Elemento getElemento(){
        Elemento el=null;
        try {
            el=arma.getElemento();
        } catch (Exception e) {
        }
        return el;
    }
    
    public void recivirDañoFisico(int dmg, EntidadCombate en){
        float mult=1;
        try {
            if(en.getElemento().mult(armor.getElemento())>0){
            mult=mult+1-armor.getMult();
        }else{
            mult=mult-armor.getMult();
        }
        } catch (Exception e) {} 
        super.recivirDañoFisico((int) (dmg*mult), en);
    }
    
    public Elemento getElementoDef(){
        Elemento el=null;
       
        try {
            el=armor.getElemento();
        } catch (Exception e) {
        }
        return el;
    }
    
    public void renderInfo(Punto p, Graphics g, GameContainer gc, Image ata, Image def){
        Animation ani=getAnimation(0);
        ani.getImage(0).draw(p.getX(), p.getY(), (float) 1.2);
        ArrayList<String> esta=toStringEst();
        g.setColor(Color.black);
        g.drawString(esta.get(esta.size()-1), p.getX()+100, p.getY());
        g.setColor(Color.white);
        for(int i=0;i<esta.size()-1;i++){
            g.drawString(esta.get(i), p.getX()+100, p.getY()+20*(i+1));
        }
        renderBarras(new Punto(p.getX(), (float) (ani.getHeight()*1.2+p.getY())), g, gc);
        g.setColor(Color.white);
        ata.draw((int)p.getX()-12, (int) p.getY()+200-20);
        try {
            arma.render((int)p.getX()+50, (int) p.getY()+200, g);
        } catch (Exception e) {
        }
        def.draw((int)p.getX()-12, (int) p.getY()+200+30-20);
        try {
            armor.render((int)p.getX()+50, (int) p.getY()+200+30, g);
        } catch (Exception e) {
        }
    }
    
    @Override
     public void writeExternal(ObjectOutput out) throws IOException {
         super.writeExternal(out);
         out.writeBoolean(arma!=null);
         System.out.println(arma!=null);
         if(arma!=null){
            arma.writeExternal(out);
         }
         System.out.println(armor!=null);
         out.writeBoolean(armor!=null);
         if(armor!=null){
            armor.writeExternal(out);
         }
     }
     
     
    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
        super.readExternal(oi);
        boolean a=oi.readBoolean();

        if(a){
            arma=new Gema();
            arma.readExternal(oi);
        }
        boolean b=oi.readBoolean();
       
        if(b){
            armor=new Gema();
            armor.readExternal(oi);
        }
        
    }

}
