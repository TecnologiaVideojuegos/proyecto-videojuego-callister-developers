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
public class GemaRayo1 extends Gema1{
    
    public GemaRayo1() throws SlickException {
        super(new Image("resources/Gemas/Rayo1.png"), "Gema Rayo NL1", new Rayo());
    }
    
}
