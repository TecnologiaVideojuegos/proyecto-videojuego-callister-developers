/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Codigo obtenido de github https://github.com/MobbyGFX96/Slick2D/tree/master/Slick2D/src/net/solace/gui

package Combate;

import chaoschild.Punto;
import entidades.EntidadCombate;
import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

public class ManaBar extends ProgressBar {

    private static final Color[] healthColors = new Color[] {
            new Color(Color.cyan), new Color(Color.cyan), new Color(Color.cyan)
    };

    private final EntidadCombate owner;
    private Font font;
    private  TrueTypeFont ttf;

    public ManaBar(EntidadCombate owner) {
        super(64, 11);
        this.owner = owner;
        font=new Font("Arial", Font.BOLD, 10);
        ttf=new TrueTypeFont(font, true);
    }

    public float getProgress() {
        return ((float) owner.getMultiplicadores()[1]) / owner.getEst()[1];
    }

    protected Color getProgressColor(float progress) {
        if (progress < .3f) {
            return healthColors[0];
        } else if (progress < .6f) {
            return healthColors[1];
        }
        return healthColors[2];
    }

    public void update(GameContainer slickContainer, int deltaMS) {

    }

    public void render(GameContainer slickContainer, Graphics g) {
        //if (owner != null && owner.getMultiplicadores()[0] < owner.getEst()[0]) {
            super.render(slickContainer, g, owner.getPosCombate().getX()+32, owner.getPosCombate().getY()+64+11);
            ttf.drawString(owner.getPosCombate().getX()+10, owner.getPosCombate().getY()+63+12, owner.getMultiplicadores()[1]+"/"+owner.getEst()[1], Color.black);
        //}
    }
    public void render(GameContainer slickContainer, Graphics g, Punto p) {
        //if (owner != null && owner.getMultiplicadores()[0] < owner.getEst()[0]) {
        Color c=Color.black;
        if(getProgress()<0.50) c=Color.gray;
            super.render(slickContainer, g, p.getX()+32, p.getY()+11);
            ttf.drawString(p.getX(), p.getY()+10, owner.getMultiplicadores()[1]+"/"+owner.getEst()[1], c);
        //}
    }
}