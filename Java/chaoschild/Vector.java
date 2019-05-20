package chaoschild;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Vector implements Externalizable{
    
    private Punto origen, destino;

    public Vector(Punto origen, Punto destino){
        this.origen = origen;
        this.destino = destino;
    }
    
    public Vector(Punto destino){
        this(new Punto(0,0), destino);
    }

    public Vector() {
        }
    
    public float getX(){
        return destino.getX() - origen.getX();
    }
    
    public float getY(){
        return destino.getY() - origen.getY();
    }
    
    public float getModulo(){
        double x = (double) this.getX();
        double y = (double) this.getY();
        return (float) Math.sqrt(x * x + y * y);
    }

    public Punto getOrigen() {
        return origen;
    }

    public void setOrigen(Punto origen) {
        this.origen = origen;
    }

    public Punto getDestino() {
        return destino;
    }

    public void setDestino(Punto destino) {
        this.destino = destino;
    }

    @Override
    public void writeExternal(ObjectOutput oo) throws IOException {
        oo.writeObject(origen);
        oo.writeObject(destino);
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
        origen=new Punto();
        origen.readExternal(oi);
        destino=new Punto();
        destino.readExternal(oi);
    }
    
}
