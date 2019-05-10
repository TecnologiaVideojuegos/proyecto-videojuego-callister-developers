package itemsjuego;

import entidades.Aliado;
import entidades.EntidadCombate;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PocionesCurar extends Pociones {
    protected int salud;
    public PocionesCurar(int salud, Image imagen) throws SlickException{
        super("salud", imagen, "resources/Magia/Curar.png");
        this.salud=salud;
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
    
    
}
