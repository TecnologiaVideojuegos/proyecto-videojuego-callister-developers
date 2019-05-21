/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapas;

import juego.Juego;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Carlos
 */
public class Edificio extends Mapa{
    
    public Edificio(String ref, String tileSetsLocation, Juego juego) throws SlickException {
        super(ref, tileSetsLocation, juego);
    }
    
    public Edificio(String ref, Juego juego) throws SlickException{
        super(ref, juego);
    }
}
