/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Combate;

import chaoschild.Punto;
import entidades.Aliado;
import entidades.EntidadCombate;
import entidades.Lucia;
import entidades.enemigos.Enemigo;
import entidades.enemigos.Geobro;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private Image fondo, comv;
    private Lucia lucia;
    private ArrayList<Enemigo> enemigos;
    private Punto enem;
    private MenuCombate menu;
    private Magia rendm;
    private int mid1;
    private int ID;
    private ArrayList<Accion> turno;
    private StateBasedGame sgb;
    private int estado;
    private boolean atacando;


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
        this.sgb=sbg;
        atacando=false;
        menu=new MenuCombate(enemigos,this);
        comv=new Image("resources/Menus/Dialogos.jpg");
        }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        fondo.draw();
        lucia.draw();

        enemigosRender(grphcs);
        equipoRender(grphcs);
        switch(estado){
            case 0:
                    menu.render(grphcs);
                break;
            case 1:
                   comv.draw(0, 640-comv.getHeight());
                   grphcs.drawString(turno.get(0).toString(), 200, 640-comv.getHeight()+50);
                   if(!atacando){

                       turno.get(0).actionCalc();
                       atacando=true;  
                   }
                   if(turno.get(0).comprobar()){
                        turno.remove(0);
                        atacando=false;
                        if(comprobarDebilitados()){
                           if(enemigos.isEmpty()){
                               turno.clear();
                               volverMapa1();
                           } else{
                               try{
                                   if(lucia.getEquipo().contains(turno.get(0).getAtacante())){
                                       turno.get(0).setDefensor(enemigos.get(0));
                                   }
                                   
                               }catch(Exception e){}
                                   
                              
                               
                           }
                           
                        }
                   }
                   if(turno.isEmpty()){
                       estado=0;
                       menu.setAcciones(0);
                   } 
                break;
                
        }
        
         
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        comprobarInputs(gc, sbg);
        try {
            if(estado==0){
                menu.update(gc.getInput());
            }
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Combate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
     public void comprobarInputs(GameContainer gc, StateBasedGame sbg) throws SlickException{
        Input input=gc.getInput();
        
        if(input.isKeyPressed(Input.KEY_C)){
             
             volverMapa1();
             
        }
        
        
        
    }
     
    public void volverMapa(StateBasedGame sbg) throws SlickException{
        sbg.enterState(0, new FadeOutTransition(),  new FadeInTransition());     
        musica.cambiarM(0);
        musica.play();
        Lucia.getLucia().caminar();
         
         
    }
    
    public void enemigosRender(Graphics g){
        for (int i=0;i<enemigos.size();i++){
            enemigos.get(i).drawC(g);   
        }
    }
    public void equipoRender(Graphics g){
        for (int i=0;i<lucia.getEquipo().size();i++){
            lucia.getEquipo().get(i).drawC(g);
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
            enemigos.get(i).setPosCombate(new Punto(enem.getX()+10*i, enem.getY()+85*i));
            enemigos.get(i).combatir();
        }
        
    }

    public void volverMapa1() throws SlickException {
        volverMapa(sgb);
    }
    
    public void añadirTurno(Accion a){
        turno.add(a);
    }

    public void setTurno(ArrayList<Accion> turno) throws SlickException {
        this.turno = turno;
    }
    
    public void cambiarEstado(int a){
        estado=a;
    }
    
    public boolean comprobarDebilitados(){
        boolean b=false;
        ArrayList<Enemigo> a=new ArrayList();
        EntidadCombate e;
        for(int i=0;i<enemigos.size();i++){
            if(enemigos.get(i).getMultiplicadores()[0]==0){
                a.add(enemigos.get(i));
                b=true;
            }
        }
        for(int i=0;i<a.size();i++){
            e=a.get(i);
            for(int j=0;j<turno.size();j++){
                if(turno.get(j).getAtacante().equals(e)){
                    turno.remove(j);
                }
            }
            enemigos.remove(e);
            
        }
        return b;
    }
    
    public void añadirTurnos(ArrayList<Accion> a){
        for (int i = 0; i < a.size(); i++) {
            turno.add(a.get(i));  
        }
        
    }
    
    public void ordenarTurnos(){
        ArrayList<Accion> aux=new ArrayList();
        int mayor=0;
        while(!turno.isEmpty()){
            for(int i=0;i<turno.size();i++){
                if(turno.get(mayor).getAtacante().getEst()[7]*turno.get(mayor).getAtacante().getMultiplicadores()[7]<turno.get(i).getAtacante().getEst()[7]*turno.get(i).getAtacante().getMultiplicadores()[7]){
                    mayor=i;
                }
            }
            aux.add(turno.remove(mayor));
            mayor=0;
        }
       turno=aux; 
    }
}
