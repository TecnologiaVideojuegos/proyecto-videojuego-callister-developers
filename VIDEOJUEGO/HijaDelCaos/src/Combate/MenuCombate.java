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
import java.util.ArrayList;
import org.newdawn.slick.Animation;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author victo
 */
public class MenuCombate {
    private Image menu, puntero;
    private Punto p, pmenu,paux, psel;
    private int estadomenu;
    private int indicepuntero;
    private String[] menusp;
    private ArrayList<String> menuss;
    private int escondidos;
    private int nR;
    private ArrayList<Sound> sonidos;
    private ArrayList<Enemigo> enemigos;
    private ArrayList<Aliado> aliados;
    private Animation selector;
    private int selmenu;
    private Graphics g;
    private int menumodo;
    private Combate combat;
    private int action, indice;
    private EntidadCombate atacante, defensor;
    private ArrayList<Accion> turnos;
    private int acciones;
    private Aliado selected;
    private IACombate ia;
    private Sound cant;
            


    public MenuCombate(ArrayList<Enemigo> enemigos, Combate combat, ArrayList<Aliado> aliados) throws SlickException {
        this.menu = new Image("resources/Menus/Combate.png");
        puntero=new Image("resources/Menus/PunteroMenu.png");
        p= new Punto(0, 640-200);
        pmenu= new Punto(p.getX()+10, p.getY()+10);
        paux = new Punto(pmenu.getX(), pmenu.getY());
        estadomenu=0;
        menusp=new String[]{"Atacar","Magia","Objeto", "Huir"};
        menuss=new ArrayList();
        aa();
        nR=9;
        sonidos=new ArrayList();
        sonidos.add(new Sound("resources/sonido/combate/menu_selec.ogg"));
        sonidos.add(new Sound("resources/sonido/combate/menu_confir.ogg"));
        sonidos.add(new Sound("resources/sonido/combate/menu_cance.ogg"));
        this.enemigos=enemigos;
        SpriteSheet sprite=new SpriteSheet("resources/Menus/Selector.png", 64, 64);
        selector=new Animation();
        selector.addFrame(sprite.getSprite(0, 0), 300);
        selector.addFrame(sprite.getSprite(0, 1), 300);
        psel=renderSelector(Lucia.getLucia());
        selmenu=0;
        menumodo=0;
        this.combat=combat;
        turnos=new ArrayList();
        acciones=0;
        try {
            selected=aliados.get(0);
        } catch (Exception e) {
        }
        
        ia=new IACombate(enemigos);
        cant=new Sound("resources/sonido/combate/menu_cant.ogg");
        this.aliados=aliados;
        
    }
    
    public void render(Graphics g){
        this.g=g;
        menu.draw(p.getX(), p.getY());
        puntero.draw(pmenu.getX(), pmenu.getY());
        rendMenus(menuss);
        selector.draw(psel.getX(), psel.getY(),128,128);

       
    }
    
    public void update(Input entrada) throws SlickException, InterruptedException{
        
        if (entrada.isKeyPressed(Input.KEY_S)){

            switch(selmenu){
                case 0:
                    
                    if(indicepuntero!=menuss.size()-1){
                        if(indicepuntero!=escondidos+nR-1){
                            pmenu.setY(pmenu.getY()+21);
                            indicepuntero++;
                        }else {
                            indicepuntero++;
                            escondidos++;
                        }

                    }else{
                        pmenu.setY(pmenu.getY()-21*(nR-1));
                        indicepuntero=0;
                        escondidos=0;
                    }
                    break;
                case 1:
                    
                    if(indicepuntero!=enemigos.size()-1){
                        indicepuntero++;
                        
                    }else{
                        indicepuntero=0;
                    }
                    psel=renderSelector(enemigos.get(indicepuntero));
                                   
                    break;
                case 2:
                   
                    if(indicepuntero!= aliados.size()-1){
                        indicepuntero++;
                        
                    }else{
                        indicepuntero=0;
                    }
                    psel=renderSelector(aliados.get(indicepuntero));
                                   
                    break;
                
            }
            sonidos.get(0).play(1, (float) 0.2);
        } 
        if (entrada.isKeyPressed(Input.KEY_W)){ 
            switch(selmenu){
                case 0:
                    if(indicepuntero!=0){
                       if(indicepuntero!=escondidos){
                            pmenu.setY(pmenu.getY()-21);
                            indicepuntero--;
                       }else{
                           indicepuntero--;
                           escondidos--;
                       }
                    }else{
                        pmenu.setY(pmenu.getY()+21*(nR-1));
                        indicepuntero=menuss.size()-1;
                        escondidos=menuss.size()-nR;
                    }
                    break;
                case 1: 
                    if(indicepuntero!=0){
                        indicepuntero--;
                        
                        
                    }else{
                        indicepuntero=enemigos.size()-1;
                    }
                    psel=renderSelector(enemigos.get(indicepuntero));
                                   
                    
                    break;
                case 2:
                    if(indicepuntero!=0){
                        indicepuntero--;
                        
                        
                    }else{
                        indicepuntero=aliados.size()-1;
                    }
                    psel=renderSelector(aliados.get(indicepuntero));
                                   
                    
                    break;
                
            }
            sonidos.get(0).play(1, (float) 0.2);
        }
         if (entrada.isKeyPressed(Input.KEY_SPACE)){
             //selmenu: 0 menus texto, 1 seleccion enemigos, 2 seleccion aliados
//------------------------------GESTION DE SONIDO------------------------------------------------------------------------------------------
             if(indicepuntero!=menuss.size()-1){
                if(selmenu==0&&menumodo==2){
                     if(Lucia.getLucia().getIntven().get(indicepuntero).isGem()){
                         cant.play(1, (float) 0.2);
                     }else sonidos.get(1).play(1, (float) 0.2);
                }else sonidos.get(1).play(1, (float) 0.2); 
             }else{
               
                sonidos.get(2).play(1, (float) 0.2);
             }
//--------------------------------------------------------------------------------------------------------------------                
                if(selmenu==0){
                    selecionMenu(menumodo, indicepuntero);
                    
                }
                else{ 
                    if(selmenu==1){
                        turnos.add(new Accion(action, indice, selected, enemigos.get(indicepuntero)));
                        indice=0;
                        selmenu=0;
                        menumodo=0;
                        indicepuntero=0;
                        escondidos=0;
                        pmenu= new Punto(p.getX()+10, p.getY()+10);
                        aa();
                        acciones++;
                        try{
                            selected=aliados.get(acciones);
                        }catch(Exception e){
                            
                        }
                       
                        psel=renderSelector(selected);
                        
                        if(acciones==aliados.size()){
                            combat.setTurno(turnos);
                            ia.setEnemigos(enemigos);
                            ia.setOmjetivos(aliados);
                            combat.añadirTurnos(ia.getAcciones());
                            combat.ordenarTurnos(); 
                            combat.cambiarEstado(1);
                        }

                    }
                    else if(selmenu==2){
                        if(action==2){
                            turnos.add(new Accion(action, Lucia.getLucia().getIntven().remove(indice), selected, aliados.get(indicepuntero)));
                        }else turnos.add(new Accion(action, indice, selected, aliados.get(indicepuntero)));
                        indice=0;
                        selmenu=0;
                        menumodo=0;
                        indicepuntero=0;
                        escondidos=0;
                        pmenu= new Punto(p.getX()+10, p.getY()+10);
                        aa();
                        acciones++;
                        try{
                             selected=aliados.get(acciones);
                        }catch(Exception e){
                            
                        }
                       
                        psel=renderSelector(selected);
                        
                        if(acciones==aliados.size()){
                            combat.setTurno(turnos);
                            ia.setEnemigos(enemigos);
                            combat.añadirTurnos(ia.getAcciones());
                            combat.ordenarTurnos();
                            combat.cambiarEstado(1);
                        }

                    }
                }
            }
            if (entrada.isKeyPressed(Input.KEY_B)){
                sonidos.get(0).stop();
                if(selmenu!=0){
                    switch(action){
                        case(0):
                            pmenu= new Punto(p.getX()+10, p.getY()+10);
                            indicepuntero=0;
                            escondidos=0;
                            selmenu=0;
                            aa();
                            menumodo=0;
                            sonidos.get(2).play(1, (float) 0.2);
                            break;
                        case(1):
                            pmenu= new Punto(p.getX()+10, p.getY()+10);
                            indicepuntero=0;
                            escondidos=0;
                            selmenu=0;
                            rendMenus(selected.getMagiasStrg());
                            menumodo=1;
                            
                            sonidos.get(2).play(1, (float) 0.2);
                            break;
                        case(2):
                            pmenu= new Punto(p.getX()+10, p.getY()+10);
                            indicepuntero=0;
                            escondidos=0;
                            selmenu=0;
                            rendMenus(Lucia.getLucia().getMagiasStrg());;
                            menumodo=2;
                            
                            sonidos.get(2).play(1, (float) 0.2);
                            break;
                        
                    }
                
                psel=renderSelector(selected);
            }else{
                if(menumodo!=0){
                    pmenu= new Punto(p.getX()+10, p.getY()+10);
                    indicepuntero=0;
                    escondidos=0;
                    selmenu=0;
                    aa();
                    menumodo=0;
                    
                    sonidos.get(2).play(1, (float) 0.2);
                }
            }
        
        } 
    }

    public void rendMenus(ArrayList<String> menu){
        int aux=0;
        
        if(menu.size()<9){
            nR=menu.size();
        }else nR=9;
        
        switch (menumodo){
            case 1:
                aux=20;
                for(int i=0;i<nR-1;i++){
                    selected.getMagias().get(i+escondidos).getElemento().getTipo().draw(paux.getX()+20, paux.getY()+i*21);


                }
                break;
        }
        
        for(int i=0;i<nR;i++){
            g.drawString(menu.get(i+escondidos), paux.getX()+20+aux, paux.getY()+i*21);

        }
        
    }
    
    private void aa(){
        ArrayList<String> a=new ArrayList();
        for (int i=0;i<menusp.length;i++){
           a.add(menusp[i]);
        }
        menuss=a;
    }
    
    private Punto renderSelector(EntidadCombate e){
        Punto p=new Punto(e.getPosCombate().getX()-32, e.getPosCombate().getY()-64-64);
        return p;
    }
       
    private void cambiarEstadoMenu(int a) throws SlickException{
        
        
        switch (a){
            case 0:
                selmenu=1;
                try {
                   psel=renderSelector(enemigos.get(0));   
                } catch (Exception e) {
                }
                
                break;
            case 1:
                menumodo=1;
                pmenu= new Punto(p.getX()+10, p.getY()+10);
                indicepuntero=0;
                escondidos=0;
                cambiaMenu(selected.getMagiasStrg());
                break;
            case 2:
                menumodo=2;
                pmenu= new Punto(p.getX()+10, p.getY()+10);
                indicepuntero=0;
                escondidos=0;
                cambiaMenu(Lucia.getLucia().getIntven().getInvString());
                break;
            case 3:
                combat.volverMapa1();
                break;
        }

        
            

        }
    
    private void selecionMenu(int modo, int sel) throws SlickException{
        switch(modo){
            case 0: 
                cambiarEstadoMenu(sel);
                indicepuntero=0;
                action=0;
                break;
            case 1:
                if(sel==menuss.size()-1){
                    pmenu= new Punto(p.getX()+10, p.getY()+10);
                    indicepuntero=0;
                    escondidos=0;
                    selmenu=0;
                    aa();
                    menumodo=0;
                    
                }else{
                    selmenu=1;
                    indice=indicepuntero;   
                    indicepuntero=0;
                    
                    psel=renderSelector(enemigos.get(0)); 
                    action=1;
                }
                    //selmenu=1;
                break;
            case 2:
                if(sel==menuss.size()-1){
                    pmenu= new Punto(p.getX()+10, p.getY()+10);
                    indicepuntero=0;
                    escondidos=0;
                    selmenu=0;
                    aa();
                    menumodo=0;
                    
                }else{
                    if(!Lucia.getLucia().getIntven().get(indicepuntero).isGem()){
                        selmenu=2;
                        psel=renderSelector(aliados.get(0));
                        action=2;
                        indice=indicepuntero;
                        indicepuntero=0;
                    }else cant.play(1, (float) 0.5);
                }
                    //selmenu=1;
                break;
            case 5:
               // selmenu=0;
                
                break;
                
        }
        
        
        }
    
    public void cambiaMenu(ArrayList<String> a){
            a.add("Atrás");
            menuss=a;
            
        }

    public void setAcciones(int acciones) throws SlickException {
        try {
            this.acciones = acciones;
            selected=aliados.get(acciones);
            psel=renderSelector(selected);
        } catch (Exception e) {
        } 
    }

    public void setEnemigos(ArrayList<Enemigo> enemigos) {
        this.enemigos = enemigos;
    }
            
            
    }

