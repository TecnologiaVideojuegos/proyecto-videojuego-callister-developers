/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Combate;

import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public abstract class Elemento {
    private String nombre;
    private ArrayList<String> efuerte,edeviles;
    private Image tipo;

    public Elemento(String [] f, String[] d, String ruta, String n) throws SlickException {
        tipo=new Image(ruta);
        nombre=n;
        efuerte=new ArrayList();
        edeviles=new ArrayList();
        setEfuerte(f);
        setEdevil(d);
    }

    private void setEfuerte(String[] a){
        for(int i=0;i<a.length;i++){
            efuerte.add(a[i]);
        }
    }
    private void setEdevil(String[] a){
        for(int i=0;i<a.length;i++){
            edeviles.add(a[i]);
        }
    }
    
    public float mult(Elemento e){
        float m=1;
        int a=0;
        if(efuerte.size()<edeviles.size()){
            a=edeviles.size();
        }else a=efuerte.size();
        String f="f";
        String d="d";
        for(int i=0;i<a;i++){
            try{
                if(efuerte.get(i)==e.toString()){
                    m=(float) 2;
                }
            }catch(Exception ex){}
            try{
                if(edeviles.get(i)==e.toString()){
                    m=(float) 0.5;
                }
            }catch(Exception ex){}
        }
        return m;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    
}


