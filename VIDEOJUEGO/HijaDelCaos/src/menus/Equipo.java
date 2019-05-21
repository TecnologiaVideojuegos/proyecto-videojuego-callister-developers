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

    private Image fondo,ata, def, puntero;
    private Punto p, punt;
    private int indicador, guardado, estado;
    private Punto[] equip;
    private Lucia lucia;
    
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
        puntero = new Image("resources/Menus/PunteroMenu.png");
        indicador=0;
        guardado=0;
        punt=new Punto(100, 20);
        estado=0;
        lucia=Lucia.getLucia();
        equip=new Punto[4];
        equip[0]=new Punto(20, 20);
        equip[1]=new Punto(20,320);
        equip[2]=new Punto(370, 20);
        equip[3]=new Punto(370, 320);
    }
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        fondo.draw();
        renderEquipo(grphcs, gc);
        puntero.draw(punt.getX(), punt.getY());
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        cEntrada(gc.getInput(), sbg);
    }
    
    public void renderEquipo( Graphics g, GameContainer gc) throws SlickException{

        for(int i=0;i<Lucia.getLucia().getEquipo().size();i++){
            lucia.getEquipo().get(i).renderInfo(equip[i], g, gc, ata, def);
        }
    }
    
    public void cEntrada(Input entrada, StateBasedGame game) throws SlickException{
        if(entrada.isKeyPressed(Input.KEY_B)){
            switch(estado){
                case 0:
                    game.enterState(1);
                    break;
                case 1:
                    indicador=0;
                    punt=new Punto(equip[indicador].getX()+80,equip[indicador].getY() );
                    estado=0;
                    break;
            }
            
        }
        
        
        if (entrada.isKeyPressed(Input.KEY_S)) {
            switch (estado){
                case 0:
                    if(indicador!=lucia.getEquipo().size()-1){
                        indicador++;
                        punt=new Punto(equip[indicador].getX()+80,equip[indicador].getY() );
                    }else{
                        indicador=0;
                        punt=new Punto(equip[indicador].getX()+80,equip[indicador].getY() );
                    }
                    break;
                case 1:
                    if(indicador!=1){
                        indicador++;
                        punt=new Punto(punt.getX(),punt.getY()+30 );
                    }else{
                        punt=new Punto(punt.getX(),punt.getY()-30);
                        indicador=0;
                    }   
                    break;
            }  
        }
        
        if (entrada.isKeyPressed(Input.KEY_W)) {
            switch(estado){
                case 0:
                    if(indicador!=0){
                        
                        indicador--;
                        System.out.println("Indicador "+indicador);
                        punt=new Punto(equip[indicador].getX()+80,equip[indicador].getY() );

                    }else{
                        indicador=lucia.getEquipo().size()-1;
                        punt=new Punto(equip[indicador].getX()+80,equip[indicador].getY() );
                    }
                    break;
                case 1:
                    if(indicador!=0){
                        indicador++;
                        punt=new Punto(punt.getX(),punt.getY()-30 );
                    }else{
                        punt=new Punto(punt.getX(),punt.getY()+30);
                        indicador=1;
                    }   
                    break;
            }  
        }
        
        if (entrada.isKeyPressed(Input.KEY_SPACE)) {
            switch(estado){
                case 0:
                    guardado=indicador;
                    indicador=0;
                    estado=1;
                    punt=new Punto(equip[guardado].getX()+27, equip[guardado].getY()+200);
                    break;
                case 1:
                    lucia.getEquipo().get(guardado).quitarGema(indicador);
                    
                    break;
            }
        }

        
    }
    
}
