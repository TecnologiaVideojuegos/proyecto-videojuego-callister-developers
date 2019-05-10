/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Combate;

import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class Agua extends Elemento{
    
    public Agua() throws SlickException {
        super(new String[]{"Tierra"}, new String[]{"Rayo"}, "resources/Menus/Tipos/water.png", "Agua");
    }
    
}
