/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemsjuego;

import Combate.Agua;
import Combate.Elemento;
import Combate.Luz;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class GemaLuz2 extends Gema2{
    
    public GemaLuz2() throws SlickException {
        super(new Image("resources/Gemas/Luz2.png"), "Gema Luz NL2", new Luz());
    }
    
}
