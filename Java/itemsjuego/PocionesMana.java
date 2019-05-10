package itemsjuego;

import entidades.Aliado;
import entidades.EntidadCombate;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PocionesMana extends Pociones {
    protected int mana;
    public PocionesMana(int mana, Image imagen) throws SlickException{
        super("mana", imagen, "resources/Magia/CurarM.png");
        this.mana=mana;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public String toString() {
        return "PocionesMana{" + "mana=" + mana + '}';
    }
     
    
    @Override
    public void usar(EntidadCombate a){
        System.out.println("reee");
        a.curarM(mana);
    }
}
