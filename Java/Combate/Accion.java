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

    public Accion(int action, int indice, EntidadCombate atacante, EntidadCombate defensor) {
        this.action = action;
        this.indice = indice;
        this.atacante = atacante;
        this.defensor = defensor;
    }
    
    
    
}
