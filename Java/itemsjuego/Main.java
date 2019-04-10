/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemsjuego;

/**
 *
 * @author ceals
 */
public class Main {
    public static void main (String[] args){
        PocionesCurar pocion_20hp=new PocionesCurar(20);
        System.out.println(pocion_20hp.toString());
        PocionesCurar pocion_50hp=new PocionesCurar(50);
        System.out.println(pocion_50hp.toString());
        PocionesCurar pocion_120hp=new PocionesCurar(120);
        System.out.println(pocion_120hp.toString());
        PocionesMana pocion_10pm=new PocionesMana(10);
        System.out.println(pocion_10pm.toString());
        PocionesMana pocion_20pm=new PocionesMana(20);
        System.out.println(pocion_20pm.toString());
        PocionesMana pocion_40pm=new PocionesMana(40);
        System.out.println(pocion_40pm.toString());
        EstadosAlterado estado_paralisis=new EstadosAlterado("paralisis");
        System.out.println(estado_paralisis.toString());
        EstadosAlterado estado_envenenado=new EstadosAlterado("envenenado");
        System.out.println(estado_envenenado.toString());
        EstadosAlterado estado_petrificado=new EstadosAlterado("petrificado");
        System.out.println(estado_petrificado.toString());
        EstadosAlterado estado_quemado=new EstadosAlterado("quemado");
        System.out.println(estado_quemado.toString());
        EstadosAlterado estado_confundido=new EstadosAlterado("confundido");
        System.out.println(estado_confundido.toString());       
    }
}
