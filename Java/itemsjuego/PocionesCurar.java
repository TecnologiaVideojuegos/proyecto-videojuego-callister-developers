package itemsjuego;

import entidades.Aliado;
import org.newdawn.slick.Image;

public class PocionesCurar extends Pociones {
    protected int salud;
    public PocionesCurar(int salud, Image imagen){
        super("salud", imagen);
        this.salud=salud;
    }
    
    public int getSalud() {
        return salud;
    }
    
    public void setSalud(int salud) {
        this.salud = salud;
    } 
    
    @Override
    public void usar(Aliado a){
        a.cVida(salud);    
    }
    
    @Override
    public String toString() {
        return "PocionesCurar{" + "salud=" + salud + '}';
    }
    
    
}
