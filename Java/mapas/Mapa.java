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
    private int[] nuevaInfo;
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
        nuevaInfo = new int[5];
        genPuertas();
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
        nuevaInfo = new int[5];
        genPuertas();
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
    }
    
    private void genPuertas()throws SlickException{
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
                    if(paredDrch && paredIzq || paredDrch && puertaIzq || paredIzq && puertaDrch || puertaDrch && puertaIzq){
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

    private void setNuevaInfo(int mapaI, int mapaJ, int posX, int posY, int conf){
        //nuevaInfo[0] y nuevaInfo[1] indican el nuevo mapa a imprimir
        //nuevaInfo[2] y nuevaInfo[3] indican la nueva posición de la entidad 
        //nuevaInfo[4] indica si hay colision: > 0 ha habido, < 0 no ha habido
        this.nuevaInfo[0] = mapaI;
        this.nuevaInfo[1] = mapaJ;
        this.nuevaInfo[2] = posX;
        this.nuevaInfo[3] = posY;
        this.nuevaInfo[4] = conf;
    }
    
    public int[] colisionPuertas(Entidad e){
        Rectangle[] hitboxE = e.getHitParedes();
        
        this.nuevaInfo[4] = -1;
        
        for(int i = 0;i < this.puertas.size();i++){
            switch(nombre){
                case "Antes_Cueva_Inicio":
                    if(puertas.get(i).intersects(hitboxE[2])){
                        setNuevaInfo(0, 1, 525, 175, 1);
                    } else if(puertas.get(i).intersects(hitboxE[0])){
                        setNuevaInfo(0, 0, 800, 1193, 1);
                    }
                    break;
                case "Casa_inicio":
                    if(puertas.get(i).intersects(hitboxE[0])){
                        setNuevaInfo(0, 1, 335, 505, 1);
                    }
                    break;
                case "Catedral":
                    if(puertas.get(i).intersects(hitboxE[2])){
                        setNuevaInfo(1, 0, 670, 238, 1);
                    }
                    break;
                case "Ciudad_ciudad":
                    if(puertas.get(i).intersects(hitboxE[2])){
                        setNuevaInfo(1, 1, 392, 473, 1);
                    } else if(puertas.get(i).intersects(hitboxE[0])){
                        setNuevaInfo(1, -1, 639, 2143, 1);
                    } else if(puertas.get(i).intersects(hitboxE[1])){
                        setNuevaInfo(2, 3, 202, 537, 1);
                    }
                    break;
                case "Cueva_inicio":
                    if(puertas.get(i).intersects(hitboxE[0])){
                        setNuevaInfo(1, 1, 591, 479, 1);
                    } else if(puertas.get(i).intersects(hitboxE[2])){
                        setNuevaInfo(0, 2, 446, 335, 1);
                    }
                    break;
                case "DungeonTorrePlanta1":
                    if(puertas.get(i).intersects(hitboxE[0])){
                        setNuevaInfo(2, 1, 464, 863, 1);
                    } else if(puertas.get(i).intersects(hitboxE[2])){
                        setNuevaInfo(2, 4, 4098, 206, 1);
                    }
                    break;
                case "DungeonTorrePlanta2":
                    if(puertas.get(i).intersects(hitboxE[0])){
                        setNuevaInfo(2, 2, 310, 543, 1);
                    } else if(puertas.get(i).intersects(hitboxE[2])){
                        setNuevaInfo(2, 0, 625, 142, 1);
                    }
                    break;
                case "DungeonTorrePlanta3":
                    if(puertas.get(i).intersects(hitboxE[2])){
                        setNuevaInfo(2, 1, 465, 15, 1);
                    }
                    break;
                case "Fuera_casa_inicio":
                    if(puertas.get(i).intersects(hitboxE[0])){
                        setNuevaInfo(0, 2, 463, 927, 1);
                    } else if(puertas.get(i).intersects(hitboxE[2])){
                        setNuevaInfo(0, -1, 256, 24, 1);
                    }
                    break;
                case "Mirador_Ciudad":
                    if(puertas.get(i).intersects(hitboxE[2])){
                        setNuevaInfo(0, 0, 750, 330, 1);
                    } else if(puertas.get(i).intersects(hitboxE[3])){
                        setNuevaInfo(1, 0, 702, 2623, 1);
                    }
                    break;
                case "RutaAntesDungeonTorre":
                    if(puertas.get(i).intersects(hitboxE[3])){
                        setNuevaInfo(2, 3, 3032, 2877, 1);
                    } else if(puertas.get(i).intersects(hitboxE[0])){
                        setNuevaInfo(2, 0, 625, 1183, 1);
                    }
                    break;
                case "Pueblo":
                    if(puertas.get(i).intersects(hitboxE[3])){
                        setNuevaInfo(1, 0, 950, 571, 1);
                    } else if(puertas.get(i).intersects(hitboxE[1])){
                        if(e.getPosicion().getY() > 730 && e.getPosicion().getY() < 950){
                        } else {
                            setNuevaInfo(2, 4, 327, 519, 1);
                        }
                    }
                    break;
            }
        }
        
        return this.nuevaInfo;
    }
    
    public void addEdificio(Edificio edificio){
        this.edificios.add(edificio);
        sortEdificios();
    }
   
    public void sortEdificios(){
        try{
            for(int i = 0;i < edificios.size();i++){
                if(edificios.get(i).getClass().getName().compareTo(edificios.get(i + 1).getClass().getName()) > 0){
                    Edificio aux = edificios.get(i);
                    this.edificios.set(i, this.edificios.get(i + 1));
                    this.edificios.set(i + 1, aux);
                }
            }
        } catch(Exception e){}
    }
    
    public void printEdificios(){
        System.out.println("Edificios: ");
        for(Edificio edificio : edificios){
            System.out.println(edificio.getNombre() + " - ");
        }
    }
    
    public String getNombre(){
        return nombre;
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
