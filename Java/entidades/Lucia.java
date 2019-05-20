/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import Combate.MagiaAgua1;
import Combate.*;
import chaoschild.Punto;
import chaoschild.Vector;
import itemsjuego.GemaAgua1;
import itemsjuego.GemaAgua2;
import itemsjuego.*;
import itemsjuego.GemaOscura1;
import itemsjuego.GemaRayo1;
import itemsjuego.Inventario;
import itemsjuego.Objeto;
import itemsjuego.PocionMGrande;
import itemsjuego.PocionMMediana;
import itemsjuego.PocionMPequeña;
import itemsjuego.PocionVGrande;
import itemsjuego.PocionVMediana;
import itemsjuego.PocionVPequeña;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;


/**
 *
 * @author victo
 */
public class Lucia extends Aliado implements Externalizable{
    
    private static Lucia Lucia=null;
    private ArrayList<Objeto> inventario;
    private Inventario intven;
    private ArrayList<Aliado> equipo;
    private int ancho, alto;
    private Sound caminar;
    
    public Lucia(int x, int y) throws SlickException {
        super("resources/Personajes/Lucia/Lucia.png","resources/Pantalla de Batalla/Lucia/Batalla_Lucia.png", 52, 34, 4, 4,new int[] {3,2,2,2}, "Lucia");
        equipo= new ArrayList();
        ancho=34;
        alto=54;
        super.setPosicion(new Punto(x, y));
        super.genHitboxes(new Punto(super.getPosicion().getX()+2, super.getPosicion().getY()+(52-24)), 24, 24);
        int[] frames={3,3,3,3};
        super.animaciones(frames);
        inventario=new ArrayList();
        inventario.add(new PocionVPequeña());
        setPosCombate(new Punto(500, 200));
        equipo.add(this);
        equipo.add(new Kato(getPosCombate().getX()-64, getPosCombate().getY()));
        aprenderMagia(new MagiaAgua1());
        aprenderMagia(new MagiaTierra1());
        aprenderMagia(new MagiaFuego1());
        estadisticasb(new int[]{70, 90, 90, 60, 80, 0, 20,0, 2, 15, 5, 10, 15, 30, 0, 20,18});
        caminar=new Sound("resources/sonido/paso.ogg");
        caminar.loop();
        caminar.stop();
        intven=new Inventario();
        intven.add(new PocionVPequeña());
        intven.add(new PocionMPequeña());
        intven.add(new PocionVMediana());
        intven.add(new PocionMMediana());
        intven.add(new PocionVGrande());
        intven.add(new PocionMGrande());
        intven.add(new GemaAgua1());
        intven.add(new GemaAgua2());
        intven.add(new GemaPlanta1());
        intven.add(new GemaPlanta2());
        intven.add(new GemaFuego1());
        intven.add(new GemaFuego2());
        intven.add(new GemaRayo1());
        intven.add(new GemaRayo2());
        intven.add(new GemaTierra1());
        intven.add(new GemaTierra2());
        intven.add(new GemaLuz1());
        intven.add(new GemaLuz2());
        intven.add(new GemaOscura1());
        intven.add(new GemaOscura2());
        
        setAnimdañar(3);
        setArma(new GemaAgua1());
        setArmor(new GemaRayo1());
        equipo.add(new Antonio(500, 80));
        equipo.add(new Paula(500, 320));
        aprenderMagia(new MagiaRayo1());
        aprenderMagia(new MagiaOscura1());
        aprenderMagia(new MagiaLuz1());
        aprenderMagia(new MagiaPlanta1());
        
    }
       

    public static Lucia getLucia() throws SlickException{
        if(Lucia==null){
            Lucia=new Lucia(640, 640);
        }
        return Lucia;
    }

    public Lucia() {
        super();
    }

    
   

    

    
    
    
    public void actualizar(Input entrada, boolean[] pasar){
        
        
        if(getVelocidad().getX()!=0||getVelocidad().getY()!=0){
            if(!caminar.playing()){
                caminar.play(1, (float) 0.09);
            }
        }else caminar.stop();
        
        
        if(entrada.isKeyDown(Input.KEY_W)){
            
            if(pasar[0]){
                super.movAr(150);
            }else{
                super.setVelocidad(new Vector(new Punto(super.getVelocidad().getX(), 0)));
            }

        }
        if(entrada.isKeyDown(Input.KEY_A)){
            
            if(pasar[3]){
                super.movI(150);
            }else{
                super.setVelocidad(new Vector(new Punto(0,super.getVelocidad().getY())));
            }

        }
        if(entrada.isKeyDown(Input.KEY_D)){
            if(pasar[1]){
                super.movD(150);
            }else{
                super.setVelocidad(new Vector(new Punto(0,super.getVelocidad().getY())));
            }

        }
        if(entrada.isKeyDown(Input.KEY_S)){
            if(pasar[2]){
                super.movAb(150);
            }else{
                super.setVelocidad(new Vector(new Punto(super.getVelocidad().getX(),0)));
            }

        }
        if(!entrada.isKeyDown(Input.KEY_S)&&!entrada.isKeyDown(Input.KEY_W)){
            super.setVelocidad(new Vector(new Punto(super.getVelocidad().getX(), 0)));
        }
        if(!entrada.isKeyDown(Input.KEY_A)&&!entrada.isKeyDown(Input.KEY_D)){
            super.setVelocidad(new Vector(new Punto(0,super.getVelocidad().getY())));
        }
        
    }
    
    @Override
    public Punto getPosicion(){
        return super.getPosicion();
    }
    
    public ArrayList<Aliado> getEquipo(){
        return equipo;
    }
    
    public ArrayList<String> getEquipoStrng(){
        ArrayList<String> a=new ArrayList();
        for(int i=0;i<equipo.size();i++){
            a.add(equipo.get(i).toString());   
        }
        return a;
    }
    
    
    public ArrayList<String> getInvStrg(){
        ArrayList<String> a=new ArrayList();
        for(int i=0;i<inventario.size();i++){
            
            a.add(inventario.get(i).toString());
            
        }
        return a;
    }

    public Inventario getIntven() {
        return intven;
    }
    
    @Override
    public int ataqueBasico(){
        int dmg=super.ataqueBasico();
        dmg=(int) ((dmg+getEst()[4]*0.8));
        return dmg;
    }

    @Override
    public void writeExternal(ObjectOutput oo) throws IOException {
        super.writeExternal(oo);
        int a=equipo.size();
        oo.writeInt(a);
        System.out.println("Equipo Size "+equipo.size());
        for(int i=1;i<equipo.size();i++){
            oo.writeUTF(equipo.get(i).getNombre());
            equipo.get(i).writeExternal(oo);
        }
        intven.writeExternal(oo);
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
        super.readExternal(oi);
        equipo=new ArrayList();
        equipo.add(this);
        int a=oi.readInt();
        for (int i=1;i<a;i++){
            String n=oi.readUTF();
            switch(n){
                case "Kato":
                    equipo.add(new Kato());
                    break;
                case "Paula":
                    equipo.add(new Paula());
                    break;
                case "Antonio":
                    equipo.add(new Antonio());
                    break;
            }
            equipo.get(i).readExternal(oi);
        }
        System.out.println("------------------------Cargar Inventario---------------------------------------");
        intven=new Inventario();
        intven.readExternal(oi);
        System.out.println("------------------------Inventario Cargado--------------------------------------");
        try {
            caminar=new Sound("resources/sonido/paso.ogg");
        } catch (SlickException ex) {
            Logger.getLogger(Lucia.class.getName()).log(Level.SEVERE, null, ex);
        }
        caminar.loop();
        caminar.stop();
        super.genHitboxes(new Punto(super.getPosicion().getX()+2, super.getPosicion().getY()+(52-24)), 24, 24);
        System.out.println("-------------------------------"+toString()+" Completamente Cargado-----------------------------------------------------");
        Lucia=this;
    }
    
}
