/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapas;

import org.newdawn.slick.SlickException;

/**
 *
 * @author Carlos
 */
public class Edificio extends Mapa{
    
    public Edificio(String ref, String tileSetsLocation) throws SlickException {
        super(ref, tileSetsLocation);
    }
    
    public Edificio(String ref) throws SlickException{
        super(ref);
    }
}
