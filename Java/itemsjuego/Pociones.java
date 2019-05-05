package itemsjuego;

import entidades.Aliado;
import org.newdawn.slick.Image;

public abstract class Pociones extends Objeto {
    
    protected String tipo;
    
    
    public Pociones(String tipo, Image imagen){
        super(imagen,"Pocion");
        this.tipo=tipo;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    } 
    
    public void usar(Aliado a){}
    
}
