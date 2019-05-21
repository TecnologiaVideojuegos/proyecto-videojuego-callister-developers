/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemsjuego;

import Combate.Agua;
import Combate.Elemento;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class GemaAgua2 extends Gema2{
    
    public GemaAgua2() throws SlickException {
        super(new Image("resources/Gemas/Agua2.png"), "Gema Agua NL2", new Agua());
    }
    
}
