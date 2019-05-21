/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.enemigos;

import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class EntOscuro extends Enemigo{
    
    public EntOscuro(String ruta, String rutaC, int h, int w, int numAnimaciones, int numC, int[] frames, String nombre) throws SlickException {
        super(ruta, rutaC, h, w, numAnimaciones, numC, frames, nombre);
    }
    
}
