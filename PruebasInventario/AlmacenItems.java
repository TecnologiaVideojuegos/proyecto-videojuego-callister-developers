/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

/**
 *
 * @author ceals
 */
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class AlmacenItems {

    Image posicion;
    public int x, y, id, item;

    public AlmacenItems(int x, int y, int id, int item) {
        try {
            posicion = new Image("res/inv/posicion.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        this.x = x;
        this.y = y;
        this.id = id;
        this.item = item;
    }

    public void render(Graphics g) {
        // Crea el posicion y le pone imagen asociada
        g.drawImage(posicion, x, y);
        g.drawImage(Item.getImg(item), x, y);
        //System.out.println("ID:" + id + " item: " + item);
    }

    public void update(){
        
    }

    public int getID(){
        return id;
    }

    public int getItem(){
        return item;
    }

    public void setItem(int iid) {
        item = iid;
    }

    

}
