/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemsjuego;

import Combate.Agua;
import Combate.Elemento;
import java.io.Externalizable;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class GemaAgua1 extends Gema1 implements Externalizable{
    
    public GemaAgua1() throws SlickException {
        super(new Image("resources/Gemas/Agua1.png"), "Gema Agua NL1", new Agua());
    }
    
}
