/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.enemigos;

import Combate.Agua;
import Combate.MagiaAgua1;
import Combate.MagiaTierra1;
import Combate.Planta;
import chaoschild.Punto;
import org.newdawn.slick.SlickException;

/**
 *
 * @author victo
 */
public class Slime extends Enemigo{
    
   

    public Slime(String ruta, String rutaC, String nombre) throws SlickException {
        super(ruta, rutaC, 54, 54, 4, 3, new int[]{4, 3,3}, nombre, 54, 54);
        animaciones(new int[]{4,4,4,4}, 250,new boolean[]{true, true, true, true});
        estadisticasb(new int[]{200, 50, 100,0, 0, 0, 0, 0,1,200, 5, 10, 15, 0, 20, 10, 9});
        setExpg(200);
        setPropobj(100);
        setAnimbaseco(1);
        setAnimest(0);
        setAnimmag(1);
        setAnimdañar(2);
    }
    
    
}
