/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemsjuego;

import chaoschild.Punto;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class Inventario implements Externalizable{
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

    @Override
    public void writeExternal(ObjectOutput oo) throws IOException {
        oo.writeObject(numi);
        for(int i=0;i<items.size();i++){
            oo.writeUTF(items.get(i).getClase());
            items.get(i).writeExternal(oo);
        }
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
        numi=new ArrayList();
        numi=(ArrayList<Integer>) oi.readObject();
        items=new ArrayList();
       
        System.out.println("------------------------------------Cargando Objetos----------------------------------");
        for(int i=0;i<numi.size();i++){
             String caso=oi.readUTF();
             System.out.println(caso);
            switch(caso){
                case "Gema":
                    items.add(new Gema());
                    
                    break;
                case "PocionesCurar": 
                    items.add(new PocionesCurar());
                    break;
                case "PocionesMana":
                    items.add(new PocionesMana());
                    break;           
            }
            System.out.println("--------------------------------Cargando Objeto "+i+"-------------------------------");
            items.get(i).readExternal(oi);
            items.get(i).setClase(caso);
            System.out.println("---------------------------------Objeto "+items.get(i).toString()+" Cargado--------------------------------");
        }
    }
    
    
}
