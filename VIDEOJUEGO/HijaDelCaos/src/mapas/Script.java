package mapas;

import chaoschild.Punto;
import static java.lang.Thread.sleep;
import juego.Juego;

/**
 *
 * @author Carlos
 */
public class Script {
    private int estado;
    private Juego juego;
    private String msg;
    private boolean activo;
    private int acDialogo;
    
    public Script(Juego juego){
        this.msg = "";
        this.acDialogo = 0;
        this.estado = -1;
        this.juego = juego;
        this.activo = false;
    }
    
    public void setEstado(int estado){
        this.estado = estado;
    }
    
    public void comprobarEstado(){
        if(activo){
            switch(this.estado){
                case 0:
                    this.juego.getLucia().getEquipo().get(1).setAnimacion(0);
                    this.juego.getLucia().getEquipo().get(1).setPosicion(new Punto(this.juego.getLucia().getPosicion().getX() - 64,
                            this.juego.getLucia().getPosicion().getY()));
                    this.juego.getLucia().getEquipo().get(1).draw();
                    
                    switch(this.acDialogo){
                        case 0:
                            msg = "Kato: Hola";
                            acDialog();
                            acDialogo++;
                        break;
                        case 1:
                            msg = "Lucía: ...";
                            acDialog();
                            acDialogo++;
                            break;
                        case 2:
                            msg = "Kato: Eres Lucía, ¿verdad?";
                            acDialog();
                            acDialogo++;
                            break;
                        case 3:
                            msg = "Lucía: S... Si.";
                            acDialog();
                            acDialogo++;
                            break;
                        case 4:
                            msg = "Kato: Pues claro que eres tú, si no , yo no estaría aquí.";
                            acDialog();
                            acDialogo++;
                            break;
                        case 5:
                            msg = "Lucía: ¿Cómo?";
                            acDialog();
                            acDialogo++;
                            break;
                        case 6:
                            msg = "Kato: Veras, ¿ves ese libro que tienes en la mano? Me mantienecon vida, se podría decir que soy parte de ese libro,        una manifestación de su voluntad, y ese libro a su vez es parte de tuser. Quizás es un poco dramático pero ya me entiendes.";
                            acDialog();  
                            acDialogo++;
                            break;
                        case 7:
                            msg = "Lucía: No, no te entiendo.";
                            acDialog();
                            acDialogo++;
                            break;
                        case 8:
                            msg = "Kato: Bueno, ya lo irás viendo con el tiempo, de momento      salgamos deaquí, este sitio me da repelús.";
                            acDialog();
                            this.estado = -1;
                            this.activo = false;
                            this.acDialogo = 0;
                            break; 
                    }       
                    break;
            }
        }
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    private void acDialog(){
        juego.setTextoDialogo(msg);
        juego.activarDialogo(); 
    }
}
