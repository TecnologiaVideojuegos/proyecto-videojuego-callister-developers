/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuPrincipal;

import sesion03_estados.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author ceals
 */
public class Principal extends StateBasedGame {
    private AppGameContainer contenedor;

    public Principal() throws SlickException {
        super("Juego basado en estados");
        contenedor=new AppGameContainer(this);
        contenedor.setDisplayMode(640, 640, false);
        contenedor.setShowFPS(false); //Debería limitarlo a 60fps
        contenedor.start();
        
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        this.addState(new MenuPrincipal());
    }
    public static void main (String[] args){
        try{
            Principal juego=new Principal();
        }catch(SlickException slick){
            slick.printStackTrace();
        }
    }
    
}
