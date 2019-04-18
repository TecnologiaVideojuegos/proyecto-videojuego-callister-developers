/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import chaoschild.Punto;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class EntidadCombate extends Entidad{
    private Entidad combate;
    private boolean lucha;
    
    public EntidadCombate(String ruta,String rutaC, int h, int w, int numAnimaciones, int numC, int[] frames) throws SlickException {
        super(ruta, h, w, numAnimaciones);
        combate=new Entidad(rutaC, 64, 64, numC);
        combate.animaciones(frames, 250);
        lucha=false;
        setAnimacionCombate(0);
        
    }
    
    public void draw(){
        if (lucha) combate.drawCombate();
        else super.draw();
    }

    public void setLucha(boolean lucha) {
        this.lucha = lucha;
    }
    
    public void setPosCombate(Punto p){
        combate.setPosicion(p);
    }
    
    public Punto getPosCombate(){
        return combate.getPosicion();
    }
    
    public void combatir(){
        lucha=true;
    }
    public void caminar(){
        lucha=false;
    }
    
    public void setAnimacionCombate(int i){
        combate.setAnimacion(0);
    }
    
    
}
