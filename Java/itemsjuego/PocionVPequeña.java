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
 * @author victo
 */
public class PocionVPequeña extends PocionesCurar{

    public PocionVPequeña() throws SlickException {
        
        super(20, new Image("resources/Menus/PunteroMenu.png"));
    }

    public String toString(){
        return"Pocion de Vida";
    }
   
    public void render(int x, int y, Graphics g){
        
        g.drawString(toString(), x, y);
        
        
    }
    
}
