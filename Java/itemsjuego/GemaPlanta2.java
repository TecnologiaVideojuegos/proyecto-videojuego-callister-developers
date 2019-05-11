/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemsjuego;

import Combate.Agua;
import Combate.Elemento;
import Combate.Fuego;
import Combate.Planta;
import Combate.Rayo;
import Combate.Terra;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class GemaPlanta2 extends Gema2{
    
    public GemaPlanta2() throws SlickException {
        super(new Image("resources/Gemas/Planta2.png"), "Gema Planta NL2", new Planta());
    }
    
}
