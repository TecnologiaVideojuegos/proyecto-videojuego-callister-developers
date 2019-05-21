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
public class GemaFuego1 extends Gema1{
    
    public GemaFuego1() throws SlickException {
        super(new Image("resources/Gemas/Fuego1.png"), "Gema Fuego NL1", new Fuego());
    }
    
}
