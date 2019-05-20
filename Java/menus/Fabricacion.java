package menus;

import Combate.Agua;
import Combate.Fuego;
import Combate.Luz;
import Combate.Oscuro;
import Combate.Planta;
import Combate.Rayo;
import Combate.Terra;
import itemsjuego.*;
import chaoschild.Punto;
import entidades.Lucia;
import itemsjuego.Gema;
import itemsjuego.GemaPlanta2;
import itemsjuego.Inventario;
import itemsjuego.Objeto;
import java.util.ArrayList;
import static org.lwjgl.opengl.Display.getX;
import static org.lwjgl.opengl.Display.getY;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author victo
 */
public class Fabricacion extends BasicGameState {

    private Lucia lucia;
    private Inventario inventario;
    private Image fondo, puntero;
    private Punto p, paux, paux2, paux3, da, centro;
    private int indicador, indicadoraux;
    private int seleccionado, estado;
    private ArrayList<Objeto> gemas;
    private Punto[] caster; 
    private Objeto resultado;

    @Override

    public int getID() {
        return 5;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        lucia = Lucia.getLucia();
        inventario = lucia.getIntven();
        fondo = new Image("resources/Menus/FabricacionPag.png");
        p = new Punto(10, (float) (9 + 32 / 2 - 9.5));
        paux = new Punto(20, 10);
        puntero = new Image("resources/Menus/PunteroMenu.png");
        indicador = 0;
        indicadoraux=0;
        paux2 = new Punto(350, 20);
        seleccionado = 0;
        paux3 = new Punto(10, (float) (9 + 32 / 2 - 9.5));
        da=new Punto(550, 10);
        centro =new Punto(475, 330);
        caster=new Punto[4];
        caster[0]=new Punto(centro.getX()+80, centro.getY());
        caster[1]=new Punto(centro.getX(), centro.getY()+80);
        caster[2]=new Punto(centro.getX()-80, centro.getY());
        caster[3]=new Punto(centro.getX(), centro.getY()-80);
        gemas=new ArrayList();

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        fondo.draw();
        puntero.draw(p.getX(), p.getY());
        inventario.render(paux, grphcs);
        renderGemas();
        if(combrobarGemas()){
            seleccionarElemento(((Gema)gemas.get(0)).getElemento().toString());
            resultado.getImagen().draw(centro.getX(), centro.getY(), (float) 1.5);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        cEntrada(gc.getInput(), sbg);
    }

    public void cEntrada(Input entrada, StateBasedGame game) throws SlickException {
        if (entrada.isKeyPressed(Input.KEY_S)) {
            switch (estado) {
                case 0:
                    if (indicador != inventario.items.size() - 1) {
                        p.setY(p.getY() + 31);
                        indicador++;
                    } else {
                        p.setY(p.getY() - 31 * (inventario.items.size() - 1));
                        indicador = 0;
                    }
                    break;
                case 1:
                    
                    if (indicador != lucia.getEquipo().size() - 1) {
                        p.setY(p.getY() + 21);
                        indicador++;
                    } else {
                        p.setY(p.getY() - 21 * (lucia.getEquipo().size() - 1));
                        indicador = 0;
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
                        p.setY(p.getY() - 31);
                        indicador--;
                    } else {
                        p.setY(p.getY() + 31 * (inventario.size() - 1));
                        indicador = inventario.size() - 1;
                    }
                    break;
                case 1:
                    
                    if (indicador != 0) {
                        p.setY(p.getY() - 21);
                        indicador--;
                    } else {
                        p.setY(p.getY() + 21 * (lucia.getEquipo().size() - 1));
                        indicador = lucia.getEquipo().size() - 1;
                    }
                    break;
                    
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
                    if(!combrobarGemas()){
                        if(gemas.size()<4){
                            if(inventario.get(indicador).isGem()){
                                gemas.add(inventario.remove(indicador));
                                seleccionado = indicador;
                            }
                        }
                    }else{
                        inventario.add(resultado);
                        gemas.clear();
                    }
                        
//                    break;
//                case 1:
//                    if(inventario.get(seleccionado).isGem()){
//                        estado=2;
//                        p=new Punto(da.getX()-20, da.getY()+20);
//                        indicadoraux=indicador;
//                        indicador=0;
//                    }else{
//                        inventario.remove(seleccionado).usar(lucia.getEquipo().get(indicador));
//                        indicador = 0;
//                        p = new Punto(paux3.getX(), paux3.getY());
//                        seleccionado = 0;
//                        estado = 0;
//                    }
//                    break;
                
            }
        }
        if (entrada.isKeyPressed(Input.KEY_B)) {
            try {
                inventario.add(gemas.get(gemas.size()-1));
                gemas.remove(gemas.size()-1);
            } catch (Exception e) {
            }
//            switch(estado){
//                case 0:
//                    estado=0;
//                    indicador = 0;
//                    p = new Punto(paux3.getX(), paux3.getY());
//                    seleccionado = 0;
//
//                    game.enterState(1);
//                    break;
//                case 1:
//                    estado=0;
//                    indicador = 0;
//                    p = new Punto(paux3.getX(), paux3.getY());
//                    seleccionado = 0;;
//                    break;
//            }
            
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
    public void renderGemas(){
        if(!gemas.isEmpty()){
            for(int i=0;i<gemas.size();i++){
                gemas.get(i).getImagen().draw((int)caster[i].getX(), (int) caster[i].getY(), (float) 1.5);
            }
        }
    }
    public boolean combrobarGemas(){
        boolean iguales=false;
        String aux;
        int contador=0;
        for(int i=0;i<gemas.size();i++){
            contador++;
            aux=gemas.get(i).toString();
            if(gemas.get(0).toString().compareTo(aux)==0){
                iguales=true;
            }else iguales=false;
            
        }
        if(contador!=4){
                iguales=false;
        }
        return iguales;
    }
    private void seleccionarElemento(String a) throws SlickException{
        switch (a){
            case "Planta":resultado=new GemaPlanta2();
                break;
            case "Agua":resultado=new GemaAgua2();
                break;
            case "Fuego":resultado=new GemaFuego2();
                break;
            case "Rayo":resultado=new GemaRayo2();
                break;
            case "Tierra":resultado=new GemaTierra2();
                break;
            case "Luz":resultado=new GemaLuz2();
                break;
            case "Oscuro":resultado=new GemaOscura2();
                break;
          
        }
    }

}
