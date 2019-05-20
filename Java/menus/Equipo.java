/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import chaoschild.Punto;
import entidades.Lucia;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author victo
 */
public class Equipo extends BasicGameState{

    private Image fondo,ata, def;
    private Punto p;
    
    @Override
    public int getID() {
        return 4;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        fondo = new Image("resources/Menus/MenuEquipo.png");
        p=new Punto(20, 20);
        SpriteSheet sprite=new SpriteSheet("resources/Menus/Defensor.png", 40, 40);
        def=sprite.getSubImage(0, 0);
        SpriteSheet prite=new SpriteSheet("resources/Menus/Atacante.png", 40, 40);
        ata=prite.getSubImage(0, 0);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        fondo.draw();
        renderEquipo(grphcs, gc);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        cEntrada(gc.getInput(), sbg);
    }
    
    public void renderEquipo( Graphics g, GameContainer gc) throws SlickException{
        Lucia.getLucia().renderInfo(p, g, gc, ata, def);
        for(int i=1;i<Lucia.getLucia().getEquipo().size();i++){
            int a=i%2;
            Lucia.getLucia().getEquipo().get(i).renderInfo(new Punto(p.getX()+350*a, p.getY()+300*((int)i/2)), g, gc, ata, def);
        }
    }
    
    public void cEntrada(Input entrada, StateBasedGame game){
        if(entrada.isKeyPressed(Input.KEY_B)){
            game.enterState(1);
        }
    }
    
}
