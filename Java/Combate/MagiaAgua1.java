/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Combate;

import entidades.EntidadCombate;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author victo
 */
public class MagiaAgua1 extends Magia{
    
    public MagiaAgua1() throws SlickException {
        super(5, 5, new Elemento(), "resources/Magia/AguaGimp.png", 100, "Burbuja","resources/sonido/combate/Magia/magic_agua.ogg", 0 );
    }
    
    
    
}
