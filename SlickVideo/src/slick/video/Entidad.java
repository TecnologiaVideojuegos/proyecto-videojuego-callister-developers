/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slick.video;


import org.newdawn.slick.Animation;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author victo
 */
public class Entidad {
    private SpriteSheet sprite;
    private Animation animacion;
    private Animation arriba;
    private Animation abajo;
    private Animation derecha;
    private Animation izq;
    private Punto posicion;
    private Vector velocidad;
    private Rectangle hitbox;
    int[] estadoSprite;
    private int direccion;
    private Rectangle[] hitParedes;
    
    public Entidad(float x, float y, String ruta) throws SlickException{
        hitParedes=new Rectangle[4];
        posicion= new Punto(x, y);
        velocidad=new Vector(new Punto (0, 0));
        sprite=new SpriteSheet(ruta, 34, 52);
        hitbox=new Rectangle(x, y, 34, 52);
        estadoSprite=new int[4];
        animacion=new Animation(true);
        arriba=new Animation();
        abajo=new Animation();
        derecha=new Animation();
        izq=new Animation();
        animacion.addFrame(sprite.getSprite(0,0), 150);
        animaciones();
        genHitPerades();
        
    }
    
    public void update(int delta, Input entrada, boolean[] a) throws SlickException{
        float x = posicion.getX() + velocidad.getX() * ((float) delta / 1000);
        float y = posicion.getY() + velocidad.getY() * ((float) delta / 1000);
        posicion = new Punto(x, y);
        hitbox.setLocation(x, y);
        this.actualizar(entrada, a);
        this.acHitPar();
        
    }
    
    public void draw(){
        //System.out.println("Lucia esta en: " +posicion.getX()+" "+posicion.getY());
        if(velocidad.getX()==0 && velocidad.getY()==0){
            animacion.getImage(0).draw(posicion.getX(), posicion.getY());
            //animacion.getImage(0).draw(10*32, 10*32);
        }else{
        animacion.draw(posicion.getX(), posicion.getY(), 34,52);
        //animacion.getImage(0).draw(10*32, 10*32);
        }
        
        //animacion.getImage(animacion.getFrameCount()-1).draw(posicion.getX(), posicion.getY());
        
    }
    
     
    
     public void actualizar(Input entrada,boolean[]paredes ) throws SlickException{
//        
//        
//        boolean t=true;
//        int[] a={0, 0};
//        if(entrada.isKeyDown(Input.KEY_UP)){
//            if (paredes[0]){
//                 t=false;   
//            }
//            if(t){
//                velocidad=new Vector(new Punto(0, 0));
//            }else{
//                velocidad=new Vector(new Punto(0, -300));
//            }
//            
//            animacion=arriba;
//           
//     
//     }else if(entrada.isKeyDown(Input.KEY_DOWN)){
//         
//        if (paredes[2]){
//            t=false;
//        }
//            if(t){
//                velocidad=new Vector(new Punto(0, 0));
//            }else{
//                velocidad=new Vector(new Punto(0, 300));
//            }
//            
//             animacion=abajo;
//             
//        } else if(entrada.isKeyDown(Input.KEY_RIGHT)){
//
//            if (paredes[1]){
//                t=false;
//            }
//            if(t){
//                velocidad=new Vector(new Punto(0, 0));
//            }else{
//                velocidad=new Vector(new Punto(300, 0));
//            }
//            
//             animacion=derecha;
//           
//        } else if(entrada.isKeyDown(Input.KEY_LEFT)){
//            
//            if (paredes[3]){
//                t=false;   
//            }
//            if(t){
//                velocidad=new Vector(new Punto(0, 0));
//            }else{
//                velocidad=new Vector(new Punto(-300, 0));
//            }
//            
//             animacion=izq;
//             
//        }
//        else {
//            velocidad=new Vector(new Punto(0, 0));
//            
//            }
//
//        //animacion.addFrame(sprite.getSprite(estadoSprite[aux], direccion), 150);
//                    
//                    
        }
     
     
     
     private void animaciones(){
        for (int j=0;j<3;j++){
            abajo.addFrame(sprite.getSprite(j,0), 150);
        }
         for (int j=0;j<3;j++){
            derecha.addFrame(sprite.getSprite(j,1), 150);
        }
          for (int j=0;j<3;j++){
            izq.addFrame(sprite.getSprite(j,2), 150);
        }
           for (int j=0;j<3;j++){
            arriba.addFrame(sprite.getSprite(j,3), 150);
        }
		
     }

    public Punto getPosicion() {
        return posicion;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
     
    

    public void setPosicion(Punto posicion) {
        this.posicion = posicion;
    }
    
    public void genHitPerades(){
        
        hitParedes[0]=new Rectangle(posicion.getX(),posicion.getY()-2, 34, 2);
        hitParedes[1]=new Rectangle(posicion.getX()+34,posicion.getY(), 2, 52);
        hitParedes[2]=new Rectangle(posicion.getX(),posicion.getY()+52, 34, 2);
        hitParedes[3]=new Rectangle(posicion.getX()-2,posicion.getY(), 2, 52);
        
    }
    
    
    public void acHitPar(){
        hitParedes[0].setLocation(posicion.getX(),posicion.getY()-2);
        hitParedes[1].setLocation(posicion.getX()+34,posicion.getY());
        hitParedes[2].setLocation(posicion.getX(),posicion.getY()+52);
        hitParedes[3].setLocation(posicion.getX()-2,posicion.getY());
    }
    
    public Rectangle[] getHitParedes() {
        return hitParedes;
    }
    
    public void setVelocidad(Vector v){
        velocidad=v;
    }
    
    public void setAnimacion(int a){
        System.out.println(a);
        switch(a){
            case 0: animacion=arriba;
            break;        
            case 1: animacion=derecha;
            break;
            case 2: animacion=abajo;
            break;        
            case 3: animacion=izq;
            break;
        }
                
    }
    
    }
    

