package itemsjuego;

import entidades.Aliado;
import entidades.EntidadCombate;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PocionesMana extends Pociones implements Externalizable
{
    protected int mana;
    public PocionesMana(int mana, Image imagen) throws SlickException{
        super("mana", imagen, "resources/Magia/CurarM.png");
        this.mana=mana;
        setClase("PocionesMana");
    }

    public PocionesMana() {
    }
    
    

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    
     
    
    @Override
    public void usar(EntidadCombate a){
        System.out.println("reee");
        a.curarM(mana);
    }
    
    @Override
    public void writeExternal(ObjectOutput oo) throws IOException {
        super.writeExternal(oo);
        oo.writeInt(mana);
    }
    
     @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
        super.readExternal(oi);
        mana=oi.readInt();
    }
}
