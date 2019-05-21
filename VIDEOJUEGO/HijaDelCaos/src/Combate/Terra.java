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
public class Terra extends Elemento{
    
    public Terra() throws SlickException {
        super(new String[]{"Rayo"}, new String[]{"Planta"}, "resources/Menus/Tipos/ground.png", "Tierra");
    }
    
}
