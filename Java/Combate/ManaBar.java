/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Codigo obtenido de github https://github.com/MobbyGFX96/Slick2D/tree/master/Slick2D/src/net/solace/gui

package Combate;

import entidades.EntidadCombate;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class ManaBar extends ProgressBar {

    private static final Color[] healthColors = new Color[] {
            new Color(Color.cyan), new Color(Color.cyan), new Color(Color.cyan)
    };

    private final EntidadCombate owner;

    public ManaBar(EntidadCombate owner) {
        super(64, 8);
        this.owner = owner;
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
            super.render(slickContainer, g, owner.getPosCombate().getX()+32, owner.getPosCombate().getY()+64+9);
        //}
    }
}