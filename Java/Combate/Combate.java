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
import entidades.enemigos.Ragebbit;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import musica.GestorMusica;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
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
    private Punto enem, nivelsubir,nivelstr;
    private MenuCombate menu;
    private Magia rendm;
    private int mid1;
    private int ID;
    private ArrayList<Accion> turno;
    private StateBasedGame sgb;
    private int estado;
    private boolean atacando;
    private boolean contar;
    private int contador;
    private Image lvlim;
    private ArrayList<String> LVLUP;
    private int EXP;
    private boolean leveling;
    private int contador2;
    private int ultimosubido;
    private int contexp;
    private boolean conte;
    private boolean printturno;
    private int printcont;
    private Animation def;
    private Animation atac;


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
        genEnemigos(new Geobro(0,0), 2);
        this.sgb=sbg;
        atacando=false;
        menu=new MenuCombate(enemigos,this);
        comv=new Image("resources/Menus/Dialogos.jpg");
        lvlim=new Image("resources/Menus/NivelUp.png");
        nivelsubir=new Punto(640-lvlim.getWidth(), 640-lvlim.getHeight());
        nivelstr=new Punto(nivelsubir.getX()+9, nivelsubir.getY()+9);
        EXP=0;
        leveling=false;
        contador2=0;
        ultimosubido=5;
        contexp=0;
        conte=false;
        printturno=false;
        printcont=0;
        SpriteSheet sprite=new SpriteSheet("resources/Menus/Defensor.png", 40, 40);
        def=new Animation();
        def.addFrame(sprite.getSprite(0, 0), 300);
        def.addFrame(sprite.getSprite(1, 0), 300);
        sprite=new SpriteSheet("resources/Menus/Atacante.png", 40, 40);
        atac=new Animation();
        atac.addFrame(sprite.getSprite(0, 0), 300);
        atac.addFrame(sprite.getSprite(1, 0), 300);
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
                       if(printcont>100 || !printturno){
                           def.draw(turno.get(0).getDefensor().getPosCombate().getX()+32-20,turno.get(0).getDefensor().getPosCombate().getY()-40 );
                           atac.draw(turno.get(0).getAtacante().getPosCombate().getX()+32-20,turno.get(0).getAtacante().getPosCombate().getY()-40 );
                       }
                       if(!printturno){


                           if(!atacando){
                               turno.get(0).actionCalc();
                               atacando=true;  
                           }
                           if(turno.get(0).comprobar()){
                                turno.remove(0);
                                printturno=true;
                                atacando=false;
                                if(comprobarDebilitados()){
                                   if(enemigos.isEmpty()){
                                       turno.clear();
                                   }   
                                   try{
                                       if(lucia.getEquipo().contains(turno.get(0).getAtacante())){
                                           turno.get(0).setDefensor(enemigos.get(0));
                                       }
                                   }catch(Exception e){}
                                }
                                if(turno.isEmpty()){
                                    estado=2;
                                }

                           }
                       
                       } 
                    break;
                case 2:
                    if(leveling){
                        repartirEXP(contador2);
                        if(conte){
                            comv.draw(0, 640-comv.getHeight());
                            grphcs.drawString(LVLUP.get(0), 640-comv.getWidth()/2-LVLUP.get(0).length()/2*10, (640-comv.getHeight()/2));
                        }
                        if(!LVLUP.isEmpty()&&!conte){
                            renderLVLUP(grphcs);
                            contar=true;
                        }
                        if(contador2==lucia.getEquipo().size()-1 && !contar && !conte){
                            leveling=false;
                        }else{
                            if(!contar&&!conte){
                                contador2++;
                            }
                        }
                        
                        
                    }else{
                        estado=1;
                        contador2=0;
                        if(enemigos.isEmpty()){
                            turno.clear();
                            volverMapa1();
                        } 
                        if(turno.isEmpty()){
                               estado=0;
                               menu.setAcciones(0);
                           } 
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
            if(contar){
                contador=contador+i;
                if(contador>2500){
                    contar=false;
                    LVLUP.clear();
                    contador=0;
                }
            }
            if(conte){
                contexp++;
                if(contexp>1000){
                    conte=false;
                    LVLUP.clear();
                    contexp=0;
                }
            }
            if(printturno){
                printcont++;
                if(printcont>500){
                    printturno=false;
                    printcont=0;
                }
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
        if(estado!=0){
            if(input.isKeyPressed(Input.KEY_A)){
                if(contar){
                     contar=false;
                }
            }   
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
        printturno=true;
    }
    
    public boolean comprobarDebilitados() throws SlickException{
        boolean b=false;
        int exp=0;
        ArrayList<String> subir=new ArrayList();
        ArrayList<Enemigo> a=new ArrayList();
        EntidadCombate e;
        for(int i=0;i<enemigos.size();i++){
            if(enemigos.get(i).getMultiplicadores()[0]==0){
                a.add(enemigos.get(i));
                exp=exp+a.get(i).getExpg();
                b=true;
                leveling=true;
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
        this.EXP=this.EXP+exp;
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
    
    public void repartirEXP(int i) throws SlickException{
        if(i!=ultimosubido){
            int ex=EXP/(lucia.getEquipo().size()-i);
            EXP=EXP-ex;
            LVLUP=Lucia.getLucia().getEquipo().get(i).ganarExperiencia(ex);
            if(LVLUP.isEmpty()){
                LVLUP.add(lucia.getEquipo().get(i)+" ha obtenido "+ex+ " Puntos de Experiencia");
                conte=true;
            }
            
            System.out.println(lucia.getEquipo().get(i)+" ha ganado "+ex+" puntos de Experiencia");
            System.out.println(LVLUP);
            
            ultimosubido=i;
        }
        
    }
    
    public void renderLVLUP(Graphics g){
        lvlim.draw(nivelsubir.getX(), nivelsubir.getY());
        g.drawString(LVLUP.get(LVLUP.size()-1), nivelstr.getX(), nivelstr.getY());
        for(int i=0;i<LVLUP.size()-1;i++){
            g.drawString(LVLUP.get(i), nivelstr.getX(), nivelstr.getY()+20+20*i);
        }
        
    }
    
}
