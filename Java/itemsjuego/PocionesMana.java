package itemsjuego;

import entidades.Aliado;
import org.newdawn.slick.Image;

public class PocionesMana extends Pociones {
    protected int mana;
    public PocionesMana(int mana, Image imagen){
        super("mana", imagen);
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
    public void usar(Aliado a){
        a.cMana(mana);
    }
}
