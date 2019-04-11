package itemsjuego;

import org.newdawn.slick.Image;

public class EstadosAlterado extends Objeto {
    protected String estado;
    public EstadosAlterado(String estado, Image imagen){
        super(imagen);
        this.estado=estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "EstadosAlterado{" + "estado=" + estado + '}';
    }        
}
