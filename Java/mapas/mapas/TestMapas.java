/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapas;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Carlos
 */
public class TestMapas extends BasicGameState{
        private Mundo mundo;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        mundo = new Mundo();
        
        /*
        Dungeon dg = new Dungeon("resources/Pruebas_Tuto.tmx");
        Pueblo p = new Pueblo("resources/Mapas/Ciudad_ciudad/Ciudad_ciudad.tmx", "/resources/Mapas/Ciudad_ciudad");
        Ruta r = new Ruta("resources/Mapas/Antes_Cueva_Inicio/Antes_Cueva_Inicio.tmx", "/resources/Mapas/Antes_Cueva_Inicio");
        //Edificio e = new Edificio("resources/Mapas/PRUEBA.tmx", "/resources/Mapas");
        
        //z = new Zona(dg, p, r);
        z = new Zona();
        
        z.addDungeon(dg);
        z.addRuta(r);
        z.addPueblo(p);
        z.sort();
        
        z.toStringAll();
        */
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        mundo.getMapa(0, 0).render(0, 0);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        
    }
    
    @Override
    public int getID() {
        return 100;
    }
    
}
