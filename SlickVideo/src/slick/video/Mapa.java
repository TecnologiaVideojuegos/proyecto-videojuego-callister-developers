/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slick.video;

import java.util.ArrayList;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author victo
 */
public class Mapa extends TiledMap {
    
    private ArrayList<int[]> paredes;
    private ArrayList<Rectangle> hitBoxes;
    private int paredesLayer;
    private int zoomx;
    private int zoomy;

    
    public Mapa(String ref, String tileSetsLocation) throws SlickException {
        super(ref, tileSetsLocation);
        paredesLayer = this.getLayerIndex("Objetos");
        paredes = new ArrayList();
        hitBoxes=new ArrayList();
        System.out.println("Entra en constructor de Mapa");
        genParedes();
        zoomx=0;
        zoomy=0;
    }

    public Mapa(String ref) throws SlickException {
        super(ref);
        paredesLayer = this.getLayerIndex("Objetos");
    }
    
    public void genParedes()throws SlickException{
        int ax=this.width;
        int ay=this.height;
        int[] a;
        a=new int[2];
        
        System.out.println(paredesLayer);
        for (int i=0;i<ay;i++){
            for (int j=0;j<ax;j++){
                   if(this.getTileId(j, i, paredesLayer)!=0){
                       a[0]=j;
                       a[1]=i;
                       hitBoxes.add(new Rectangle(j*32, i*32, 32, 32));
                       int[] coord={j,i};
                       paredes.add(coord);
                       
                       System.out.println("Pared"+ " detectadada en "+paredes.get(paredes.size()-1)[0] + " "+paredes.get(paredes.size()-1)[1]);
                   }//else System.out.println("Pared no detectada en "+j + " "+i);   
               
            }
        }
    }
    
    public void genParedes(int x, int y)throws SlickException{
        hitBoxes.clear();
        paredes.clear();
        
        int ax=this.width;
        int ay=this.height;
        int[] a;
        a=new int[2];
        
        System.out.println(paredesLayer);
        for (int i=0;i<ay;i++){
            for (int j=0;j<ax;j++){
                   if(this.getTileId(j, i, paredesLayer)!=0){
                       a[0]=j;
                       a[1]=i;
                       hitBoxes.add(new Rectangle(j*32+x, i*32+y, 32, 32));
                       int[] coord={j+x/32,i+y/32};
                       paredes.add(coord);
                       
                       System.out.println("Pared"+ " detectadada en "+paredes.get(paredes.size()-1)[0] + " "+paredes.get(paredes.size()-1)[1]);
                   }//else System.out.println("Pared no detectada en "+j + " "+i);   
               
            }
        }
    }
    

    public ArrayList<int[]> getParedes() {
        return paredes;
    }

    public ArrayList<Rectangle> getHitBoxes() {
        return hitBoxes;
    }
    
    public void moverParedes(int x, int y){
        if(x==zoomx && y==zoomy){
            x=0;
            y=0;
        }else{
            x=x+zoomx;
            y=y+zoomy;
            zoomx=x;
            zoomy=y;
        }
            for (int i = 0;i<hitBoxes.size()-1;i++){
                hitBoxes.get(i).setLocation(hitBoxes.get(i).getLocation().x+x, hitBoxes.get(i).getLocation().y+y);
            }
        
    
    }
}
