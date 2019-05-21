/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Combate;

import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class MagiaRayo1 extends Magia{
    
    public MagiaRayo1() throws SlickException {
        super(5, 20, new Rayo(), "resources/Magia/Rayo.png", 100, "Trueno", "resources/sonido/combate/Magia/magic_elect.ogg", 0);
    }
    
}
