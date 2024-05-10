package xogo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Impostor extends Xogador implements Observer{

    List<Estudante> eliminados;

    public Impostor(String alias) {

        super(alias);
        eliminados=new  ArrayList<>();
    }

    @Override
    public void update(Xogador xogador) {
        if (Math.random() < 0.50) { // 60% probabilidades de matar
            if(xogador instanceof Estudante) {
                //System.out.println("Impostor " + getAlias() + " matou " + xogador.getAlias());
                xogador.setVivo(false);
                eliminados.add((Estudante) xogador);
            }
        }
    }
    public List<Estudante> getEliminados() {
        return eliminados;
    }
}
