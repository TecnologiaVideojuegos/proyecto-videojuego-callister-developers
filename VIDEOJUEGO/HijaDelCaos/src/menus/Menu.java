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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author ceals
 */
public class Menu extends BasicGameState {
    private Image fondo;
    private Image botonInventario, botonEquipo, botonFabricacion, botonSalir,botonGuardar, punteroInventario, punteroEquipo;
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
        botonGuardar=new Image("resources/Menus/Guardar.png");
        punteroInventario=new Image("resources/Menus/PunteroMenu.png");

        indicador=0;
        p=new Punto(80,70);
        renderMenu=new Punto(90,50);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondo.draw();
        botonInventario.draw(renderMenu.getX(),renderMenu.getY());
        botonEquipo.draw(renderMenu.getX(),renderMenu.getY()+70);
        botonFabricacion.draw(renderMenu.getX(),renderMenu.getY()+70*2);
        botonGuardar.draw(renderMenu.getX(),renderMenu.getY()+70*3);
        botonSalir.draw(renderMenu.getX(),renderMenu.getY()+70*4);
        punteroInventario.draw(p.getX(),p.getY());
        
        
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        
        Input entrada=container.getInput();
        try {
            cEntrada(entrada, game);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void cEntrada(Input entrada, StateBasedGame game) throws FileNotFoundException, IOException, SlickException, ClassNotFoundException{
        
        if (entrada.isKeyPressed(Input.KEY_S)){
            if(indicador!=4){
                p.setY(p.getY()+70);
                indicador++;
            }else{
                p.setY(p.getY()-70*4);
                indicador=0;
            }
              
        }
        if (entrada.isKeyPressed(Input.KEY_W)){ 
           if(indicador!=0){
                p.setY(p.getY()-70);
                indicador--;
            }else{
                p.setY(p.getY()+70*4);
                indicador=4;
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
                case 1:
                    game.enterState(4);
                    break;
                case 2:
                    game.enterState(5);
                    break;
                case 3:
                    FileOutputStream guardar=new FileOutputStream("saves/Lucia.dat");
                    ObjectOutputStream oo=new ObjectOutputStream(guardar);
                    Lucia.getLucia().writeExternal(oo);
                    oo.close();
                    guardar.close();
                    Lucia.getJuego().guardarMapa();                    
                    break;
                case 4:

                    game.enterState(0);
                    break;

            }
              
        }
        entrada.clearKeyPressedRecord();
    }
    
    }
    
