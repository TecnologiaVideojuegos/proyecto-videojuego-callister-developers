/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemsjuego;

import Combate.Agua;
import Combate.Elemento;
import Combate.Fuego;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class GemaFuego2 extends Gema2{
    
    public GemaFuego2() throws SlickException {
        super(new Image("resources/Gemas/Fuego2.png"), "Gema Fuego NL2", new Fuego());
    }
    
}
