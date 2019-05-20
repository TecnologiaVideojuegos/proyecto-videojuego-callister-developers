package itemsjuego;

import entidades.Aliado;
import entidades.EntidadCombate;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PocionesCurar extends Pociones implements Externalizable{
    protected int salud;

    public PocionesCurar(int salud, Image imagen) throws SlickException{
        super("salud", imagen, "resources/Magia/Curar.png");
        this.salud=salud;
        setClase("PocionesCurar");
    }

    public PocionesCurar(){
        super();
    }
    
    
    
    public int getSalud() {
        return salud;
    }
    
    public void setSalud(int salud) {
        this.salud = salud;
    } 
    
    @Override
    public void usar(EntidadCombate a){
        System.out.println("eeeeeee");
        a.curarV(salud);   
    }
    
    
    @Override
    public String toString() {
        return "PocionesCurar{" + "salud=" + salud + '}';
    }
    
    @Override
    public void writeExternal(ObjectOutput oo) throws IOException {
        super.writeExternal(oo);
        oo.writeInt(salud);
    }
    
     @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
        super.readExternal(oi);
        salud=oi.readInt();
        
    }
}
