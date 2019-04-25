/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapas;

import chaoschild.Punto;
import entidades.Entidad;
import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author victo
 */
public class Mapa extends TiledMap {
    
    private ArrayList<Rectangle> hitBoxes;
    private ArrayList<Entidad> entidades;
    private ArrayList<Edificio> edificios;
    private int paredesLayer;

    
    public Mapa(String ref, String tileSetsLocation) throws SlickException {
        super(ref, tileSetsLocation);
        paredesLayer = this.getLayerIndex("ObjColisionables");
        hitBoxes = new ArrayList();
        entidades = new ArrayList();
        edificios = new ArrayList();
        genParedes();
    }

    public Mapa(String ref) throws SlickException {
        super(ref);
        paredesLayer = this.getLayerIndex("ObjColisionables");
        hitBoxes = new ArrayList();
        entidades = new ArrayList();
        edificios = new ArrayList();
        genParedes();
    }
    
    private void genParedes()throws SlickException{
        int ax  = this.width;
        int ay = this.height;
        int[] a;
        a = new int[2];
        
        //System.out.println(paredesLayer);
        for (int i = 0;i < ay;i++){
            for (int j = 0;j < ax;j++){
                   if(this.getTileId(j, i, paredesLayer) !=0 ){
                       a[0] = j;
                       a[1] = i;
                       hitBoxes.add(new Rectangle(j * 32,i * 32, 32, 32));
                   }   
            }
        }
    }

    public ArrayList<Entidad> getEntidades(){
        return entidades;
    }
    
    public ArrayList<Rectangle> getHitBoxes() {
        return hitBoxes;
    }
    
    public ArrayList<Edificio> getEdificios(){
        return edificios;
    }
    
    public void render(){
        this.render(0, 0);
    }
}
