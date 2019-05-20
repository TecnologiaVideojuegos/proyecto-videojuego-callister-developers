/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author victo
 */
public class Sprite {
    private SpriteSheet sprite;

    public Sprite(String ruta, int w, int h) throws SlickException {
        sprite=new SpriteSheet(ruta, w, h);
    }

    public Image getSprite(int i, int i0) {
        return sprite.getSprite(i, i);
    }
}
