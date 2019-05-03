/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import Combate.Elemento;
import Combate.Magia;
import chaoschild.Punto;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author victo
 */
public class EntidadCombate extends Entidad{
    private Entidad combate;
    private boolean lucha;
    ArrayList<Magia> magias;
    private int EXPN, EXPA, LVL;
    private int[] est;
    private int[] estb;
    private int[] multiplicadores;
    private int[] luest;
    private Sound daño;
    private Sound dañar;
    
    
    
    public EntidadCombate(String ruta,String rutaC, int h, int w, int numAnimaciones, int numC, int[] frames, String nombre) throws SlickException {
        super(ruta, h, w, numAnimaciones, nombre);
        combate=new Entidad(rutaC, 64, 64, numC, nombre+" de Combate");
        combate.animaciones(frames, 250, genLimited(numC));
        lucha=false;
        setAnimacionCombate(0);
        magias=new ArrayList();
        est=new int[8];
        multiplicadores=new int [8];
        estb=new int [8];
        luest=new int[8];
        daño=new Sound("resources/sonido/HitDamage.ogg");
        dañar=new Sound("resources/sonido/combate/fisica_flecha_o_lanzar.ogg");
        
    }
    public EntidadCombate(String ruta,String rutaC, int h, int w, int numAnimaciones, int numC, int[] frames, String nombre, int a) throws SlickException {
        super(ruta, h, w, numAnimaciones, nombre);
        combate=new Entidad(rutaC, 65, 65, numC, nombre+" de Combate");
        combate.animaciones(frames, 250, genLimited(numC));
        lucha=false;
        magias=new ArrayList();
        setAnimacionCombate(0);
        
    }
    
    public void draw(){
        if (lucha){
            if(combate.getAnimacion().isStopped()){
                combate.getAnimacion().restart();
                combate.setAnimacion(0);
                
            }
            combate.drawCombate();
        }
        else super.draw();
    }
    public void draw(Graphics g){
        if (lucha){
            if(combate.getAnimacion().isStopped()){
                combate.getAnimacion().restart();
                combate.setAnimacion(0);
                
                
            }
            g.draw(new Rectangle( combate.getPosicion().getX()+5, combate.getPosicion().getY()+64, 65,20));
            g.drawString(multiplicadores[0]+"/"+est[0], combate.getPosicion().getX()+5, combate.getPosicion().getY()+64);
            
            combate.drawCombate();
        }
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
        combate.setAnimacion(i);
    }
    
    public void recivirDañoFisico(int dmg, Elemento elem){
        multiplicadores[0]=multiplicadores[0]-(int)((double)dmg+0.7*(double)est[3]);
        if(multiplicadores[0]<0){
            multiplicadores[0]=0;
        }
        combate.setAnimacion(2);
        daño.play(1, (float) 0.1);
    }
    
    public int ataqueBasico(){
        int dmg;
        int DMG=(int) (0.9*est[5]+(0.9*LVL*10*0.3));
        dmg=(int) (0.5*est[2]+DMG);
        combate.setAnimacion(1);
        dañar.play(1, (float) 0.1);
        return dmg;
    }
    
    public boolean[] genLimited(int anims){
        boolean[] limited= new boolean[anims];
        limited[0]=true;
        for(int i=1;i<anims;i++){
            limited[i]=false;
        }
        return limited;
    }
    
    public void aprenderMagia(Magia magia){
        magias.add(magia);
    }

    public ArrayList<Magia> getMagias() {
        return magias;
    }
    
    public void estadisticasb(int [] a){
        for(int i=0;i<estb.length;i++){
            estb[i]=a[i];
        }
        LVL=a[8];
        for(int i=9;i<estb.length+9;i++){
            luest[i-9]=a[i];
        }
        calcEst();
    }
    
        public void calcEst(){
            for(int i=0;i<est.length;i++){
                est[i]=estb[i]+luest[i]+LVL;
                multiplicadores[i]=1;
            }
            multiplicadores[0]=est[0];
            multiplicadores[1]=est[1];
        }
    
        public int getAnimId(){
            return combate.getAnimid();
        }

    public int[] getMultiplicadores() {
        return multiplicadores;
    }

    public Sound getDañado() {
        return daño;
    }

    public Sound getDañar() {
        return dañar;
    }

    public void setDañar(Sound dañar) {
        this.dañar = dañar;
    }
        
    public int hacerMagia(int indice, EntidadCombate objetivo){
        setAnimacionCombate(2);
        int da=magias.get(indice).usar(this, objetivo);
        return da;
    }
    
    public boolean drawMag(int a){
        boolean b;
        magias.get(a).draw();
        if(magias.get(a).getAnim().isStopped()){
            b=true;
            
        }else b=false;
        return b;
    }

    public int[] getEst() {
        return est;
    }
    
    public ArrayList<String> getMagiasStrg(){
        ArrayList<String> a=new ArrayList();
        for(int i=0;i<getMagias().size();i++){
            a.add(getMagias().get(i).toString());
            
        }
        return a;
    }
}
