package itemsjuego;

import entidades.Aliado;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Pociones extends Objeto implements Externalizable{
    
    protected String tipo;
    
    
    public Pociones(String tipo, Image imagen, String ruta) throws SlickException{
        super(imagen,"Pocion", ruta, "resources/sonido/combate/healing.ogg");
        this.tipo=tipo;
    }

    public Pociones() {
    }
    
    
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    } 
    
    public void usar(Aliado a){}
    
    @Override
    public void writeExternal(ObjectOutput oo) throws IOException {
        super.writeExternal(oo);
        oo.writeUTF(tipo);
    }
    
     @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
        super.readExternal(oi);
        tipo=oi.readUTF();
    }
    
}
