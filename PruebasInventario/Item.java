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
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
public class Item {

    public static int daño, nivel;
    public static int  maxItems = 2;
    public static String nombre;

    public static Image noitem;
    public static Image[] img = new Image[2]; 
    public static String[][] nombres = new String[maxItems][1];

    public Item(String nombre, int daño, int nivel){
        this.nombre = nombre;
        this.daño = daño;
        this.nivel = nivel;
    }

    public static void init(){
        try {
            noitem = new Image("res/items/noitem.png");
        } catch (SlickException e1) {
            e1.printStackTrace();
        }

        for(int i = 0; i < maxItems; i++){
            try {
                img[i] = new Image("res/items/" + i + ".png");
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
    }

    public static Image getImg(int itemid){
        if(itemid != -1){
            return img[itemid];
        }else{
            return noitem;
        }
    }

    public String getNombre(int itemid){
        return nombres[itemid][0];
    }

    public String getNivel(int itemid){
        return nombres[itemid][1];
    }
}
