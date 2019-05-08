package itemsjuego;

import entidades.Aliado;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Pociones extends Objeto {
    
    protected String tipo;
    
    
    public Pociones(String tipo, Image imagen, String ruta) throws SlickException{
        super(imagen,"Pocion", ruta, "resources/sonido/combate/healing.ogg");
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
