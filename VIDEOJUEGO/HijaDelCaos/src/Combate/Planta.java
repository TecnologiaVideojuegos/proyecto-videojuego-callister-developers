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
public class Planta extends Elemento{
    
    public Planta() throws SlickException {
        super(new String[]{"Tierra"}, new String[]{"Fuego"}, "resources/Menus/Tipos/grass.png", "Planta");
    }
    
}
