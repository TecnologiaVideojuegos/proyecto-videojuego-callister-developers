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

    public Accion(int action, int indice, EntidadCombate atacante, EntidadCombate defensor) {
        this.action = action;
        this.indice = indice;
        this.atacante = atacante;
        this.defensor = defensor;
        anima=0;
        animd=0;
    }
    
    
    public boolean comprobar(){
        boolean a= false;
        if(anima!=atacante.getAnimId()&&animd!=defensor.getAnimId()) a=true;
        return a;
    }
    
    public void actionCalc(){
        switch(action){
            case 0:
                defensor.recivirDañoFisico(atacante.ataqueBasico(), null);
                anima=atacante.getAnimId();
                animd=defensor.getAnimId();
                break;
        }
    }

    
    
}
