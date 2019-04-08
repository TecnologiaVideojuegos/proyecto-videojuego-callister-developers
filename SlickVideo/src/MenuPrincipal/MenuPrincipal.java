/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuPrincipal;

import org.newdawn.slick.*;

import slick.video.Punto;



/**
 *
 * @author ceals
 */
public class MenuPrincipal{
    private Image fondo;
    private Image botonInventario, botonEquipo, botonFabricacion, botonSalir, punteroInventario, punteroEquipo;
    private int indicador;
    private Punto p;
    private Punto renderMenu;

    public MenuPrincipal() throws SlickException {
        fondo=new Image("src/MenuPrincipal/res/fondo.png");
        botonInventario=new Image("src/MenuPrincipal/res/inventario.png");
        botonEquipo=new Image("src/MenuPrincipal/res/Equipo.png");
        botonFabricacion=new Image("src/MenuPrincipal/res/Fabricacion.png");
        botonSalir=new Image("src/MenuPrincipal/res/Salir.png");
        punteroInventario=new Image("src/MenuPrincipal/res/PunteroMenu.png");
        punteroEquipo=new Image("src/MenuPrincipal/res/cursor.png");
        indicador=0;
        p=new Punto(425,35);
        renderMenu=new Punto(300,10);
    }
   

    public void render() throws SlickException {
        fondo.draw();
        botonInventario.draw(renderMenu.getX(),renderMenu.getY());
        botonEquipo.draw(renderMenu.getX(),renderMenu.getY()+70);
        botonFabricacion.draw(renderMenu.getX(),renderMenu.getY()+70*2);
        botonSalir.draw(renderMenu.getX(),renderMenu.getY()+70*3);
        punteroInventario.draw(p.getX(),p.getY());
        
    }

    public void update(GameContainer container) throws SlickException {
        Input entrada=container.getInput();
        if (entrada.isKeyPressed(Input.KEY_DOWN)){
            if(indicador!=3){
                p.setY(p.getY()+70);
                indicador++;
            }else{
                p.setY(p.getY()-70*3);
                indicador=0;
            }
              
        }
        if (entrada.isKeyPressed(Input.KEY_UP)){ 
           if(indicador!=0){
                p.setY(p.getY()-70);
                indicador--;
            }else{
                p.setY(p.getY()+70*3);
                indicador=3;
            }
        }
    }

    public int getID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean accion(Input input){
        boolean seguir=true;
        if(input.isKeyPressed(input.KEY_ENTER)){
            if(indicador==3){
                seguir=false;
            }
        }
       return seguir; 
    }
    
    
}
