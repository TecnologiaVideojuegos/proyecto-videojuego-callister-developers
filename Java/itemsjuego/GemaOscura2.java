/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemsjuego;

import Combate.Agua;
import Combate.Elemento;
import Combate.Oscuro;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class GemaOscura2 extends Gema2{
    
    public GemaOscura2() throws SlickException {
        super(new Image("resources/Gemas/Osc2.png"), "Gema Oscura NL2", new Oscuro());
    }
    
}
