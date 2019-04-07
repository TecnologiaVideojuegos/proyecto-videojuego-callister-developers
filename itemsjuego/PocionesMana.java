package itemsjuego;

public class PocionesMana extends Pociones {
    protected int mana;
    public PocionesMana(int mana){
        super("mana");
        this.mana=mana;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public String toString() {
        return "PocionesMana{" + "mana=" + mana + '}';
    }
        
}
