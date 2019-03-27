/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slick.video;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


/**
 *
 * @author victo
 */
public class Juego extends BasicGameState{
    static int WIDTH = 640;
    static int HEIGHT = 640;
    private Mapa mapa;
    private Lucia lucia;
    private Input entrada;
    private float renderx;
    private float rendery;
    
    
    
    public Juego() {
        super();
        
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        mapa=new Mapa("resources/Pruebas_Tuto.tmx", "/resources");
        lucia=new Lucia(32*10, 32*10);
        renderx=0;
        rendery=0;
        this.entrada = gc.getInput();
        
        
    }   


    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        int objectLayer=mapa.getLayerIndex("Objetos");
        mapa.getTileId(0, 0, objectLayer);//ID de el objeto
        int[] a=new int[4]; 
        lucia.update(delta, entrada, colisionPared(lucia));
        renderx=lucia.getPosicion().getX()-(10*32);
        rendery=lucia.getPosicion().getY()-(10*32);
        mapa.moverParedes(0,0);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        
        grphcs.translate(10*32-lucia.getPosicion().getX(), 10*32-lucia.getPosicion().getY());
        mapa.render(0, 0);
        lucia.draw();
        grphcs.draw(lucia.getHitbox());
        for(int i=0;i<mapa.getHitBoxes().size()-1;i++){
            grphcs.draw(mapa.getHitBoxes().get(i));
        }
        for(int i=0;i<4;i++){
            grphcs.draw(lucia.getHitParedes()[i]);
        }
    }

    @Override
    public int getID() {
        return 0;
    }
        
    public boolean[] colisionPared(Lucia lucia){
        int x= (int)lucia.getPosicion().getX();
        int y =(int)lucia.getPosicion().getY();
        Rectangle[] hitbox=lucia.getHitParedes();
        
        //System.out.println(y/32);
        //System.out.println((x+w)/32);
        
        
        boolean[] pasar={true, true, true, true};
        
        for(int i =0;i<mapa.getHitBoxes().size();i++){
            
                if(mapa.getHitBoxes().get(i).intersects(hitbox[0])){
                    pasar[0]=false;
                    
                    
                }
            }
        
        for(int i =0;i<mapa.getHitBoxes().size();i++){
            
                if(mapa.getHitBoxes().get(i).intersects(hitbox[1])){
                    pasar[1]=false;
                   
                    
                }
            }
        
        for(int i =0;i<mapa.getHitBoxes().size();i++){
            
                if(mapa.getHitBoxes().get(i).intersects(hitbox[2])){
                    pasar[2]=false;
                  
                
            }
        }
        for(int i =0;i<mapa.getHitBoxes().size();i++){
            
                
                if(mapa.getHitBoxes().get(i).intersects(hitbox[3])){
                    pasar[3]=false;
               
                }
            
        }
        
        return pasar;
    }
   

    
    
}
