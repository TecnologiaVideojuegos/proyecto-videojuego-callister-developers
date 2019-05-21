/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musica;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class GestorMusica {
    private static GestorMusica Musica;
    private Music sonando;
    private Music ruta;
    private Music combate;

    public GestorMusica() throws SlickException {
        
        ruta=new Music("resources/backround2.ogg");
        combate = new Music("resources/sonido/combate/combate.ogg");
        sonando=ruta;
        
    }
    
    public void play(){
        sonando.play();
    }
    
    public void resume(){
        sonando.resume();
    }
    
    public void pause(){
        sonando.pause();
    }
    
    public boolean playing(){
        return sonando.playing();
    }
    
    public void cambiarM(int a){
        switch (a){
            case 0:sonando=ruta;
            break;
            case 1: sonando=combate;
            break;
        }
    }
    
    public static GestorMusica getGestor() throws SlickException{
        if(Musica==null){
            Musica=new GestorMusica();
        }
        return Musica;
    }
}
