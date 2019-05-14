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
import entidades.enemigos.Hipograsidi;
import entidades.enemigos.Ragebbit;
import itemsjuego.GemaFuego1;
import itemsjuego.*;
import itemsjuego.Objeto;
import itemsjuego.PocionVGrande;
import itemsjuego.PocionVMediana;
import itemsjuego.PocionVPequeña;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import musica.GestorMusica;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
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
    private ArrayList<Aliado> aliados;
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
    private HealthBar barra;
    private boolean turno0;
    private ArrayList<Objeto> objetos;
    private boolean prinr;
    private int rcont;


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
        aliados=new ArrayList();
        genEnemigos(new Hipograsidi(0,0), 1);
        this.sgb=sbg;
        atacando=false;
        aliados=(ArrayList<Aliado>) lucia.getEquipo().clone();
        menu=new MenuCombate(enemigos,this, aliados);
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
        barra=new HealthBar(lucia);
        comprobar1();
        turno0=true;
        prinr=true;
        rcont=0;
        objetos=new ArrayList();
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        fondo.draw();
        if(turno0&&aliados.isEmpty()){
            estado=4;
            volverMapa1();
        }
        enemigosRender(gc, grphcs);
        equipoRender(gc, grphcs);
        grphcs.setColor(Color.white);
        
        turno0=false;
        
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
                                       if(aliados.contains(turno.get(0).getAtacante())){
                                           turno.get(0).setDefensor(enemigos.get(0));
                                       }
                                   }catch(Exception e){}
                                }
                                if(comprobarDebilitadosA()){

                                    if(aliados.isEmpty()){
                                        turno.clear();
                                        
                                    }
                                    try {
                                        if(enemigos.contains(turno.get(0).getAtacante())){
                                           turno.get(0).setDefensor(aliados.get(0));
                                       }
                                    } catch (Exception e) {
                                    }
                                       
                                   
                                }
                                if(turno.isEmpty()){
                                    estado=2;
                                }

                           }
                       
                       } 
                    break;
                case 2:
                    if(aliados.isEmpty()){
                            turno.clear();
                            volverMapa1();
                        }
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
                        if(contador2==aliados.size()-1 && !contar && !conte){
                            leveling=false;
                        }else{
                            if(!contar&&!conte){
                                contador2++;
                            }
                        }
                        
                        
                    }else{
                        estado=1;
                        contador2=0;
                         
                        if(turno.isEmpty()){
                               estado=0;
                               menu.setAcciones(0);
                        } 
                        if(enemigos.isEmpty()){
                            turno.clear();
                            estado=3;
                            
                            repartirR();
                        }
                        
                    }

                    break;
                case 3: 
                    
                    if(objetos.isEmpty()||aliados.isEmpty())prinr=false;
                    if(prinr){
                        
                        renderRecompensas(grphcs);
                        
                    }else{
                        objetos.clear();
                        volverMapa(sbg);
                        estado=4;
                    }
                    
                    break;
                case 4:
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
                    LVLUP.remove(0);
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
            if(prinr&&estado==3){
                rcont++;
                if(rcont>800){
                    prinr=false;
                    rcont=0;
                }
            }
//            if(nivelsubir.getY()<640-lvlim.getHeight()+50){
//                nivelsubir.setY(nivelsubir.getY()*1*i);
//            }else{
//                nivelsubir.setY(nivelsubir.getY()*-1*i);
//            }
            
            
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Combate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
     public void comprobarInputs(GameContainer gc, StateBasedGame sbg) throws SlickException{
        Input input=gc.getInput();
        
        if(input.isKeyPressed(Input.KEY_C)){
             
             volverMapa1();
             
        }

            if(input.isKeyPressed(Input.KEY_A)){
                if(contar){
                     contar=false;
                }
            }   
          
    }
     
    public void volverMapa(StateBasedGame sbg) throws SlickException{
        sbg.enterState(0, new FadeOutTransition(),  new FadeInTransition());     
        musica.cambiarM(0);
        musica.play();
        Lucia.getLucia().caminar();
         
         
    }
    
    public void enemigosRender(GameContainer c,Graphics g){
        for (int i=0;i<enemigos.size();i++){
            enemigos.get(i).drawC(c, g);   
        }
    }
    
    public void equipoRender(GameContainer c, Graphics g){
        for (int i=0;i<aliados.size();i++){
            aliados.get(i).drawC(c, g);
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
                exp=exp+enemigos.get(i).getExpg();
                b=true;
                leveling=true;
                calcRec(enemigos.get(i));
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
            int ex=EXP/(aliados.size()-i);
            EXP=EXP-ex;
            LVLUP=Lucia.getLucia().getEquipo().get(i).ganarExperiencia(ex);
            LVLUP.add(0, aliados.get(i)+" ha obtenido "+ex+ " Puntos de Experiencia");
            conte=true;
            
            System.out.println(aliados.get(i)+" ha ganado "+ex+" puntos de Experiencia");
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
    
    public boolean comprobarDebilitadosA() throws SlickException{
        boolean b=false;
        ArrayList<Aliado> a=new ArrayList();
        EntidadCombate e;
        for(int i=0;i<aliados.size();i++){
            if(aliados.get(i).getMultiplicadores()[0]==0){
                a.add(aliados.get(i));
                b=true;
            }
        }
        try {
            for(int i=0;i<a.size();i++){
            e=a.get(i);
            for(int j=0;j<turno.size();j++){
                if(turno.get(j).getAtacante().equals(e)){
                    turno.remove(j);
                }
            }
            aliados.remove(e);
            
            
        }
        } catch (Exception ex) {
        }
        
        return b;
    }
    
    private void comprobar1(){
        
        for(int i=aliados.size()-1;i>=0;i--){

            if(aliados.get(i).getMultiplicadores()[0]==0){
                aliados.remove(i);
               
            }
        }
    }
    
    private void calcRec(Enemigo e) throws SlickException{
        Random m;
        m=new Random();
        int r=m.nextInt(101);

        if(e.getPropobj()>r){
            m=new Random();
            r=m.nextInt(2);
            if(r==0){
                if(e.getLVL()<5){
                    objetos.add(new PocionVPequeña());
                }
                if(5<e.getLVL()&&e.getLVL()<15){
                    objetos.add(new PocionVMediana());
                }
                if(15<e.getLVL()){
                    objetos.add(new PocionVGrande());
                }
            }else{
                switch(e.getElemento().toString()){
                    case "Fuego":
                        objetos.add(new GemaFuego1());
                        break;
                    case "Agua":
                        objetos.add(new GemaAgua1());
                        break;
                    case "Rayo":
                        objetos.add(new GemaRayo1());
                        break;
                    case "Planta":
                        objetos.add(new GemaPlanta1());
                        break;
                    case "Tierra":
                        objetos.add(new GemaTierra1());
                        break;
                    case "Oscuro":
                        objetos.add(new GemaOscura1());
                        break;
                    case "Luz":
                        objetos.add(new GemaLuz1());
                        break;
                }
            }
            prinr=true;

            System.out.println(objetos.get(objetos.size()-1));
        }
        
    }
    
    private void renderRecompensas(Graphics g){
        lvlim.draw(nivelsubir.getX(), nivelsubir.getY());
        g.drawString("Recompensas: ", nivelstr.getX(), nivelstr.getY());
        for(int i=0;i<objetos.size();i++){
            g.drawString(objetos.get(i).toString(), nivelstr.getX()+21, nivelstr.getY()+20+20*i);
            objetos.get(i).getImagen().draw(nivelstr.getX(), nivelstr.getY()+20+20*i);
        }
    }
    
    private void repartirR(){
        try {
             for(int i=0;i<objetos.size();i++){
            lucia.getIntven().add(objetos.get(i));
        }
        } catch (Exception e) {
        }
        
    }
    
    
}
