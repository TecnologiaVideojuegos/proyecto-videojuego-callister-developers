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
public class Pueblo extends Mapa{
    
    private ArrayList<Edificio> edificios;
    
    public Pueblo(String ref, String tileSetsLocation) throws SlickException {
        super(ref, tileSetsLocation);
        edificios = new ArrayList();
    }
    
    public Pueblo(String ref) throws SlickException{
        super(ref);
        edificios = new ArrayList();
    }
}
