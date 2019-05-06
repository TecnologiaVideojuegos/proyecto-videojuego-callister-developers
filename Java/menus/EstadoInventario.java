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
public class EstadoInventario extends BasicGameState{
    private Lucia lucia;
    private Inventario inventario;
    private Image fondo;
    private Punto p;
    private int indicador;
    @Override
    
    public int getID() {
        return 3;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
        lucia=Lucia.getLucia();
        inventario=lucia.getIntven();
        fondo=new Image("resources/Menus/fondo.jpg");
        p=new Punto(100, 100);
        
        
    }
    

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        fondo.draw();
        for (int i=0;i<inventario.items.size();i++){
            inventario.getItems().get(i).render((int)p.getX(), (int)p.getY(), grphcs);           
            inventario.getItems().get(i).getImagen().draw((int)p.getX()-5, (int)p.getY());
            p.setX(p.getX()+40);
            p.setY(p.getY()+40);           
        }
        
        
        
    }
    

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        cEntrada(gc.getInput(), sbg);
    }
    
    
    public void cEntrada(Input entrada, StateBasedGame game){
        if (entrada.isKeyPressed(Input.KEY_S)){
            if (indicador!=inventario.items.size()){
                p.setY(p.getY()+40);
                indicador++;
            }else{
                p.setY(p.getY()-40*inventario.items.size());
                indicador=0;
            }
              
        }
        if (entrada.isKeyPressed(Input.KEY_W)){ 
           if(indicador!=0){
                p.setY(p.getY()-40);
                indicador--;
            }else{
                p.setY(p.getY()+40*inventario.items.size());
                indicador=3;
            }
        }
        if(entrada.isKeyPressed(Input.KEY_M)){
            
            game.enterState(0);
            
        }
        if(entrada.isKeyPressed(Input.KEY_SPACE)){

            
            
            
        }
    }
    
    private void rendItems(int a, Graphics g){
        g.drawString(inventario.getItems().get(indicador).getDescripcion(),getX(),getY());
    }
    
}
