/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Combate;

import entidades.EntidadCombate;
import entidades.Lucia;
import itemsjuego.Inventario;
import itemsjuego.Objeto;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class Accion {
    private int action, indice;
    private Inventario inventario;
    private EntidadCombate atacante, defensor;
    private int anima, animd;
    private int daño;
    private Elemento el;
    private boolean reciviendodaño;
    private Magia mag;
    private boolean mg;
    private Objeto ob;

    public Accion(int action, int indice, EntidadCombate atacante, EntidadCombate defensor) throws SlickException {
        this.action = action;
        this.indice = indice;
        this.atacante = atacante;
        this.defensor = defensor;
        anima=0;
        animd=defensor.getAnimest();
        reciviendodaño=false;
        mag=null;
        mg=true;
        inventario=Lucia.getLucia().getIntven();
        System.out.println(atacante+" va a hacer la accion "+action+" contra "+defensor);
    }
    public Accion(int action, Objeto objeto, EntidadCombate atacante, EntidadCombate defensor) throws SlickException {
        this.action = action;
        this.indice = indice;
        this.atacante = atacante;
        this.defensor = defensor;
        anima=0;
        animd=defensor.getAnimest();
        reciviendodaño=false;
        mag=null;
        mg=true;
        inventario=Lucia.getLucia().getIntven();
        ob=objeto;
        
    }
    
    
    public boolean comprobar() throws SlickException{
        boolean a= false;
        if(anima!=atacante.getAnimId()){
            switch(action){
                case 0:
                    a=fisico();
                break;
                case 1:
                    a=magia();
                break;
                case 2:
                    a=objeto();   
                break;
            }
      
        }
        return a;
    }
    
    public void actionCalc() throws SlickException{
                System.out.println("Turno de "+atacante);
        switch(action){
            case 0: 
                daño=atacante.ataqueBasico();
                anima=atacante.getAnimId();
                break;
            case 1: 
                daño=atacante.hacerMagia(indice, defensor);
                anima=atacante.getAnimId();
                break;
            case 2:
                
                atacante.usarObjeto();
                anima=atacante.getAnimId();
                ob.restart();
                //Lucia.getLucia().getIntven().get(indice).restart();
                break;
        }
    }

    public void setAtacante(EntidadCombate atacante) {
        this.atacante = atacante;
    }

    public void setDefensor(EntidadCombate defensor) {
        System.out.println("Defensor de "+atacante+" Cambiado a "+ defensor);
        this.defensor = defensor;
    }

    public EntidadCombate getAtacante() {
        return atacante;
    }

    public EntidadCombate getDefensor() {
        return defensor;
    }

    private boolean fisico(){
        boolean a=false;
        if(!reciviendodaño){
            defensor.recivirDañoFisico(daño, atacante);
            animd=defensor.getAnimId();
            reciviendodaño=true;
        }
        if(animd!=defensor.getAnimId()){
            a=true;
            reciviendodaño=false;

        }
        return a;    
    }
    private boolean magia(){
        boolean a = false;
        
        if(atacante.drawMag(indice)&&!reciviendodaño){
            defensor.recivirDañoMagico(daño);
            animd=defensor.getAnimId();
            reciviendodaño=true;
        }
        if(animd!=defensor.getAnimId()){
            a=true;
            reciviendodaño=false;
        }
        return a;
    }
    
    private boolean objeto(){
        boolean a = false;

        if(ob.draw((int) defensor.getPosCombate().getX(), (int) defensor.getPosCombate().getY())){
            a=true;
            ob.usar(defensor);

        }
        
        return a;
         
    }
    
    public String toString(){
        String a=atacante+" usó ";
        String b="ataque básico en ";
        String c=defensor+".";
        if(action==1){
            //b=atacante.getMagias().get(0)+" en ";
        }
        if(action==2){
            b=ob.toString()+" en ";
        }
        return a+b+c;
    }
    
}
