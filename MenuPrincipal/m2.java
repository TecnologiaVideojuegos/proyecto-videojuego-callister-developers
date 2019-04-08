/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuPrincipal;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import MenuPrincipal.Punto;



/**
 *
 * @author ceals
 */
public class m2 extends BasicGameState {
    private Image fondo;
    private Image botonInventario, botonEquipo, botonFabricacion, botonSalir, punteroInventario, punteroEquipo;
    private int indicador;
    private Punto p;
    private Punto renderMenu;
    
    
    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        fondo=new Image("src/MenuPrincipal/res/fondo.jpg");
        botonInventario=new Image("src/MenuPrincipal/res/inventario.png");
        botonEquipo=new Image("src/MenuPrincipal/res/Equipo.png");
        botonFabricacion=new Image("src/MenuPrincipal/res/Fabricacion.png");
        botonSalir=new Image("src/MenuPrincipal/res/Salir.png");
        punteroInventario=new Image("src/MenuPrincipal/res/PunteroMenu.png");
        punteroEquipo=new Image("src/MenuPrincipal/res/cursor.png");
        indicador=0;
        p=new Punto(425,35);
        renderMenu=new Punto(300,10);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondo.draw();
        botonInventario.draw(renderMenu.getX(),renderMenu.getY());
        botonEquipo.draw(renderMenu.getX(),renderMenu.getY()+70);
        botonFabricacion.draw(renderMenu.getX(),renderMenu.getY()+70*2);
        botonSalir.draw(renderMenu.getX(),renderMenu.getY()+70*3);
        punteroInventario.draw(p.getX(),p.getY());
        
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        
        Input entrada=container.getInput();
        if (entrada.isKeyPressed(Input.KEY_DOWN)){
            if(indicador!=3){
                p.setY(p.getY()+70);
                indicador++;
            }else{
                p.setY(p.getY()-70*3);
                indicador=0;
            }
              
        }
        if (entrada.isKeyPressed(Input.KEY_UP)){ 
           if(indicador!=0){
                p.setY(p.getY()-70);
                indicador--;
            }else{
                p.setY(p.getY()+70*3);
                indicador=3;
            }
        }
        if(entrada.isKeyPressed(Input.KEY_M)){
            
            game.enterState(0);
        }
        if(entrada.isKeyPressed(Input.KEY_SPACE)&& indicador==3){
            
            game.enterState(0);
        }
    }
    }
    
