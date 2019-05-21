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
import entidades.Antonio;
import entidades.EntidadCombate;
import entidades.NPC;
import entidades.Paula;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapas.ConjuntoEnemigos;
import mapas.Mapa;
import mapas.Mundo;
import mapas.Script;
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
    private boolean cambioMapa;
    private int contCambioMapa;
    private Dialogo dialogo;
    private Guardar gMapa;
    private boolean acNpc;
    private Script script;
    
    @Override
    public int getID() {
        return 0;
    }
    
    public Juego() {
        
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
        this.script = new Script(this);
        music=GestorMusica.getGestor();
        this.gMapa = new Guardar();
        cambioMapa = false;
        contCambioMapa = 0;
        music.play();
        Lucia=Lucia.getLucia(); 
        Lucia.setJuego(this);
        mundo=new Mundo(this);
        try{
            mapa = mundo.getMapaCargado();
        } catch(Exception e){}
        posiniy=gc.getHeight()/2;
        posinix=gc.getWidth()/2;
        renderx=0;
        rendery=0;
        this.dialogo = new Dialogo(this);
        this.acNpc = false;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        if(!cambioMapa){
            grphcs.translate(renderx, rendery);
            mundo.render();
            mapa.drawEnemigos();
            Lucia.draw(); 
            this.mapa.getGestorNpc().draw();
            for(NPC npc : this.mapa.getNpcs()){
                this.mapa.getGestorNpc().drawAnim(npc);
            }
            this.dialogo.draw(grphcs);
        
            /*
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
            
            for(int i = 0;i < mapa.getGestorNpc().getNpcs().size();i++){
                for(int j = 0;j < mapa.getGestorNpc().getNpcs().get(i).getAreaAccion().size();j++){
                    grphcs.draw(mapa.getGestorNpc().getNpcs().get(i).getAreaAccion().get(j));
                }
            }
            
            drawHitEnemigos(grphcs);
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
        if(!cambioMapa){
            if(!dialogo.isActivo()){
                this.script.comprobarEstado();
                //System.out.println(Lucia.getPosicion().getX() + " - " + Lucia.getPosicion().getY());

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
                for(NPC npc : this.mapa.getGestorNpc().getNpcs()){
                    comprobarDialogoNPC(gc, Lucia);
                }
                this.mapa.getGestorNpc().update(i);
                comprobarCofre(gc, Lucia);
                actualizarEnemigos(i);
                colisionEnemigos(sbg, gc);
                comprobarPuertas();
                mapa = mundo.getMapaCargado();
            }
        } else {
            contCambioMapa += i;
        }
        comprobarFinDialogo(gc);
    }
    
    public void setMapa(int[] coord){
        this.mundo.setCoord(coord);
        this.mundo.cambiarMapa(mundo.getCoord()[0], mundo.getCoord()[1]); 
        this.script.setEstado(0);
        this.script.setActivo(true);
    }
    
    private void colisionEnemigos(StateBasedGame sbg, GameContainer gc){
        ArrayList<Enemigo> ene = this.mapa.getEnemigos();
        ArrayList<ConjuntoEnemigos> conjEne = this.mapa.getConjuntoEnemigos();
        Rectangle[] hitbox = Lucia.getHitParedes();
        boolean combate = false;
        ConjuntoEnemigos enemigo = new ConjuntoEnemigos();
        
        for(ConjuntoEnemigos conjunto : conjEne){
            for(Enemigo e : conjunto.getEnemigos()){
                if(!combate){
                    for(int i = 0;i < 4;i++){
                        if(!combate){
                            if(e.getHitParedes()[i].intersects(hitbox[0]) ||
                                    e.getHitParedes()[i].intersects(hitbox[1]) ||
                                    e.getHitParedes()[i].intersects(hitbox[2]) ||
                                    e.getHitParedes()[i].intersects(hitbox[3])){
                                enemigo = conjunto;
                                combate = true;
                            }
                        }
                    }
                }
            }
        }
        
        if(combate){
            try {
                combate(sbg, gc, enemigo);
            } catch (SlickException ex) {}
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
        for(ConjuntoEnemigos enemigo : mapa.getConjuntoEnemigos()){
            try {
                enemigo.getEnemigo().actualizar(colisionPared(enemigo.getEnemigo()), n);
            } catch (SlickException ex) {}
        }
    }
   
    public void comprobarPuertas(){
        int[] info = new int[5];
        info = this.mapa.colisionPuertas(Lucia);
        
        setNuevaInfo(info);
    }
    
    public void setNuevaInfo(int[] info){
        if(info[4] > 0){
            this.cambioMapa = true;
            this.mundo.cambiarMapa(info[0], info[1]);
            Lucia.setPosicion(new Punto(info[2], info[3]));
        }
    }
 
    public void comprobarDialogoNPC(GameContainer gc, Entidad e){
        Input input=gc.getInput();
        
        if(!this.acNpc){
            Rectangle[] hitbox = e.getHitParedes();
            ArrayList<NPC> npcs = this.mapa.getNpcs();
            ArrayList<NPC> excl = new ArrayList();
            ArrayList<Rectangle> areaAccion = new ArrayList();
            boolean colision = false;
            for(int i = 0;i < npcs.size();i++){
                areaAccion = npcs.get(i).getAreaAccion();
                for(int j = 0;j < areaAccion.size();j++){
                    if(hitbox[0].intersects(areaAccion.get(j)) || 
                        hitbox[1].intersects(areaAccion.get(j)) ||
                        hitbox[2].intersects(areaAccion.get(j)) ||
                        hitbox[3].intersects(areaAccion.get(j))){
                        colision = true;
                        excl.add(npcs.get(i));
                        this.mapa.getGestorNpc().setNpcHablando(npcs.get(i));
                    }
                }
            }
            
            if(colision){
                this.mapa.getGestorNpc().setInter(true);
            } else {
                this.mapa.getGestorNpc().setInter(false);
            }
            
            if(input.isKeyPressed(Input.KEY_SPACE) && colision){
                this.mapa.getGestorNpc().accion(mapa.getGestorNpc().getNpcHablando());
                this.acNpc = true;
            }
        } else {
            this.mapa.getGestorNpc().accion(mapa.getGestorNpc().getNpcHablando());
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
        }/*
        if(input.isKeyPressed(Input.KEY_C)){
            combate(sbg, gc);
        }*/
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
    
    private void drawHitEnemigos(Graphics grphcs){
        for(Enemigo enemigo : mapa.getEnemigos()){
            for(int i = 0;i < enemigo.getHitParedes().length;i++){
                grphcs.draw(enemigo.getHitParedes()[i]);
            }
        }
    }
    
    public void analizarResulCombate(){
        ArrayList<ConjuntoEnemigos> enemigos = this.mapa.getConjuntoEnemigos();
        Iterator<ConjuntoEnemigos> iter = enemigos.iterator();
        int cont;
        while(iter.hasNext()){
            ConjuntoEnemigos enes = iter.next();
            cont = 0;
            for(Enemigo ene : enes.getEnemigos()){
                if(ene.getMultiplicadores()[0] <= 0){
                    System.out.println(ene.getNombre());
                    if(ene.getNombre().equals("Antonio") || ene.getNombre().equals("Paula")){
                        try {
                            Antonio ant = new Antonio(580, 80);
                            Lucia.addEquipo(ant);
                            Paula pa = new Paula(580, 320);
                            Lucia.addEquipo(pa);
                        } catch (SlickException ex) {}
                    }
                    this.mapa.getConjuntoEnemigos().remove(ene);
                    cont++;
                }
            }
            if(enes.getEnemigos().size() == cont){
                iter.remove();
            }
        }
        
        int contAux = 0;
        for(EntidadCombate e : Lucia.getEquipo()){
            if(e.getMultiplicadores()[0] <= 0){
                contAux++;
            }
        }
        if(contAux == Lucia.getEquipo().size()){
            if(this.mundo.getMundo().get(2).getZona().get(3).getEdificios().get(3).isDescubierto()){
                this.mapa.setNuevaInfo(2, -4, 192, 90, 1);
            } else {
                this.mapa.setNuevaInfo(0, -1, 200, 100, 1);
            }
            setNuevaInfo(this.mapa.getNuevaInfo());
            for(EntidadCombate e : Lucia.getEquipo()){
                e.getMultiplicadores()[0] = e.getEst()[0];
            }
        }
    }
    
    public void combate(StateBasedGame sbg, GameContainer gc, ConjuntoEnemigos enemigos) throws SlickException{
        Combate combate = new Combate(2);
        sbg.addState(combate);
        sbg.getState(2).init(gc, sbg);
        combate.genEnemigos(enemigos);
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

    public Dialogo getDialogo() {
        return dialogo;
    }

    public void setAcNpc(boolean acNpc) {
        this.acNpc = acNpc;
    } 
}