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
    
    public Zona(Dungeon dungeon, Pueblo pueblo, Ruta ruta){
        zona = new ArrayList();
        zona.add(dungeon);
        zona.add(pueblo);
        zona.add(ruta);
    }
    
    public Zona(){
        zona = new ArrayList();
    }
    
    public void addDungeon(Dungeon d){
        zona.add(d);
    }
    
    public void addPueblo(Pueblo p){
        zona.add(p);
    }
    
    public void addRuta(Ruta r){
        zona.add(r);
    }
    
    public void sort(){
        ArrayList<Dungeon> dng = new ArrayList();
        ArrayList<Pueblo> pueblos = new ArrayList();
        ArrayList<Ruta> rutas = new ArrayList();
        
        for(int i = 0;i < zona.size();i++){
            switch (zona.get(i).getClass().getName()) {
                case "mapas.Dungeon":
                    dng.add((Dungeon) zona.get(i));
                    break;
                case "mapas.Pueblo":
                    pueblos.add((Pueblo) zona.get(i));
                    break;
                case "mapas.Ruta":
                    rutas.add((Ruta) zona.get(i));
                    break;
            }
        }
        
        for(int j = 0;j < dng.size() - 1;j++){
            for(int i = 0;i < dng.size() - 1;i++){
                if(dng.get(i).getNombre().compareTo(dng.get(i + 1).getNombre()) > 0){
                    Dungeon aux = dng.get(i);
                    dng.set(i, dng.get(i + 1));
                    dng.set(i + 1, aux);
                }
            }
        }
        for(int j = 0;j < pueblos.size() - 1;j++){
            for(int i = 0;i < pueblos.size() - 1;i++){
                if(pueblos.get(i).getNombre().compareTo(pueblos.get(i + 1).getNombre()) > 0){
                    Pueblo aux = pueblos.get(i);
                    pueblos.set(i, pueblos.get(i + 1));
                    pueblos.set(i + 1, aux);
                }
            }
        }
        
        for(int j = 0;j < rutas.size() - 1;j++){
            for(int i = 0;i < rutas.size() - 1;i++){
                if(rutas.get(i).getNombre().compareTo(rutas.get(i + 1).getNombre()) > 0){
                    Ruta aux = rutas.get(i);
                    rutas.set(i, rutas.get(i + 1));
                    rutas.set(i + 1, aux);
                }
            }
        }
        
        for(int i = 0;i < zona.size();i++){
            if(i < dng.size()){
                zona.set(i, dng.get(i));
            } else if(i >= dng.size() && i < pueblos.size() + dng.size()){
                zona.set(i, pueblos.get(i - dng.size()));
            } else {
                zona.set(i, rutas.get(i - dng.size() - pueblos.size()));
            }
        }
    }

    public ArrayList<Mapa> getZona() {
        return zona;
    }

    public void toStringAll() {
        for(int i = 0;i < zona.size();i++){
            System.out.print(zona.get(i).getNombre() + " - ");
        }
        System.out.println("");
    }
}
