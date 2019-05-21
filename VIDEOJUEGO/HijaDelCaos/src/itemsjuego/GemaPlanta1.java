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
import Combate.Terra;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class GemaPlanta1 extends Gema1{
    
    public GemaPlanta1() throws SlickException {
        super(new Image("resources/Gemas/Planta1.png"), "Gema Planta NL1", new Planta());
    }
    
}
