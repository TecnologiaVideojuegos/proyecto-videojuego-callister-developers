package mapas;

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
    
    public void accion(NPC npc){
        switch(npc.getNombre()){
            case "cPrueba":
                switch(ordDialogo){
                    case 0:
                        msg = "ESTO ES UNA PRUEBA LOCO";
                        acDialog();
                        ordDialogo++;
                        break;
                    case 1:
                        msg = "PUES ESO LO QUE TE ESTABA CONTANDO MIERDAD ASQUEROSO AJJAJSJADFASFJSFNFSDNFDASFASDFAS"
                            + "FASDFSDAFSDAFSFDFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF"
                            + "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF"
                            + "FFF";
                        acDialog();
                        ordDialogo++;
                        break;
                    case 2:
                        msg = "MORE PRUEBAS HERMANO";
                        acDialog();
                        juego.setAcNpc(false);
                        ordDialogo = 0;
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
