/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemsjuego;

import chaoschild.Punto;
//import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import entidades.EntidadCombate;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author ceals
 */
public abstract class Objeto {
    
    private String nombre;
    private Image imagen;
    private Animation animacion;
    private Sound sonido;
    private boolean gem;

    public Objeto(Image imagen, String n) {
        this.imagen = imagen;
        this.nombre=n;
        gem=false;
    }
    
    public Objeto(Image imagen, String n, String ruta, String sonido) throws SlickException {
        this.imagen = imagen;
        this.nombre=n;
        animacion=new Animation();
        genAnim(ruta);
        animacion.setLooping(false);
        this.sonido=new Sound(sonido);
        gem=false;

    }
    
    
    public boolean draw(int x, int y){
        boolean a=false;
        if(!animacion.isStopped()){
            animacion.draw(x, y);
            if(!sonido.playing()){
                sonido.play((float) 1.01, (float) 0.5);
            }
             
        }else{
            if(!sonido.playing()) a=true;
           
        }
           
       return a;     
    }   

    @Override
    public String toString() {
        return nombre;
    }

    public Image getImagen() {
        return imagen;
    }

    public void render(int x, int y, Graphics g){
        g.drawString(toString(), x, y);
        imagen.draw( x-32, y-7);
    }

    public String getDescripcion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   private void genAnim(String ruta) throws SlickException{
       SpriteSheet s=new SpriteSheet(ruta, 64, 64);
       for(int i=0;i<8;i++){
           animacion.addFrame(s.getSprite(i,0), 100);
       }
   }

    public Animation getAnimacion() {
        return animacion;
    }
   
   public void restart(){
       animacion.restart();
   }
   
    public void usar(EntidadCombate e){
        System.out.println("aaaaaaaaaaaaaaaaa");
    }
    
    public void renderImagen(Punto p, int i){
        imagen.draw(p.getX(), p.getY()+i*33);  
    }

    public boolean isGem() {
        return gem;
    }

    public void setGem(boolean gem) {
        this.gem = gem;
    }
    
    
}
