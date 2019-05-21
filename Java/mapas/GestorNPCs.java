package mapas;

import entidades.Entidad;
import entidades.EntidadCombate;
import entidades.Lucia;
import entidades.NPC;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import juego.Juego;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Carlos
 */
public class GestorNPCs {
    private ArrayList<NPC> npcs;
    private Juego juego;
    private String msg;
    private NPC npcHablando;
    private int ordDialogo;
    private Animation anim;
    private SpriteSheet sprite;
    private boolean inter;
    
    public GestorNPCs(ArrayList<NPC> npcs, Juego juego){
        this.juego = juego;
        this.npcs = npcs;
        this.ordDialogo = 0;
        try {
            this.sprite = new SpriteSheet("resources/NPC/excls_anim.png", 39, 11);
            this.anim = new Animation(sprite, 400);
            this.anim.setLooping(true);
        } catch (SlickException ex) {}
        this.inter = false;
        genAreaAccion();
    }
    
    public GestorNPCs(Juego juego){
        this.juego = juego;
        this.npcs = new ArrayList();
        this.ordDialogo = 0;
        try {
            this.sprite = new SpriteSheet("resources/NPC/excls_anim.png", 11, 39);
            this.anim = new Animation(sprite, 500);
        } catch (SlickException ex) {}
        this.inter = false;
    }
    
    public void drawAnim(NPC npc){
        if(inter){
            this.anim.draw(npc.getPosicion().getX() + 32, npc.getPosicion().getY() - 45);
        }
    }
    
    public void stopAnim(){
        if(!this.anim.isStopped()){
            this.anim.stop();
        }
    }

    public void setNpcHablando(NPC npcHablando) {
        this.npcHablando = npcHablando;
    }

    public NPC getNpcHablando() {
        return npcHablando;
    }
    
    public void add(NPC npc){
        genAreaAccIndv(npc);
        this.npcs.add(npc);
    }
    
    public void accion(NPC npc) {
        switch(npc.getNombre()){
            case "medic":
                switch(ordDialogo){
                    case 0:
                        msg = "Bienvenida al hospital de Gran Pueblo.";
                        acDialog();
                        ordDialogo++;
                        break;
                    case 1:
                        msg = "Como verás, el tamaño de nuestro querido pueblo hace honor a  su nombre";
                        acDialog();
                        ordDialogo++;
                        break;
                    case 2:
                        msg = "El famoso arquitecto Osman quiso realizar su gran obra magna con el diseño de este pueblo.";
                        acDialog();
                        ordDialogo++;
                        break;
                    case 3:
                        msg = "¡Vaya! Ya me he ido por la ramas... Espera un segundo a que osrecupere la salud.";
                        acDialog();
                        try {
                            for(EntidadCombate e : Lucia.getLucia().getEquipo()){
                                e.getMultiplicadores()[0] = e.getEst()[0];
                            }
                        } catch (SlickException ex) {}
                        ordDialogo++;
                        break;
                    case 4:
                        msg = "Listo. ¡¡Mucha suerte en tus aventuras!!";
                        acDialog();
                        ordDialogo = 0;
                        juego.setAcNpc(false);
                        break;
                }
                break;
            case "c2p":
                switch(ordDialogo){
                    case 0:
                        msg = "¿Has visitado ya la enfermería?";
                        acDialog();
                        ordDialogo++;
                        break;
                    case 1:
                        msg = "Está al sureste del pueblo, en una plaza con un pozo en el     centro. Tenemos uno de los mejores médicos de la zona";
                        acDialog();
                        ordDialogo = 0;
                        juego.setAcNpc(false);
                        break;
                }
                break;
            case "c3p":
                switch(ordDialogo){
                    case 0:
                        msg = "No te recomiendo entrar en esa casa antes de visitar la       enfermería.";
                        ordDialogo++;
                        acDialog();
                        break;
                    case 1:
                        msg = "Es un edificio con una bandera roja. También puedes visitar      el ayuntamiento, es un edificio con dos banderas rojas.";
                        acDialog();
                        ordDialogo = 0;
                        juego.setAcNpc(false);
                        break;
                }
                break;
            case "c4p":
                switch(ordDialogo){
                    case 0:
                        ordDialogo++;
                        break;
                    case 1:
                        msg = "¿Has visto a mi gemelo?";
                        acDialog();
                        ordDialogo = 0;
                        juego.setAcNpc(false);
                        break;
                }
                break;
            case "c5p":
                switch(ordDialogo){
                    case 0:
                        ordDialogo++;
                        break;
                    case 1:
                        msg = "Hace mucho que no visito la catedral...";
                        acDialog();
                        ordDialogo = 0;
                        juego.setAcNpc(false);
                        break;
                }
                break;
            case "c6p":
                switch(ordDialogo){
                    case 0:
                        msg = "Últimamente están apareciendo demasiado monstruos en la playa.";
                        acDialog();
                        ordDialogo++;
                        break;
                    case 1:
                        msg = "Una pena... Tenía pensado la próxima semana.";
                        acDialog();
                        ordDialogo++;
                        break;
                    case 2:
                        msg = "Puedes ir a través de la ruta que está a la derecha de la          catedral. Son bastante cuirosos esos monstruos";
                        acDialog();
                        ordDialogo = 0;
                        juego.setAcNpc(false);
                        break;
                }
                break;
            case "cpp":
                switch(ordDialogo){
                    case 0:
                        msg = "A veces me vengo aquí cuendo estoy estresado.";
                        acDialog();
                        ordDialogo++;
                        break;
                    case 1:
                        msg = "¿No te parece que somos muy parecidos los habitantes de Gran        Pueglo?";
                        acDialog();
                        ordDialogo = 0;
                        juego.setAcNpc(false);
                        break;
                }
                break;
            case "ccc":
                switch(ordDialogo){
                    case 0:
                        ordDialogo++;
                        break;
                    case 1:
                        msg = "Si estás buscando el pueblo puedes llegar a él a través de la       ruta que está a la izquierda de la catedral.";
                        acDialog();
                        ordDialogo = 0;
                        juego.setAcNpc(false);
                        break;
                }
                break;
            case "ccca":
                switch(ordDialogo){
                    case 0:
                        msg = "Creo que soy el único que se da cuenta de lo parecidos que         somos...";
                        acDialog();
                        ordDialogo++;
                        break;
                    case 1:
                        msg = "Vengo todos los días a rezar para que esta tortura termine.";
                        acDialog();
                        ordDialogo = 0;
                        juego.setAcNpc(false);
                        break;
                }
                break;
        }
    }
    
    private void acDialog(){
        juego.setTextoDialogo(msg);
        juego.activarDialogo(); 
    }
    
    public void update(int i) throws SlickException{
        for(NPC npc : npcs){
            npc.update(i, this.juego.colisionPared(npc));
        }
    }
    
    public void draw(){
        for(NPC npc : npcs){
            npc.draw();
        }
    }
    
    private void genAreaAccIndv(NPC npc){
        ArrayList<Rectangle> areaAccion = new ArrayList();
            float w = npc.getAnimacion().getImage(0).getWidth();
            float h = npc.getAnimacion().getImage(0).getHeight();
            float x = npc.getPosicion().getX() - w;
            float y = npc.getPosicion().getY() - h;


            for(int i = 0;i < 3;i++){
                areaAccion.add(new Rectangle(x + w * i, y, w, h));
            }
            for(int i = 1;i < 3;i++){
                areaAccion.add(new Rectangle(x, y + h * i, w, h));
            }
            areaAccion.add(new Rectangle(x + w * 2, y + h, w, h));
            areaAccion.add(new Rectangle(x + w, y + h * 2, w, h));
            areaAccion.add(new Rectangle(x + w * 2, y + h * 2, w, h));
            
            npc.setAreaAccion(areaAccion);
    }
    
    private void genAreaAccion(){
        for(NPC npc : npcs){
            genAreaAccIndv(npc);
        }
    }
    
    public void setNPCS(ArrayList<NPC> npcs){
        this.npcs = npcs;
    }

    public ArrayList<NPC> getNpcs() {
        return npcs;
    }

    public void setInter(boolean inter) {
        this.inter = inter;
    }
    
}
