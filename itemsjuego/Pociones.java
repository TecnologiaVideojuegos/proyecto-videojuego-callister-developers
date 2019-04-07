package itemsjuego;

public abstract class Pociones extends Objeto {
    protected String tipo;
    public Pociones(String tipo){
        this.tipo=tipo;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    } 
    
}
