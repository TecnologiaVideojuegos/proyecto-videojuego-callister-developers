/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import Combate.Combate;
import chaoschild.Punto;
import entidades.Lucia;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import mapas.Mundo;
import menus.Equipo;
import menus.EstadoInventario;
import menus.Fabricacion;
import menus.Menu;
import org.newdawn.slick.Animation;
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
public class PantallaDeInicio extends BasicGameState{
    private Image fondo;
    private Animation selector;
    private int indicador;
    private Punto p;

    @Override
    public int getID() {
        return 6;
    }

    
    

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        fondo=new Image("resources/Menus/Pantalla Principal.png");
        SpriteSheet sprite=new SpriteSheet("resources/Menus/Selector.png", 64, 64);
        selector=new Animation();
        selector.addFrame(sprite.getSprite(0, 0), 300);
        selector.addFrame(sprite.getSprite(0, 1), 300);
        p=new Punto(440,50);
        indicador=0;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        fondo.draw();
        selector.draw(p.getX(), p.getY(),128,128);
        System.out.println(indicador);
    }
    

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        cEntrada(gc.getInput(), gc, sbg);
    }
    
    
    
    public void cEntrada(Input entrada, GameContainer gc, StateBasedGame sbg) throws SlickException {
        Juego juego = new Juego();
        if (entrada.isKeyPressed(Input.KEY_S)) {
                    if (indicador != 1) {
                        p.setY(p.getY() + 200);
                        indicador++;
                        System.out.println(p.getY());
                    } else {
                        p.setY(p.getY() - 200);
                        indicador = 0;
                        System.out.println(p.getY());
                    }
        }
        if (entrada.isKeyPressed(Input.KEY_W)) {
            //si estado 0, p, si estado 1 paux2
            
                    if (indicador != 0) {
                        p.setY(p.getY() - 200);
                        indicador--;
                        System.out.println(p.getY());
                    } else {
                        p.setY(p.getY() + 200) ;
                        indicador = 1;
                        System.out.println(p.getY());
                    }    
          }         

        if (entrada.isKeyPressed(Input.KEY_SPACE)) {
            Lucia Lucia;
            switch (indicador){
                case 0:
                    
                    Lucia=new Lucia();
                        try{
                            FileInputStream fileInputStream = new FileInputStream("saves/Lucia.dat");

                        ObjectInputStream objectInputStream;


                        objectInputStream = new ObjectInputStream(fileInputStream);


                        Lucia.readExternal(objectInputStream);

                        objectInputStream.close();
                        fileInputStream.close();
                        }catch(Exception e){

                        }
                        juego = new Juego();
                        sbg.addState(juego);
                        sbg.getState(0).init(gc, sbg);
                        sbg.enterState(0);
                    break;
                case 1:
                    
                    Lucia=new Lucia(200, 100);
                    juego = new Juego();
                    sbg.addState(juego);
                    sbg.getState(0).init(gc, sbg);
                    juego.setMapa(new int[]{0,-1});
                    sbg.enterState(0);
                    break;
            }
            sbg.addState(new Menu());
            sbg.addState(new EstadoInventario());
            sbg.addState(new Equipo());
            sbg.addState(new Fabricacion());
            sbg.getState(1).init(gc, sbg);
            sbg.getState(3).init(gc, sbg);
            sbg.getState(4).init(gc, sbg);
            sbg.getState(5).init(gc, sbg);
        }
    
        }
}
