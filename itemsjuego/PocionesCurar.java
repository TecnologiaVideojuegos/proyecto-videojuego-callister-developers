package itemsjuego;

public class PocionesCurar extends Pociones {
    protected int salud;
    public PocionesCurar(int salud){
        super("salud");
        this.salud=salud;
    }
    
    public int getSalud() {
        return salud;
    }
    
    public void setSalud(int salud) {
        this.salud = salud;
    } 

    @Override
    public String toString() {
        return "PocionesCurar{" + "salud=" + salud + '}';
    }
    
    
}
