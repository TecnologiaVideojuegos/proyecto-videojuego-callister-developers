package Guardado;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import mapas.ConjuntoEnemigos;
import mapas.Mapa;
import mapas.Mundo;
/**
 *
 * @author Carlos
 */
public class Guardar {
    
    public Guardar(){
    }
    /*
    public void guardarEnemigos(Mundo mundo){
        String ruta = "saves/bosses.txt";
        HashMap<String, ArrayList<ConjuntoEnemigos>> bosses = new HashMap();
        ArrayList<Mapa> mapas = mundo.getMapas();
        for(Mapa mapa : mapas){
            switch(mapa.getNombre()){
                case "Cueva_inicio": 
                    ArrayList<ConjuntoEnemigos> jefes1 = getBosses(mapa.getConjuntoEnemigos());
                    bosses.put("Cueva_inicio", jefes1);
                    break;
                case "Ruta_Antes_Dungeon_Desierto": 
                    ArrayList<ConjuntoEnemigos> jefes2 = getBosses(mapa.getConjuntoEnemigos());
                    bosses.put("Ruta_Antes_Dungeon_Desierto", jefes2);
                    break;
                case "": 
                    break;
                case "": 
                    break;
                case "": 
                    break;
            }
        }
    }
    */
    private ArrayList<ConjuntoEnemigos> getBosses(ArrayList<ConjuntoEnemigos> enemigos){
        ArrayList<ConjuntoEnemigos> bosses = new ArrayList();
        
        for(ConjuntoEnemigos enemigo : enemigos){
            if(enemigo.getEnemigos().get(0).getClass().getName().equals("entidades.enemigos.boss")){
                bosses.add(enemigo);
            }
        }
        
        return bosses;
    }
    
    public void leerEnemigos(Mundo mundo){
        
    }
    
    public void guardarMapa(int[] coordenadas){
        String ruta = "saves/mapa.txt";
        
        String coordUTF = "";
        int[] coord = coordenadas;
        
        coordUTF += coord[0] + "," + coord[1];
        try {
            FileOutputStream out = new FileOutputStream(ruta);
            ObjectOutputStream escribir = new ObjectOutputStream(out);
            System.out.println(coordUTF);
            escribir.writeObject(coordUTF);
            System.out.println("Guardado");
        } catch (IOException ex) {
            System.out.println("Fallo\n" + ex.toString());
        }
    }
    
    public int[] leerMapa(){
        String ruta = "saves/mapa.txt";
        int[] coord = new int[2];
        String coordUTF = "";
        
        try{
            FileInputStream fis = new FileInputStream(ruta);
            ObjectInputStream leer = new ObjectInputStream(fis);
            
            coordUTF = (String) leer.readObject();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } catch (ClassNotFoundException ex) {}
        
        try{
            coord[0] = Integer.parseInt(Character.toString(coordUTF.charAt(0)));
            if(coordUTF.charAt(2) == '-'){
                coord[1] = Integer.parseInt(Character.toString(coordUTF.charAt(3))) * -1;
            } else {
                coord[1] = Integer.parseInt(Character.toString(coordUTF.charAt(2)));
            }
        } catch(NumberFormatException e){
            coord[0] = -1;
            coord[1] = -1;
        }
        
        return coord;
    }
}
