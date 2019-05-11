/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapas;

import entidades.Entidad;
import java.util.ArrayList;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Carlos
 */
public class Mundo {
    
    private ArrayList<Zona> mundo;
    private Mapa mapaCargado;
    private Entidad entidad;
    
    public Mundo(Entidad entidad) throws SlickException{
        this.mundo = new ArrayList();
        this.entidad = entidad;
        genZonas();
        initMapa();
    }
    
    private void initMapa(){
        mapaCargado = this.getMapa(0, 0);
    }
    
    private void genZonas() throws SlickException{
        for(int i = 0;i < 2;i++){
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
        //mundo.get(1).addDungeon(new Dungeon("resources/Mapas/Prueba.tmx"));
    }
    
    private void genPueblos() throws SlickException{
        mundo.get(0).addPueblo(new Pueblo("resources/Mapas/Ciudad_ciudad/Ciudad_ciudad.tmx"));
        mundo.get(1).addPueblo(new Pueblo("resources/Mapas/Fuera_casa_inicio/Fuera_casa_inicio.tmx"));
    }
    
    private void genRutas() throws SlickException{
        mundo.get(0).addRuta(new Ruta("resources/Mapas/Antes_Cueva_Inicio/Antes_Cueva_Inicio.tmx"));
        mundo.get(0).addRuta(new Ruta("resources/Mapas/Mirador_Ciudad/Mirador_Ciudad.tmx"));
        //mundo.get(1).addRuta(new Ruta("resources/Pruebas_Tuto.tmx"));
    }
    
    public void cambiarMapa(int z, int m){
        this.mapaCargado = this.getMapa(z, m);
        //entidad.setPosicion(new Punto(400, 500));
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
