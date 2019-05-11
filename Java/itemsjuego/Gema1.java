/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemsjuego;

import Combate.Elemento;
import org.newdawn.slick.Image;

/**
 *
 * @author victo
 */
public abstract class Gema1  extends Gema{
    
    public Gema1(Image imagen, String n, Elemento e) {
        super(imagen, n, e, (float)0.3);
        setNivel(1);
    }
    
}
