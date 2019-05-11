/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemsjuego;

import Combate.Agua;
import Combate.Elemento;
import Combate.Fuego;
import Combate.Rayo;
import Combate.Terra;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class GemaRayo2 extends Gema2{
    
    public GemaRayo2() throws SlickException {
        super(new Image("resources/Gemas/Rayo2.png"), "Gema Rayo NL2", new Rayo());
    }
    
}
