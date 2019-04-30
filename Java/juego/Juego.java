/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import Combate.Combate;
import chaoschild.ChaosChild;
import entidades.Entidad;
import entidades.Lucia;
import entidades.enemigos.Geobro;
import mapas.Mapa;
import musica.GestorMusica;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.CombinedTransition;


/**
 *
 * @author victo
 */
public class Juego extends BasicGameState{
    
    private Mapa mapa;
    private GestorMusica music;
    private Lucia Lucia;
    private float renderx;
    private float rendery;
    private float posinix;
    private float posiniy;
    private Geobro geobro;
    private ChaosChild chaos;
    //private Mundo mundo;
    
    @Override
    public int getID() {
        return 0;
    }

    
    public Juego(ChaosChild chaos) {
        this.chaos=chaos;
    }

    
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //mundo=new Mundo();
        music=GestorMusica.getGestor();
        music.play();
        //mapa=mundo.getMapaCargado();
        mapa=new Mapa("resources/Mapas/Ciudad_ciudad/Ciudad_ciudad.tmx", "/resources/Mapas/Ciudad_ciudad");
        Lucia=Lucia.getLucia(); 
        posiniy=gc.getHeight()/2;
        posinix=gc.getWidth()/2;
        renderx=0;
        rendery=0;
        geobro=new Geobro(250, 250);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        
        grphcs.translate(renderx, rendery);
        mapa.render(0,0);
        Lucia.draw();
        geobro.draw();
        for(int i=0;i<4;i++){
            grphcs.draw(Lucia.getHitParedes()[i]);
        }
        for(int i=0;i<mapa.getHitBoxes().size();i++){
            grphcs.draw(mapa.getHitBoxes().get(i));
        }
        try{
            mapa.render(0, 0, mapa.getLayerIndex("Puente"));
        }catch(Exception e){
            
        }
        
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if(!music.playing()){
            music.resume();
        }
        //mapa=mundo.getMapaCargado();
        comprobarInputs(gc,sbg);
        boolean[] pas={false};
        Lucia.actualizar(gc.getInput(),colisionPared(Lucia));
        geobro.actualizar(colisionPared(geobro));
        renderx=posinix-Lucia.getPosicion().getX();
        rendery=posiniy-Lucia.getPosicion().getY();
        geobro.update(i);
        Lucia.update(i);
    }
   
    
    
    public void comprobarInputs(GameContainer gc, StateBasedGame sbg) throws SlickException{
        Input input=gc.getInput();
        if(input.isKeyPressed(Input.KEY_M)){
            music.pause();
            sbg.enterState(1);
        }
        if(input.isKeyPressed(Input.KEY_C)){
            combate(sbg, gc);
        }
    }
    
    public boolean[] colisionPared(Entidad e) throws SlickException{
        
        Rectangle[] hitbox=e.getHitParedes();
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
    
    public void combate(StateBasedGame sbg, GameContainer gc) throws SlickException{
        sbg.addState(new Combate(2));
        sbg.getState(2).init(gc, sbg);
        music.cambiarM(1);
        music.play();
        sbg.enterState(2);
        Lucia.combatir();
    }
    
}
