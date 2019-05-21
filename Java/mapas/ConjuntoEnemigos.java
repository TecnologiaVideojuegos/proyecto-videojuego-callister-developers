package mapas;

import chaoschild.Punto;
import entidades.enemigos.*;
import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class ConjuntoEnemigos {
    private ArrayList<Enemigo> enemigos;
    private Enemigo enemigo;
    
    public ConjuntoEnemigos(ArrayList<Enemigo> enemigos, Punto posicion){
        this.enemigos = enemigos;
        this.enemigo = enemigos.get(0);
    }
    
    public ConjuntoEnemigos(){
    }
    
    public Enemigo getEnemigo(){
        return this.enemigo;
    }
    
    public void delete(ArrayList<Enemigo> ene){
        for(int i = 0;i < ene.size();i++){
            this.enemigos.remove(ene.get(i));
        }
        try{
            this.enemigo = this.enemigos.get(0);
        } catch(Exception e){
            this.enemigo = null;
        }
    }
    
    public void draw(){
        this.enemigo.draw();
    }
    
    public ArrayList<Enemigo> getEnemigos(){
        return enemigos;
    }
}
