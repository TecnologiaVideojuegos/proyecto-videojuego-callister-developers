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
            


    public MenuCombate(ArrayList<Enemigo> enemigos, Combate combat) throws SlickException {
        this.menu = new Image("resources/Menus/Combate.png");
        puntero=new Image("resources/Menus/PunteroMenu.png");
        p= new Punto(0, 640-200);
        pmenu= new Punto(p.getX()+450, p.getY()+10);
        paux = new Punto(pmenu.getX(), pmenu.getY());
        estadomenu=0;
        menusp=new String[]{"Atacar","Magia","Objeto", "Huir"};
        menuss=new ArrayList();
        aa();
        nR=9;
        sonidos=new ArrayList();
        sonidos.add(new Sound("resources/sonido/combate/menu_selec.ogg"));
        sonidos.add(new Sound("resources/sonido/combate/menu_confir.ogg"));
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
        selected=Lucia.getLucia().getEquipo().get(0);
        
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
            System.out.println(indicepuntero);
            switch(selmenu){
                case 0:
                    System.out.println("case 0");
                    if(indicepuntero!=menuss.size()-1){
                        if(indicepuntero!=escondidos+nR-1){
                            pmenu.setY(pmenu.getY()+20);
                            indicepuntero++;
                        }else {
                            indicepuntero++;
                            escondidos++;
                        }

                    }else{
                        pmenu.setY(pmenu.getY()-20*(nR-1));
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
                   
                    if(indicepuntero!= Lucia.getLucia().getEquipo().size()-1){
                        indicepuntero++;
                        
                    }else{
                        indicepuntero=0;
                    }
                    psel=renderSelector(selected);
                                   
                    break;
                
            }
            sonidos.get(0).play(1, (float) 0.2);
        } 
        if (entrada.isKeyPressed(Input.KEY_W)){ 
            switch(selmenu){
                case 0:
                    if(indicepuntero!=0){
                       if(indicepuntero!=escondidos){
                            pmenu.setY(pmenu.getY()-20);
                            indicepuntero--;
                       }else{
                           indicepuntero--;
                           escondidos--;
                       }
                    }else{
                        pmenu.setY(pmenu.getY()+20*(nR-1));
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
                case 3:
                    if(indicepuntero!=0){
                        indicepuntero--;
                        
                        
                    }else{
                        indicepuntero=Lucia.getLucia().getEquipo().size()-1;
                    }
                    psel=renderSelector(Lucia.getLucia().getEquipo().get(indicepuntero));
                                   
                    
                    break;
                
            }
            sonidos.get(0).play(1, (float) 0.2);
        }
         if (entrada.isKeyPressed(Input.KEY_SPACE)){
                sonidos.get(1).play(1, (float) 0.2);
                if(selmenu==0){
                    selecionMenu(menumodo, indicepuntero);
                    
                }
                else{ 
                    if(selmenu==1||selmenu==2){
                        turnos.add(new Accion(action, indice, selected, enemigos.get(indicepuntero)));
                        selmenu=0;
                        menumodo=0;
                        indicepuntero=0;
                        escondidos=0;
                        pmenu= new Punto(p.getX()+450, p.getY()+10);
                        aa();
                        acciones++;
                        try{
                             selected=Lucia.getLucia().getEquipo().get(acciones);
                        }catch(Exception e){
                            
                        }
                       
                        psel=renderSelector(selected);
                        
                        if(acciones==Lucia.getLucia().getEquipo().size()){
                            combat.setTurno(turnos);
                            combat.cambiarEstado(1);
                        }

                    }
                }
            }
        
        } 
  

        
    
    public void rendMenus(ArrayList<String> menu){
        
        if(menu.size()<9){
            nR=menu.size();
        }
        
        for(int i=0;i<nR;i++){
            g.drawString(menu.get(i+escondidos), paux.getX()+20, paux.getY()+i*20);

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
                psel=renderSelector(enemigos.get(0));  
                System.out.println("Modo 0");
                break;
            case 1:
                System.out.println("Modo 1");
                menumodo=1;
                pmenu= new Punto(p.getX()+450, p.getY()+10);
                indicepuntero=0;
                escondidos=0;
                cambiaMenu(Lucia.getLucia().getMagiasStrg());
                break;
            case 2:
                menumodo=2;
                pmenu= new Punto(p.getX()+450, p.getY()+10);
                indicepuntero=0;
                escondidos=0;
                cambiaMenu(Lucia.getLucia().getInvStrg());
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
                    pmenu= new Punto(p.getX()+450, p.getY()+10);
                    indicepuntero=0;
                    escondidos=0;
                    selmenu=0;
                    aa();
                    menumodo=0;
                }else{
                    selmenu=1;
                    psel=renderSelector(enemigos.get(0)); 
                    action=1;
                }
                    //selmenu=1;
                break;
            case 2:
                if(sel==menuss.size()-1){
                    pmenu= new Punto(p.getX()+450, p.getY()+10);
                    indicepuntero=0;
                    escondidos=0;
                    selmenu=0;
                    aa();
                    menumodo=0;
                }else{
                    selmenu=2;
                    psel=renderSelector(selected);
                    action=2;
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
            for(int i=0;i<a.size();i++){
                System.out.println(menuss);
            }
            System.out.println("Cambio de Menu");
        }

    public void setAcciones(int acciones) throws SlickException {
        this.acciones = acciones;
        selected=Lucia.getLucia().getEquipo().get(acciones);
        psel=renderSelector(selected);
        
    }
            
            
    }

