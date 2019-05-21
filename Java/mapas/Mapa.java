/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapas;

import chaoschild.Punto;
import cofres.Cofre;
import entidades.Civil1;
import entidades.Civil2;
import entidades.Entidad;
import entidades.NPC;
import entidades.enemigos.*;
import java.util.ArrayList;
import juego.Juego;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author victo y carlos
 */
public class Mapa extends TiledMap {
    private ArrayList<ConjuntoEnemigos> enemigos;
    private ArrayList<Rectangle> hitBoxes;
    private ArrayList<Rectangle> puertas;
    private ArrayList<Edificio> edificios;
    private ArrayList<Cofre> cofres;
    private GestorNPCs gestorNpc;
    private int[] nuevaInfo;
    private String nombre;
    private int puertasLayer;
    private int paredesLayer;
    private Sound abrir_puerta;
    private Sound cerrar_puerta;
    private boolean descubierto;

    
    public Mapa(String ref, String tileSetsLocation, Juego juego) throws SlickException {
        super(ref, tileSetsLocation);
        paredesLayer = this.getLayerIndex("ObjColisionables");
        puertasLayer = this.getLayerIndex("Puerta");
        this.cofres = new ArrayList();
        hitBoxes = new ArrayList();
        enemigos = new ArrayList();
        edificios = new ArrayList();
        puertas = new ArrayList();
        nuevaInfo = new int[5];
        this.abrir_puerta = new Sound("/resources/sonido/abrir_puerta.ogg");
        this.cerrar_puerta = new Sound("/resources/sonido/cerrar_puerta.ogg");
        this.descubierto = false;
        this.gestorNpc = new GestorNPCs(juego);
        genPuertas();
        genNombre(ref);
        genNPCs();
    }

    public Mapa(String ref, Juego juego) throws SlickException {
        super(ref);
        paredesLayer = this.getLayerIndex("ObjColisionables");
        puertasLayer = this.getLayerIndex("Puerta");
        this.cofres = new ArrayList();
        hitBoxes = new ArrayList();
        enemigos = new ArrayList();
        edificios = new ArrayList();
        puertas = new ArrayList();
        nuevaInfo = new int[5];
        this.abrir_puerta = new Sound("/resources/sonido/abrir_puerta.ogg");
        this.cerrar_puerta = new Sound("/resources/sonido/cerrar_puerta.ogg");
        this.descubierto = false;
        this.gestorNpc = new GestorNPCs(juego);
        genPuertas();
        genNombre(ref);
        genNPCs();
    }
    
    private void genNPCs() throws SlickException{
        switch(nombre){
            case "Fuera_casa_inicio":
                Civil1 c1 = new Civil1("cPrueba", 459, 374);
                this.gestorNpc.add(c1);
                break;
            case "Hospital_Pueblo":
                Civil1 medic = new Civil1("medic", 124, 137);
                this.gestorNpc.add(medic);
                break;
            case "Pueblo":
                Civil2 c2p = new Civil2("c2p", 877, 503);
                Civil1 c3p = new Civil1("c3p", 760, 919);
                Civil2 c4p = new Civil2("c4p", 411, 1948);
                Civil2 c5p = new Civil2("c5p", 1570, 2122);
                Civil1 c6p = new Civil1("c6p", 2757, 2256);
                this.gestorNpc.add(c3p);
                this.gestorNpc.add(c4p);
                this.gestorNpc.add(c5p);
                this.gestorNpc.add(c6p);
                this.gestorNpc.add(c2p);
                break;
            case "Cueva_Pueblo":
                Civil1 ccp = new Civil1("ccp", 563, 598);
                this.gestorNpc.add(ccp);
                break;
            case "Ciudad_ciudad":
                Civil2 ccc = new Civil2("ccc", 827, 980);
                this.gestorNpc.add(ccc);
                break;
            case "Catedral":
                Civil1 ccca = new Civil1("ccca", 903, 879);
                this.gestorNpc.add(ccca);
                break;
        }
    }
    
    public void drawEnemigos(){
        for(ConjuntoEnemigos conEnemigos : enemigos){
            conEnemigos.draw();
        }
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
    
    public void deleteConjuntoEnemigos(ConjuntoEnemigos cjene){
        this.enemigos.remove(cjene);
    }
    
    public void genEnemigos() throws SlickException{
        int x, y;
        ConjuntoEnemigos ce;
        ArrayList<Enemigo> aux;
        switch(nombre){
                case "Cueva_inicio":
                    ce = new ConjuntoEnemigos();
                    aux = new ArrayList();
                    x = 909; 
                    y = 1086;
                    SlimeBase slime = new SlimeBase(x, y);
                    aux.add(slime);
                    ce = new ConjuntoEnemigos(aux, new Punto(x, y));
                    this.enemigos.add(ce);
                    
                    ce = new ConjuntoEnemigos();
                    aux = new ArrayList();
                    x = 1028;
                    y = 472;
                    Geobro geo = new Geobro(x, y);
                    aux.add(geo);
                    ce = new ConjuntoEnemigos(aux, new Punto(x, y));
                    this.enemigos.add(ce);
                    
                    break;
                case "DungeonTorrePlanta1":
                    ce = new ConjuntoEnemigos();
                    aux = new ArrayList();
                    x = 1000;
                    y = 682;
                    Ghostler go = new Ghostler(x, y);
                    aux.add(go);
                    ce = new ConjuntoEnemigos(aux, new Punto(x, y));
                    this.enemigos.add(ce);

                    break;
                case "DungeonTorrePlanta2":
                    ce = new ConjuntoEnemigos();
                    aux = new ArrayList();
                    x = 207;
                    y = 701;
                    Ghostler go1 = new Ghostler(x, y);
                    aux.add(go1);
                    ce = new ConjuntoEnemigos(aux, new Punto(x, y));
                    this.enemigos.add(ce);
                    
                    ce = new ConjuntoEnemigos();
                    aux = new ArrayList();
                    x = 653;
                    y = 359;
                    Ragebbit rg = new Ragebbit(x, y);
                    Ghostler go2 = new Ghostler(-1000, -1000);
                    aux.add(rg);
                    aux.add(go2);
                    ce = new ConjuntoEnemigos(aux, new Punto(x, y));
                    this.enemigos.add(ce);

                    break;
                case "DungeonTorrePlanta3":
                    ce = new ConjuntoEnemigos();
                    aux = new ArrayList();
                    x = 314;
                    y = 0;
                    PaulaE pa = new PaulaE(x, y);
                    AntonioE an = new AntonioE(x, y);
                    aux.add(pa);
                    aux.add(an);
                    ce = new ConjuntoEnemigos(aux, new Punto(x, y));
                    this.enemigos.add(ce);
                    
                    break;
                case "RutaAntesDungeonTorre":
                    ce = new ConjuntoEnemigos();
                    aux = new ArrayList();
                    x = 811;
                    y = 823;
                    Hipograsidi hg = new Hipograsidi(x, y);
                    aux.add(hg);
                    ce = new ConjuntoEnemigos(aux, new Punto(x, y));
                    this.enemigos.add(ce);
                    
                    ce = new ConjuntoEnemigos();
                    aux = new ArrayList();
                    x = 1814;
                    y = 659;
                    EntOscuro eo = new EntOscuro(x, y);
                    aux.add(eo);
                    ce = new ConjuntoEnemigos(aux, new Punto(x, y));
                    this.enemigos.add(ce);
                    
                    ce = new ConjuntoEnemigos();
                    aux = new ArrayList();
                    x = 2948;
                    y = 871;
                    Hipograsidi hg2 = new Hipograsidi(x, y);
                    Hipograsidi hg3 = new Hipograsidi(-1000, -1000);
                    aux.add(hg2);
                    aux.add(hg3);
//                    Hipograsidi bajo nivel;
//                    Hipograsidi bajo nivel;
                    ce = new ConjuntoEnemigos(aux, new Punto(x, y));
                    this.enemigos.add(ce);
                    
                    break;
                case "Pueblo":
                    ce = new ConjuntoEnemigos();
                    aux = new ArrayList();
                    x = 462;
                    y = 358;
                    SlimeFuego sf = new SlimeFuego(x, y);
                    aux.add(sf);
                    ce = new ConjuntoEnemigos(aux, new Punto(x, y));
                    this.enemigos.add(ce);
                    
                    ce = new ConjuntoEnemigos();
                    aux = new ArrayList();
                    x = 569;
                    y = 390;
                    SlimeFuego sf2 = new SlimeFuego(x, y);
                    aux.add(sf2);
                    ce = new ConjuntoEnemigos(aux, new Punto(x, y));
                    this.enemigos.add(ce);

                    break;
                case "Casa_Pueblo":
                    ce = new ConjuntoEnemigos();
                    aux = new ArrayList();
                    x = 56;
                    y = 143;
                    Ragebbit rg2 = new Ragebbit(x, y);
                    SlimeOscuro so2 = new SlimeOscuro(-1000, -1000);
                    aux.add(rg2);
                    aux.add(so2);
                    ce = new ConjuntoEnemigos(aux, new Punto(x, y));
                    this.enemigos.add(ce);

                    break;
                case "Cueva_Antes_Ruta_Dungeon_Final":
                    ce = new ConjuntoEnemigos();
                    aux = new ArrayList();
                    x = 461;
                    y = 1837;
                    Geobro g1 = new Geobro(x, y);
                    Geobro g2 = new Geobro(x, y);
                    Geobro g3 = new Geobro(x, y);
                    aux.add(g1);
                    aux.add(g2);
                    aux.add(g3);
                    ce = new ConjuntoEnemigos(aux, new Punto(x, y));
                    this.enemigos.add(ce);

                    break;
                case "Dungeon_Desierto":
                    //Aumentar alzance -> setRanto(400);
                    ce = new ConjuntoEnemigos();
                    aux = new ArrayList();
                    x = 1877;
                    y = 2102;
                    SlimeFuego sf3 = new SlimeFuego(x, y);
                    SlimeFuego sf4 = new SlimeFuego(-1000, -1000);
                    aux.add(sf3);
                    aux.add(sf4);
                    ce = new ConjuntoEnemigos(aux, new Punto(x, y));
                    this.enemigos.add(ce);
                    
                    ce = new ConjuntoEnemigos();
                    aux = new ArrayList();
                    x = 805;
                    y = 1344;
                    SlimeBase sa = new SlimeBase(x, y);
                    SlimeBase sa2 = new SlimeBase(-1000, -1000);
                    aux.add(sa);
                    aux.add(sa2);
                    ce = new ConjuntoEnemigos(aux, new Punto(x, y));
                    this.enemigos.add(ce);
                    
                    ce = new ConjuntoEnemigos();
                    aux = new ArrayList();
                    x = 1300;
                    y = 900;
                    Geobro g5 = new Geobro(x, y);
                    Geobro g6 = new Geobro(-1000, -1000);
                    aux.add(g5);
                    aux.add(g6);
                    ce = new ConjuntoEnemigos(aux, new Punto(x, y));
                    this.enemigos.add(ce);
                    
                    ce = new ConjuntoEnemigos();
                    aux = new ArrayList();
                    x = 2015;
                    y = 1755;
                    Ragebbit rg3 = new Ragebbit(x, y);
                    SlimeFuego sf5 = new SlimeFuego(-1000, -1000);
                    SlimeBase sa3 = new SlimeBase(-1000, -1000);
                    aux.add(rg3);
                    aux.add(sf5);
                    aux.add(sa3);
                    ce = new ConjuntoEnemigos(aux, new Punto(x, y));
                    this.enemigos.add(ce);
                    
                    /*
                    Geobro ene = new Geobro(x, y);
                    Ragebbit rag = new Ragebbit(-1000, -1000);
                    Hipograsidi hip = new Hipograsidi(-1000, -1000);
                    ArrayList<Enemigo> aux = new ArrayList();
                    aux.add(ene);
                    aux.add(rag);
                    aux.add(hip);
                    ConjuntoEnemigos conjunto = new ConjuntoEnemigos(aux, new Punto(x, y));
                    
                    this.enemigos.add(conjunto);
                    */
                    break;
                case "Dungeon_Final":
                    
                    ce = new ConjuntoEnemigos();
                    aux = new ArrayList();
                    x = 870;
                    y = 729;
                    //Crear bossfinal
                    //BossFinal;
                    Clerigo cl2 = new Clerigo(x, y);
                    Clerigo cl3 = new Clerigo(-1000, -1000);
                    aux.add(cl3);
                    aux.add(cl2);
                    ce = new ConjuntoEnemigos(aux, new Punto(x, y));
                    this.enemigos.add(ce);

                    break;
                case "Ruta_Antes_Cueva_Dungeon_Final":
                    ce = new ConjuntoEnemigos();
                    aux = new ArrayList();
                    x = 1338;
                    y = 1066;
                    //Crear trevenant
                    //2 Trevenant;
                    EntOscuro eo2 = new EntOscuro(x, y);
                    EntOscuro eo3 = new EntOscuro(-1000, -1000);
                    Hipograsidi hg6 = new Hipograsidi(-1000, -1000);
                    aux.add(eo2);
                    aux.add(eo3);
                    aux.add(hg6);
                    ce = new ConjuntoEnemigos(aux, new Punto(x, y));
                    this.enemigos.add(ce);

                    break;
                case "Ruta_Antes_Dungeon_Desierto":
                    ce = new ConjuntoEnemigos();
                    aux = new ArrayList();
                    x = 411;
                    y = 1525;
                    SlimeRayo sy = new SlimeRayo(x, y);
                    SlimeBase sa9 = new SlimeBase(-1000, -100);
                    aux.add(sy);
                    aux.add(sa9);
                    ce = new ConjuntoEnemigos(aux, new Punto(x, y));
                    this.enemigos.add(ce);
                    
                    ce = new ConjuntoEnemigos();
                    aux = new ArrayList();
                    x = 773;
                    y = 2212;
                    EntOscuro eo4 = new EntOscuro(x, y);
                    Ragebbit rg9 = new Ragebbit(-1000, -1000);
                    aux.add(eo4);
                    aux.add(rg9);
                    ce = new ConjuntoEnemigos(aux, new Punto(x, y));
                    this.enemigos.add(ce);
                    
                    ce = new ConjuntoEnemigos();
                    aux = new ArrayList();
                    x = 507;
                    y = 4029;
                    SlimeRayo sy2 =new SlimeRayo(x, y);
                    aux.add(sy2);
                    ce = new ConjuntoEnemigos(aux, new Punto(x, y));
                    this.enemigos.add(ce);
                    
                    ce = new ConjuntoEnemigos();
                    aux = new ArrayList();
                    x = 979;
                    y = 3897;
                    Geobro go8 = new Geobro(x, y);
                    aux.add(go8);
                    ce = new ConjuntoEnemigos(aux, new Punto(x, y));
                    this.enemigos.add(ce);

                    break;
                case "Catedral":
                    ce = new ConjuntoEnemigos();
                    aux = new ArrayList();
                    x = 633;
                    y = 734;
                    Clerigo cl=new Clerigo(x,y);
                    aux.add(cl);
                    ce = new ConjuntoEnemigos(aux, new Punto(x, y));
                    this.enemigos.add(ce);
            }
    }
    
    private void genPuertas()throws SlickException{
        int ax  = this.width;
        int ay = this.height;
        
        boolean paredIzq;
        boolean paredCentro;
        boolean paredDrch;
        boolean paredDown;
        boolean puertaCentro;
        boolean puertaDrch;
        boolean puertaIzq;
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

    public void setNuevaInfo(int mapaI, int mapaJ, int posX, int posY, int conf){
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
                        setNuevaInfo(0, 2, 690, 343, 1);
                    } else if(puertas.get(i).intersects(hitboxE[0])){
                        setNuevaInfo(0, 1, 800, 1193, 1);
                    }
                    break;
                case "Casa_inicio":
                    if(puertas.get(i).intersects(hitboxE[0])){
                        abrir_puerta.play();
                        setNuevaInfo(0, 2, 493, 656, 1);
                    }
                    break;
                case "Catedral":
                    if(puertas.get(i).intersects(hitboxE[2])){
                        setNuevaInfo(1, 1, 670, 238, 1);
                    }
                    break;
                case "Ciudad_ciudad":
                    if(puertas.get(i).intersects(hitboxE[2])){
                        setNuevaInfo(1, 2, 392, 473, 1);
                    } else if(puertas.get(i).intersects(hitboxE[0])){
                        setNuevaInfo(1, -1, 639, 2143, 1);
                    } else if(puertas.get(i).intersects(hitboxE[1])){
                        setNuevaInfo(2, 3, 202, 537, 1);
                    } else if(puertas.get(i).intersects(hitboxE[3])){
                        setNuevaInfo(1, 3, 1138, 341, 1);
                    }
                    break;
                case "Cueva_inicio":
                    if(puertas.get(i).intersects(hitboxE[0])){
                        setNuevaInfo(1, 2, 591, 479, 1);
                    } else if(puertas.get(i).intersects(hitboxE[2])){
                        setNuevaInfo(0, 3, 446, 335, 1);
                    } else if(puertas.get(i).intersects(hitboxE[1])){
                        setNuevaInfo(0, 0, 360, 507, 1);
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
                        setNuevaInfo(0, 3, 463, 927, 1);
                    } else if(puertas.get(i).intersects(hitboxE[2])){
                        cerrar_puerta.play();
                        setNuevaInfo(0, -1, 256, 24, 1);
                    }
                    break;
                case "Mirador_Ciudad":
                    if(puertas.get(i).intersects(hitboxE[2])){
                        setNuevaInfo(0, 1, 750, 330, 1);
                    } else if(puertas.get(i).intersects(hitboxE[3])){
                        setNuevaInfo(1, 1, 702, 2623, 1);
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
                        setNuevaInfo(1, 1, 950, 571, 1);
                    } else if(puertas.get(i).intersects(hitboxE[1])){
                        if(e.getPosicion().getY() > 730 && e.getPosicion().getY() < 950){
                            setNuevaInfo(3, 2, 359, 776, 1);
                        } else {
                            setNuevaInfo(2, 4, 327, 519, 1);
                        }
                    } else if(puertas.get(i).intersects(hitboxE[0])){
                        int x = (int) e.getPosicion().getX();
                        int y = (int) e.getPosicion().getY();
                        
                        if(x > 2295 && x < 2315){
                            this.abrir_puerta.play();
                            setNuevaInfo(2, -1, 381, 165, 1);
                        } else if(x > 890 && x < 900){
                            this.abrir_puerta.play();
                            setNuevaInfo(2, -2, 110, 259, 1);
                        } else if(x > 500 && x < 580){
                            setNuevaInfo(2, -3, 623, 895, 1);
                        } else if(x > 2170 && x < 2180){
                            this.abrir_puerta.play();
                            setNuevaInfo(2, -4, 498, 325, 1);
                        } else if(x > 345 && x < 360){
                            this.abrir_puerta.play();
                            setNuevaInfo(2, -5, 445, 325, 1);
                        }
                    }
                    break;
                case "Ayuntamiento_Pueblo":
                    if(puertas.get(i).intersects(hitboxE[2])){
                        this.cerrar_puerta.play();
                        setNuevaInfo(2, 3, 2303, 284, 1);
                    }
                    break;
                case "Casa_Pueblo":
                    if(puertas.get(i).intersects(hitboxE[2])){
                        this.cerrar_puerta.play();
                        setNuevaInfo(2, 3, 895, 846, 1);
                    }
                    break;
                case "Cueva_Pueblo":
                    if(puertas.get(i).intersects(hitboxE[2])){
                        setNuevaInfo(2, 3, 545, 2579, 1);
                    }
                    break;
                case "Hospital_Pueblo":
                    if(puertas.get(i).intersects(hitboxE[2])){
                        this.cerrar_puerta.play();
                        setNuevaInfo(2, 3, 2175, 1464, 1);
                    }
                    break;
                case "Residencia_Pueblo":
                    if(puertas.get(i).intersects(hitboxE[2])){
                        this.cerrar_puerta.play();
                        setNuevaInfo(2, 3, 355, 1758, 1);
                    }
                    break;
                case "Cueva_Antes_Ruta_Dungeon_Final":
                    if(puertas.get(i).intersects(hitboxE[2])){
                        setNuevaInfo(3, 2, 1917, 761, 1);
                    } else if(puertas.get(i).intersects(hitboxE[1])){
                        setNuevaInfo(3, 3, 399, 735, 1);
                    }
                    break;
                case "Cueva_Inicio_Extension":
                    if(puertas.get(i).intersects(hitboxE[3])){
                        setNuevaInfo(0, 1, 1302, 550, 1);
                    }
                    break;
                case "Dungeon_Desierto":
                    if(puertas.get(i).intersects(hitboxE[0])){
                        setNuevaInfo(1, 3, 668, 4224, 1);
                    }
                    break;
                case "Dungeon_Final":
                    if(puertas.get(i).intersects(hitboxE[0])){
                        setNuevaInfo(3, 3, 380, 174, 1);
                    }
                    break;
                case "Ruta_Antes_Cueva_Dungeon_Final":
                    if(puertas.get(i).intersects(hitboxE[3])){
                        setNuevaInfo(2, 3, 3032, 841, 1);
                    } else if(puertas.get(i).intersects(hitboxE[0])){
                        setNuevaInfo(3, 0, 208, 1859, 1);
                    }
                    break;
                case "Ruta_Antes_Dungeon_Desierto":
                    if(puertas.get(i).intersects(hitboxE[1])){
                        setNuevaInfo(1, 1, 265, 605, 1);
                    } else if(puertas.get(i).intersects(hitboxE[2])){
                        setNuevaInfo(1, 0, 1537, 338, 1);
                    }
                    break;
                case "Ruta_Antes_Dungeon_Final":
                    if(puertas.get(i).intersects(hitboxE[2])){
                        setNuevaInfo(3, 0, 1500, 193, 1);
                    } else if(puertas.get(i).intersects(hitboxE[0])){
                        setNuevaInfo(3, 1, 925, 337, 1);
                    }
                    break;
            }
        }
        
        return this.nuevaInfo;
    }
    
    public void addEdificio(Edificio edificio){
        this.edificios.add(edificio);
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
    
    public void drawCofres(){
        for(Cofre cofre : cofres){
            cofre.draw();
        }
    }

    public int[] getNuevaInfo() {
        return nuevaInfo;
    }
    
    public ArrayList<Cofre> getCofres(){
        return this.cofres;
    }

    public ArrayList<NPC> getNpcs() {
        return gestorNpc.getNpcs();
    }

    
    public boolean isDescubierto() {
        return descubierto;
    }

    public void setDescubierto(boolean descubierto) {
        this.descubierto = descubierto;
    }
    
    public void addCofre(Cofre cofre){
        this.cofres.add(cofre);
    }
    
    public void delCofre(int pos){
        this.cofres.remove(pos);
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public ArrayList<ConjuntoEnemigos> getConjuntoEnemigos(){
        return enemigos;
    }
    
    public void delEnemigo(ConjuntoEnemigos conjunto){
        this.enemigos.remove(conjunto);
    }

    public ArrayList<Enemigo> getEnemigos(){
        ArrayList<Enemigo> aux = new ArrayList();
        for (int i = 0;i < enemigos.size();i++){
            for (Enemigo enemigo : enemigos.get(i).getEnemigos()) {
                aux.add(enemigo);
            }
        }
        return aux;
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

    public GestorNPCs getGestorNpc() {
        return gestorNpc;
    }
    
}
