/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemsjuego;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author ceals
 */
public class PocionVMediana extends PocionesCurar {
    public PocionVMediana() throws SlickException{
        super(40,new Image("resources/PotiVidaMediana.png"));
    }
    @Override
    public String toString(){
        return "Pocion de vida mediana";
    }
  
    
    
}
