/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chaoschild;


import Combate.Combate;
import juego.Juego;
import menus.Menu;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class ChaosChild extends StateBasedGame{

    private final AppGameContainer contenedor;
    
    public static void main(String[] args) throws SlickException{
        ChaosChild p = new ChaosChild("Chaos");
    }

    public ChaosChild(String name) throws SlickException {
        super(name);
        contenedor = new AppGameContainer(this);
        contenedor.setDisplayMode(640, 640, false);
        contenedor.setShowFPS(true);
        contenedor.start();
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.addState(new Juego());
        this.addState(new Menu());
        this.addState(new Combate());
    }
    
}