/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Combate;

import chaoschild.Punto;
import entidades.EntidadCombate;
import entidades.Lucia;
import entidades.enemigos.Enemigo;
import entidades.enemigos.Geobro;
import java.util.ArrayList;
import musica.GestorMusica;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.CombinedTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author victo
 */
public class Combate extends BasicGameState{
    
    private GestorMusica musica;
    private Image fondo;
    private Lucia lucia;
    private ArrayList<Enemigo> enemigos;
    private Punto enem;
    private MenuCombate menu;
    private Magia rendm;
    private int mid1;
    private int ID;
    private ArrayList<Accion> turno;

    public Combate(int ID) {
        this.ID = ID;
    }

    
    
    
    
    @Override
    public int getID() {
         return ID;
    }
    
    

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        enem=new Punto(150, 180);
        musica=GestorMusica.getGestor();
        fondo=new Image("resources/Mapas/Cueva_inicio/Cueva_inicio.png");
        lucia=Lucia.getLucia();
        enemigos=new ArrayList();
        genEnemigos(new Geobro(0,0), 3);
        
        menu=new MenuCombate(enemigos);
        }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        
        fondo.draw();
        lucia.draw();
        enemigosRender();
        equipoRender();
        menu.render(grphcs);
        try{
                rendm.draw();
            
            if(rendm.getAnim().isStopped()){
                rendm=null;
            }
            
        }catch(Exception e){}
        
                
        
        
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        comprobarInputs(gc, sbg);
        menu.update(gc.getInput());
        
    }
    
    
     public void comprobarInputs(GameContainer gc, StateBasedGame sbg) throws SlickException{
        Input input=gc.getInput();
        
        if(input.isKeyPressed(Input.KEY_C)){
             
             volverMapa(sbg);
             
        }
        if(input.isKeyPressed(Input.KEY_Q)){
             
             Lucia.getLucia().setAnimacionCombate(2);
             rendm=lucia.getMagias().get(0);
             rendm.daño(lucia, enemigos.get(0));
             System.out.println(enemigos.get(0).getPosCombate());
             
        }
        
        
    }
     
    public void volverMapa(StateBasedGame sbg) throws SlickException{
        sbg.enterState(0, new FadeOutTransition(),  new FadeInTransition());     
        musica.cambiarM(0);
        musica.play();
        Lucia.getLucia().caminar();
         
         
    }
    
    public void enemigosRender(){
        for (int i=0;i<enemigos.size();i++){
            enemigos.get(i).draw();
            
        }
    }
    public void equipoRender(){
        for (int i=0;i<lucia.getEquipo().size();i++){
            lucia.getEquipo().get(i).draw();
        }
    }
    
    
    private void genEnemigos(Enemigo e, int a) throws SlickException{
        if (a!=1){
        
            enem.setY((360-64)/a);
            
            
        }
        enemigos.add(e); 
        for(int i=1;i<a;i++){
            enemigos.add(new Geobro(0,0));
            
        }
        for(int i=0;i<enemigos.size();i++){
            enemigos.get(i).setPosCombate(new Punto(enem.getX()+10*i, enem.getY()+80*i));
            enemigos.get(i).combatir();
            System.out.println(i);
        }
        
    }
    
    
    
    
}
