/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemsjuego;

import org.newdawn.slick.Image;

/**
 *
 * @author ceals
 */
public abstract class Objeto {
    
    private Image imagen;

    public Objeto(Image imagen) {
        this.imagen = imagen;
    }
    
    
    
    public void verImagen(){
        
    }
    
    
}