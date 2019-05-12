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

public class HealthBar extends ProgressBar {

    private static final Color[] healthColors = new Color[] {
            new Color(.8f, .0f, .0f), new Color(.7f, .6f, .1f), new Color(.2f, .9f, .1f)
    };

    private final EntidadCombate owner;

    public HealthBar(EntidadCombate owner) {
        super(64, 8);
        this.owner = owner;
    }

    public float getProgress() {
        return ((float) owner.getMultiplicadores()[0]) / owner.getEst()[0];
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
            super.render(slickContainer, g, owner.getPosCombate().getX()+32, owner.getPosCombate().getY()+64);
        //}
    }
}