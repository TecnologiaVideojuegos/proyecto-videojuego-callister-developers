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
public class MagiaFuego1 extends Magia{
    
    public MagiaFuego1() throws SlickException {
        super(5, 20, new Fuego(), "resources/Magia/Fuego.png", 100, "Giro Fuego", "resources/sonido/combate/Magia/magic_fuego.ogg", 0);
    }
    
}
