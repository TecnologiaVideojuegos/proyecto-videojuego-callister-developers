/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Inventario {

    public static int x, y;
    public static int numItem;
    static Image imagen;

    public static ArrayList<AlmacenItems> almacenitems = new ArrayList<AlmacenItems>();

    public Inventario(int numItem){
        this.numItem=numItem;

        try {
            imagen = new Image("res/inv/imagen.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        x = (1080/2) - imagen.getWidth();
        y = 20;
    }

    public static void init(){
        // Inicializa todas las posiciones/slots
        int k = 0;
        for(int i = 0; i < (numItem / 10); i++){
            for(int j = 0; j < 10; j++){
                almacenitems.add(new AlmacenItems((i * 32), (j * 32), k, 1));
                System.out.println((i * 32) + " / " + (j * 32) + " / " + k);
                k++;
            }
        }

        Item.init();
    }

    public static void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
        g.drawImage(imagen, x, y);
        for(AlmacenItems s : almacenitems){
            s.render(g);
            //System.out.println("ID: " + s.getID());
        }


    }

    public void update(Graphics g){
        for(AlmacenItems s : almacenitems){
            s.update();
        }
    }

    public static void addItem(int id){
        for(AlmacenItems s : almacenitems){
            if(s.getItem() == -1){
                s.setItem(id);
            }
        }
    }
}
