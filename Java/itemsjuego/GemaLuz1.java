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
public class GemaLuz1 extends Gema1{
    
    public GemaLuz1() throws SlickException {
        super(new Image("resources/Gemas/Luz1.png"), "Gema Luz NL1", new Luz());
    }
    
}
