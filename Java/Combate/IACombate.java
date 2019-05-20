/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Combate;

import entidades.Aliado;
import entidades.EntidadCombate;
import entidades.Lucia;
import entidades.enemigos.Enemigo;
import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class IACombate {
    
    private ArrayList<Enemigo> atacante;
    private ArrayList<Aliado> enemigos;
    private ArrayList<Accion> acciones;
    private EntidadCombate defensor;
    private int acion, indice;

    public IACombate(ArrayList<Enemigo> atacante) throws SlickException {
        this.atacante = atacante;
        enemigos=Lucia.getLucia().getEquipo();
        acciones=new ArrayList();
        acion=0;
        
    }

    
    private void accion(Enemigo e){
        Random m;
        m=new Random();
        int r=m.nextInt(101);

        if(e.getPropmagia()>r){
            elegirMagia(e);
        }

    }
    
    
    private void elegirMagia(Enemigo e){
        int a;
        if(!e.getMagias().isEmpty()){
            a=(new Random()).nextInt(e.getMagias().size()); 
            if(e.getMagias().get(a).getCoste()<e.getMultiplicadores()[1]){
                acion=1;
                indice=a;
            }
        }
    }
    
    
    
    private EntidadCombate selecionarObjetivo(Enemigo e){
        EntidadCombate c=enemigos.get((new Random()).nextInt(enemigos.size()));;
        if(acion==1){
            try{
            if(e.getMagias().get(indice).getTipo()==1){
                c=atacante.get((new Random()).nextInt(atacante.size()));
            }
            }catch(Exception ex){}
            
        }
        return c;
    }
    
    private void genTurnos() throws SlickException{
        for(int i=0;i<atacante.size();i++){
            accion(atacante.get(i));
            acciones.add(new Accion(acion, indice, atacante.get(i), selecionarObjetivo(atacante.get(i))));
            acion=0;
            
        }
    }

    public ArrayList<Accion> getAcciones() throws SlickException {
        genTurnos();
        ArrayList<Accion> aux=new ArrayList();
        int a=acciones.size();
                
        for(int i=0;i<a;i++){
            aux.add(acciones.remove(0));
        }
        return aux;
    }

    
        
    

    void setEnemigos(ArrayList<Enemigo> enemigos) {
        this.atacante = enemigos;
    }
    
    public void setOmjetivos(ArrayList<Aliado> aliados){
        enemigos=aliados;
    }

    
    
    
    
}
