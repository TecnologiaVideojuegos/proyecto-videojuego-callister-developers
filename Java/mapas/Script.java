package mapas;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import juego.Juego;

/**
 *
 * @author Carlos
 */
public class Script {
    private int estado;
    private Juego juego;
    private String msg;
    private int acDialogo;
    
    public Script(Juego juego){
        this.estado = 0;
        this.juego = juego;
        this.acDialogo = 0;
    }
    
    public void comprobarEstado(){
        switch(this.estado){
            case 0:
                msg = "Kato: Hola";
                acDialog();
                try {
                    sleep(500);
                } catch (InterruptedException ex) {}
                
                msg = "Lucía: ...";
                acDialog();
                try {
                    sleep(500);
                } catch (InterruptedException ex) {}
                
                msg = "Kato: Eres Lucía, ¿verdad?";
                acDialog();
                try {
                    sleep(500);
                } catch (InterruptedException ex) {}
                
                
                
                acDialogo++;
                break;
        }
    }
    
    private void acDialog(){
        juego.setTextoDialogo(msg);
        juego.activarDialogo(); 
    }
}
