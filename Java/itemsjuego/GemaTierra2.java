/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemsjuego;

import Combate.Agua;
import Combate.Elemento;
import Combate.Fuego;
import Combate.Terra;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class GemaTierra2 extends Gema2{
    
    public GemaTierra2() throws SlickException {
        super(new Image("resources/Gemas/Tierra2.png"), "Gema Tierra NL2", new Terra());
    }
    
}
