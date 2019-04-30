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
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class EntidadCombate extends Entidad{
    private Entidad combate;
    private boolean lucha;
    ArrayList<Magia> magias;
    private int HP, MP, STR , INT, AGI, HIT, DEF, lvl;//vALOR
    private int AHP, AMP, MSTR , MINT, MAGI, MHIT, MDEF;//mULTIPLICADOR
    private int BHP, BMP, BSTR , BINT, BAGI, BHIT, BDEF;//bASE
    private int nivel, EXPN, EXPA;
    
    
    
    public EntidadCombate(String ruta,String rutaC, int h, int w, int numAnimaciones, int numC, int[] frames, String nombre) throws SlickException {
        super(ruta, h, w, numAnimaciones, nombre);
        combate=new Entidad(rutaC, 64, 64, numC, nombre+" de Combate");
        combate.animaciones(frames, 250, genLimited(numC));
        lucha=false;
        setAnimacionCombate(0);
        magias=new ArrayList();
        
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

    public int getHP() {
        return HP;
    }

    public int getMP() {
        return MP;
    }

    public int getSTR() {
        return STR;
    }

    public int getINT() {
        return INT;
    }

    public int getAGI() {
        return AGI;
    }

    public int getHIT() {
        return HIT;
    }

    public int getAHP() {
        return AHP;
    }

    public int getAMP() {
        return AMP;
    }

    public int getMSTR() {
        return MSTR;
    }

    public int getMAGI() {
        return MAGI;
    }

    public int getMHIT() {
        return MHIT;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public void setAHP(int AHP) {
        this.AHP = AHP;
    }

    public void setAMP(int AMP) {
        this.AMP = AMP;
    }

    public void setMSTR(int MSTR) {
        this.MSTR = MSTR;
    }

    public void setMINT(int MINT) {
        this.MINT = MINT;
    }

    public void setMAGI(int MAGI) {
        this.MAGI = MAGI;
    }

    public void setMHIT(int MHIT) {
        this.MHIT = MHIT;
    }
    

    public void recivirDañoFisico(int dmg, Elemento elem){
        AHP=(int)((double)dmg+0.7*(double)DEF);
    }
    
    public int ataqueBasico(){
        int dmg;
        int DMG=(int) (0.9*HIT+(0.9*lvl*10*0.3));
        dmg=(int) (0.5*STR+DMG);
        combate.setAnimacion(1);
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
    
    
    
}
