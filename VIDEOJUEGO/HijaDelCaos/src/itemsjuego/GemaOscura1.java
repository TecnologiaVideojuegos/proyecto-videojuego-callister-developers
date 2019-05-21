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
public class GemaOscura1 extends Gema1{
    
    public GemaOscura1() throws SlickException {
        super(new Image("resources/Gemas/Osc1.png"), "Gema Oscura NL1", new Oscuro());
    }
    
}
