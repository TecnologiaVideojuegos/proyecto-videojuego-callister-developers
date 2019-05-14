/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import Combate.Elemento;
import itemsjuego.Gema;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class Aliado extends EntidadCombate{
    
    private Gema arma, armor;


    public Aliado(String ruta, String rutaC, int h, int w, int numAnimaciones, int numC, int[] frames, String nombre) throws SlickException {
        super(ruta, rutaC, h, w, numAnimaciones, numC, frames, nombre);

    }
    public Aliado(String ruta, String rutaC, int h, int w, int numAnimaciones, int numC, int[] frames, String nombre, int a) throws SlickException {
        super(ruta, rutaC, h, w, numAnimaciones, numC, frames, nombre, a);
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
    
    

}
