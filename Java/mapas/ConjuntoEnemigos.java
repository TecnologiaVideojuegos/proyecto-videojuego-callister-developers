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
    private Punto posicion;
    private Mapa mapa;
    
    public ConjuntoEnemigos(ArrayList<Enemigo> enemigos, Punto posicion, Mapa mapa){
        this.enemigos = new ArrayList();
        this.enemigo = enemigos.get(0);
        this.mapa = mapa;
    }
    
    public void draw(){
        this.enemigo.draw();
    }
    
    public ArrayList<Enemigo> getEnemigos(){
        return enemigos;
    }
}
