/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slick.video;

import MenuPrincipal.MenuPrincipal;
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
    private Protagonista lucia;
    private Input entrada;
    private float renderx;
    private float rendery;
    private float posinix;
    private float posiniy;
    private MenuPrincipal menu;
    private boolean m;
    
    
    public Juego() {
        super();
        
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        mapa=new Mapa("resources/Mapas/PRUEBA.tmx", "/resources/Mapas");
        posinix=32*9+3;
        posiniy=32*9+3;
        lucia=new Protagonista(posinix, posiniy, "resources/Lucia.png");
        posiniy=gc.getHeight()/2;
        posinix=gc.getWidth()/2;
        
        renderx=0;
        rendery=0;
        this.entrada = gc.getInput();
        menu=new MenuPrincipal();
        m=false;
        System.out.println("init");
        
    }   


    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if(m){
           
           updateMenu(gc);
        }else{
            
            int objectLayer=mapa.getLayerIndex("Objetos");
            mapa.getTileId(0, 0, objectLayer);//ID de el objeto
            int[] a=new int[4]; 
            lucia.update(delta, entrada, colisionPared(lucia));
            renderx=posinix-lucia.getPosicion().getX();
            rendery=posiniy-lucia.getPosicion().getY();
            mapa.moverParedes(0,0);
            inputs(entrada, sbg);
            
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        if(m){
            menu.render();
        }else{
            grphcs.translate(renderx,rendery);
            mapa.render(0, 0);
            lucia.draw();
//        grphcs.draw(lucia.getHitbox());
        for(int i=0;i<mapa.getHitBoxes().size();i++){
            grphcs.draw(mapa.getHitBoxes().get(i));
        }
        for(int i=0;i<4;i++){
            grphcs.draw(lucia.getHitParedes()[i]);
        }
        }
        
        
    }

    @Override
    public int getID() {
        return 0;
    }
        
    public boolean[] colisionPared(Entidad lucia){
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
    
    private void inputs(Input input, StateBasedGame sbg){
        if(input.isKeyPressed(Input.KEY_M)){
            //m=true;
            sbg.enterState(1);
            sbg.getState(1);
        }
    }
   
    private void updateMenu(GameContainer gc) throws SlickException{
        menu.update(gc); 
        m=menu.accion(entrada);
    }
    
    
}
