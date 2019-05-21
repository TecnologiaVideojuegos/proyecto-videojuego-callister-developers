/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapas;

import java.util.ArrayList;
import juego.Juego;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Carlos
 */
public class Pueblo extends Mapa{
    
    private ArrayList<Edificio> edificios;
    
    public Pueblo(String ref, String tileSetsLocation, Juego juego) throws SlickException {
        super(ref, tileSetsLocation, juego);
        edificios = new ArrayList();
    }
    
    public Pueblo(String ref, Juego juego) throws SlickException{
        super(ref, juego);
        edificios = new ArrayList();
    }
}
