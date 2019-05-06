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
public class PocionMPequeña extends PocionesMana {
    public PocionMPequeña() throws SlickException{
       super(20, new Image("resources/Menus/PunteroMenu.png"));
    }

    @Override
    public String toString(){
        return "Pocion de maná pequeña";
    }
   
    @Override
    public void render(int x, int y, Graphics g){        
        g.drawString(toString(), x, y);   
    } 
    
}
