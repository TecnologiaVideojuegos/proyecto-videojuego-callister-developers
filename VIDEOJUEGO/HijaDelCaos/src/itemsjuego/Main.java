/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemsjuego;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author ceals
 */
public class Main {
    public static void main (String[] args) throws SlickException{
        PocionesCurar pocion_20hp=new PocionVPequeña();
        System.out.println(pocion_20hp.toString());
        PocionesCurar pocion_40hp=new PocionVMediana();
        System.out.println(pocion_40hp.toString());
        PocionesCurar pocion_60hp=new PocionVGrande();
        System.out.println(pocion_60hp.toString());
        PocionesMana pocion_20pm=new PocionMPequeña();
        System.out.println(pocion_20pm.toString());
        PocionesMana pocion_40pm=new PocionMMediana();
        System.out.println(pocion_40pm.toString());
        PocionesMana pocion_60pm=new PocionMGrande();
        System.out.println(pocion_40pm.toString());
        EstadosAlterado estado_paralisis=new EstadosAlterado("paralisis",new Image("resources/Menus/PunteroMenu.png"));
        System.out.println(estado_paralisis.toString());
        EstadosAlterado estado_envenenado=new EstadosAlterado("envenenado",new Image("resources/Menus/PunteroMenu.png"));
        System.out.println(estado_envenenado.toString());
        EstadosAlterado estado_petrificado=new EstadosAlterado("petrificado",new Image("resources/Menus/PunteroMenu.png"));
        System.out.println(estado_petrificado.toString());
        EstadosAlterado estado_quemado=new EstadosAlterado("quemado",new Image("resources/Menus/PunteroMenu.png"));
        System.out.println(estado_quemado.toString());
        EstadosAlterado estado_confundido=new EstadosAlterado("confundido",new Image("resources/Menus/PunteroMenu.png"));
        System.out.println(estado_confundido.toString());       
    }
}
