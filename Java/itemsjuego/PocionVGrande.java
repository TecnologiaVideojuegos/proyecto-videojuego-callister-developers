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
public class PocionVGrande extends PocionesCurar {
    public PocionVGrande() throws SlickException{
        super(200,new Image("resources/PotiVidaGrande.png"));
        setNombre("Pocion de vida grande");
    }
    @Override
    public String toString(){
        return "Pocion de vida grande";
    }
    
}
