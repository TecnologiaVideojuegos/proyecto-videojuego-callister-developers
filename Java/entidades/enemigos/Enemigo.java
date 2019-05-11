/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.enemigos;

import Combate.Elemento;
import Combate.Normal;
import chaoschild.Punto;
import chaoschild.Vector;
import entidades.Entidad;
import entidades.EntidadCombate;
import entidades.Lucia;
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
public class Enemigo extends EntidadCombate{
    private int speed;
    private int propmagia;
    private Elemento elemento;
    private int expg, propobj;
    
    public Enemigo(String ruta, String rutaC, int h, int w, int numAnimaciones, int numC, int[] frames, String nombre) throws SlickException {
        super(ruta, rutaC, h, w, numAnimaciones, numC, frames, nombre);
        speed=50;
        propmagia=30;
        elemento=new Normal();
    }

    public Enemigo(String ruta, String rutaC, int h, int w, int numAnimaciones, int numC, int[] frames, String nombre, int hc, int wc) throws SlickException {
        super(ruta, rutaC, h, w, numAnimaciones, numC, frames, nombre, hc, wc);
        speed=50;
        propmagia=30;
        elemento=new Normal();
        
    }

    public Enemigo(String ruta, String rutaC, int h, int w, int numAnimaciones, int numC, int[] frames, String nombre, int wc, int hc, boolean[] limited) throws SlickException {
        super(ruta, rutaC, h, w, numAnimaciones, numC, frames, nombre, wc, hc, limited);
        speed=50;
        propmagia=30;
    }
    
    
    
    
    
    
    public Vector calcRuta() throws SlickException{
        Vector v=new Vector(super.getPosicion(), getLucia().getPosicion() );
        int modulo=(int)v.getModulo();
        
        if(v.getModulo()<100){
            if (modulo!=0){
                v=new Vector(new Punto(v.getX()/modulo*speed, v.getY()/modulo*speed));
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
        
    public void actualizar(boolean[] pasar, int a) throws SlickException{
        ia(pasar, calcRuta());
        super.update(a);
    }

    public int getPropmagia() {
        return propmagia;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setPropmagia(int propmagia) {
        this.propmagia = propmagia;
    }

    public void setElemento(Elemento elemento) {
        this.elemento = elemento;
    }

    @Override
    public Elemento getElemento() {
        return elemento;
    }
    
    @Override
    public void recivirDañoFisico(int dmg, EntidadCombate en){
        float res=0;
        if(en.getTieneGema()!=0){
            switch(en.getTieneGema()){
                case 1:
                    res=(float) 0.3;
                    break;
                case 2:
                    res=(float) 0.75;
                    break;
            }
        }
        int daux=(int) (dmg*res);
        dmg=dmg-daux;
        System.out.println("DAUX tiene un valor de "+daux);
        try {
            daux=(int) (daux*en.getElemento().mult(getElemento()));
            System.out.println("DAUX ahora tiene un valor de "+daux);
        } catch (Exception e) {
        }
        dmg=dmg+daux;
        super.recivirDañoFisico(dmg, en);
    }
    
    @Override
    public int hacerMagia(int indice, EntidadCombate en){
        setAnimacionCombate(2);
        int da=getMagias().get(indice).usar(this, en);
        float ml=(int) (getMagias().get(indice).getElemento().mult(en.getElementoDef()));
        if(en.getTieneGemaD()!=0){
            switch(en.getTieneGemaD()){
                case 1:
                    ml=(float) 0.80;
                    break;
                case 2:
                    ml=(float) 0.4;
                    break;
            }
        }
        da=(int) ((int) (da+getEst()[4]*0.9)*ml);
        return da;
    }

    public int getExpg() {
        return expg;
    }

    public void setExpg(int expg) {
        this.expg = (int) (expg*1.10*getLVL());
    }

    public int getPropobj() {
        return propobj;
    }

    public void setPropobj(int propobj) {
        this.propobj = propobj;
    }

    
    
    
    
}
