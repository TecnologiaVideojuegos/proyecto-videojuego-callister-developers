/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuPrincipal;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;



/**
 *
 * @author ceals
 */
public class MenuPrincipal extends BasicGameState {
    private Image fondo;
    private SpriteSheet botonInventario, botonEquipo, botonFabricacion, botonSalir, punteroInventario, punteroEquipo;
    private int indicador;
    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        fondo=new Image("src/MenuPrincipal/res/fondo.jpg");
        botonInventario=new SpriteSheet("src/MenuPrincipal/res/inventario.png",400,250);
        botonEquipo=new SpriteSheet("src/MenuPrincipal/res/Equipo.png",400,325);
        botonFabricacion=new SpriteSheet("src/MenuPrincipal/res/Fabricacion.png",400,400);
        botonSalir=new SpriteSheet("src/MenuPrincipal/res/Salir.png",400,475);
        punteroInventario=new SpriteSheet("src/MenuPrincipal/res/cursor.png",400,550);
        //punteroEquipo=new SpriteSheet("res/cursor.png",400,550);
        indicador=0;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondo.draw();
        botonInventario.draw(300,10);
        botonEquipo.draw(300,85);
        botonFabricacion.draw(300,160);
        botonSalir.draw(300,235);
        punteroInventario.draw(400,50);
        
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input entrada=container.getInput();
        if (entrada.isKeyPressed(Input.KEY_DOWN) && indicador==0){ 
            punteroInventario.destroy();
            //punteroEquipo.draw(400, 125);
           
        }
    }
    
}
