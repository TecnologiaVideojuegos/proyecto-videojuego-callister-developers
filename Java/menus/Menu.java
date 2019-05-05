/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import chaoschild.Punto;
import entidades.Lucia;



/**
 *
 * @author ceals
 */
public class Menu extends BasicGameState {
    private Image fondo;
    private Image botonInventario, botonEquipo, botonFabricacion, botonSalir, punteroInventario, punteroEquipo;
    private int indicador;
    private Punto p;
    private Punto renderMenu;
    private Lucia Lucia;
    
    
    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.Lucia=Lucia.getLucia();
        fondo=new Image("resources/Menus/fondo.jpg");
        botonInventario=new Image("resources/Menus/inventario.png");
        botonEquipo=new Image("resources/Menus/Equipo.png");
        botonFabricacion=new Image("resources/Menus/Fabricacion.png");
        botonSalir=new Image("resources/Menus/Salir.png");
        punteroInventario=new Image("resources/Menus/PunteroMenu.png");

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
        cEntrada(entrada, game);
        
    }
    
    public void cEntrada(Input entrada, StateBasedGame game){
        
        if (entrada.isKeyPressed(Input.KEY_S)){
            if(indicador!=3){
                p.setY(p.getY()+70);
                indicador++;
            }else{
                p.setY(p.getY()-70*3);
                indicador=0;
            }
              
        }
        if (entrada.isKeyPressed(Input.KEY_W)){ 
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
        
        if(entrada.isKeyPressed(Input.KEY_SPACE)){
            System.out.println(indicador);
            switch (indicador){
                case 0:
                    game.enterState(3);
                    break;
                case 3:
                    game.enterState(0);
                    break;
            }
              
        }
    }
    
    }
    
