/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapas;

import java.util.ArrayList;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Carlos
 */
public class Mundo {
    
    private ArrayList<Zona> mundo;
    private Mapa mapaCargado;
    
    public Mundo() throws SlickException{
        this.mundo = new ArrayList();
        genZonas();
        initMapa();
    }
    
    private void initMapa(){
        cambiarMapa(1, 0);
    }
    
    private void genZonas() throws SlickException{
        for(int i = 0;i < 3;i++){
            mundo.add(new Zona());
        }
        
        genDungeons();
        genPueblos();
        genRutas();
        
        for(Zona zona : mundo){
            zona.sort();
        }
    }
    
    private void genDungeons() throws SlickException{
        mundo.get(0).addDungeon(new Dungeon("resources/Mapas/Cueva_inicio/Cueva_inicio.tmx"));
        mundo.get(2).addDungeon(new Dungeon("resources/Mapas/DungeonTorrePlanta1/DungeonTorrePlanta1.tmx"));
        mundo.get(2).addDungeon(new Dungeon("resources/Mapas/DungeonTorrePlanta2/DungeonTorrePlanta2.tmx"));
        mundo.get(2).addDungeon(new Dungeon("resources/Mapas/DungeonTorrePlanta3/DungeonTorrePlanta3.tmx"));
    }
    
    private void genPueblos() throws SlickException{
        Pueblo fuera_casa = new Pueblo("resources/Mapas/Fuera_casa_inicio/Fuera_casa_inicio.tmx");
        Pueblo pueblo = new Pueblo("resources/Mapas/Pueblo/Pueblo.tmx");
        Pueblo ciudad = new Pueblo("resources/Mapas/Ciudad_ciudad/Ciudad_ciudad.tmx");
        
        Edificio casa_inicio = new Edificio("resources/Mapas/Casa_inicio/Casa_inicio.tmx");
        Edificio catedral = new Edificio("resources/Mapas/Catedral/Catedral.tmx");
  
        //Faltan los edificios del pueblo
        
        fuera_casa.addEdificio(casa_inicio);
        ciudad.addEdificio(catedral);
        
        mundo.get(0).addPueblo(fuera_casa);
        mundo.get(1).addPueblo(ciudad);
        mundo.get(2).addPueblo(pueblo);
    }
    
    private void genRutas() throws SlickException{
        mundo.get(0).addRuta(new Ruta("resources/Mapas/Antes_Cueva_Inicio/Antes_Cueva_Inicio.tmx"));
        mundo.get(1).addRuta(new Ruta("resources/Mapas/Mirador_Ciudad/Mirador_Ciudad.tmx"));
        mundo.get(2).addRuta(new Ruta("resources/Mapas/RutaAntesDungeonTorre/RutaAntesDungeonTorre.tmx"));
    }
    
    public void cambiarMapa(int z, int m){
        if(m >= 0){
            this.mapaCargado = this.getMapa(z, m);
        } else {
            switch (z) {
                case 0:
                    this.mapaCargado = this.getMapa(0, 1).getEdificios().get(0);
                    break;
                case 1:
                    this.mapaCargado = this.getMapa(1, 0).getEdificios().get(0);
                    break;
                case 2:
                    this.mapaCargado = this.getMapa(2, 3).getEdificios().get(-m + 1);
                    break;
            }
        }
    }
    
    public void render(){
        this.mapaCargado.render();
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
}
