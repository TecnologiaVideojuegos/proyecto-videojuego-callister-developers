/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class Aliado extends EntidadCombate{
    
    private int HP, MP, STR , INT, AGI, HIT;

    public Aliado(String ruta, String rutaC, int h, int w, int numAnimaciones, int numC, int[] frames, String nombre) throws SlickException {
        super(ruta, rutaC, h, w, numAnimaciones, numC, frames, nombre);
    }
    public Aliado(String ruta, String rutaC, int h, int w, int numAnimaciones, int numC, int[] frames, String nombre, int a) throws SlickException {
        super(ruta, rutaC, h, w, numAnimaciones, numC, frames, nombre, a);
    }
    
    
    public void cMana(int m){
        
    }
    
    
    public void cVida(int v){
        
    }
}
