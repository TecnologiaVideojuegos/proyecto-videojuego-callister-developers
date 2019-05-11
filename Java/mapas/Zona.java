/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapas;

import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class Zona {
    
    private ArrayList<Mapa> zona; 
    private ArrayList<Puerta> puertas;
    
    public Zona(Dungeon dungeon, Pueblo pueblo, Ruta ruta){
        zona = new ArrayList();
        puertas = new ArrayList();
        zona.add(dungeon);
        zona.add(pueblo);
        zona.add(ruta);
    }
    
    public Zona(){
        zona = new ArrayList();
    }
    
    public void addDungeon(Dungeon d){
        try{
            if(!"mapas.Dungeon".equals(this.zona.get(0).getClass().getName()) && this.zona.size() > 0){
                zona.add(d);
            }
        }catch(IndexOutOfBoundsException e){
            zona.add(d);
        }
    }
    
    public void addPueblo(Pueblo p){
        try{
            if(!"mapas.Pueblo".equals(this.zona.get(1).getClass().getName()) && this.zona.size() > 1){
                zona.add(p);
            }
        }catch(IndexOutOfBoundsException e){
            zona.add(p);
        }
    }
    
    public void addRuta(Ruta r){
        zona.add(r);
    }
    
    public void sort(){
        for(int i = 0;i < zona.size() - 1;i++){
            if(zona.get(i).getClass().getName().compareTo(zona.get(i + 1).getClass().getName()) > 0){
                Mapa aux = zona.get(i);
                this.zona.set(i, this.zona.get(i + 1));
                this.zona.set(i + 1, aux);
            }
        }
    }

    public ArrayList<Mapa> getZona() {
        return zona;
    }

    public void toStringAll() {
        System.out.println("Dungeon: " + zona.get(0).getClass().getName() + 
                " Pueblo: " + zona.get(1).getClass().getName() + 
                " Ruta: " + zona.get(2).getClass().getName());
    }
}
