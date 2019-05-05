/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import Combate.MagiaAgua1;
import chaoschild.Punto;
import chaoschild.Vector;
import itemsjuego.Inventario;
import itemsjuego.Objeto;
import itemsjuego.PocionVPequeña;
import java.util.ArrayList;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author victo
 */
public class Lucia extends Aliado{
    
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
        estadisticasb(new int[]{80, 60, 80, 0, 0, 0, 0, 0, 0, 20, 5, 10, 10, 20, 0, 0, 20, 18});
        caminar=new Sound("resources/sonido/paso.ogg");
        caminar.loop();
        caminar.stop();
        intven=new Inventario();
        intven.add(new PocionVPequeña());
        
    }
       

    public static Lucia getLucia() throws SlickException{
        if(Lucia==null){
            Lucia=new Lucia(640, 640);
        }
        return Lucia;
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
    
    
    
}
