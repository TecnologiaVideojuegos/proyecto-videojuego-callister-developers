/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author victo
 */
public class Animacion{
    private Animation animacion;

    public Animacion() {
        this.animacion = new Animation();
    }
    
    

    public void addFrame(Image sprite, int i) {
        animacion.addFrame(sprite, i);
    }

    public Image getImage(int i) {
        return animacion.getImage(i); //To change body of generated methods, choose Tools | Templates.
    }

    public void draw(float x, float y) {
        animacion.draw(x, y); //To change body of generated methods, choose Tools | Templates.
    }

    public void setLooping(boolean limited) {
        animacion.setLooping(limited);
    }

    boolean isStopped() {
        return animacion.isStopped(); //To change body of generated methods, choose Tools | Templates.
    }

    void restart() {
        animacion.restart();
    }
    
}
