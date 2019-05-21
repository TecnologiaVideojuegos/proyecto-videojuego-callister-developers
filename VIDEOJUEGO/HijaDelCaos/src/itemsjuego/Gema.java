/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemsjuego;

import Combate.Agua;
import Combate.Elemento;
import Combate.Fuego;
import Combate.Luz;
import Combate.Oscuro;
import Combate.Planta;
import Combate.Rayo;
import Combate.Terra;
import chaoschild.Punto;
import entidades.Aliado;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class Gema extends Objeto implements Externalizable{
    private Elemento elemento;
    private float mult;
    private int nivel;

    public Gema(Image imagen, String n, Elemento e, float mult) {
        super(imagen, n);
        elemento=e;
        this.mult=mult;
        setGem(true);
        setClase("Gema");
    }

    public Gema() {
          }

    public Elemento getElemento() {
        return elemento;
    }

    public float getMult() {
        return mult;
    }
    
    @Override
    public void usar(int i, Aliado a) throws SlickException{
        switch(i){
            case 0:
                a.setArma(this);
                break;
            case 1:
                a.setArmor(this);
                break;
        }
    }
    
    
    @Override
    public void renderImagen(Punto p, int i){
       getImagen().draw(p.getX()+6, p.getY()+i*31+6);  
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    
    
    private void seleccionarElemento(String a) throws SlickException{
        switch (a){
            case "Planta":elemento=new Planta();
                break;
            case "Agua":elemento=new Agua();
                break;
            case "Fuego":elemento=new Fuego();
                break;
            case "Rayo":elemento=new Rayo();
                break;
            case "Tierra":elemento=new Terra();
                break;
            case "Luz":elemento=new Luz();
                break;
            case "Oscuro":elemento=new Oscuro();
                break;
            
        }
    }

    @Override
    public void writeExternal(ObjectOutput oo) throws IOException {
        super.writeExternal(oo);
        oo.writeInt(nivel);
        oo.writeFloat(mult);
        oo.writeUTF(elemento.toString());
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
        super.readExternal(oi);
        nivel=oi.readInt();
        mult=oi.readFloat();
        String a=oi.readUTF();
        try {
            seleccionarElemento(a);
        } catch (SlickException ex) {
            Logger.getLogger(Gema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
