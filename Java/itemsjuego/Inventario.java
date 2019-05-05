/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemsjuego;

import java.util.ArrayList;

/**
 *
 * @author victo
 */
public class Inventario {
    private ArrayList<Objeto> items;
    private ArrayList<Integer> numi;

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

   
    
    
    
}
