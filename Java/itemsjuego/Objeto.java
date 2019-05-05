/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemsjuego;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 *
 * @author ceals
 */
public abstract class Objeto {
    
    private String nombre;
    private Image imagen;

    public Objeto(Image imagen, String n) {
        this.imagen = imagen;
        this.nombre=n;
    }
    
    
    
    public void verImagen(){
        
    }

    @Override
    public String toString() {
        return "Objeto{" + "nombre=" + nombre + '}';
    }

    public Image getImagen() {
        return imagen;
    }

    public void render(int x, int y, Graphics g){
        
        g.drawString(toString(), x, y);
        imagen.draw(x, y);
                
        
        
    }

    public String getDescripcion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
}
