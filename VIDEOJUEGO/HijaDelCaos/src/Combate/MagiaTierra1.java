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
public class MagiaTierra1 extends Magia{
    
    public MagiaTierra1() throws SlickException {
        super(5, 20, new Terra(), "resources/Magia/Roca.png", 100, "Lanza Rocas", "resources/sonido/combate/Magia/Falling_Rock.ogg", 0);
    }
    
}
