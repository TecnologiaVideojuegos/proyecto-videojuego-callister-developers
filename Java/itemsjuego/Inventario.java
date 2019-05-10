/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemsjuego;

import chaoschild.Punto;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;

/**
 *
 * @author victo
 */
public class Inventario {
    public ArrayList<Objeto> items;
    public ArrayList<Integer> numi;
    

    public Inventario() {
        this.items = new ArrayList();
        this.numi = new ArrayList();
    }
    
    public void add(Objeto a){
        boolean esta=false;
        int indicador=-1;
        for (int i=0;i<items.size();i++){
            if(items.get(i).toString()==a.toString()){
                 esta=true;
                 indicador=i;
            }
        }
        if(esta){
            numi.set(indicador, numi.get(indicador)+1);
        }else{
            items.add(a);
            numi.add(1);
        }
    }

    public ArrayList<Objeto> getItems() {
        return items;
    }

    public ArrayList<Integer> getNumi() {
        return numi;
    }

    public void setItems(ArrayList<Objeto> items) {
        this.items = items;
    }

    public void setNumi(ArrayList<Integer> numi) {
        this.numi = numi;
    }

   
    public void render(Punto p, Graphics g){
        for (int i=0;i<items.size();i++){
            g.drawString(items.get(i).toString()+"   x"+numi.get(i).toString(),(int)p.getX()+32, (int)p.getY()+i*33+32/2-10);
            items.get(i).renderImagen(p, i);
        }

    }
    
    public ArrayList<String> getInvString(){
        ArrayList<String> a=new ArrayList();
        for (int i=0;i<items.size();i++){
            a.add(items.get(i).toString()+"  x"+numi.get(i).toString());
        }
        return a;
    }
    
    public Objeto get(int i){
        return items.get(i);
    }
    
    public int size(){
        return items.size();
    }
    
    public Objeto remove(int i){
        Objeto a=items.get(i);
        if(numi.get(i)<=1){
            a=items.remove(i);
            numi.remove(i);
        }else{
            numi.set(i, numi.get(i)-1);
        }
        return a;
    }
    
    
}
