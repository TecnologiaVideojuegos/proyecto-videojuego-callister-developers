/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Combate;

import entidades.EntidadCombate;

/**
 *
 * @author victo
 */
public class Accion {
    private int action, indice;
    
    private EntidadCombate atacante, defensor;
    private int anima, animd;
    private int daño;
    private Elemento el;
    private boolean reciviendodaño;
    private Magia mag;
    private boolean mg;

    public Accion(int action, int indice, EntidadCombate atacante, EntidadCombate defensor) {
        this.action = action;
        this.indice = indice;
        this.atacante = atacante;
        this.defensor = defensor;
        anima=0;
        animd=0;
        reciviendodaño=false;
        mag=null;
        mg=true;
    }
    
    
    public boolean comprobar(){
        boolean a= false;
        if(anima!=atacante.getAnimId()){
            switch(action){
                case 0:
                    a=fisico();
                break;
                case 1:
                    a=magia();
                break;
            }
      
        }
        return a;
    }
    
    public void actionCalc(){
        switch(action){
            case 0:
                
                daño=atacante.ataqueBasico();
                anima=atacante.getAnimId();
                
                break;
            case 1: 
                System.out.println("Magia");
                daño=atacante.hacerMagia(indice, defensor);
                anima=atacante.getAnimId();
                
                break;
        }
    }

    public void setAtacante(EntidadCombate atacante) {
        this.atacante = atacante;
    }

    public void setDefensor(EntidadCombate defensor) {
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
            defensor.recivirDañoFisico(daño, null);
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
            defensor.recivirDañoFisico(daño, null);
            animd=defensor.getAnimId();
            reciviendodaño=true;
        }
        if(animd!=defensor.getAnimId()){
            a=true;
            reciviendodaño=false;
        }
        
        return a;
    }
}
