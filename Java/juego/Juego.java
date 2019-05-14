/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import Combate.Combate;
import chaoschild.ChaosChild;
import chaoschild.Punto;
import entidades.Entidad;
import entidades.Lucia;
import entidades.enemigos.Ragebbit;
import mapas.Mapa;
import mapas.Mundo;
import musica.GestorMusica;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


/**
 *
 * @author victo
 */
public class Juego extends BasicGameState{
    
    private Mapa mapa;
    private Mundo mundo;
    private GestorMusica music;
    private Lucia Lucia;
    private float renderx;
    private float rendery;
    private float posinix;
    private float posiniy;
    private Ragebbit ragebit;
    private ChaosChild chaos;
    private boolean cambioMapa;
    private int contCambioMapa;
    
    @Override
    public int getID() {
        return 0;
    }

    
    public Juego(ChaosChild chaos) {
        this.chaos=chaos;
    }

    
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
        music=GestorMusica.getGestor();
        cambioMapa = false;
        contCambioMapa = 0;
        music.play();
        //Lucia=Lucia.getLucia(); 
        Lucia = new Lucia(3000, 841);
        mundo=new Mundo();
        mapa = mundo.getMapaCargado();
        posiniy=gc.getHeight()/2;
        posinix=gc.getWidth()/2;
        renderx=0;
        rendery=0;
        ragebit=new Ragebbit((int)Lucia.getPosicion().getX(), (int)Lucia.getPosicion().getY());
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        if(!cambioMapa){
            grphcs.translate(renderx, rendery);
            mundo.render();
            Lucia.draw(); 
            ragebit.draw();
            
            for(int i=0;i<4;i++){
                grphcs.draw(ragebit.getHitParedes()[i]);
            }
            for(int i=0;i<mapa.getHitBoxes().size();i++){
                grphcs.draw(mapa.getHitBoxes().get(i));
            }

            for(int i = 0;i < Lucia.getHitParedes().length;i++){
                grphcs.draw(Lucia.getHitParedes()[i]);
            }

            for(int i = 0;i < mapa.getPuertas().size();i++){
                grphcs.draw(mapa.getPuertas().get(i));
            }

            try{
                mapa.render(0, 0, mapa.getLayerIndex("Puente"));
            }catch(Exception e){  }

        } else {
            grphcs.setBackground(Color.black);
        }
        
        if(contCambioMapa >= 500){
            cambioMapa = false;
            contCambioMapa = 0;
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if(!cambioMapa){
            //System.out.println(Lucia.getPosicion().getX() + " - " + Lucia.getPosicion().getY());

            if(!music.playing()){
                music.play();
            }
            ragebit.actualizar(colisionPared(ragebit), i);
            comprobarInputs(gc,sbg);
            boolean[] pas={false};
            Lucia.actualizar(gc.getInput(),colisionPared(Lucia));

            renderx=posinix-Lucia.getPosicion().getX();
            rendery=posiniy-Lucia.getPosicion().getY();
            Lucia.update(i);
            comprobarPuertas();
            mapa = mundo.getMapaCargado();
        } else {
            contCambioMapa += i;
        }
    }
   
    public void comprobarPuertas(){
        int[] info = new int[5];
        info = this.mapa.colisionPuertas(Lucia);
        if(info[4] > 0){
            this.cambioMapa = true;
            this.mundo.cambiarMapa(info[0], info[1]);
            Lucia.setPosicion(new Punto(info[2], info[3]));
        }
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
        sbg.enterState(2, new FadeOutTransition(),new FadeInTransition());
        Lucia.combatir();
    }
    
}
