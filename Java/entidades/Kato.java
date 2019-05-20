/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import chaoschild.Punto;
import itemsjuego.Gema;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class Kato extends Aliado implements Externalizable{
    
    
    
    public Kato(float x, float y) throws SlickException {
        super("resources/Personajes/Kato/Kato.png", "resources/Pantalla de Batalla/Kato/Pantalla de batalla de kato1.png", 54, 54, 4, 3, new int[]{4, 4, 3}, "Kato");
        combatir();
        setPosCombate(new Punto(x,y));
        estadisticasb(new int[]{70, 50, 70, 0, 0, 0, 0, 0, 2, 20, 5, 10, 10, 20, 0, 0, 20, 18});
        int[] frames={8,4,4,8};
        super.animaciones(frames);
    }

    public Kato() {
    }
    
    
    
    @Override
    public int ataqueBasico(){
        int dmg=super.ataqueBasico();
        System.out.println("-----------------------------DMG KATO: "+dmg+"---------------------------------");
        dmg=(int) ((dmg+getEst()[2]*0.8));
        return dmg;
    }
    @Override
     public void writeExternal(ObjectOutput out) throws IOException {
         super.writeExternal(out);
         
     }
     
     
    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
        super.readExternal(oi);
       
        
    }
}
