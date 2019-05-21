/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import Combate.Combate;
import chaoschild.ChaosChild;
import chaoschild.Punto;
import cofres.Cofre;
import dialogo.Dialogo;
import entidades.Entidad;
import entidades.Lucia;
import entidades.enemigos.*;
import java.util.ArrayList;
import Guardar.Guardar;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapas.Mapa;
import mapas.Mundo;
import musica.GestorMusica;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.*;


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
    private ChaosChild chaos;
    private boolean cambioMapa;
    private int contCambioMapa;
    private Dialogo dialogo;
    private Guardar gMapa;
    
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
        this.gMapa = new Guardar();
        cambioMapa = false;
        contCambioMapa = 0;
        music.play();
        //Lucia=Lucia.getLucia(); 
//        Lucia = new Lucia(500, 500);
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
           
        
        Lucia.setJuego(this);
        mundo=new Mundo(this);
        mapa = mundo.getMapaCargado();
        posiniy=gc.getHeight()/2;
        posinix=gc.getWidth()/2;
        renderx=0;
        rendery=0;
        this.dialogo = new Dialogo(this);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        if(!cambioMapa){
            grphcs.translate(renderx, rendery);
            mundo.render();
            mapa.drawEnemigos();
            Lucia.draw(); 
            this.dialogo.draw(grphcs);
            
            
            /*
           
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
            
            for(int i = 0;i < mapa.getCofres().size();i++){
                grphcs.draw(mapa.getCofres().get(i).getHitBox());
                for(int j = 0;j < mapa.getCofres().get(i).getAreaAccion().size();j++){
                    grphcs.draw(mapa.getCofres().get(i).getAreaAccion().get(j));
                }
            }
*/
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
        boolean aux;
        try{
            aux = mapa.getCofres().get(0).isAbriendo();
        } catch(Exception e){
            aux = false;
        }
        if(!cambioMapa){
            if(!aux && !dialogo.isActivo()){
                System.out.println(Lucia.getPosicion().getX() + " - " + Lucia.getPosicion().getY());

                if(!music.playing()){
                    music.play();
                }
               
                actualizarEnemigos(i);
                comprobarInputs(gc,sbg);
                boolean[] pas={false};
                Lucia.actualizar(gc.getInput(),colisionPared(Lucia));

                renderx=posinix-Lucia.getPosicion().getX();
                rendery=posiniy-Lucia.getPosicion().getY();
                Lucia.update(i);
                comprobarCofre(gc, Lucia);
                comprobarPuertas();
                mapa = mundo.getMapaCargado();
            }
        } else {
            contCambioMapa += i;
        }
        
        aux(gc);
        comprobarFinDialogo(gc);
    }
    
    private void aux(GameContainer gc){
        Input input=gc.getInput();
        if(input.isKeyPressed(Input.KEY_O)){
            guardarMapa();
        }
    }
    
    public void guardarMapa(){
        this.gMapa.guardarMapa(this.mundo.getCoord());
    }
    
    private void comprobarFinDialogo(GameContainer gc){
        if(this.dialogo.isActivo()){
            Input input=gc.getInput();
            
            if(input.isKeyPressed(Input.KEY_SPACE)){
                this.dialogo.setActivo(false);
            }
        }
    }
    
    public void setTextoDialogo(String texto){
        this.dialogo.setTexto(texto);
    }
    
    public void activarDialogo(){
        this.dialogo.setActivo(true);
    }
    
    public Guardar getGMapa(){
        return this.gMapa;
    }
    
    private void actualizarEnemigos(int n){
        for (Enemigo enemigo : mapa.getEnemigos()) {
            try {
                enemigo.actualizar(colisionPared(enemigo), n);
            } catch (SlickException ex) {}
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
 
    public void comprobarCofre(GameContainer gc, Entidad e){
        Input input=gc.getInput();
               
        if(input.isKeyPressed(Input.KEY_SPACE)){
            Rectangle[] hitbox = e.getHitParedes();
            ArrayList<Cofre> cofres = this.mapa.getCofres();
            ArrayList<Rectangle> areaAccion = new ArrayList();
            boolean colision = false;
            Cofre cofre = new Cofre();
            
            for(int i = 0;i < cofres.size();i++){
                if(!cofres.get(i).isAbierto()){
                    areaAccion = cofres.get(i).getAreaAccion();
                    for(int j = 0;j < areaAccion.size();j++){
                        if(hitbox[0].intersects(areaAccion.get(j)) || 
                           hitbox[1].intersects(areaAccion.get(j)) ||
                           hitbox[2].intersects(areaAccion.get(j)) ||
                           hitbox[3].intersects(areaAccion.get(j))){
                            colision = true;
                            cofre = cofres.get(i);
                        }
                    }
                }
            }
            
            if(colision){
                try {
                    Lucia.getIntven().add(cofre.abrir());
                } catch (SlickException ex) {}
            }
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
                } else if(mapa.getHitBoxes().get(i).intersects(hitbox[1])){
                    pasar[1]=false;  
                } else if(mapa.getHitBoxes().get(i).intersects(hitbox[2])){
                    pasar[2]=false;
                } else if(mapa.getHitBoxes().get(i).intersects(hitbox[3])){
                    pasar[3]=false;
                }
            }
        
        for(int i = 0;i < mapa.getCofres().size();i++){
            if(mapa.getCofres().get(i).getHitBox().intersects(hitbox[0])){
                pasar[0] = false;
            } else if(mapa.getCofres().get(i).getHitBox().intersects(hitbox[1])){
                pasar[1] = false;
            } else if(mapa.getCofres().get(i).getHitBox().intersects(hitbox[2])){
                pasar[2] = false;
            } else if(mapa.getCofres().get(i).getHitBox().intersects(hitbox[3])){
                pasar[3] = false;
            }
        }
        
        return pasar;
    }
    
    public void combate(StateBasedGame sbg, GameContainer gc) throws SlickException{
        Combate combate = new Combate(2);
        combate.genEnemigos(this.mundo.getMapaCargado().getEnemigos());
        sbg.addState(combate);
        sbg.getState(2).init(gc, sbg);
        music.cambiarM(1);
        music.play();
        sbg.enterState(2, new FadeOutTransition(),new FadeInTransition());
        Lucia.combatir();
    }
    
    public Lucia getLucia(){
        return this.Lucia;
    }
    
    public Mundo getMundo(){
        return this.mundo;
    }
    
}