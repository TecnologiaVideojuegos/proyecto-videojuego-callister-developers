/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import itemsjuego.Inventario;
import chaoschild.Punto;
import entidades.Lucia;
import itemsjuego.Inventario;
import java.util.ArrayList;
import static org.lwjgl.opengl.Display.getX;
import static org.lwjgl.opengl.Display.getY;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author victo
 */
public class EstadoInventario extends BasicGameState {

    private Lucia lucia;
    private Inventario inventario;
    private Image fondo, puntero;
    private Punto p, paux, paux2, paux3;
    private int indicador, indicadoraux;
    private int seleccionado, estado;

    @Override

    public int getID() {
        return 3;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        lucia = Lucia.getLucia();
        inventario = lucia.getIntven();
        fondo = new Image("resources/Menus/fondo.jpg");
        p = new Punto(31, (float) (60 + 32 / 2 - 9.5));
        paux = new Punto(50, 60);
        puntero = new Image("resources/Menus/PunteroMenu.png");
        indicador = 0;
        indicadoraux=0;
        paux2 = new Punto(400, 50);
        seleccionado = 0;
        paux3 = new Punto(31, (float) (60 + 32 / 2 - 9.5));

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        fondo.draw();
        puntero.draw(p.getX(), p.getY());
        inventario.render(paux, grphcs);
        rendMenus(lucia.getEquipoStrng(), grphcs);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        cEntrada(gc.getInput(), sbg);
    }

    public void cEntrada(Input entrada, StateBasedGame game) {
        if (entrada.isKeyPressed(Input.KEY_S)) {
            switch (estado) {
                case 0:
                    if (indicador != inventario.items.size() - 1) {
                        p.setY(p.getY() + 33);
                        indicador++;
                    } else {
                        p.setY(p.getY() - 33 * (inventario.items.size() - 1));
                        indicador = 0;
                    }
                    break;
                case 1:
                    
                    if (indicadoraux != lucia.getEquipo().size() - 1) {
                        paux2.setY(paux2.getY() - 21);
                        indicadoraux++;
                    } else {
                        paux2.setY(paux2.getY() + 21 * (lucia.getEquipo().size() - 1));
                        indicadoraux = 0;
                    }

                    break;
            }
            //si estado 0, p, si estado 1 paux2

        }
        if (entrada.isKeyPressed(Input.KEY_W)) {
            //si estado 0, p, si estado 1 paux2
            switch (estado) {
                case 0:
                    if (indicador != 0) {
                        p.setY(p.getY() - 33);
                        indicador--;
                    } else {
                        p.setY(p.getY() + 33 * (inventario.size() - 1));
                        indicador = inventario.size() - 1;
                    }
                    break;
                case 1:
                    
                    if (indicadoraux != 0) {
                        paux2.setY(paux2.getY() + 21);
                        indicadoraux--;
                    } else {
                        paux2.setY(paux2.getY() - 21 * (lucia.getEquipo().size() - 1));
                        indicadoraux = lucia.getEquipo().size() - 1;
                    }
//                    } else {
//                        
//                    }
            }
        }
        if (entrada.isKeyPressed(Input.KEY_M)) {
            estado=0;
            indicador = 0;
            p = new Punto(paux3.getX(), paux3.getY());
            seleccionado = 0;
            
            game.enterState(0);

        }
        if (entrada.isKeyPressed(Input.KEY_SPACE)) {
            switch (estado) {
                case 0:
                    if (!inventario.get(indicador).isGem()) {
                        seleccionado = indicador;
                        indicador = 0;
                        p = new Punto(paux2.getX(), paux2.getY());
                        estado = 1;
                    }
                    break;
                case 1:
                    inventario.remove(seleccionado).usar(lucia.getEquipo().get(indicador));
                    indicador = 0;
                    p = new Punto(31, (float) (60 + 32 / 2 - 9.5));
                    seleccionado = 0;
                    estado = 0;
                    break;

            }

        }
    }

    private void rendItems(int a, Graphics g) {
        g.drawString(inventario.getItems().get(indicador).getDescripcion(), getX(), getY());
    }

    public void rendMenus(ArrayList<String> menu, Graphics g) {

        for (int i = 0; i < menu.size(); i++) {
            g.drawString(menu.get(i), paux2.getX() + 20, paux2.getY() + i * 20);

        }

    }

}
