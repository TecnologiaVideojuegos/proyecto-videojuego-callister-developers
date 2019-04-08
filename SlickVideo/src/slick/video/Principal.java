package slick.video;

import MenuPrincipal.m2;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Principal extends StateBasedGame{

    private final AppGameContainer contenedor;
    
    public static void main(String[] args) throws SlickException{
        Principal p = new Principal("Chaos");
    }

    public Principal(String name) throws SlickException {
        super(name);
        contenedor = new AppGameContainer(this);
        contenedor.setDisplayMode(640, 640, false);
        contenedor.setShowFPS(true);
        contenedor.start();
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.addState(new Juego());
        this.addState(new m2());
    }
    
}
