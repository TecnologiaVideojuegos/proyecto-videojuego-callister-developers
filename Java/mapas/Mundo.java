/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapas;

import chaoschild.Punto;
import cofres.Cofre;
import itemsjuego.*;
import java.util.ArrayList;
import juego.Juego;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Carlos
 */
public class Mundo {
    
    private ArrayList<Zona> mundo;
    private Mapa mapaCargado;
    private Juego juego;
    private int[] coord;
    
    public Mundo(Juego juego) throws SlickException{
        this.juego = juego;
        this.mundo = new ArrayList();
        this.coord = new int[2];
        genZonas();
        genCofres();
        this.coord = juego.getGMapa().leerMapa();
        initMapa();
    }
    
    private void initMapa(){
        
        if(this.coord[1] == -1){
            cambiarMapa(2, 3);
        } else {
            cambiarMapa(this.coord[0], this.coord[1]);
        }
        
        //cambiarMapa(0, 1);
    }
    
    private void genZonas() throws SlickException{
        for(int i = 0;i < 4;i++){
            mundo.add(new Zona());
        }
        
        genDungeons();
        genPueblos();
        genRutas();
        
        for(Zona zona : mundo){
            zona.sort();
            //zona.toStringAll();
        }
    }
    
    private void genCofres() throws SlickException{
        PocionVPequeña Pvp = new PocionVPequeña();
        PocionVPequeña Pvp2 = new PocionVPequeña();
        PocionVMediana Pvm = new PocionVMediana();
        PocionVMediana Pvm2 = new PocionVMediana();
        PocionVGrande Pvg = new PocionVGrande();
        PocionVGrande Pvg2 = new PocionVGrande();
        PocionMPequeña Pmp = new PocionMPequeña();
        PocionMMediana Pmm = new PocionMMediana();
        PocionMGrande Pmg = new PocionMGrande();
        GemaTierra1 gemaTierra = new GemaTierra1();
        GemaLuz1 gemaLuz = new GemaLuz1();
        GemaLuz2 gemaLuz2 = new GemaLuz2();
        GemaFuego1 gemaFuego = new GemaFuego1();
        
        Cofre cofre = new Cofre(new Punto(19 * 32, 15 * 32), Pvp, this.juego);
        mundo.get(0).getZona().get(0).addCofre(cofre);
        
        cofre = new Cofre(new Punto(613, 373), Pmp, this.juego);
        mundo.get(2).getZona().get(3).getEdificios().get(2).addCofre(cofre);
        
        cofre = new Cofre(new Punto(32 * 7, 32 * 2), Pvm, this.juego);
        mundo.get(2).getZona().get(3).getEdificios().get(0).addCofre(cofre);
        
        cofre = new Cofre(new Punto(35, 640), Pmm, this.juego);
        mundo.get(2).getZona().get(0).addCofre(cofre);
        
        cofre = new Cofre(new Punto(80, 127), gemaTierra, this.juego);
        mundo.get(2).getZona().get(1).addCofre(cofre);
        
        cofre = new Cofre(new Punto(1000, 220), Pvg, this.juego);
        mundo.get(3).getZona().get(0).addCofre(cofre);
        
        cofre = new Cofre(new Punto(445, 303), gemaLuz, this.juego);
        mundo.get(2).getZona().get(3).addCofre(cofre);
        
        cofre = new Cofre(new Punto(137, 105), gemaLuz2, this.juego);
        mundo.get(2).getZona().get(3).getEdificios().get(1).addCofre(cofre);
        
        cofre = new Cofre(new Punto(192, 131), Pvp2, this.juego);
        mundo.get(2).getZona().get(3).getEdificios().get(4).addCofre(cofre);
        
        cofre = new Cofre(new Punto(1117, 909), gemaFuego, this.juego);
        mundo.get(1).getZona().get(3).addCofre(cofre);
        
        cofre = new Cofre(new Punto(1960, 1026), Pvg2, this.juego);
        mundo.get(1).getZona().get(0).addCofre(cofre);
        
        cofre = new Cofre(new Punto(2686, 1984), Pvm2, this.juego);
        mundo.get(1).getZona().get(0).addCofre(cofre);
    }
    
    private void genDungeons() throws SlickException{
        mundo.get(0).addDungeon(new Dungeon("resources/Mapas/Cueva_inicio/Cueva_inicio.tmx"));
        mundo.get(0).addDungeon(new Dungeon("resources/Mapas/Cueva_Inicio_Extension/Cueva_Inicio_Extension.tmx"));
        mundo.get(1).addDungeon(new Dungeon("resources/Mapas/Dungeon_Desierto/Dungeon_Desierto.tmx"));
        mundo.get(2).addDungeon(new Dungeon("resources/Mapas/DungeonTorrePlanta1/DungeonTorrePlanta1.tmx"));
        mundo.get(2).addDungeon(new Dungeon("resources/Mapas/DungeonTorrePlanta2/DungeonTorrePlanta2.tmx"));
        mundo.get(2).addDungeon(new Dungeon("resources/Mapas/DungeonTorrePlanta3/DungeonTorrePlanta3.tmx"));
        mundo.get(3).addDungeon(new Dungeon("resources/Mapas/Dungeon_Final/Dungeon_Final.tmx"));
        mundo.get(3).addDungeon(new Dungeon("resources/Mapas/Cueva_Antes_Ruta_Dungeon_Final/Cueva_Antes_Ruta_Dungeon_Final.tmx")); 
    }
    
    private void genPueblos() throws SlickException{
        Pueblo fuera_casa = new Pueblo("resources/Mapas/Fuera_casa_inicio/Fuera_casa_inicio.tmx");
        Pueblo pueblo = new Pueblo("resources/Mapas/Pueblo/Pueblo.tmx");
        Pueblo ciudad = new Pueblo("resources/Mapas/Ciudad_ciudad/Ciudad_ciudad.tmx");
        
        Edificio casa_inicio = new Edificio("resources/Mapas/Casa_inicio/Casa_inicio.tmx");
        Edificio catedral = new Edificio("resources/Mapas/Catedral/Catedral.tmx");
        Edificio ayuntamiento = new Edificio("resources/Mapas/Ayuntamiento_Pueblo/Ayuntamiento_Pueblo.tmx");
        Edificio casa_pueblo = new Edificio("resources/Mapas/Casa_Pueblo/Casa_Pueblo.tmx");
        Edificio cueva_pueblo = new Edificio("resources/Mapas/Cueva_Pueblo/Cueva_Pueblo.tmx");
        Edificio hospital = new Edificio("resources/Mapas/Hospital_Pueblo/Hospital_Pueblo.tmx");
        Edificio residencia = new Edificio("resources/Mapas/Residencia_Pueblo/Residencia_Pueblo.tmx");
        
        fuera_casa.addEdificio(casa_inicio);
        ciudad.addEdificio(catedral);
        pueblo.addEdificio(ayuntamiento);
        pueblo.addEdificio(casa_pueblo);
        pueblo.addEdificio(cueva_pueblo);
        pueblo.addEdificio(hospital);
        pueblo.addEdificio(residencia);
        
        pueblo.sortEdificios();
        
        mundo.get(0).addPueblo(fuera_casa);
        mundo.get(1).addPueblo(ciudad);
        mundo.get(2).addPueblo(pueblo);
    }
    
    private void genRutas() throws SlickException{
        mundo.get(0).addRuta(new Ruta("resources/Mapas/Antes_Cueva_Inicio/Antes_Cueva_Inicio.tmx"));
        mundo.get(1).addRuta(new Ruta("resources/Mapas/Mirador_Ciudad/Mirador_Ciudad.tmx"));
        mundo.get(1).addRuta(new Ruta("resources/Mapas/Ruta_Antes_Dungeon_Desierto/Ruta_Antes_Dungeon_Desierto.tmx"));
        mundo.get(2).addRuta(new Ruta("resources/Mapas/RutaAntesDungeonTorre/RutaAntesDungeonTorre.tmx"));
        mundo.get(3).addRuta(new Ruta("resources/Mapas/Ruta_Antes_Cueva_Dungeon_Final/Ruta_Antes_Cueva_Dungeon_Final.tmx"));
        mundo.get(3).addRuta(new Ruta("resources/Mapas/Ruta_Antes_Dungeon_Final/Ruta_Antes_Dungeon_Final.tmx"));
    }
    
    public void cambiarMapa(int z, int m){
        this.coord[0] = z;
        this.coord[1] = m;
        if(m >= 0){
            this.mapaCargado = this.getMapa(z, m);
        } else {
            switch (z) {
                case 0:
                    this.mapaCargado = this.getMapa(0, 2).getEdificios().get(0);
                    break;
                case 1:
                    this.mapaCargado = this.getMapa(1, 1).getEdificios().get(0);
                    break;
                case 2:
                    this.mapaCargado = this.getMapa(2, 3).getEdificios().get(-m - 1);
                    break;
            }
        }
        
        try {
            this.mapaCargado.genEnemigos();
        } catch (SlickException ex) {}
    }
    
    public void render(){
        this.mapaCargado.render();
        this.mapaCargado.drawCofres();
    }
    
    public Zona getZona(int Nzona){
        return mundo.get(Nzona);
    }
    
    public Mapa getMapa(int Nzona, int Ntipo){
        return mundo.get(Nzona).getZona().get(Ntipo);
    }
    
    public Mapa getMapaCargado(){
        return this.mapaCargado;
    }
    
    public int[] getCoord(){
        return this.coord;
    }
    
    public void setCoord(int[] coord){
        this.coord = coord;
    }
    
    public ArrayList<Mapa> getMapas(){
        ArrayList<Mapa> mapas = new ArrayList();
        for(Zona zona : mundo){
            for(Mapa mapa : zona.getZona()){
                String nombre = mapa.getNombre();
                if(nombre.equals("Cueva_inicio") || nombre.equals("Pueblo") ||
                        nombre.equals("Ciudad_ciudad") || nombre.equals("Fuera_casa_inicio")){
                    for(Edificio ed : mapa.getEdificios()){
                            mapas.add(ed);
                        }
                }
                mapas.add(mapa);
            }
        }
        return mapas;
    }
}
