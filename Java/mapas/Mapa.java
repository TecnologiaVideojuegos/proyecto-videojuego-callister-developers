/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapas;

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
    private ArrayList<Rectangle> puertas;
    private ArrayList<Entidad> entidades;
    private ArrayList<Edificio> edificios;
    private String nombre;
    private int puertasLayer;
    private int paredesLayer;

    
    public Mapa(String ref, String tileSetsLocation) throws SlickException {
        super(ref, tileSetsLocation);
        paredesLayer = this.getLayerIndex("ObjColisionables");
        puertasLayer = this.getLayerIndex("Puerta");
        hitBoxes = new ArrayList();
        entidades = new ArrayList();
        edificios = new ArrayList();
        puertas = new ArrayList();
        genParedes();
        genNombre(ref);
    }

    public Mapa(String ref) throws SlickException {
        super(ref);
        paredesLayer = this.getLayerIndex("ObjColisionables");
        puertasLayer = this.getLayerIndex("Puerta");
        hitBoxes = new ArrayList();
        entidades = new ArrayList();
        edificios = new ArrayList();
        puertas = new ArrayList();
        genParedes();
        genNombre(ref);
    }
    
    private void genNombre(String ref){
        int pos = 0;
        
        for(int i = 0;i < ref.length();i++){
            if(ref.charAt(i) == '/'){
                pos = i;
            }
        }
        this.nombre = ref.substring(pos + 1, ref.length() - 4);
        System.out.println(this.nombre);
    }
    
    private void genParedes()throws SlickException{
        int ax  = this.width;
        int ay = this.height;
        
        boolean paredIzq;
        boolean paredCentro;
        boolean paredDrch;
        boolean paredUp;
        boolean paredDown;
        boolean puertaCentro;
        boolean puertaDrch;
        boolean puertaIzq;
        boolean puertaUp;
        boolean puertaDown;
        
        for (int i = 0;i < ay;i++){
            for (int j = 0;j < ax;j++){
                paredCentro = this.getTileId(j, i, paredesLayer) != 0;
                try{
                    paredIzq = this.getTileId(j - 1, i, paredesLayer) != 0;
                    puertaIzq = this.getTileId(j - 1, i, puertasLayer) != 0;
                } catch(Exception e){
                    paredIzq = false;
                    puertaIzq = false;
                }
                
                try{
                    paredUp = this.getTileId(j, i - 1, paredesLayer) != 0;
                    puertaUp = this.getTileId(j, i - 1, puertasLayer) != 0;
                } catch(Exception e){
                    paredUp = false;
                    puertaUp = false;
                }
                
                try{
                    paredDown = this.getTileId(j, i + 1, paredesLayer) != 0;
                    puertaDown = this.getTileId(j, i + 1, puertasLayer) != 0;
                } catch(Exception e) {
                    paredDown = false;
                    puertaDown = false;
                }
                
                try{
                    paredDrch = this.getTileId(j + 1, i, paredesLayer) != 0;
                    puertaDrch = this.getTileId(j + 1, i, puertasLayer) != 0;
                } catch(Exception e){
                    paredDrch = false;
                    puertaDrch = false;
                }
                
                puertaCentro = this.getTileId(j, i, puertasLayer) != 0;
                
                if(paredCentro){
                    hitBoxes.add(new Rectangle(j * 32,i * 32, 32, 32));
                } else if(puertaCentro){
                    if(paredDrch && paredIzq || paredDrch && puertaIzq || paredIzq && puertaDrch){
                        if(paredDown || puertaDown){
                            puertas.add(new Rectangle(j * 32, i * 32 + 27, 32, 5));
                        } else {
                            puertas.add(new Rectangle(j * 32, i * 32, 32, 5));
                        }
                    } else {
                        if(paredIzq || puertaIzq){
                            puertas.add(new Rectangle(j * 32, i * 32, 5, 32));
                        } else {
                            puertas.add(new Rectangle(j * 32 + 27, i * 32, 5, 32));
                        }
                    }      
                }   
            }
        }
    }

    public int[] colisionPuertas(Entidad e){
        //nuevaInfo[0] y nuevaInfo[1] indican el nuevo mapa a imprimir
        //nuevaInfo[2] y nuevaInfo[3] indican la nueva posición de la entidad 
        //nuevaInfo[4] indica si hay colision: > 0 ha habido, < 0 no ha habido
        int[] nuevaInfo = new int[5];
        Rectangle[] hitboxE = e.getHitParedes();
        
        nuevaInfo[4] = -1;
        
        for(int i = 0;i < this.puertas.size();i++){
            switch(nombre){
                case "Antes_Cueva_Inicio":
                    break;
                case "Casa_inicio":
                    break;
                case "Catedral":
                    break;
                case "Ciudad_ciudad":
                    break;
                case "Cueva_inicio":
                    if(puertas.get(i).intersects(hitboxE[0])){
                        nuevaInfo[0] = 0;
                        nuevaInfo[1] = 3;
                        nuevaInfo[2] = 591;
                        nuevaInfo[3] = 479;
                        nuevaInfo[4] = 1;
                    } else if(puertas.get(i).intersects(hitboxE[2])){
                        
                    }
                    break;
                case "DungeonTorrePlanta1":
                    break;
                case "DungeonTorrePlanta2":
                    break;
                case "DungeonTorrePlanta3":
                    break;
                case "Fuera_casa_inicio":
                    break;
                case "Mirador_Ciudad":
                    if(puertas.get(i).intersects(hitboxE[2])){
                        nuevaInfo[0] = 0;
                        nuevaInfo[1] = 0;
                        nuevaInfo[2] = 750;
                        nuevaInfo[3] = 330;
                        nuevaInfo[4] = 1;
                    }
                    break;
                case "RutaAntesDungeonTorre":
                    break;
                case "Pueblo":
                    break;
            }
        }
        
        return nuevaInfo;
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
    
    public ArrayList<Rectangle> getPuertas(){
        return puertas;
    }
    
    public void render(){
        this.render(0, 0);
    }
}
