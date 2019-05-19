package dialogo;

import chaoschild.Punto;
import java.util.ArrayList;
import juego.Juego;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Carlos
 */
public class Dialogo {
    private String texto;
    private Image imagen;
    private Image puntero;
    private Punto pos;
    private boolean activo;
    private Juego juego;
    private int cont;
    private String msg;
    
    public Dialogo(Juego juego){
        try {
            this.cont = 0;
            this.juego = juego;
            this.activo = false;
            this.imagen = new Image("resources/Menus/Dialogos.png");
            this.puntero = new Image("resources/Menus/PunteroMenu.png");
        } catch (SlickException ex) {}
    }
    
    public void setTexto(String texto){
        this.texto = texto;
    }
    
    public void draw(Graphics g){
        float x = juego.getLucia().getPosicion().getX();
        float y = juego.getLucia().getPosicion().getY();
        this.pos = new Punto(x - 320, y + 170);
        
        if(activo){
            this.imagen.draw(pos.getX(), pos.getY());
            this.puntero.draw(pos.getX() + imagen.getWidth() - 40, pos.getY() + imagen.getHeight() - 40);
            escribir(g);
            cont++;
        } else {
            this.msg = "";
        }
    }
    
    private void escribir(Graphics g){
        g.setColor(Color.black);
        
        if(((cont / 10) < this.texto.length()) && (cont % 10 == 0)){
            if((cont / 10) % 62 == 0 && cont / 10 != 0){
                msg += "\n";
            }
            msg += this.texto.charAt(cont / 10);
        }
        g.drawString(msg, pos.getX() + 20, pos.getY() + 20);
        g.setColor(Color.white);
    }
    
    public Punto getPos(){
        return this.pos;
    }
    
    public void setActivo(boolean activo){
        this.activo = activo;
        if(!activo){
            this.cont = 0;
        }
    }
    
    public boolean isActivo(){
        return this.activo;
    }
}
