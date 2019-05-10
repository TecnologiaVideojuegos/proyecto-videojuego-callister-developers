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
public class Fuego extends Elemento{
    
    public Fuego() throws SlickException {
        super(new String[]{"Planta"}, new String[]{"Agua"}, "resources/Menus/Tipos/fire.png", "Fuego");
    }
    
}
