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
public class PocionMGrande extends PocionesMana {
    public PocionMGrande() throws SlickException{
       super(60, new Image("resources/PotiManaGrande.png"));
    }

    @Override
    public String toString(){
        return "Pocion de maná grande";
    }
   
     
    
}
